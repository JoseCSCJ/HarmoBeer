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
 * Classe que modela o objeto Usuario
 *
 * @author Jos� Carlos Soares da Cruz Junior 
 */

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	@SequenceGenerator(name = "sequence_generator", sequenceName = "sequsuario")
	@Column(name = "id_user")
	private int id_user;
	
	@Column (name = "username")
	private String username;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "senha")
	private String senha;
	
	@Column (name = "privilegio")
	private int privilegio;
	
	@Column (name = "info")
	private String info;
	
	@OneToMany(mappedBy="user", targetEntity=Avaliacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Avaliacao> avaliacoes;

	public Usuario() {

	}
	/**
	 * @param id_user
	 * @param username
	 * @param email
	 * @param senha
	 * @param privilegio
	 * @param info
	 */
	public Usuario(int id_user, String username, String email, String senha, int privilegio, String info) {
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.privilegio = privilegio;
		this.info = info;
	}

	/**
	 * @param username
	 * @param email
	 * @param senha
	 * @param info
	 */
	public Usuario(String username, String email, String senha, String info) {
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.info = info;
	}

	/**
	 * @param username
	 * @param senha
	 */
	public Usuario(String username, String senha) {
		this.username = username;
		this.senha = senha;

	}

	/**
	 * @param username
	 * @param email
	 * @param senha
	 * @param privilegio
	 * @param info
	 */
	public Usuario(String username, String email, String senha, int privilegio, String info) {
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.privilegio = privilegio;
		this.info = info;
	}

	/**
	 * @param id_user
	 * @param username
	 * @param email
	 * @param senha
	 * @param info
	 */
	public Usuario(int id_user, String username, String email, String senha, String info) {
		this.id_user = id_user;
		this.username = username;
		this.email = email;
		this.senha = senha;
		this.info = info;
	}

	/**
	 * @param id_user
	 */
	public Usuario(int id_user) {
		this.id_user = id_user;

	}
	

	/**
	 * @return the id_user
	 */
	public int getId_user() {
		return id_user;
	}

	/**
	 * @param id_user
	 *            the id_user to set
	 */
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the privilegio
	 */
	public int getPrivilegio() {
		return privilegio;
	}

	/**
	 * @param privilegio
	 *            the privilegio to set
	 */
	public void setPrivilegio(int privilegio) {
		this.privilegio = privilegio;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
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
