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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que modela o objeto Harmonizacao
 *
 * @author Jose Carlos Soares da Cruz Junior
 *
 */

@Entity
@Table(name = "Harmonizacao")
public class Harmonizacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "seqharmo")
	@Column(name = "id_harmo")
	private int id_harmo;

	@ManyToOne
	@JoinColumn(name = "id_cerv")
	private Cerveja cerveja;

	@ManyToOne
	@JoinColumn(name = "id_prato")
	private Prato prato;
	
	@OneToMany(mappedBy="harmonizacao", targetEntity=Avaliacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes;

	@Column(name = "media")
	private double media;


	/**
	 * Construtor sem o id_harmo do objeto Harmonizacao. Utilizado para a inclus�o
	 * do objeto no banco de dados.
	 * 
	 * @param Cerveja
	 * @param Prato
	 * @param media
	 *
	 */

	public Harmonizacao(Cerveja cerveja, Prato prato, double media) {
		this.cerveja = cerveja;
		this.prato = prato;
		this.media = media;
	}

	/**
	 * Metodo construtor vazio para gerar objeto nulo
	 * 
	 * @param
	 */
	public Harmonizacao() {

	}

	/**
	 * @return the id_harmo
	 */
	public int getId_harmo() {
		return id_harmo;
	}

	/**
	 * @param id_harmo the id_harmo to set
	 */
	public void setId_harmo(int id_harmo) {
		this.id_harmo = id_harmo;
	}



	/**
	 * @return the media
	 */
	public double getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(double media) {
		this.media = media;
	}

	/**
	 * @return the cerveja
	 */	
	public Cerveja getCerveja() {
		return cerveja;
	}

	/**
	 * @param cerveja the cerveja to set
	 */
	public void setCerveja(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	/**
	 * @return the prato
	 */
	@ManyToOne
	@JoinColumn(name = "id_prato")
	public Prato getPrato() {
		return prato;
	}

	/**
	 * @param prato the prato to set
	 */
	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	/**
	 * @return the avaliacoes
	 */
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	/**
	 * @param avaliacoes the avaliacoes to set
	 */
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
