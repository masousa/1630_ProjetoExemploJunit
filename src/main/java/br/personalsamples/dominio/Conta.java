package br.personalsamples.dominio;

import java.math.BigDecimal;

public class Conta {
	private Cliente cliente;
	private Long numeroConta;
	private BigDecimal saldo;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}