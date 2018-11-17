/**
 *
 */
package com.harmobeer.db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.harmobeer.interfaces.ICervejaDAO;
import com.harmobeer.vo.Cerveja;


/**
 *
 * Classe responsavel pelo acesso ao banco de dados do objeto cerveja
 * 
 * @author Jose Carlos Soares da Cruz Junior
 *
 */
public class CervejaDAO implements ICervejaDAO {

	private Session session;

	// Construtor
	public CervejaDAO() {

	}

	/**
	 * Metodo responsavel por realizar a inclusao de Cervejas no banco.
	 *
	 * @param Cerveja cerveja a ser incluida
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 *
	 */
	@Override
	public boolean incluir(Cerveja cerveja) {
		Transaction transaction = null;

		try {
			transaction = this.session.beginTransaction();
			this.session.save(cerveja);
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
	 * Metodo responsavel por realizar a edicao de Cervejas cadastradas no banco.
	 *
	 * @param Cerveja cerveja a ser editada
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */
	@Override
	public boolean editar(Cerveja cerveja) {
		Transaction transaction = null;
		try {

			transaction = this.session.beginTransaction();
			this.session.merge(cerveja);
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
	 * Metodo responsavel por realizar a exclusao de Cervejas cadastradas no banco
	 *
	 * @param Cerveja cerveja a deletada
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */
	@Override
	public boolean deletar(Cerveja cerveja) {
		Transaction transaction = null;
		try {
			int id = cerveja.getId_cerv();
			transaction = this.session.beginTransaction();
			this.session.clear();
			this.session.delete(cerveja);
			this.session.flush();
			transaction.commit();
			try {
				cerveja = selecionarCerv(id);
			} catch (Exception e) {
				System.out.println("Erro na validação da deleção");
				e.printStackTrace();
				return false;
			}
			if (cerveja == null) {

				return true;

			} else {

				return false;
			}

		} catch (HibernateException e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo responsavel por realizar a listagem de todas as Cervejas cadastradas
	 * no banco.
	 *
	 * @return ArrayList com os objetos da Classe Cerveja gerados com os dados
	 *         recebidos do banco de dados.
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Cerveja> listarTodos() throws Exception {

		List<Cerveja> cervejas = null;
		Criteria criteria = null;

		try {

			criteria = this.session.createCriteria(Cerveja.class).addOrder(Order.asc("nm_cerv"));
			cervejas = (List<Cerveja>) criteria.list();
			return cervejas;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo responsavel por buscar e retornar um objeto da classe Cerveja no banco
	 *
	 * @param id ID da cerveja cadastrada no banco
	 * @return Cerveja selecionada
	 */
	@Override
	public Cerveja selecionarCerv(int id) throws Exception {
		Cerveja cerveja = null;
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Cerveja.class).add(Restrictions.eq("id_cerv", id));
			cerveja = (Cerveja) criteria.uniqueResult();
			return cerveja;
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
