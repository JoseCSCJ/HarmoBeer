package com.harmobeer.util;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Java EE filtro para interceptar as requisições do servidor
 * 
 * @author Baracho
 * 
 * @since September 09, 2018
 *
 * @version 1.0
 *
 */
@WebFilter(urlPatterns = { "*.jsf" })
public class HibernateFilter implements Filter {

	/**
	 * Instance variables
	 */

	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
		this.sessionFactory = HibernateUtil.getSessionFactory();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		Session session = null;

		Transaction transaction = null;

		session = this.sessionFactory.getCurrentSession();

		try {

			transaction = session.beginTransaction();
			filterChain.doFilter(request, response);

			transaction.commit();

			if (session.isOpen()) {

				session.close();

			}

		} catch (Throwable e) {

			try {

				if (transaction.isActive()) {

					transaction.rollback();
				}

			} catch (Throwable t) {

				e.printStackTrace();
			}

			throw new ServletException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

}
