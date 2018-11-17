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
@Named(value = "adminBean")
@ViewScoped
public class AdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CervejaController cervejaController;
	private PratoController pratoController;
	private UsuarioController usuarioController;
	private AvaliacaoController avaliacaoController;
	private HarmonizacaoController harmonizacaoController;

	private List<SelectItem> listaCerv = new ArrayList<SelectItem>();
	private List<SelectItem> listaPrato = new ArrayList<SelectItem>();
	private List<SelectItem> listaUser = new ArrayList<SelectItem>();

	private List<Avaliacao> listaAval = new ArrayList<Avaliacao>();
	private Avaliacao avalSelec;

	private String nm_cerv;
	private String estilo_cerv;
	private double teor;

	private String nm_prato;

	private Cerveja cervSelec;
	private int idCervSelec;
	private String nm_cervSelec;
	private String estilo_cervSelec;
	private double teorSelec;

	private Prato pratoSelec;
	private int idPratoSelec;
	private String nm_pratoSelec;

	private Usuario userSelec;
	private int idUserSelec;
	private String username;
	private String email;
	private String info;

	public AdminBean() {
		cervejaController = new CervejaController();
		pratoController = new PratoController();
		usuarioController = new UsuarioController();
		avaliacaoController = new AvaliacaoController();
		harmonizacaoController = new HarmonizacaoController();
	}

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
		try {
			ArrayList<SelectItem> listaUser = new ArrayList<SelectItem>();
			ArrayList<Usuario> users = new ArrayList<Usuario>();
			users = (ArrayList<Usuario>) usuarioController.listarTodos();
			for (Usuario u : users) {
				SelectItem user = new SelectItem(u.getId_user(), u.getUsername());
				listaUser.add(user);
			}
			setListaUser(listaUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cadastrarCerv() {
		try {
			Cerveja cerv = new Cerveja(nm_cerv, estilo_cerv, teor);
			if (cervejaController.incluir(cerv)) {
				Util.mensagemInfo("Cerveja adicionada com sucesso!");
				setNm_cerv("");
				setEstilo_cerv("");
				setTeor(0);
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível adicionar essa cerveja. Tente novamente mais tarde.");
			}
		} catch (Exception e) {
			Util.mensagemErro("Não foi possível adicionar essa cerveja. Tente novamente mais tarde.");
			e.printStackTrace();
		}

	}

	public void cadastrarPrato() {
		try {
			Prato prato = new Prato(nm_prato);
			if (pratoController.incluir(prato)) {
				Util.mensagemInfo("Prato adicionado com sucesso!");
				setNm_prato("");
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível adicionar esse prato. Tente novamente mais tarde.");
			}
		} catch (Exception e) {
			Util.mensagemErro("Não foi possível adicionar esse prato. Tente novamente mais tarde.");
			e.printStackTrace();
		}
	}

	public void selecionarCerv() {
		try {
			setCervSelec(cervejaController.selecionarCerv(getIdCervSelec()));
			setNm_cervSelec(getCervSelec().getNm_cerv());
			setEstilo_cervSelec(getCervSelec().getNm_estilo());
			setTeorSelec(getCervSelec().getTeor_alcool());

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer a seleção. Tente novamente mais tarde!");
			e.printStackTrace();
		}
	}

	public void selecionarPrato() {
		try {
			setPratoSelec(pratoController.selecionarPrato(getIdPratoSelec()));
			setNm_pratoSelec(getPratoSelec().getNm_prato());

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer a seleção. Tente novamente mais tarde!");
			e.printStackTrace();
		}
	}

	public void selecionarUser() {
		try {
			setUserSelec(usuarioController.selecionarUser(getIdUserSelec()));
			setUsername(getUserSelec().getUsername());
			setEmail(getUserSelec().getEmail());
			setInfo(getUserSelec().getInfo());
			listarAval();

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer a seleção. Tente novamente mais tarde!");
			e.printStackTrace();
		}
	}

	public void editarCerv() {
		try {
			Cerveja c = new Cerveja(getIdCervSelec(), getNm_cervSelec(), getEstilo_cervSelec(), getTeorSelec());
			if (cervejaController.editar(c)) {
				Util.mensagemInfo("Cerveja editada com sucesso!");
				setNm_cervSelec("");
				setEstilo_cervSelec("");
				setTeorSelec(0);
				gerarMenus();

			} else {
				Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
		}

	}

	public void editarPrato() {
		try {
			Prato p = new Prato(getIdPratoSelec(), getNm_pratoSelec());
			if (pratoController.editar(p)) {
				Util.mensagemInfo("Prato editado com sucesso!");
				setNm_pratoSelec("");
				gerarMenus();

			} else {
				Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
		}

	}

	public void editarUser() {
		try {
			Usuario u = new Usuario(getIdUserSelec(), getUsername(), getEmail(), getUserSelec().getSenha(), getInfo());
			if (usuarioController.editar(u)) {
				Util.mensagemInfo("Usuario editado com sucesso!");
				setUsername("");
				setEmail("");
				setInfo("");
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Util.mensagemErro("Não foi possível fazer a edição. Tente novamente mais tarde!");
		}

	}

	public void removerCerv() {
		try {
			if (cervejaController.deletar(getCervSelec())) {
				Util.mensagemInfo("Cerveja deletada");
				setNm_cervSelec("");
				setEstilo_cervSelec("");
				setTeorSelec(0);
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível fazer essa remoção");
			}

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer essa remoção");
			e.printStackTrace();
		}

	}

	public void removerPrato() {
		try {
			if (pratoController.deletar(getPratoSelec())) {
				Util.mensagemInfo("Prato deletado");
				setNm_pratoSelec("");
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível fazer essa remoção");
			}

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer essa remoção");
			e.printStackTrace();
		}

	}

	public void removerUser() {
		try {
			if (usuarioController.deletar(getUserSelec())) {
				Util.mensagemInfo("Usuario passou a ser um usuário anônimo!");
				setUsername("");
				setEmail("");
				setInfo("");
				gerarMenus();
			} else {
				Util.mensagemErro("Não foi possível fazer essa remoção");
			}

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível fazer essa remoção");
			e.printStackTrace();
		}

	}

	public void listarAval() {
		try {
			ArrayList<Avaliacao> listaProv = new ArrayList<Avaliacao>();

			for (Avaliacao a : avaliacaoController.listarAvalporUser(getIdUserSelec())) {
				Harmonizacao h = harmonizacaoController.selecionarHarmo(a.getHarmonizacao().getId_harmo());
				Prato p = pratoController.selecionarPrato(h.getPrato().getId_prato());
				Cerveja c = cervejaController.selecionarCerv(h.getCerveja().getId_cerv());
				Usuario u = usuarioController.selecionarUser(getIdUserSelec());
				h.setCerveja(c);
				h.setPrato(p);
				Avaliacao aval = new Avaliacao(a.getId_aval(), h, u, a.getNota(), a.getComentario());
				listaProv.add(aval);
			}
			setListaAval(listaProv);

		} catch (Exception e) {
			Util.mensagemErro("Não foi possível carregar as avaliações desse usuário");
			e.printStackTrace();
		}
	}

	public void removerAval() {
		try {
			if (avaliacaoController.deletarAvaliacao(getAvalSelec())) {
				listarAval();
				Util.mensagemInfo("Avaliação apagada!");
			} else {
				Util.mensagemErro("Não foi possível apagar essa avaliação...");
			}
		} catch (Exception e) {
			Util.mensagemErro("Não foi possível apagar essa avaliação...");
			e.printStackTrace();

		}

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
	 * @return the teor
	 */
	public double getTeor() {
		return teor;
	}

	/**
	 * @param teor the teor to set
	 */
	public void setTeor(double teor) {
		this.teor = teor;
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
	 * @return the listaUser
	 */
	public List<SelectItem> getListaUser() {
		return listaUser;
	}

	/**
	 * @param listaUser the listaUser to set
	 */
	public void setListaUser(List<SelectItem> listaUser) {
		this.listaUser = listaUser;
	}

	/**
	 * @return the cervSelec
	 */
	public Cerveja getCervSelec() {
		return cervSelec;
	}

	/**
	 * @param cervSelec the cervSelec to set
	 */
	public void setCervSelec(Cerveja cervSelec) {
		this.cervSelec = cervSelec;
	}

	/**
	 * @return the pratoSelec
	 */
	public Prato getPratoSelec() {
		return pratoSelec;
	}

	/**
	 * @param pratoSelec the pratoSelec to set
	 */
	public void setPratoSelec(Prato pratoSelec) {
		this.pratoSelec = pratoSelec;
	}

	/**
	 * @return the idCervSelec
	 */
	public int getIdCervSelec() {
		return idCervSelec;
	}

	/**
	 * @param idCervSelec the idCervSelec to set
	 */
	public void setIdCervSelec(int idCervSelec) {
		this.idCervSelec = idCervSelec;
	}

	/**
	 * @return the nm_cervSelec
	 */
	public String getNm_cervSelec() {
		return nm_cervSelec;
	}

	/**
	 * @param nm_cervSelec the nm_cervSelec to set
	 */
	public void setNm_cervSelec(String nm_cervSelec) {
		this.nm_cervSelec = nm_cervSelec;
	}

	/**
	 * @return the estilo_cervSelec
	 */
	public String getEstilo_cervSelec() {
		return estilo_cervSelec;
	}

	/**
	 * @param estilo_cervSelec the estilo_cervSelec to set
	 */
	public void setEstilo_cervSelec(String estilo_cervSelec) {
		this.estilo_cervSelec = estilo_cervSelec;
	}

	/**
	 * @return the teorSelec
	 */
	public double getTeorSelec() {
		return teorSelec;
	}

	/**
	 * @param teorSelec the teorSelec to set
	 */
	public void setTeorSelec(double teorSelec) {
		this.teorSelec = teorSelec;
	}

	/**
	 * @return the idPratoSelec
	 */
	public int getIdPratoSelec() {
		return idPratoSelec;
	}

	/**
	 * @param idPratoSelec the idPratoSelec to set
	 */
	public void setIdPratoSelec(int idPratoSelec) {
		this.idPratoSelec = idPratoSelec;
	}

	/**
	 * @return the nm_pratoSelec
	 */
	public String getNm_pratoSelec() {
		return nm_pratoSelec;
	}

	/**
	 * @param nm_pratoSelec the nm_pratoSelec to set
	 */
	public void setNm_pratoSelec(String nm_pratoSelec) {
		this.nm_pratoSelec = nm_pratoSelec;
	}

	/**
	 * @return the userSelec
	 */
	public Usuario getUserSelec() {
		return userSelec;
	}

	/**
	 * @param userSelec the userSelec to set
	 */
	public void setUserSelec(Usuario userSelec) {
		this.userSelec = userSelec;
	}

	/**
	 * @return the idUserSelec
	 */
	public int getIdUserSelec() {
		return idUserSelec;
	}

	/**
	 * @param idUserSelec the idUserSelec to set
	 */
	public void setIdUserSelec(int idUserSelec) {
		this.idUserSelec = idUserSelec;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
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
	 * @return the avalSelec
	 */
	public Avaliacao getAvalSelec() {
		return avalSelec;
	}

	/**
	 * @param avalSelec the avalSelec to set
	 */
	public void setAvalSelec(Avaliacao avalSelec) {
		this.avalSelec = avalSelec;
	}

}
