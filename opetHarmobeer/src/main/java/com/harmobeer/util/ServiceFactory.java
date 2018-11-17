package com.harmobeer.util;

import org.hibernate.Session;

import com.harmobeer.db.dao.AvaliacaoService;
import com.harmobeer.db.dao.CervejaService;
import com.harmobeer.db.dao.HarmonizacaoService;
import com.harmobeer.db.dao.PratoService;
import com.harmobeer.db.dao.UsuarioService;

/**
 * Class to instantiate the UsuarioService class.
 * 
 * @author Baracho
 * 
 * @since July 19, 2018
 *
 * @version 1.0
 *
 */
public class ServiceFactory {

	/**
	 * Default constructor
	 */
	public ServiceFactory() {

	}

	/**
	 * @return the pratoService
	 */
	public static PratoService getInstancePratoService() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		PratoService pratoService = new PratoService();

		pratoService.setConnection(session);

		return pratoService;
	}

	/**
	 * @return the cervejaService
	 */
	public static CervejaService getInstanceCervejaService() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		CervejaService cervejaService = new CervejaService();

		cervejaService.setConnection(session);

		return cervejaService;
	}

	/**
	 * @return the harmonizacaoService
	 */
	public static HarmonizacaoService getInstanceHarmonizacaoService() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		HarmonizacaoService harmonizacaoService = new HarmonizacaoService();

		harmonizacaoService.setConnection(session);

		return harmonizacaoService;
	}

	/**
	 * @return the usuarioService
	 */
	public static UsuarioService getInstanceUsuarioService() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		UsuarioService usuarioService = new UsuarioService();

		usuarioService.setConnection(session);

		return usuarioService;
	}

	/**
	 * @return the avaliacaoService
	 */
	public static AvaliacaoService getInstanceAvaliacaoService() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		AvaliacaoService avaliacaoService = new AvaliacaoService();

		avaliacaoService.setConnection(session);

		return avaliacaoService;
	}
}
