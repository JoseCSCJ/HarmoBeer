/**
 * 
 */
package com.harmobeer.mvc.model;

import java.util.List;

import com.harmobeer.db.dao.PratoService;
import com.harmobeer.interfaces.IPratoDAO;
import com.harmobeer.util.ServiceFactory;
import com.harmobeer.vo.Prato;

/**
 * Classe responsavel pelo Modelo dos objetos Prato
 * 
 * @author Jose Carlos Soares da Cruz Junior 
 * 
 */
public class PratoModel implements IPratoDAO{
	
	private PratoService pratoService;

	/**
	 * Construtor da classe Prato Model, utilizando a criacaSo de um novo objeto
	 * da classe PratoService.
	 */
	public PratoModel() {
		this.pratoService = ServiceFactory.getInstancePratoService();
	}

	/**
	 * Metodo que inclui um prato no banco, utilizando o objeto pratoService para
	 * acessar o banco
	 * 
	 * @param Prato
	 *            a ser incluido
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 * 
	 */
	@Override
	public boolean incluir(Prato prato) {
		return pratoService.incluir(prato);
	}

	/**
	 * Metodo que edita uma prato no banco, utilizando o objeto pratoService para
	 * acessar o banco
	 * 
	 * @param Prato
	 *            a ser editada
	 * @return boolean true para transação bem sucedida e false para transação
	 *         interrompida.
	 */

	@Override
	public boolean editar(Prato prato) {
		return pratoService.editar(prato);
	}

	/**
	 * Metodo que exclui uma prato no banco, utilizando o objeto pratoService para
	 * acessar o banco
	 * 
	 * @param Prato
	 *            a ser excluida
	 * @return boolean true para transacao bem sucedida e false para transacao
	 *         interrompida.
	 */

	@Override
	public boolean deletar(Prato prato) {
		return pratoService.deletar(prato);
	}

	/**
	 * Metodo responsavel por realizar a listagem de todas os Pratos cadastradas
	 * no banco.
	 * 
	 * @return ArrayList com os objetos da Classe Prato gerados com os dados
	 *         recebidos do banco de dados.
	 * @throws Exception 
	 * 
	 */

	@Override
	public List<Prato> listarTodos() throws Exception {
		return pratoService.listarTodos();
	}

	/**
	 * Metodo responsavel por buscar e trazer o objeto de um Prato, com os dados
	 * do banco de dados.
	 * 
	 * @param id
	 *            ID do prato cadastrado no banco
	 * @return Prato selecionado
	 * @throws Exception 
	 */

	public Prato selecionarPrato(int id) throws Exception {
		return pratoService.selecionarPrato(id);
	}

	
	

}
