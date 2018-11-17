/**
 * 
 */
package com.harmobeer.mvc.model;

import java.util.List;

import com.harmobeer.db.dao.CervejaService;
import com.harmobeer.interfaces.ICervejaDAO;
import com.harmobeer.util.ServiceFactory;
import com.harmobeer.vo.Cerveja;

/**
 * Classe responsavel pelo Modelo dos objetos Cerveja
 * 
 * @author Jose Carlos Soares da Cruz Junior
 * 
 */
public class CervejaModel implements ICervejaDAO {

	private CervejaService cervejaService;
	
	

	/**
	 * Construtor da classe Cerveja Model, utilizando a criacao de um novo
	 * objeto da classe CervejaService.
	 */
	public CervejaModel() {
		this.cervejaService = ServiceFactory.getInstanceCervejaService();
	}

	/**
	 * Metodo que inclui uma cerveja no banco, utilizando o objeto cervejaService
	 * para acessar o banco
	 * 
	 * @param Cerveja
	 *            a ser incluida
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 * 
	 */
	@Override
	public boolean incluir(Cerveja cerveja) {
		return cervejaService.incluir(cerveja);
	}

	/**
	 * Metodo que edita uma cerveja no banco, utilizando o objeto cervejaService
	 * para acessar o banco
	 * 
	 * @param Cerveja
	 *            a ser editada
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */

	@Override
	public boolean editar(Cerveja cerveja) {
		return cervejaService.editar(cerveja);
	}

	/**
	 * Metodo que exclui uma cerveja no banco, utilizando o objeto cervejaService
	 * para acessar o banco
	 * 
	 * @param Cerveja
	 *            a ser excluída
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */

	@Override
	public boolean deletar(Cerveja cerveja) {
		return cervejaService.deletar(cerveja);
	}

	/**
	 * Metodo responsavel por realizar a listagem de todas as Cervejas
	 * cadastradas no banco.
	 * 
	 * @return ArrayList com os objetos da Classe Cerveja gerados com os dados
	 *         recebidos do banco de dados.
	 * @throws Exception 
	 * 
	 */

	@Override
	public List<Cerveja> listarTodos() throws Exception {
		return cervejaService.listarTodos();
	}

	/**
	 * Metodo responsavel por buscar e trazer o objeto de uma Cerveja, com os
	 * dados do banco de dados.
	 * 
	 * @param id
	 *            ID da cerveja cadastrada no banco
	 * @return Cerveja selecionada
	 * @throws Exception 
	 */

	public Cerveja selecionarCerv(int id) throws Exception {
		return cervejaService.selecionarCerv(id);
	}

	

}
