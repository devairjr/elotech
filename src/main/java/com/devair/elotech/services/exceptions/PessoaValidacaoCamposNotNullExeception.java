package com.devair.elotech.services.exceptions;

public class PessoaValidacaoCamposNotNullExeception  extends RuntimeException {

	private static final long serialVersionUID = 1869300553614629711L;
	
	public PessoaValidacaoCamposNotNullExeception(String mensagem) {
		super(mensagem);
	}

}
