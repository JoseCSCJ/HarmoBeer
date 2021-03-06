/**
 * 
 */
package com.harmobeer.interfaces;

import java.util.List;

import com.harmobeer.vo.Prato;

/**
 * Interface responsavel pelos DAO's da classe Prato
 * 
 * @author Jose Carlos Soares da Cruz Junior
 *
 */
public interface IPratoDAO {
	public boolean incluir(Prato prato);

	public boolean editar(Prato prato);

	public boolean deletar(Prato prato);

	public List<Prato> listarTodos() throws Exception;

	public Prato selecionarPrato(int id) throws Exception;

}
