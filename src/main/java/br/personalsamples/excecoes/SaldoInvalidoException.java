package br.personalsamples.excecoes;

public class SaldoInvalidoException extends RuntimeException
{
	public SaldoInvalidoException(){
		super("Saldo invalido");
	}
}
