/**
 * 
 */
package com.harmobeer.main;

import com.harmobeer.mvc.view.CervejaView;

/**
 * Classe responsável por carregar o menu, puxando diretamente da View 
 * @author José Carlos Soares da Cruz Junior 
 * 
 *
 */
public class Main {

	/**
	 * Método para invocar a View da classe Cerveja
	 * @param args
	 */
	public static void main(String[] args) {
		CervejaView cv = new CervejaView();
		cv.menu();
		

	}

}
