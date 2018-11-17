/**
 * 
 */
package com.harmobeer.db.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.harmobeer.interfaces.IHarmonizacaoDAO;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 * @author Usuário
 *
 */
public class HarmonizacaoService implements IHarmonizacaoDAO {

	private HarmonizacaoDAO harmonizacaoDAO;

	public HarmonizacaoService() {
		this.harmonizacaoDAO = new HarmonizacaoDAO();
	}

	public void setConnection(Session session) {
		this.harmonizacaoDAO.setSession(session);
	}

	@Override
	public boolean incluirHarmonizacao(Cerveja cerveja, Prato prato) {
		return this.harmonizacaoDAO.incluirHarmonizacao(cerveja, prato);
	}

	@Override
	public boolean calcularMedia(Harmonizacao harmo) {
		return this.harmonizacaoDAO.calcularMedia(harmo);
	}

	@Override
	public ArrayList<Harmonizacao> gerarRanking(Cerveja cerveja) throws Exception{
		return this.harmonizacaoDAO.gerarRanking(cerveja);
	}

	@Override
	public ArrayList<Harmonizacao> gerarRanking(Prato prato) throws Exception{
		return this.harmonizacaoDAO.gerarRanking(prato);
	}

	@Override
	public int selecionaridHarmonizacao(Cerveja cerveja, Prato prato) throws Exception{
		return this.harmonizacaoDAO.selecionaridHarmonizacao(cerveja, prato);
	}

	@Override
	public Harmonizacao selecionarHarmo(int id_harmo) throws Exception{
		return this.harmonizacaoDAO.selecionarHarmo(id_harmo);
	}

	@Override
	public ArrayList<Harmonizacao> listarTodos() throws Exception{
		return this.harmonizacaoDAO.listarTodos();
	}

}
