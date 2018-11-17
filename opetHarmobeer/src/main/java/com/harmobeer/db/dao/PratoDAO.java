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

import com.harmobeer.interfaces.IPratoDAO;
import com.harmobeer.vo.Prato;

/**
 *
 * Classe responsavel pelo acesso ao banco de dados do objeto prato
 *
 * @author Jose Carlos Soares da Cruz Junior
 *
 */
public class PratoDAO implements IPratoDAO {

	private Session session;

	// Construtor
	public PratoDAO() {

	}

	/**
	 * Metodo responsavel por realizar a inclusao de pratos no banco.
	 *
	 * @param Prato prato a ser incluido
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 *
	 */
	@Override
	public boolean incluir(Prato prato) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.save(prato);
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
	 * Metodo responsavel por realizar a edicao de Pratos cadastrados no banco.
	 *
	 * @param Prato prato a ser editado
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */
	@Override
	public boolean editar(Prato prato) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.merge(prato);
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
	 * Metodo responsavel por realizar a exclusao de Pratos cadastrados no banco
	 *
	 * @param Prato prato a deletado
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */
	@Override
	public boolean deletar(Prato prato) {
		Transaction transaction = null;
		try {
			int id = prato.getId_prato();
			transaction = this.session.beginTransaction();
			this.session.clear();
			this.session.delete(prato);
			this.session.flush();
			transaction.commit();
			try {
				prato = selecionarPrato(id);
			} catch (Exception e) {
				System.out.println("Erro na validação da deleção");
				e.printStackTrace();
				return false;
			}
			if (prato == null) {

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
	 * Metodo responsavel por realizar a listagem de todas os Pratos cadastrados no
	 * banco.
	 *
	 * @return ArrayList com os objetos da Classe Prato gerados com os dados
	 *         recebidos do banco de dados.
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Prato> listarTodos() throws Exception {

		List<Prato> pratos = null;
		Criteria criteria = null;

		try {

			criteria = this.session.createCriteria(Prato.class).addOrder(Order.asc("nm_prato"));
			pratos = (List<Prato>) criteria.list();
			return pratos;

		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo responsavel por buscar e retornar um objeto da classe Prato no banco
	 *
	 * @param id ID da prato cadastrada no banco
	 * @return Prato selecionada
	 */
	@Override
	public Prato selecionarPrato(int id) throws Exception {
		Prato prato = null;
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Prato.class).add(Restrictions.eq("id_prato", id));
			prato = (Prato) criteria.uniqueResult();
			return prato;

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