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
 * Classe que modela o objeto Cerveja
 * 
 * @author Jose Carlos Soares da Cruz Junior
 */
@Entity
@Table(name = "Cerveja")
public class Cerveja implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "seqcerv")
	@Column(name = "id_cerv") 
	private int id_cerv;

	@Column(name = "nm_cerv")
	private String nm_cerv;

	@Column(name = "nm_estilo")
	private String nm_estilo;

	@Column(name = "teor_alcoolico")
	private double teor_alcool;	
	
	@OneToMany(mappedBy="cerveja", targetEntity=Harmonizacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Harmonizacao> harmonizacoes;


	/**
	 * Construtor da classe cerveja passando o id como parametro. Utilizado para
	 * construir o objeto em situacoes onde o usuario seleciona uma cerveja.
	 * 
	 * @param id_cerv
	 */
	public Cerveja(int id_cerv) {
		this.id_cerv = id_cerv;
	}

	/**
	 * Construtor da classe cerveja passando o id, nome, estilo e teor alcoolico
	 * como parametros. Utilizado para construir o objeto completo, com todos os
	 * parametros possiveis.
	 * 
	 * @param id_cerv
	 * @param nm_cerv
	 * @param nm_estilo
	 * @param teor_alcool
	 */
	public Cerveja(int id_cerv, String nm_cerv, String nm_estilo, double teor_alcool) {
		this.id_cerv = id_cerv;
		this.nm_cerv = nm_cerv;
		this.nm_estilo = nm_estilo;
		this.teor_alcool = teor_alcool;
	}

	/**
	 * Construtor da classe cerveja passando o nome, estilo e teor alcoolico como
	 * parametros. Utilizado para inclusao da cerveja no banco de dados. O parametro
	 * id eh recebido apenas no banco de dados.
	 * 
	 * @param nm_cerv
	 * @param nm_estilo
	 * @param teor_alcool
	 */
	public Cerveja(String nm_cerv, String nm_estilo, double teor_alcool) {
		this.nm_cerv = nm_cerv;
		this.nm_estilo = nm_estilo;
		this.teor_alcool = teor_alcool;
	}

	/**
	 * Construtor vazio do objeto cerveja.
	 */
	public Cerveja() {

	}

	/**
	 * Metodo que recebe o id da cerveja
	 * 
	 * @return the id_cerv
	 */
	public int getId_cerv() {
		return id_cerv;
	}

	/**
	 * Metodo que seta o valor do id da cerveja
	 * 
	 * @param id_cerv the id_cerv to set
	 */
	public void setId_cerv(int id_cerv) {
		this.id_cerv = id_cerv;
	}

	/**
	 * Metodo que recebe o nome da cerveja
	 * 
	 * @return the nm_cerv
	 */
	public String getNm_cerv() {
		return nm_cerv;
	}

	/**
	 * Metodo que seta o valor do nome da cerveja
	 * 
	 * @param nm_cerv the nm_cerv to set
	 */
	public void setNm_cerv(String nm_cerv) {
		this.nm_cerv = nm_cerv;
	}

	/**
	 * Metodo que recebe o estilo da cerveja
	 * 
	 * @return the nm_estilo
	 */
	public String getNm_estilo() {
		return nm_estilo;
	}

	/**
	 * Metodo que seta o valor do estilo da cerveja
	 * 
	 * @param nm_estilo the nm_estilo to set
	 */
	public void setNm_estilo(String nm_estilo) {
		this.nm_estilo = nm_estilo;
	}

	/**
	 * Metodo que recebe o teor alcoolico da cerveja
	 * 
	 * @return the teor_alcool
	 */
	public double getTeor_alcool() {
		return teor_alcool;
	}

	/**
	 * Metodo que seta o valor do teor alcoolico da cerveja
	 * 
	 * @param teor_alcool the teor_alcool to set
	 */
	public void setTeor_alcool(double teor_alcool) {
		this.teor_alcool = teor_alcool;
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
