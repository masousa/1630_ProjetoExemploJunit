package br.personalsamples.operacoes;

import br.personalsamples.dominio.Conta;
import br.personalsamples.validadores.ValidaSaldo;

import java.math.BigDecimal;

public class OperacoesSimples {

	public BigDecimal sacar(BigDecimal valor, Conta conta){
		ValidaSaldo.validate(conta, valor);

		BigDecimal saldo = conta.getSaldo().subtract(valor);
		conta.setSaldo(saldo);
		return saldo;
	}
}
