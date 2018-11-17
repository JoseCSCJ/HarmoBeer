/**
 * 
 */
package com.harmobeer.vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que modela o objeto Prato
 * 
 * @author Jose Carlos Soares da Cruz Junior 
 */

@Entity
@Table(name = "Prato")
public class Prato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "seqprato")
	@Column(name = "id_prato")
	private int id_prato;
	
	@Column(name = "nm_prato")
	private String nm_prato;
	
	@OneToMany(mappedBy="prato", targetEntity=Harmonizacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	private List<Harmonizacao> harmonizacoes;


	public Prato() {
		
	}

	/**
	 * Construtor da classe prato passando o id como parametro. Utilizado para
	 * construir o objeto em situações onde o usuário seleciona um prato.
	 * 
	 * @param id_prato
	 */
	public Prato(int id_prato) {
		this.id_prato = id_prato;
	}

	/**
	 * Construtor da classe cerveja passando o id e nome. Utilizado para construir o objeto completo, com todos os
	 * parametros possíveis.
	 * 
	 * @param id_prato
	 * @param nm_prato

	 */
	public Prato(int id_prato, String nm_prato) {
		this.id_prato = id_prato;
		this.nm_prato = nm_prato;
		
	}

	/**
	 * Construtor da classe cerveja passando o nome. Utilizado para inclusao do prato no banco de dados. O
	 * parametro id eh recebido apenas no banco de dados.
	 * 
	 * @param nm_prato
	 */
	public Prato(String nm_prato) {
		this.nm_prato = nm_prato;
	}

	/**
	 * Metodo que recebe o id da prato
	 * 
	 * @return the id_prato
	 */
	public int getId_prato() {
		return id_prato;
	}

	/**
	 * Metodo que seta o valor do id da prato
	 * 
	 * @param id_prato
	 *            the id_prato to set
	 */
	public void setId_prato(int id_prato) {
		this.id_prato = id_prato;
	}

	/**
	 * Metodo que recebe o nome da prato
	 * 
	 * @return the nm_prato
	 */
	public String getNm_prato() {
		return nm_prato;
	}

	/**
	 * Metodo que seta o valor do nome da prato
	 * 
	 * @param nm_prato
	 *            the nm_prato to set
	 */
	public void setNm_prato(String nm_prato) {
		this.nm_prato = nm_prato;
	}

	/**
	 * @return the harmonizacoes
	 */	
	public List<Harmonizacao> getHarmonizacoes() {
		return harmonizacoes;
	}

	/**
	 * @param harmonizacoes the harmonizacoes to set
	 */
		
	public void setHarmonizacoes(List<Harmonizacao> harmonizacoes) {
		this.harmonizacoes = harmonizacoes;
	}

	


}
