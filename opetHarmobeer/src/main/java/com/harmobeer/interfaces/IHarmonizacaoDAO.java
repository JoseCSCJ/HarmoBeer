/**
 * 
 */
package com.harmobeer.interfaces;

import java.util.ArrayList;

import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 * @author Usuário
 *
 */
public interface IHarmonizacaoDAO {
	public boolean incluirHarmonizacao(Cerveja cerveja, Prato prato);
	
	public boolean calcularMedia(Harmonizacao harmo);
	
	public ArrayList<Harmonizacao> gerarRanking(Cerveja cerveja) throws Exception;
	
	public ArrayList<Harmonizacao> gerarRanking(Prato prato) throws Exception;
	
	public int selecionaridHarmonizacao(Cerveja cerveja, Prato prato) throws Exception;
	
	public Harmonizacao selecionarHarmo(int id_harmo) throws Exception;
	
	public ArrayList<Harmonizacao> listarTodos() throws Exception;	

}
