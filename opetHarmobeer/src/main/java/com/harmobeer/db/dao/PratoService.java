/**
 * 
 */
package com.harmobeer.db.dao;

import java.util.List;

import org.hibernate.Session;

import com.harmobeer.interfaces.IPratoDAO;
import com.harmobeer.vo.Prato;

/**
 * @author Usuário
 *
 */
public class PratoService implements IPratoDAO {
	
	private PratoDAO pratoDAO;
	
	public PratoService() {
		this.pratoDAO = new PratoDAO();
	}
	
	
	public void setConnection(Session session) {
		this.pratoDAO.setSession(session);
	}

	@Override
	public boolean incluir(Prato prato) {
		return this.pratoDAO.incluir(prato);
	}

	@Override
	public boolean editar(Prato prato) {
		return this.pratoDAO.editar(prato);
	}

	@Override
	public boolean deletar(Prato prato) {
		return this.pratoDAO.deletar(prato);
	}

	@Override
	public List<Prato> listarTodos() throws Exception {
		return this.pratoDAO.listarTodos();
	}

	@Override
	public Prato selecionarPrato(int id) throws Exception {
		return this.pratoDAO.selecionarPrato(id);
	}

}
