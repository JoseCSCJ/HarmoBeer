/**
 *
 */
package com.harmobeer.db.dao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.harmobeer.interfaces.IHarmonizacaoDAO;
import com.harmobeer.vo.Avaliacao;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 *
 * Classe de acesso ao banco de dados da classe Harmonizacao.
 *
 * @author Jose Carlos Soares da Cruz Junior
 *
 */

public class HarmonizacaoDAO implements IHarmonizacaoDAO {

	private Session session;

	public HarmonizacaoDAO() {

	}

	/**
	 * Metodo que verifica se a harmonizacao de uma cerveja com um prato ja existe
	 * no banco de dados. Caso exista, retorna boolean true. Caso não exista retorna
	 * false. Caso haja algum problema de validação, lança uma exceção para
	 * tratamento pelo método posterior.
	 *
	 * @param cerveja
	 * @param prato
	 * @return boolean
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public boolean verificarHarmonizacao(Cerveja cerveja, Prato prato) throws Exception {

		Criteria criteria = null;

		try {
			ArrayList<Harmonizacao> harmoCerv = new ArrayList<Harmonizacao>();
			criteria = this.session.createCriteria(Harmonizacao.class)
					.createAlias("cerveja", "c")
					.add(Restrictions.eq("c.id_cerv", cerveja.getId_cerv()));
			harmoCerv = (ArrayList<Harmonizacao>) criteria.list();
			for (Harmonizacao h : harmoCerv) {
				if (h.getPrato().getId_prato() == prato.getId_prato()) {
					return true;
				}
			}
			return false;

		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Metodo que inclui uma harmonizacao no banco de dados caso ela ja nao exista.
	 *
	 * @param cerveja
	 * @param prato
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean incluirHarmonizacao(Cerveja cerveja, Prato prato) {
		Transaction transaction = null;
		Harmonizacao h = new Harmonizacao(cerveja, prato, 0);

		try {
			if (verificarHarmonizacao(cerveja, prato)) {
				System.out.println("Harmonizacao já existente");
				return true;
			} else {
				transaction = this.session.beginTransaction();
				this.session.save(h);
				this.session.flush();
				transaction.commit();
				return true;
			}
		} catch (Exception e) {

			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Metodo para calcular a media de determinada harmonizacao baseada nas
	 * avaliacoes feitas por ela.
	 *
	 * @param harmo
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean calcularMedia(Harmonizacao harmo) {
		int nota = 0;
		int qtdnota = 0;
		double media = 0.0;
		Criteria criteria = null;
		Transaction transaction = null;
		try {
			ArrayList<Avaliacao> listaAval = new ArrayList<Avaliacao>();
			criteria = this.session.createCriteria(Avaliacao.class)
					.createAlias("harmonizacao", "h")
					.add(Restrictions.eq("h.id_harmo", harmo.getId_harmo()));
			listaAval = (ArrayList<Avaliacao>) criteria.list();
			for (Avaliacao a : listaAval) {
				nota = nota + a.getNota();
				qtdnota = qtdnota + 1;
			}
			if (qtdnota == 0) {
				media = 0;
			} else {
				media = (double) nota / qtdnota;
				DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
				otherSymbols.setDecimalSeparator('.');
				otherSymbols.setGroupingSeparator(','); 
				DecimalFormat df = new DecimalFormat("#0.00", otherSymbols);				
				System.out.println("A média formatada ficou " + df.format(media));
				media = Double.parseDouble(df.format(media));
			}

			harmo.setMedia(media);
			transaction = this.session.beginTransaction();
			this.session.merge(harmo);
			this.session.flush();
			transaction.commit();
			return true;

		} catch (ArithmeticException ae) {
			ae.printStackTrace();
			System.out.println("Divisão por zero...");
			return false;
		} catch (HibernateException he) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			he.printStackTrace();
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes para
	 * determinada cerveja.
	 *
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes pela
	 *         media
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Harmonizacao> gerarRanking(Cerveja cerveja) throws Exception {
		ArrayList<Harmonizacao> listaHarmo = new ArrayList<Harmonizacao>();
		Criteria criteria;

		try {
			criteria = this.session.createCriteria(Harmonizacao.class)
					.createAlias("cerveja", "c")
					.add(Restrictions.eq("c.id_cerv", cerveja.getId_cerv()))
					.addOrder(Order.desc("media"));
			listaHarmo = (ArrayList<Harmonizacao>) criteria.list();
			return listaHarmo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes para
	 * determinado prato.
	 *
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes pela
	 *         media
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Harmonizacao> gerarRanking(Prato prato) throws Exception {
		ArrayList<Harmonizacao> listaHarmo = new ArrayList<Harmonizacao>();
		Criteria criteria;

		try {			
			criteria = this.session.createCriteria(Harmonizacao.class)
					.createAlias("prato","p")
					.add(Restrictions.eq("p.id_prato", prato.getId_prato())).addOrder(Order.desc("media"));
			listaHarmo = (ArrayList<Harmonizacao>) criteria.list();
			return listaHarmo;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Mï¿½todo que devolve a id de uma harmonizaï¿½ï¿½o com base na cerveja e no
	 * prato
	 *
	 * @param cerveja
	 * @param prato
	 * @return int id_harmo
	 */
	@Override
	public int selecionaridHarmonizacao(Cerveja cerveja, Prato prato) throws Exception {
		Criteria criteria = null;

		try {
			if (verificarHarmonizacao(cerveja, prato)) {
				criteria = this.session.createCriteria(Harmonizacao.class)
						.createAlias("cerveja", "c")
						.createAlias("prato", "p")
						.add(Restrictions.eq("c.id_cerv", cerveja.getId_cerv()))
						.add(Restrictions.eq("p.id_prato", prato.getId_prato()));
				Harmonizacao h = (Harmonizacao) criteria.uniqueResult();
				return h.getId_harmo();
			} else {
				System.out.println("Harmonização não existe ou não foi localizada na verificação");
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * Metodo para selecionar uma harmonizacao no banco de dados
	 *
	 * @param id_harmo
	 * @return objeto harmonizacao
	 */
	@Override
	public Harmonizacao selecionarHarmo(int id_harmo) throws Exception {
		Harmonizacao harmo = new Harmonizacao();
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Harmonizacao.class).add(Restrictions.eq("id_harmo", id_harmo));
			harmo = (Harmonizacao) criteria.uniqueResult();
			return harmo;
		} catch (HibernateException he) {
			he.printStackTrace();
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Metodo que devolve uma ArrayList com toda as harmonizacoes cadastradas, em ordem de médias das avaliações
	 * 
	 * @return ArrayList com todas as harmonizacoes
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Harmonizacao> listarTodos() throws Exception {
		ArrayList<Harmonizacao> listaHarmo = new ArrayList<Harmonizacao>();
		Criteria criteria = null;
		try {
			criteria = this.session.createCriteria(Harmonizacao.class).
					addOrder(Order.desc("media"));
			listaHarmo=(ArrayList<Harmonizacao>) criteria.list();
			return listaHarmo;
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
