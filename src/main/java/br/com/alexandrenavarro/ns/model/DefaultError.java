package br.com.alexandrenavarro.ns.model;

import java.io.Serializable;

public class DefaultError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
