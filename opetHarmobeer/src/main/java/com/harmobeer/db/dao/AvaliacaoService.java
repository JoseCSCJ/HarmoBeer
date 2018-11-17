/**
 * 
 */
package com.harmobeer.db.dao;

import java.util.List;

import org.hibernate.Session;

import com.harmobeer.interfaces.IAvaliacaoDAO;
import com.harmobeer.vo.Avaliacao;

/**
 * @author Usuário
 *
 */
public class AvaliacaoService implements IAvaliacaoDAO {
	private AvaliacaoDAO avaliacaoDAO;

	public AvaliacaoService() {
		this.avaliacaoDAO = new AvaliacaoDAO();
	}

	public void setConnection(Session session) {
		this.avaliacaoDAO.setSession(session);
	}

	@Override
	public boolean incluirAvaliacao(Avaliacao aval) {
		return avaliacaoDAO.incluirAvaliacao(aval);
	}

	@Override
	public boolean editarAvaliacao(Avaliacao aval) {
		return avaliacaoDAO.editarAvaliacao(aval);
	}

	@Override
	public boolean deletarAvaliacao(Avaliacao aval) {
		return avaliacaoDAO.deletarAvaliacao(aval);
	}

	@Override
	public List<Avaliacao> listarAvalporUser(int idUser) throws Exception {
		return avaliacaoDAO.listarAvalporUser(idUser);
	}

	@Override
	public List<Avaliacao> listarAvalporHarmo(int idHarmo) throws Exception {
		return avaliacaoDAO.listarAvalporHarmo(idHarmo);
	}

	@Override
	public Avaliacao selecionarAval(int id) throws Exception {		
		return avaliacaoDAO.selecionarAval(id);
	}

	@Override
	public Avaliacao selecionarUltAval() throws Exception {		
		return avaliacaoDAO.selecionarUltAval();
	}

}
