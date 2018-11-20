package com.harmobeer.util;

import java.util.ArrayList;

import com.harmobeer.mvc.controller.AvaliacaoController;
import com.harmobeer.mvc.controller.CervejaController;
import com.harmobeer.mvc.controller.HarmonizacaoController;
import com.harmobeer.mvc.controller.PratoController;
import com.harmobeer.mvc.controller.UsuarioController;
import com.harmobeer.vo.Avaliacao;
import com.harmobeer.vo.Cerveja;
import com.harmobeer.vo.Harmonizacao;
import com.harmobeer.vo.Prato;
import com.harmobeer.vo.Usuario;

public class PopulaBanco {
	
	public PopulaBanco() {
		
	}

	/**
	 * 
	 */
	public void popularBanco() {

		UsuarioController usuarioController = new UsuarioController();
		AvaliacaoController avaliacaoController = new AvaliacaoController();
		HarmonizacaoController harmonizacaoController = new HarmonizacaoController();
		CervejaController cervejaController = new CervejaController();
		PratoController pratoController = new PratoController();

		ArrayList<String> nome = new ArrayList<String>();
		ArrayList<String> comentarioBom = new ArrayList<String>();
		ArrayList<String> comentarioRegular = new ArrayList<String>();
		ArrayList<String> comentarioRuim = new ArrayList<String>();
		ArrayList<String> info = new ArrayList<String>();

		nome = popularNome1();
		comentarioBom = popularComentarioBom();
		comentarioRegular = popularComentarioRegular();
		comentarioRuim = popularComentarioRuim();
		info = popularInfo();

		for (int b = 0; b < 400; b++) {
			try {
				Usuario u = new Usuario();
				int rand1 = (int) (Math.random()*44);
				int rand2 = (int) (Math.random()*44);
				int rand3 = (int) (Math.random()*19);
				int rand4 = (int) (Math.random()*19);
				int rand5 = (int) (Math.random()*19);
				u.setUsername(nome.get(rand1) + " " + nome.get(rand2));
				u.setEmail(nome.get(rand1) + rand4 + rand3+ "@gmail.com");
				u.setSenha(nome.get(rand2) + rand1 + rand5);
				u.setInfo("Gosto de " + info.get(rand3) + ", " + info.get(rand4) + " e " + info.get(rand5));
				u.setPrivilegio(0);
				usuarioController.incluir(u);
				
				System.out.println("Incluido " + u.getUsername());
			} catch (Exception e) {
				System.out.println("num deu pra adicionar esse usuario...");
			}
		}

		for (int a = 0; a < 500; a++) {
			try{Avaliacao aval = new Avaliacao();
			int rand1 = (int) (Math.random() * 10 + 1); // nota
			int rand2 = (int) (Math.random() * 44); // comentario
			int rand3 = (int) (Math.random() * 400); // usuario
			int rand4 = (int) (Math.random() * 27); // prato
			int rand5 = (int) (Math.random() * 23); // cerveja

			Cerveja c = new Cerveja();
			Prato p = new Prato();
			Harmonizacao h = new Harmonizacao();
			Usuario u = new Usuario();
			
			u = usuarioController.selecionarUser(rand3);
			c = cervejaController.selecionarCerv(rand5);
			p = pratoController.selecionarPrato(rand4);
			harmonizacaoController.incluirHarmonizacao(c, p);
			h = harmonizacaoController.selecionarHarmo(harmonizacaoController.selecionaridHarmonizacao(c, p));
			
			aval.setHarmonizacao(h);
			aval.setNota(rand1);
			if(aval.getNota()>=8) {
				aval.setComentario(comentarioBom.get(rand2));				
			}
			if(aval.getNota()<8 && aval.getNota()>4) {
				aval.setComentario(comentarioRegular.get(rand2));				
			}
			if(aval.getNota()<=4) {
				aval.setComentario(comentarioRuim.get(rand2));
			}
			aval.setUser(u);
			avaliacaoController.incluirAvaliacao(aval);
			System.out.println("Incluida avaliacao de... "+ aval.getUser().getUsername());
			
			}catch (Exception e) {
				System.out.println("Num deu para adicionar essa avaliacao...");
				
			}
		}

	}

	public static ArrayList<String> popularNome1() {
		ArrayList<String> h = new ArrayList<String>();

		h.add("Maria");
		h.add("Valentina");
		h.add("Enzo");
		h.add("Henrique");
		h.add("Luan");
		h.add("Tiago");
		h.add("Victor");
		h.add("Vitor");
		h.add("Thiago");
		h.add("Alberto");
		h.add("Monica");
		h.add("Pedro");
		h.add("Paulo");
		h.add("Leon");
		h.add("Leonardo");
		h.add("Vania");
		h.add("Veruska");
		h.add("Veronica");
		h.add("Janio");
		h.add("Julio");
		h.add("Olga");
		h.add("Helio");
		h.add("Heleno");
		h.add("Helena");
		h.add("Hellen");
		h.add("Ellen");
		h.add("Joe");
		h.add("John");
		h.add("Pietro");
		h.add("Valadares");
		h.add("Vital");
		h.add("Nando");
		h.add("Fernando");
		h.add("Fernanda");
		h.add("Camilo");
		h.add("Camila");
		h.add("Orlando");
		h.add("Francis");
		h.add("Sergio");
		h.add("William");
		h.add("Daniela");
		h.add("Imaculada");
		h.add("Willian");
		h.add("Daniel");
		h.add("Imaculado");

		return h;

	}

	public static ArrayList<String> popularComentarioBom() {
		ArrayList<String> h = new ArrayList<String>();

		h.add("Muito bom!");
		h.add("Ótimo!");
		h.add("Recomendo essa combinação para todo mundo.");
		h.add("Merece muitos aplausos!");
		h.add("Causou uma explosão de sabor!");
		h.add("Repetiria 10 vezes se não fosse engordar");
		h.add("Quase passei vergonha no restaurante!");
		h.add("Recomendo demais essa cerveja");
		h.add("Parabéns para quem teve essa idéia");
		h.add("Faço isso toda sexta-feira");
		h.add("Minha família inteira adorou");
		h.add("Ideal para dividir com os amigos");
		h.add("Comida com cerveja geladinha não tem como dar errado...");
		h.add("Melhor que isso só ganhar na loteria");

		return h;
	}

	public static ArrayList<String> popularComentarioRegular() {
		ArrayList<String> h = new ArrayList<String>();

		h.add("Mais ou menos");
		h.add("Regular...");
		h.add("Eu merecia bem mais do que isso");
		h.add("Faltou sal na comida, sobrou levedura na cerveja");
		h.add("Amarga demais!");
		h.add("Quase deu certo, só faltou acertar");
		h.add("Já vi combinações bem melhores");
		h.add("Sugiro que troque essa cerveja por uma mais lupulada");
		h.add("Faltou gaseificação na bebida");
		h.add("Na próxima vez melhor colocar queijo para acompanhar");
		h.add("Daria mais nota se a cerveja não fosse tão ruim");
		h.add("A cerveja tava boa! O prato nem tanto...");
		h.add("Minha comida tava maravilhosa... Estraguei com essa bebida");
		h.add("Não nasci para comer mal. Não recomendo.");

		return h;
	}

	public static ArrayList<String> popularComentarioRuim() {
		ArrayList<String> h = new ArrayList<String>();

		h.add("Nunca mais volto naquele restaurante");
		h.add("Tão bom quanto o resultado no banheiro");
		h.add("Se vomitar fosse bom, dava 10.");
		h.add("Gosto de podre");
		h.add("Muito amarga, até demais");
		h.add("Se desse pra votar negativo, essa seria -10");
		h.add("Explodiu minha mente! de tão ruim");
		h.add("Não ornou. De forma alguma");
		h.add("Maravilhoso! só que não");
		h.add("Não gosto de cerveja...");
		h.add("Daria mais nota se a cerveja não fosse tão bosta ");
		h.add("A cerveja tava ruim! O prato também!");
		h.add("Bebida dos deuses! só se for dos deuses lá debaixo");
		h.add("Não nasci para comer mal e nem pra beber mal.");

		return h;
	}

	public static ArrayList<String> popularInfo() {
		ArrayList<String> h = new ArrayList<String>();

		h.add("Netflix");
		h.add("IPA");
		h.add("Pilsen");
		h.add("APA");
		h.add("bar");
		h.add("baladas");
		h.add("amigos");
		h.add("séries");
		h.add("livros");
		h.add("dançar a macarena");
		h.add("esportes");
		h.add("pescar");
		h.add("teatro");
		h.add("cinema");
		h.add("games");
		h.add("programação");
		h.add("arte");
		h.add("história");
		h.add("matemática");
		h.add("vinho");

		return h;
	}
}
