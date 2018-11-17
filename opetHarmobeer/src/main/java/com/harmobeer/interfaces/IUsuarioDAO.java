/**
 *
 */
package com.harmobeer.interfaces;


import java.util.List;

import com.harmobeer.vo.Usuario;

/**
 * Interface responsavel pelos DAO da classe Usuario
 *
 * @author Jose Carlos Soares da Cruz Junior 
 *
 */
public interface IUsuarioDAO {
	public boolean incluir(Usuario usuario);

	public boolean editar(Usuario usuario);

	public boolean deletar(Usuario usuario);

	public Usuario logar (String username, String senha) throws Exception;

	public boolean verificarPrivilegio(Usuario usuario);
	
	public Usuario selecionarUser(int id_user) throws Exception;
	
	public List<Usuario> listarTodos() throws Exception;
}