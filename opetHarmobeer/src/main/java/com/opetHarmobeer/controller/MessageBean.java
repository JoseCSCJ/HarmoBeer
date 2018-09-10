package com.opetHarmobeer.controller;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "messageBean")
@SessionScoped

public class MessageBean implements Serializable {

	private static final long serialVersionUID = -6767049708779239551L;
	private String message;
	private String nome;

	public MessageBean() {

// TODO Auto-generated constructor stub

	}

	public String getMessage() {

		Date date = new Date();

		message = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date);

		return message;

	}
	
	public String getNome() {
		nome="Jose";
		return nome;
	}

}