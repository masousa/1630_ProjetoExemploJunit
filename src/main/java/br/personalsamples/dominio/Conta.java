package br.personalsamples.dominio;

import java.math.BigDecimal;

public class Conta {
	private Cliente cliente;
	private Long numeroConta;
	private BigDecimal saldo;
	private StatusConta statusConta;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isAtiva() {
		return statusConta == StatusConta.ATIVA;
	}

	public void setStatusConta(StatusConta statusConta) {
		this.statusConta = statusConta;
	}
}