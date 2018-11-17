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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.harmobeer.interfaces.IAvaliacaoDAO;
import com.harmobeer.vo.Avaliacao;


/**
 * Classe responsavel pelo acesso ao banco de dados para Avaliacao
 *
 * @author Jose Carlos Soares da Cruz Junior
 *
 */
public class AvaliacaoDAO implements IAvaliacaoDAO {

	private Session session;

	/**
	 * 
	 * Metodo para incluir uma avaliacao no banco de dados, de um determinado
	 * usuario em uma determinada harmonizacao.
	 * 
	 * @param aval
	 * @param user
	 * @param harmo
	 * @return boolean
	 */
	public boolean incluirAvaliacao(Avaliacao aval) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.save(aval);
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
	 * Metodo para editar uma avaliacao nos itens de nota e comentario.
	 * 
	 * @param aval
	 * @return boolean
	 */
	public boolean editarAvaliacao(Avaliacao aval) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.merge(aval);
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
	 * Metodo para deletar uma avaliacao.
	 * 
	 * @param aval
	 * @return boolean
	 */
	public boolean deletarAvaliacao(Avaliacao aval) {
		Transaction transaction = null;
		try {
			transaction = this.session.beginTransaction();
			this.session.clear();
			this.session.delete(aval);
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
	 * Metodo que obtem do banco de dados uma lista de avaliacoes feitas por
	 * determinado usuario
	 * 
	 * @param idUser
	 * @return List<Avaliacao>
	 */
	@SuppressWarnings("unchecked")
	public List<Avaliacao> listarAvalporUser(int idUser) throws Exception {
		ArrayList<Avaliacao> listaAval = new ArrayList<Avaliacao>();
		Criteria criteria = null;

		try {
			criteria = this.session.createCriteria(Avaliacao.class).createAlias("user", "u")
					.add(Restrictions.eq("u.id_user", idUser));

			listaAval = (ArrayList<Avaliacao>) criteria.list();

			return listaAval;

		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo que obtem do banco de dados uma lista de avaliacoes feitas para
	 * determinada harmonizacao
	 * 
	 * @param idUser
	 * @return List<Avaliacao>
	 */
	@SuppressWarnings("unchecked")
	public List<Avaliacao> listarAvalporHarmo(int idHarmo) throws Exception {
		ArrayList<Avaliacao> listaAval = new ArrayList<Avaliacao>();
		Criteria criteria = null;

		try {
			criteria = this.session.createCriteria(Avaliacao.class).createAlias("harmonizacao", "h")
					.add(Restrictions.eq("h.id_harmo", idHarmo));

			listaAval = (ArrayList<Avaliacao>) criteria.list();

			return listaAval;

		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo responsavel por buscar e retornar um objeto da classe Avaliacao no
	 * banco
	 *
	 * @param id ID da avaliacao cadastrada no banco
	 * @return Avaliacao selecionada
	 */
	public Avaliacao selecionarAval(int id) throws Exception {
		Avaliacao avaliacao = null;
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Avaliacao.class).add(Restrictions.eq("id_aval", id));

			avaliacao = (Avaliacao) criteria.uniqueResult();
			return avaliacao;

		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo responsavel por buscar e retornar o último objeto gerado da classe
	 * Avaliacao no banco
	 *
	 * @return int id Ultima Avaliacao criada
	 */
	public Avaliacao selecionarUltAval() throws Exception {
		Criteria criteria = null;
		Avaliacao avaliacao = null;
			try {
				DetachedCriteria idMax= DetachedCriteria.forClass(Avaliacao.class).setProjection(Projections.max("id_aval"));
				criteria=this.session.createCriteria(Avaliacao.class).add(Property.forName("id_aval").eq(idMax));
				avaliacao = (Avaliacao) criteria.uniqueResult();
				return avaliacao;

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
