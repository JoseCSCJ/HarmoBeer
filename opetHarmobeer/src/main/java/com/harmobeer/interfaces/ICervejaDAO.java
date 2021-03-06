/**
 * 
 */
package com.harmobeer.interfaces;

import java.util.List;

import com.harmobeer.vo.Cerveja;

/**
 * Interface responsavel pelos DAO's da classe Cerveja
 * @author Jose Carlos Soares da Cruz Junior 
 * 
 */
public interface ICervejaDAO {
	
	public boolean incluir(Cerveja cerveja);
	
	public boolean editar(Cerveja cerveja);
	
	public boolean deletar(Cerveja cerveja);
	
	public List <Cerveja> listarTodos() throws Exception;
	
	public Cerveja selecionarCerv(int id) throws Exception;

	
}
