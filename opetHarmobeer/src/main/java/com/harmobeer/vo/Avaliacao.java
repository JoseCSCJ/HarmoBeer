/**
 * 
 */
package com.harmobeer.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que modela o objeto Avaliacao
 * 
 * @author Jose Carlos Soares da Cruz Junior
 */

@Entity
@Table(name = "Avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "seqaval")
	@Column(name = "id_aval")
	private int id_aval;

	@ManyToOne
	@JoinColumn(name = "id_harmo")
	private Harmonizacao harmonizacao;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario user;

	@Column(name = "nota")
	private int nota;

	@Column(name = "comentario")
	private String comentario;

	/**
	 * @param id_aval
	 * 
	 */
	public Avaliacao(int id_aval) {
		this.id_aval = id_aval;

	}
	
	/**
	 * @param id_aval
	 * @param harmonizacao
	 * @param user
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao (int id_aval, Harmonizacao harmonizacao, Usuario user, int nota, String comentario) {
		this.id_aval=id_aval;
		this.harmonizacao=harmonizacao;
		this.user=user;
		this.nota=nota;
		this.comentario=comentario;
	}

	/**
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao(int nota, String comentario) {
		this.nota = nota;
		this.comentario = comentario;
	}

	/**
	 * Construtor vazio para avaliacao
	 */
	public Avaliacao() {

	}

	/**
	 * @param harmonizacao
	 * @param user
	 * @param nota
	 * @param comentario
	 */
	public Avaliacao(Harmonizacao harmonizacao, Usuario user, int nota, String comentario) {		
		this.harmonizacao=harmonizacao;
		this.user=user;
		this.nota=nota;
		this.comentario=comentario;
	}

	/**
	 * @return the id_aval
	 */
	public int getId_aval() {
		return id_aval;
	}

	/**
	 * @param id_aval the id_aval to set
	 */
	public void setId_aval(int id_aval) {
		this.id_aval = id_aval;
	}

	/**
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the harmonizacao
	 */
	public Harmonizacao getHarmonizacao() {
		return harmonizacao;
	}

	/**
	 * @param harmonizacao the harmonizacao to set
	 */
	public void setHarmonizacao(Harmonizacao harmonizacao) {
		this.harmonizacao = harmonizacao;
	}

	/**
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

}
