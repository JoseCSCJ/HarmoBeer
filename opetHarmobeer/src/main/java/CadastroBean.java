import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.harmobeer.mvc.controller.UsuarioController;
import com.harmobeer.util.Util;
import com.harmobeer.vo.Usuario;

/**
 * @author Usuário
 *
 */
@Named(value = "cadastroBean")
@RequestScoped
public class CadastroBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private UsuarioController usuarioController;

	private Usuario usuarioLogado;

	private int idUser;
	private String username;
	private String email;
	private String senha;
	private String senhaAntiga;
	private String info;

	public CadastroBean() {
		usuarioController = new UsuarioController();
	}

	public void cadastrar() {
		try {
			Usuario user = new Usuario(username, email, senha, info);
			if (usuarioController.incluir(user)) {
				Util.mensagemInfo("Usuario cadastrado com sucesso! Tente logar para começar suas avaliações");
			} else {
				Util.mensagemErro(
						"Algo de errado aconteceu. Verifique se todos os dados foram inseridos corretamente e tente novamente.");
			}

		} catch (Exception e) {
			Util.mensagemErro(
					"Algo de errado aconteceu. Verifique se todos os dados foram inseridos corretamente e tente novamente.");
			e.printStackTrace();
		}
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
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
	 * @return the senhaAntiga
	 */
	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	/**
	 * @param senhaAntiga the senhaAntiga to set
	 */
	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

}
