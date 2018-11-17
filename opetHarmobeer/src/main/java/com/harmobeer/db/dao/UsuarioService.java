/**
 * 
 */
package com.harmobeer.db.dao;

import java.util.List;

import org.hibernate.Session;

import com.harmobeer.interfaces.IUsuarioDAO;
import com.harmobeer.vo.Usuario;

/**
 * @author Usuário
 *
 */
public class UsuarioService implements IUsuarioDAO {

	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAO();
	}

	public void setConnection(Session session) {
		this.usuarioDAO.setSession(session);
	}

	@Override
	public boolean incluir(Usuario usuario) {
		return this.usuarioDAO.incluir(usuario);
	}

	@Override
	public boolean editar(Usuario usuario) {
		return this.usuarioDAO.editar(usuario);
	}

	@Override
	public boolean deletar(Usuario usuario) {
		return this.usuarioDAO.deletar(usuario);
	}

	@Override
	public Usuario logar(String username, String senha) throws Exception {
		return this.usuarioDAO.logar(username, senha);
	}

	@Override
	public boolean verificarPrivilegio(Usuario usuario) {
		return this.usuarioDAO.verificarPrivilegio(usuario);
	}

	@Override
	public Usuario selecionarUser(int id_user) throws Exception {

		return this.usuarioDAO.selecionarUser(id_user);
	}

	@Override
	public List<Usuario> listarTodos() throws Exception {
		return this.usuarioDAO.listarTodos();
	}
}