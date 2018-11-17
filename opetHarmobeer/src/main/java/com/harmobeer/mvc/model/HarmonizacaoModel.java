/**
 *
 */
package com.harmobeer.mvc.model;

import java.util.ArrayList;

import com.harmobeer.db.dao.HarmonizacaoService;
import com.harmobeer.interfaces.IHarmonizacaoDAO;
import com.harmobeer.util.ServiceFactory;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;

/**
 *
 * Classe responsavel pelo model do objeto Harmonizacao
 *
 * @author Jose Carlos Soares da Cruz Junior 
 *
 */
public class HarmonizacaoModel implements IHarmonizacaoDAO {
	private HarmonizacaoService harmonizacaoService ;

	/**
	 * Construtor da classe HarmonizacaoModel utilizando a classe
	 * HarmonizacaoService
	 */
	public HarmonizacaoModel() {
		this.harmonizacaoService = ServiceFactory.getInstanceHarmonizacaoService();
	}

	/**
	 * Metodo que inclui uma harmonizacao no banco de dados caso ela ja nao
	 * exista.
	 *
	 * @param cerveja
	 * @param prato
	 * @return boolean
	 */
	public boolean incluirHarmonizacao(Cerveja cerveja, Prato prato) {
		return harmonizacaoService.incluirHarmonizacao(cerveja, prato);
	}

	/**
	 * Metodo para calcular a media de determinada harmonizacao baseada nas
	 * avaliacoes feitas por ela.
	 * 
	 * @param harmo
	 * @return boolean
	 */
	public boolean calcularMedia(Harmonizacao harmo) {
		return harmonizacaoService.calcularMedia(harmo);
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes
	 * para determinada cerveja.
	 * 
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes
	 *         pela media
	 * @throws Exception 
	 */
	public ArrayList<Harmonizacao> gerarRanking(Cerveja cerveja) throws Exception {
		return harmonizacaoService.gerarRanking(cerveja);
	}

	/**
	 * Metodo para gerar um ranking em um ArrayList das melhores harmonizacoes
	 * para determinado prato.
	 * 
	 * @param cerveja
	 * @return ArrayList<Harmonizacao> em ordem decrescente das harmonizacoes
	 *         pela media
	 * @throws Exception 
	 */
	public ArrayList<Harmonizacao> gerarRanking(Prato prato) throws Exception {
		return harmonizacaoService.gerarRanking(prato);
	}
	/**
	 * Método que devolve a id de uma harmonização com base na cerveja e no prato
	 * @param cerveja
	 * @param prato
	 * @return int id_harmo
	 * @throws Exception 
	 */
	public int selecionaridHarmonizacao(Cerveja cerveja, Prato prato) throws Exception {
		return harmonizacaoService.selecionaridHarmonizacao(cerveja, prato);
	}
	/**
	 * Metodo para selecionar uma harmonizacao no banco de dados
	 * @param id_harmo
	 * @return objeto harmonizacao 
	 * @throws Exception 
	 */
	public Harmonizacao selecionarHarmo(int id_harmo) throws Exception {
		return harmonizacaoService.selecionarHarmo(id_harmo);
	}
	/**
	 * Metodo que devolve uma ArrayList com toda as harmonizacoes cadastradas.
	 * @return ArrayList com todas as harmonizacoes
	 * @throws Exception 
	 */
	public ArrayList<Harmonizacao> listarTodos() throws Exception {
		return harmonizacaoService.listarTodos();
	}
	
}
