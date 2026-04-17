package br.personalsamples.validadores;

import br.personalsamples.dominio.Conta;
import br.personalsamples.excecoes.SaldoInvalidoException;

import java.math.BigDecimal;

public class ValidaSaldo {

	public static void validate(Conta conta, BigDecimal valor){
		if(conta.getSaldo().compareTo(valor)<0){
			throw new SaldoInsuficienteException();
		}
	}
}
