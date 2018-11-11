import java.io.Serializable;
import java.util.ArrayList;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.harmobeer.mvc.controller.AvaliacaoController;
import com.harmobeer.mvc.controller.CervejaController;
import com.harmobeer.mvc.controller.HarmonizacaoController;
import com.harmobeer.mvc.controller.PratoController;
import com.harmobeer.mvc.controller.UsuarioController;
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
@Named(value = "painelInicialBean")
@RequestScoped

public class PainelInicialBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private AvaliacaoController avaliacaoController;
	private HarmonizacaoController harmonizacaoController;
	private UsuarioController usuarioController;
	private CervejaController cervejaController;
	private PratoController pratoController;	
	
	private Avaliacao avaliacaoSelecionada;	
	
	private int idAvalSelec;
	private String nmCervAvalSelec;
	private String nmPratoAvalSelec;
	private String usernameAvalSelec;
	private int notaAvalSelec;
	private String comentarioAvalSelec;
	
	private Harmonizacao harmonizacaoSelecionada;
	private ArrayList<Harmonizacao> rankingGeral = new ArrayList<Harmonizacao>();
	
	
	public PainelInicialBean() {		

		usuarioController = new UsuarioController();
		cervejaController = new CervejaController();
		pratoController = new PratoController();
		harmonizacaoController = new HarmonizacaoController();
		avaliacaoController = new AvaliacaoController();
		ultAvaliacao();
		retornarRankingGeral();
		
		
	}
	
	/**
	 * Método que busca a última avaliação feita no sistema para aparecer em tela. 
	 * 
	 */	
	
	public void ultAvaliacao() {
		try {
			int id = avaliacaoController.selecionarUltAval();
			Avaliacao aval = avaliacaoController.selecionarAval(id);
			
			Usuario u = usuarioController.selecionarUser(aval.getId_user());
			Harmonizacao h = harmonizacaoController.selecionarHarmo(aval.getId_harmo());
			Cerveja c = cervejaController.selecionarCerveja(h.getId_cerv());
			Prato p = pratoController.selecionarPrato(h.getId_prato());
			setNmCervAvalSelec(c.getNm_cerv());
			setNmPratoAvalSelec(p.getNm_prato());
			setUsernameAvalSelec(u.getUsername());
			setNotaAvalSelec(aval.getNota());
			setComentarioAvalSelec(aval.getComentario());			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Método que gera o ranking geral das harmonizações, sem definir um prato
	 * ou cerveja.
	 * 
	 */
	
	public void retornarRankingGeral() {
		try {

			ArrayList<Harmonizacao> rankingPrev = new ArrayList<Harmonizacao>();
			rankingPrev = harmonizacaoController.listarTodos();
			
			for (int i = 0; i < 10; i++) {
				rankingGeral.add(rankingPrev.get(i));
			}
			setRankingGeral(rankingGeral);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the pratoController
	 */
	public PratoController getPratoController() {
		return pratoController;
	}
	/**
	 * @param pratoController the pratoController to set
	 */
	public void setPratoController(PratoController pratoController) {
		this.pratoController = pratoController;
	}
	/**
	 * @return the avaliacaoSelecionada
	 */
	public Avaliacao getAvaliacaoSelecionada() {
		return avaliacaoSelecionada;
	}
	/**
	 * @param avaliacaoSelecionada the avaliacaoSelecionada to set
	 */
	public void setAvaliacaoSelecionada(Avaliacao avaliacaoSelecionada) {
		this.avaliacaoSelecionada = avaliacaoSelecionada;
	}
	/**
	 * @return the idAvalSelec
	 */
	public int getIdAvalSelec() {
		return idAvalSelec;
	}
	/**
	 * @param idAvalSelec the idAvalSelec to set
	 */
	public void setIdAvalSelec(int idAvalSelec) {
		this.idAvalSelec = idAvalSelec;
	}
	/**
	 * @return the nmCervAvalSelec
	 */
	public String getNmCervAvalSelec() {
		return nmCervAvalSelec;
	}
	/**
	 * @param nmCervAvalSelec the nmCervAvalSelec to set
	 */
	public void setNmCervAvalSelec(String nmCervAvalSelec) {
		this.nmCervAvalSelec = nmCervAvalSelec;
	}
	/**
	 * @return the nmPratoAvalSelec
	 */
	public String getNmPratoAvalSelec() {
		return nmPratoAvalSelec;
	}
	/**
	 * @param nmPratoAvalSelec the nmPratoAvalSelec to set
	 */
	public void setNmPratoAvalSelec(String nmPratoAvalSelec) {
		this.nmPratoAvalSelec = nmPratoAvalSelec;
	}
	/**
	 * @return the usernameAvalSelec
	 */
	public String getUsernameAvalSelec() {
		return usernameAvalSelec;
	}
	/**
	 * @param usernameAvalSelec the usernameAvalSelec to set
	 */
	public void setUsernameAvalSelec(String usernameAvalSelec) {
		this.usernameAvalSelec = usernameAvalSelec;
	}
	/**
	 * @return the notaAvalSelec
	 */
	public int getNotaAvalSelec() {
		return notaAvalSelec;
	}
	/**
	 * @param notaAvalSelec the notaAvalSelec to set
	 */
	public void setNotaAvalSelec(int notaAvalSelec) {
		this.notaAvalSelec = notaAvalSelec;
	}
	/**
	 * @return the comentarioAvalSelec
	 */
	public String getComentarioAvalSelec() {
		return comentarioAvalSelec;
	}
	/**
	 * @param comentarioAvalSelec the comentarioAvalSelec to set
	 */
	public void setComentarioAvalSelec(String comentarioAvalSelec) {
		this.comentarioAvalSelec = comentarioAvalSelec;
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
	 * @return the rankingGeral
	 */
	public ArrayList<Harmonizacao> getRankingGeral() {
		return rankingGeral;
	}
	/**
	 * @param rankingGeral the rankingGeral to set
	 */
	public void setRankingGeral(ArrayList<Harmonizacao> rankingGeral) {
		this.rankingGeral = rankingGeral;
	}
	
	
	

}
