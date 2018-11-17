/**
 * 
 */
package com.harmobeer.interfaces;

import java.util.List;


import com.harmobeer.vo.Avaliacao;

/**
 * @author Usuário
 *
 */
public interface IAvaliacaoDAO {
	public boolean incluirAvaliacao(Avaliacao aval);

	public boolean editarAvaliacao(Avaliacao aval);

	public boolean deletarAvaliacao(Avaliacao aval);

	public List<Avaliacao> listarAvalporUser(int idUser) throws Exception;

	public List<Avaliacao> listarAvalporHarmo(int idHarmo) throws Exception;

	public Avaliacao selecionarAval(int id) throws Exception;

	public Avaliacao selecionarUltAval() throws Exception;

}
