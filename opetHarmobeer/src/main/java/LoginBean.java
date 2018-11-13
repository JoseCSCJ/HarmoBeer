import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
 * @author Usu�rio
 *
 */

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;

	private CervejaController cervejaController;
	private PratoController pratoController;
	private UsuarioController usuarioController;
	private AvaliacaoController avaliacaoController;
	private HarmonizacaoController harmonizacaoController;

	private int id_user;
	private String username;
	private String senha;
	private String senhaant;
	private String info;
	private String email;
	private boolean privilegio;

	private boolean renderizarUserLog;	

	private List<Avaliacao> listaAval = new ArrayList<Avaliacao>();
	private Avaliacao avalSelec;

	public LoginBean() {
		usuarioLogado = new Usuario();
		cervejaController = new CervejaController();
		pratoController = new PratoController();
		usuarioController = new UsuarioController();
		avaliacaoController = new AvaliacaoController();
		harmonizacaoController = new HarmonizacaoController();
		renderizarUserLog = false;
	}

	/**
	 * Metodo para logar no sistema.
	 * 
	 * @return acesso ao menu principal ou mensagem de erro ao logar.
	 */
	public String entrar() {

		try {

			usuarioLogado = usuarioController.logar(getUsername(), getSenha());

			if (usuarioLogado != null) {
				setId_user(usuarioLogado.getId_user());
				setEmail(usuarioLogado.getEmail());
				setInfo(usuarioLogado.getInfo());
				setPrivilegio(usuarioController.verificarPrivilegio(usuarioLogado));
				renderizarUserLog = true;
				Util.setSessionParameter("userLog", usuarioLogado);
				listarAval();

			} else {
				zerarUsuario();
				Util.mensagemErro("valNull", "O usu�rio ou a senha n�o est�o corretos.");
				renderizarUserLog = false;
			}
			return "/inicial.xhtml";
		} catch (Exception e) {
			FacesContext f = FacesContext.getCurrentInstance();
			f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro interno", "Contacte o Admin"));
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * Metodo para deslogar no sistema.
	 * 
	 * @return p�gina inicial ou mensagem de erro ao logar.
	 */
	public String sair() {

		try {
			if (usuarioLogado != null) {
				zerarUsuario();
				Util.removeSessionParameter("userLog");
				renderizarUserLog = false;

			} else {
				zerarUsuario();
				Util.mensagemErro("valNull", "Ocorreu um erro");
				renderizarUserLog = false;

			}
			return "/inicial.xhtml";
		} catch (Exception e) {
			FacesContext f = FacesContext.getCurrentInstance();
			f.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro interno", "Contacte o Admin"));
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * Edita os dados username, email e info do usu�rio logado no sistema.
	 */
	public void editar() {
		try {
			Usuario user = (Usuario) Util.getSessionParameter("userLog");
			user.setUsername(username);
			user.setEmail(email);
			user.setInfo(info);

			if (usuarioController.editar(user)) {
				setUsuarioLogado(user);
				Util.setSessionParameter("userLog", usuarioLogado);
				Util.mensagemInfo("Editado com sucesso!");
			} else {
				Util.mensagemErro(
						"N�o foi poss�vel processar seu requerimento. Verifique as informa��es ou tente mais tarde.");
			}
		} catch (Exception e) {
			Util.mensagemErro(
					"N�o foi poss�vel processar seu requerimento. Verifique as informa��es ou tente mais tarde.");
			e.printStackTrace();
		}

	}

	/**
	 * Compara a senha nova com a antiga e, caso esteja certo, altera a senha do
	 * usu�rio.
	 */
	public void alterarSenha() {
		try {
			Usuario user = (Usuario) Util.getSessionParameter("userLog");
			if (getSenhaant().compareTo(user.getSenha()) == 0) {
				user.setSenha(senha);
				if (usuarioController.editar(user)) {
					setUsuarioLogado(user);
					Util.setSessionParameter("userLog", usuarioLogado);
					Util.mensagemInfo("Senha alterada com sucesso!");
				} else {
					Util.mensagemErro("N�o foi alterar sua senha.");
				}
			} else {
				Util.mensagemErro("A senha antiga informada n�o � condizente...");

			}
		}

		catch (	Exception e) {
			Util.mensagemErro("N�o foi alterar sua senha.");
			e.printStackTrace();
		}

	}
	
	public void listarAval() {
		try {
			ArrayList<Avaliacao> listaProv = new ArrayList<Avaliacao>();

			for (Avaliacao a : avaliacaoController.listarAvalporUser(getId_user())) {
				Harmonizacao h = harmonizacaoController.selecionarHarmo(a.getId_harmo());
				Prato p = pratoController.selecionarPrato(h.getId_prato());
				Cerveja c = cervejaController.selecionarCerveja(h.getId_cerv());
				Avaliacao aval = new Avaliacao(a.getId_aval(), a.getId_harmo(), c.getNm_cerv(), p.getNm_prato(),
						getId_user(), getUsername(), a.getNota(), a.getComentario());				
				listaProv.add(aval);
			}
			
			
			setListaAval(listaProv);

		} catch (Exception e) {
			Util.mensagemErro("N�o foi poss�vel carregar as avalia��es desse usu�rio");
			e.printStackTrace();
		}
	}

	public void removerAval() {
		try {
			if (avaliacaoController.deletarAvaliacao(getAvalSelec())) {
				listarAval();
				Util.mensagemInfo("Avalia��o apagada!");
			} else {
				Util.mensagemErro("N�o foi poss�vel apagar essa avalia��o...");
			}
		} catch (Exception e) {
			Util.mensagemErro("N�o foi poss�vel apagar essa avalia��o...");
			e.printStackTrace();

		}

	}

	/**
	 * Metodo que zera os tipos envolvidos com o usuario para fins de limpeza de
	 * tela.
	 */
	private void zerarUsuario() {
		setId_user(0);
		setUsername("");
		setSenha("");
		setEmail("");
		setInfo("");
		setPrivilegio(false);
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the id_user
	 */
	public int getId_user() {
		return id_user;
	}

	/**
	 * @param id_user the id_user to set
	 */
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the senhaant
	 */
	public String getSenhaant() {
		return senhaant;
	}

	/**
	 * @param senhaant the senhaant to set
	 */
	public void setSenhaant(String senhaant) {
		this.senhaant = senhaant;
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
	 * @return the privilegio
	 */
	public boolean isPrivilegio() {
		return privilegio;
	}

	/**
	 * @param privilegio the privilegio to set
	 */
	public void setPrivilegio(boolean privilegio) {
		this.privilegio = privilegio;
	}

	/**
	 * @return the renderizarUserLog
	 */
	public boolean isRenderizarUserLog() {
		return renderizarUserLog;
	}

	/**
	 * @param renderizarUserLog the renderizarUserLog to set
	 */
	public void setRenderizarUserLog(boolean renderizarUserLog) {
		this.renderizarUserLog = renderizarUserLog;
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
