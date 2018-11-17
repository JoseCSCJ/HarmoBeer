
package com.harmobeer.db.dao;

import java.util.List;

import org.hibernate.Session;

import com.harmobeer.interfaces.ICervejaDAO;
import com.harmobeer.vo.Cerveja;

/**
 * @author Usuário
 *
 */
public class CervejaService implements ICervejaDAO {
	
	private CervejaDAO cervejaDAO;
	
	public CervejaService() {
		this.cervejaDAO = new CervejaDAO();
	}
	
	
	public void setConnection(Session session) {
		this.cervejaDAO.setSession(session);
	}

	@Override
	public boolean incluir(Cerveja cerveja) {
		return this.cervejaDAO.incluir(cerveja);
	}

	@Override
	public boolean editar(Cerveja cerveja) {
		return this.cervejaDAO.editar(cerveja);
	}

	@Override
	public boolean deletar(Cerveja cerveja) {
		return this.cervejaDAO.deletar(cerveja);
	}

	@Override
	public List<Cerveja> listarTodos() throws Exception {
		return this.cervejaDAO.listarTodos();
	}

	@Override
	public Cerveja selecionarCerv(int id) throws Exception {
		return this.cervejaDAO.selecionarCerv(id);
	}

}