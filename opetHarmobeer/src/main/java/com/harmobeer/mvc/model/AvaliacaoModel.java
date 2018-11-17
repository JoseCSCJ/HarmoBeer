/**
 * 
 */
package com.harmobeer.mvc.model;

import java.util.List;

import com.harmobeer.db.dao.AvaliacaoService;
import com.harmobeer.interfaces.IAvaliacaoDAO;
import com.harmobeer.util.ServiceFactory;
import com.harmobeer.vo.Avaliacao;

/**
 * Classe responsavel pelo modelo para o objeto Avaliacao
 *
 * @author Jose Carlos Soares da Cruz Junior
 *
 */
public class AvaliacaoModel implements IAvaliacaoDAO {

	private AvaliacaoService avaliacaoService;

	public AvaliacaoModel() {
		this.avaliacaoService = ServiceFactory.getInstanceAvaliacaoService();
	}

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
		return avaliacaoService.incluirAvaliacao(aval);
	}

	/**
	 * Metodo para editar uma avaliacao nos itens de nota e comentario.
	 * 
	 * @param aval
	 * @return boolean
	 */
	public boolean editarAvaliacao(Avaliacao aval) {
		return avaliacaoService.editarAvaliacao(aval);
	}

	/**
	 * Metodo para deletar uma avaliacao.
	 * 
	 * @param aval
	 * @return boolean
	 */
	public boolean deletarAvaliacao(Avaliacao aval) {
		return avaliacaoService.deletarAvaliacao(aval);
	}

	/**
	 * Metodo que obtem do banco de dados uma lista de avaliacoes feitas por
	 * determinado usuario
	 * 
	 * @param idUser
	 * @return List<Avaliacao>
	 * @throws Exception
	 */
	public List<Avaliacao> listarAvalporUser(int idUser) throws Exception {
		return avaliacaoService.listarAvalporUser(idUser);

	}

	/**
	 * Metodo que obtem do banco de dados uma lista de avaliacoes feitas para
	 * determinada harmonizacao
	 * 
	 * @param idUser
	 * @return List<Avaliacao>
	 * @throws Exception
	 */
	public List<Avaliacao> listarAvalporHarmo(int idHarmo) throws Exception {
		return avaliacaoService.listarAvalporHarmo(idHarmo);
	}

	/**
	 * Metodo responsavel por buscar e retornar um objeto da classe Avaliacao no
	 * banco
	 *
	 * @param id ID da avaliacao cadastrada no banco
	 * @return Avaliacao selecionada
	 * @throws Exception
	 */
	public Avaliacao selecionarAval(int id) throws Exception {
		return avaliacaoService.selecionarAval(id);
	}

	/**
	 * Metodo responsavel por buscar e retornar o último objeto gerado da classe
	 * Avaliacao no banco
	 *
	 * @return int id Ultima Avaliacao criada
	 * @throws Exception
	 */
	public Avaliacao selecionarUltAval() throws Exception {
		return avaliacaoService.selecionarUltAval();
	}

}
