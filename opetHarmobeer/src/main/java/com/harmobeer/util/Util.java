package com.harmobeer.util;

//import java.util.Properties;

//xmlns:p="http://primefaces.org/ui" - Primefaces para colocar no html da página ou no ui:decorate do template

//Dependecia para JavaMail
/*<!-- https://mvnrepository.com/artifact/javax.mail/mail -->
	<dependency>
		<groupId>javax.mail</groupId>
		<artifactId>mail</artifactId>
		<version>1.4</version>
	</dependency>
*/

//Dependencia para Hibernate
/*<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-core</artifactId>
		<version>4.3.4.Final</version>
	</dependency>
 */

//Repositório Oracle (Não tenho certeza) para não precisar do jar
/*<repository>
	<id>codelds</id>
	<url>https://code.lds.org/nexus/content/groups/main-repo</url>
</repository>
*/

/**
 * Meus imports @SessionScoped - javax.enterprise.context.SessionScoped;
 * 				@Named  -        import javax.inject.Named;
 */
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

/**
 * Classe responsavel pelas utilidades do Sistema HarmoBeer
 * 
 * @author henrique.leite
 *
 */
public class Util {

	/**
	 * Colocar objeto na sessão, caso queira recuperar em outra classe ou bean.
	 * 
	 * @param nome
	 * @param objeto
	 */
	public static void setSessionParameter(String nome, Object objeto) {
		/* Ex.: Util.setSessionParameter("usuarioLogado", usuario) */
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(nome, objeto);
	}

	/**
	 * Recuperar o objeto na sessão.
	 * 
	 * @param nome
	 * @return
	 */
	public static Object getSessionParameter(String nome) {
		/* Ex.: Usuario usuario = Util.getSessioParameter("usuarioLogado") */
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(nome);
	}

	/* Remover objeto da sessão */
	public static void removeSessionParameter(String nome) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(nome);
	}

	/**
	 * Mensagem de informação
	 * 
	 * @param mensagem
	 */
	public static void mensagemInfo(String mensagem) {
		/* Ex.: Util.mensagemInfo("Operação realizada com sucesso") */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

	/**
	 * Mensagem de aviso/erro
	 * 
	 * @param mensagem
	 */
	
	public static void mensagemErro(String mensagem) {
		/* Ex.: Util.mensagemErro("Não foi possivel realizar a operação") */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
	}
	
	/**
	 * Mensagem de aviso/erro com especificaçao de clientID
	 * 
	 * @param clientID
	 * @param mensagem
	 */
	public static void mensagemErro(String clientID, String mensagem) {
		/* Ex.: Util.mensagemErro("Não foi possivel realizar a operação") */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(clientID, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, ""));
	}

	/*
	 * Método extra caso queira enviar um email simples :D
	 * 
	 * @param oferta
	 * @param usuario
	 
	public static void email( Seus parametros ) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Email", "senha");  Email e senha do remetente 
			}
		});
		session.setDebug(true);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Remetente(s)")); // Remetente

			Address[] toUser = InternetAddress.parse("Destinátario");

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Assunto");
			message.setText("Texto");
			Transport.send(message);

			System.out.println("Email enviado com sucesso ==============================================!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}*/

}
