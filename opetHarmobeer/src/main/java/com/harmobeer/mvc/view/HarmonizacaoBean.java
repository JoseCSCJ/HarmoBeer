package com.harmobeer.mvc.view;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.harmobeer.mvc.controller.AvaliacaoController;
import com.harmobeer.mvc.controller.CervejaController;
import com.harmobeer.mvc.controller.HarmonizacaoController;
import com.harmobeer.mvc.controller.PratoController;
import com.harmobeer.mvc.controller.UsuarioController;
import com.harmobeer.util.Util;
import com.harmobeer.vo.Avaliacao;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;
import com.harmobeer.vo.Usuario;

/**
 * 
 */

/**
 * @author Usuário
 *
 */
@Named(value = "harmonizacaoBean")
@ViewScoped
public class HarmonizacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private HarmonizacaoController harmonizacaoController;
	private PratoController pratoController;
	private CervejaController cervejaController;
	private AvaliacaoController avaliacaoController;
	private UsuarioController usuarioController;

	private List<SelectItem> listaCerv = new ArrayList<SelectItem>();
	private List<SelectItem> listaPrato = new ArrayList<SelectItem>();

	private List<Avaliacao> listaAval = new ArrayList<Avaliacao>();

	private Cerveja cervejaSelecionada;
	private int idCervejaSelecionada;
	private String nm_cerv;
	private String estilo_cerv;
	private double teor_alcoolico;

	private Prato pratoSelecionado;
	private int idPratoSelecionado;
	private String nm_prato;

	private Harmonizacao harmonizacaoSelecionada;
	private double media;

	private Avaliacao avaliacaoFeita;
	private int nota;
	private String comentario;

	private Usuario usuarioLogado;

	private List<Harmonizacao> rankingPrato = new ArrayList<Harmonizacao>();
	private List<Harmonizacao> rankingCerveja = new ArrayList<Harmonizacao>();
	private List<Harmonizacao> rankingPratoTotal = new ArrayList<Harmonizacao>();
	private List<Harmonizacao> rankingCervejaTotal = new ArrayList<Harmonizacao>();

	private boolean renderizarAvisoPrato;
	private boolean renderizarAvisoCerveja;

	/**
	 * Construtor que inicializa os controllers utilizados no bean
	 */

	public HarmonizacaoBean() {

		cervejaController = new CervejaController();

		pratoController = new PratoController();

		harmonizacaoController = new HarmonizacaoController();

		avaliacaoController = new AvaliacaoController();

		usuarioController = new UsuarioController();

	}

	/**
	 * Metodo para gerar a lista de cervejas e de pratos no menu de seleção
	 * 
	 * 
	 */
	@PostConstruct
	public void gerarMenus() {

		try {
			ArrayList<SelectItem> listaCerv = new ArrayList<SelectItem>();
			ArrayList<Cerveja> cervejas = new ArrayList<Cerveja>();
			cervejas = (ArrayList<Cerveja>) cervejaController.listarTodos();
			for (Cerveja c : cervejas) {
				SelectItem cerv = new SelectItem(c.getId_cerv(), c.getNm_cerv());
				listaCerv.add(cerv);
			}
			setListaCerv(listaCerv);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ArrayList<SelectItem> listaPrato = new ArrayList<SelectItem>();
			ArrayList<Prato> pratos = new ArrayList<Prato>();
			pratos = (ArrayList<Prato>) pratoController.listarTodos();
			for (Prato p : pratos) {
				SelectItem prato = new SelectItem(p.getId_prato(), p.getNm_prato());
				listaPrato.add(prato);

			}
			setListaPrato(listaPrato);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza os parametros de página de acordo com a seleção de cerveja no menu.
	 * Se houver prato selecionado, atualiza as informações da harmonização.
	 * 
	 */
	public void selecionarPrato() {
		try {
			setPratoSelecionado(pratoController.selecionarPrato(idPratoSelecionado));
			setNm_prato(pratoSelecionado.getNm_prato());
			if (cervejaSelecionada != null) {
				harmonizacaoController.incluirHarmonizacao(cervejaSelecionada, pratoSelecionado);
				int id = harmonizacaoController.selecionaridHarmonizacao(cervejaSelecionada, pratoSelecionado);
				setHarmonizacaoSelecionada(harmonizacaoController.selecionarHarmo(id));
				harmonizacaoController.calcularMedia(harmonizacaoSelecionada);
				setMedia(harmonizacaoSelecionada.getMedia());
				retornarAval();
				retornarRankingCerveja();
			}
			retornarRankingPrato();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza os parametros de página de acordo com a seleção de cerveja no menu
	 * Se houver prato selecionado, atualiza as informações da harmonização.
	 */
	public void selecionarCerv() {
		try {
			setCervejaSelecionada(cervejaController.selecionarCerv(idCervejaSelecionada));
			setNm_cerv(cervejaSelecionada.getNm_cerv());
			setEstilo_cerv(cervejaSelecionada.getNm_estilo());
			setTeor_alcoolico(cervejaSelecionada.getTeor_alcool());
			if (pratoSelecionado != null) {
				harmonizacaoController.incluirHarmonizacao(cervejaSelecionada, pratoSelecionado);
				int id = harmonizacaoController.selecionaridHarmonizacao(cervejaSelecionada, pratoSelecionado);
				setHarmonizacaoSelecionada(harmonizacaoController.selecionarHarmo(id));
				harmonizacaoController.calcularMedia(harmonizacaoSelecionada);
				setMedia(harmonizacaoSelecionada.getMedia());
				retornarAval();
				retornarRankingPrato();
			}
			retornarRankingCerveja();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gera o ranking de 10 melhores harmonizações para o prato selecionado.
	 */
	public void retornarRankingPrato() {
		try {
			setRenderizarAvisoPrato(false);
			setRankingPrato(null);
			setRankingPratoTotal(null);

			int tamanhoRanking = 0;
			ArrayList<Harmonizacao> rankingTodos = new ArrayList<Harmonizacao>();
			ArrayList<Harmonizacao> ranking10 = new ArrayList<Harmonizacao>();

			rankingTodos = harmonizacaoController.gerarRanking(pratoController.selecionarPrato(idPratoSelecionado));
			setRankingPratoTotal(rankingTodos);
			if (rankingTodos.size() == 0) {
				setRenderizarAvisoPrato(true);
			}
			if (rankingTodos.size() < 10 && rankingTodos.size() > 0) {
				tamanhoRanking = rankingTodos.size();
			}
			if (rankingTodos.size() >= 10) {
				tamanhoRanking = 10;
			}

			for (int i = 0; i < tamanhoRanking; i++) {

				ranking10.add(rankingTodos.get(i));
			}
			setRankingPrato(ranking10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gera o ranking total e de 10 melhores harmonizações para a cerveja
	 * selecionada.
	 */
	public void retornarRankingCerveja() {
		try {
			setRenderizarAvisoCerveja(false);
			setRankingCerveja(null);
			setRankingCervejaTotal(null);

			int tamanhoRanking = 0;
			ArrayList<Harmonizacao> rankingTodos = new ArrayList<Harmonizacao>();
			ArrayList<Harmonizacao> ranking10 = new ArrayList<Harmonizacao>();

			rankingTodos = harmonizacaoController
					.gerarRanking(cervejaController.selecionarCerv(idCervejaSelecionada));
			setRankingCervejaTotal(rankingTodos);

			if (rankingTodos.size() == 0) {
				setRenderizarAvisoCerveja(true);

			}
			if (rankingTodos.size() < 10 && rankingTodos.size() > 0) {
				tamanhoRanking = rankingTodos.size();
			}
			if (rankingTodos.size() >= 10) {
				tamanhoRanking = 10;
			}

			for (int i = 0; i < tamanhoRanking; i++) {
				ranking10.add(rankingTodos.get(i));
			}
			setRankingCerveja(ranking10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cadastra a avaliação de um usuário logado.
	 */
	public void cadastrarAval() {
		try {			
			usuarioLogado = (Usuario) Util.getSessionParameter("userLog");
			Avaliacao aval = new Avaliacao (getHarmonizacaoSelecionada(), getUsuarioLogado(),getNota(), getComentario());
			avaliacaoController.incluirAvaliacao(aval);
			harmonizacaoController.calcularMedia(harmonizacaoSelecionada);
			setMedia(harmonizacaoSelecionada.getMedia());
			Util.mensagemInfo("Avaliação submetida com sucesso!");
			setNota(0);
			setComentario(null);
			retornarAval();
			retornarRankingPrato();
			retornarRankingCerveja();

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível efetuar a avaliação! Verifique se todos os campos foram preenchidos.");
			e.printStackTrace();
		}

	}

	/**
	 * Retorna as avaliações de uma harmonizacao após o usuário selecionar a cerveja
	 * e o prato.
	 */
	public void retornarAval() {
		try {
			List<Avaliacao> aval = new ArrayList<Avaliacao>();
			List<Avaliacao> listaCompleta = new ArrayList<Avaliacao>();
			int idHarmo = harmonizacaoController.selecionaridHarmonizacao(cervejaSelecionada, pratoSelecionado);
			Harmonizacao h =harmonizacaoController.selecionarHarmo(idHarmo);
			aval = avaliacaoController.listarAvalporHarmo(idHarmo);
			for (Avaliacao a : aval) {
				Usuario u = usuarioController.selecionarUser(a.getUser().getId_user());
				Avaliacao avaliacao = new Avaliacao(a.getId_aval(), h, u , a.getNota(), a.getComentario());
				listaCompleta.add(avaliacao);
			}
			setListaAval(listaCompleta);

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível verificar a lista de avaliações! Tente novamente mais tarde");
			e.printStackTrace();
		}
	}

	/**
	 * @return the listaCerv
	 */
	public List<SelectItem> getListaCerv() {
		return listaCerv;
	}

	/**
	 * @param listaCerv the listaCerv to set
	 */
	public void setListaCerv(List<SelectItem> listaCerv) {
		this.listaCerv = listaCerv;
	}

	/**
	 * @return the listaPrato
	 */
	public List<SelectItem> getListaPrato() {
		return listaPrato;
	}

	/**
	 * @param listaPrato the listaPrato to set
	 */
	public void setListaPrato(List<SelectItem> listaPrato) {
		this.listaPrato = listaPrato;
	}

	/**
	 * @return the listaAval
	 */
	public List<Avaliacao> getListaAval() {
		return listaAval;
	}

	/**
	 * @param listaAval the listaAval to set
	 */
	public void setListaAval(List<Avaliacao> listaAval) {
		this.listaAval = listaAval;
	}

	/**
	 * @return the cervejaSelecionada
	 */
	public Cerveja getCervejaSelecionada() {
		return cervejaSelecionada;
	}

	/**
	 * @param cervejaSelecionada the cervejaSelecionada to set
	 */
	public void setCervejaSelecionada(Cerveja cervejaSelecionada) {
		this.cervejaSelecionada = cervejaSelecionada;
	}

	/**
	 * @return the idCervejaSelecionada
	 */
	public int getIdCervejaSelecionada() {
		return idCervejaSelecionada;
	}

	/**
	 * @param idCervejaSelecionada the idCervejaSelecionada to set
	 */
	public void setIdCervejaSelecionada(int idCervejaSelecionada) {
		this.idCervejaSelecionada = idCervejaSelecionada;
	}

	/**
	 * @return the nm_cerv
	 */
	public String getNm_cerv() {
		return nm_cerv;
	}

	/**
	 * @param nm_cerv the nm_cerv to set
	 */
	public void setNm_cerv(String nm_cerv) {
		this.nm_cerv = nm_cerv;
	}

	/**
	 * @return the estilo_cerv
	 */
	public String getEstilo_cerv() {
		return estilo_cerv;
	}

	/**
	 * @param estilo_cerv the estilo_cerv to set
	 */
	public void setEstilo_cerv(String estilo_cerv) {
		this.estilo_cerv = estilo_cerv;
	}

	/**
	 * @return the teor_alcoolico
	 */
	public double getTeor_alcoolico() {
		return teor_alcoolico;
	}

	/**
	 * @param teor_alcoolico the teor_alcoolico to set
	 */
	public void setTeor_alcoolico(double teor_alcoolico) {
		this.teor_alcoolico = teor_alcoolico;
	}

	/**
	 * @return the pratoSelecionado
	 */
	public Prato getPratoSelecionado() {
		return pratoSelecionado;
	}

	/**
	 * @param pratoSelecionado the pratoSelecionado to set
	 */
	public void setPratoSelecionado(Prato pratoSelecionado) {
		this.pratoSelecionado = pratoSelecionado;
	}

	/**
	 * @return the idPratoSelecionado
	 */
	public int getIdPratoSelecionado() {
		return idPratoSelecionado;
	}

	/**
	 * @param idPratoSelecionado the idPratoSelecionado to set
	 */
	public void setIdPratoSelecionado(int idPratoSelecionado) {
		this.idPratoSelecionado = idPratoSelecionado;
	}

	/**
	 * @return the nm_prato
	 */
	public String getNm_prato() {
		return nm_prato;
	}

	/**
	 * @param nm_prato the nm_prato to set
	 */
	public void setNm_prato(String nm_prato) {
		this.nm_prato = nm_prato;
	}

	/**
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * @return the harmonizacaoSelecionada
	 */
	public Harmonizacao getHarmonizacaoSelecionada() {
		return harmonizacaoSelecionada;
	}

	/**
	 * @param harmonizacaoSelecionada the harmonizacaoSelecionada to set
	 */
	public void setHarmonizacaoSelecionada(Harmonizacao harmonizacaoSelecionada) {
		this.harmonizacaoSelecionada = harmonizacaoSelecionada;
	}

	/**
	 * @return the rankingPrato
	 */
	public List<Harmonizacao> getRankingPrato() {
		return rankingPrato;
	}

	/**
	 * @param rankingPrato the rankingPrato to set
	 */
	public void setRankingPrato(List<Harmonizacao> rankingPrato) {
		this.rankingPrato = rankingPrato;
	}

	/**
	 * @return the rankingCerveja
	 */
	public List<Harmonizacao> getRankingCerveja() {
		return rankingCerveja;
	}

	/**
	 * @param rankingCerveja the rankingCerveja to set
	 */
	public void setRankingCerveja(List<Harmonizacao> rankingCerveja) {
		this.rankingCerveja = rankingCerveja;
	}

	/**
	 * @return the renderizarAvisoCerveja
	 */
	public boolean isRenderizarAvisoCerveja() {
		return renderizarAvisoCerveja;
	}

	/**
	 * @param renderizarAvisoCerveja the renderizarAvisoCerveja to set
	 */
	public void setRenderizarAvisoCerveja(boolean renderizarAvisoCerveja) {
		this.renderizarAvisoCerveja = renderizarAvisoCerveja;
	}

	/**
	 * @return the renderizarAvisoPrato
	 */
	public boolean isRenderizarAvisoPrato() {
		return renderizarAvisoPrato;
	}

	/**
	 * @param renderizarAvisoPrato the renderizarAvisoPrato to set
	 */
	public void setRenderizarAvisoPrato(boolean renderizarAvisoPrato) {
		this.renderizarAvisoPrato = renderizarAvisoPrato;
	}

	/**
	 * @return the media
	 */
	public double getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(double media) {
		this.media = media;
	}

	/**
	 * @return the avaliacaoFeita
	 */
	public Avaliacao getAvaliacaoFeita() {
		return avaliacaoFeita;
	}

	/**
	 * @param avaliacaoFeita the avaliacaoFeita to set
	 */
	public void setAvaliacaoFeita(Avaliacao avaliacaoFeita) {
		this.avaliacaoFeita = avaliacaoFeita;
	}

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the rankingPratoTotal
	 */
	public List<Harmonizacao> getRankingPratoTotal() {
		return rankingPratoTotal;
	}

	/**
	 * @param rankingPratoTotal the rankingPratoTotal to set
	 */
	public void setRankingPratoTotal(List<Harmonizacao> rankingPratoTotal) {
		this.rankingPratoTotal = rankingPratoTotal;
	}

	/**
	 * @return the rankingCervejaTotal
	 */
	public List<Harmonizacao> getRankingCervejaTotal() {
		return rankingCervejaTotal;
	}

	/**
	 * @param rankingCervejaTotal the rankingCervejaTotal to set
	 */
	public void setRankingCervejaTotal(List<Harmonizacao> rankingCervejaTotal) {
		this.rankingCervejaTotal = rankingCervejaTotal;
	}

}
