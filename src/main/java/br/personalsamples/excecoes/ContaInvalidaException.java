package br.personalsamples.excecoes;

import br.personalsamples.dominio.Conta;

public class ContaInvalidaException extends RuntimeException {
	public ContaInvalidaException(Conta conta) {
		super("Conta invalida");
	}
}
