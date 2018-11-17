/**
 *
 */
package com.harmobeer.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.harmobeer.interfaces.IUsuarioDAO;
import com.harmobeer.vo.Usuario;

/**
 * Classe responsavel pelo acesso ao banco de dados para Usuario
 *
 * @author Jose Carlos Soares da Cruz Junior
 */
public class UsuarioDAO implements IUsuarioDAO {

	private Session session;

	public UsuarioDAO() {

	}

	/**
	 * Inclui um usuario no banco de dados.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean incluir(Usuario user) {
		Transaction transaction = null;
		Criteria criteria = null;
		user.setPrivilegio(0);
		try {
			// verificar se já existe alguém com Username/email
			criteria = this.session.createCriteria(Usuario.class).add(Restrictions.eq("username", user.getUsername()));

			if (criteria.list() == null) {
				System.out.println("Usuário com esse username já existe no sistema");
				return false;
			}
			criteria = null;
			criteria = this.session.createCriteria(Usuario.class).add(Restrictions.eq("email", user.getEmail()));
			if (criteria.list() == null) {
				System.out.println("Usuário com esse email já existe no sistema");
				return false;
			}

			// adicionando no banco de dados

			transaction = this.session.beginTransaction();
			this.session.save(user);
			this.session.flush();
			transaction.commit();
			return true;

		} catch (HibernateException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Edita um usuario no banco de dados, podendo atualizar as seguintes
	 * informacoes: username, email, info e senha.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean editar(Usuario user) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.merge(user);
			this.session.flush();
			transaction.commit();
			return true;

		} catch (HibernateException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Transforma um usuario em um usuario anonimo, mantendo as suas avaliacoes e
	 * contribuicoes para a plataforma intactas.
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean deletar(Usuario user) {
		Transaction transaction = null;
		String nome = "Anonimo" + user.getId_user();
		user.setUsername(nome);
		user.setSenha(nome.toUpperCase());
		user.setEmail(nome + "@harmobeer.com");
		user.setInfo("Usuário banido por mal uso da plataforma");
		try {
			transaction = this.session.beginTransaction();
			this.session.update(user);
			this.session.flush();
			transaction.commit();
			return true;

		} catch (HibernateException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo responsavel por retornar um usuario cujas Strings para username e
	 * senha tenham um match no banco de dados
	 *
	 * @param Usuario usuario
	 * @return Usuario com username e senha correspondentes ou null se nao houver
	 *         correspondente
	 */
	@Override
	public Usuario logar(String username, String senha) throws Exception {

		Usuario user = null;
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Usuario.class).add(Restrictions.eq("username", username))
					.add(Restrictions.eq("senha", senha));
			user = (Usuario) criteria.uniqueResult();
			if (user == null) {
				System.out.println("Busca não retornou resultados");				
			}
			return user;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Verifica se o usuario recebido possui privilegio de administrador.
	 *
	 * @param usuario
	 * @return boolean
	 */
	@Override
	public boolean verificarPrivilegio(Usuario usuario) {
		if (usuario.getPrivilegio() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Metodo para retornar um usuario do banco de dados utilizando seu id
	 *
	 * @param id_user
	 * @return
	 */
	public Usuario selecionarUser(int id_user) throws Exception {
		Criteria criteria = null;
		Usuario user = new Usuario();
		try {
			criteria = this.session.createCriteria(Usuario.class).add(Restrictions.eq("id_user", id_user));
			user = (Usuario) criteria.uniqueResult();
			return user;
		} catch (HibernateException he) {
			he.printStackTrace();
			System.out.println("Possivelmente não trouxe resultado único na seleção de usuário");
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Metodo responsavel por realizar a listagem de todos os Usuarios cadastradas
	 * no banco.
	 *
	 * @return ArrayList com os objetos da Classe Usuario gerados com os dados
	 *         recebidos do banco de dados.
	 *
	 */

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() throws Exception {
		ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Usuario.class).addOrder(Order.desc("username"));
			listaUsuario = (ArrayList<Usuario>) criteria.list();
			return listaUsuario;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}
}
