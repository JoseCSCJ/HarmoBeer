package com.harmobeer.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Classe para conectar o hibernate.cfg.xml ao banco de dados
 * 
 * 
 *
 */
public class HibernateUtil {

	/**
	 * Variáveis estáticas
	 */

	private static final SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Classe de operações
	 */

	private static SessionFactory buildSessionFactory() {

		try {

			Configuration config = new Configuration();

			config.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

			standardServiceRegistryBuilder.applySettings(config.getProperties());

			StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();			

			return config.buildSessionFactory(standardServiceRegistry);

		} catch (Throwable e) {

			System.err.println("\n Initial creation of the SessioFactory object failed. Error: " + e.getMessage());

			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
