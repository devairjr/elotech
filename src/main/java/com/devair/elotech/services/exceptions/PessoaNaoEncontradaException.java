package com.devair.elotech.services.exceptions;

public class PessoaNaoEncontradaException  extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629710L;
	
	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
