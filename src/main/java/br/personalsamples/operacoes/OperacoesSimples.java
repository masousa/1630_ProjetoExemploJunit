package br.personalsamples.operacoes;

import br.personalsamples.dominio.Conta;
import br.personalsamples.dominio.Operacao;
import br.personalsamples.excecoes.ContaInvalidaException;
import br.personalsamples.servico.ContaService;
import br.personalsamples.servico.MovimentacoesService;
import br.personalsamples.validadores.SaldoInsuficienteException;

import java.math.BigDecimal;

public class OperacoesSimples {

	private final ContaService contaService;
	private final MovimentacoesService movimentacoesService;

	public OperacoesSimples(ContaService contaService, MovimentacoesService movimentacoesService){
		this.contaService= contaService;
		this.movimentacoesService = movimentacoesService;
	}

	public BigDecimal sacar(BigDecimal valor, Conta conta){

		if(contaService.isValidAccount(conta)) {
			validarSaldoSaque(valor, conta);
			BigDecimal saldo = conta.getSaldo().subtract(valor);
			conta.setSaldo(saldo);
			movimentacoesService.cadastrarOperacao(conta, Operacao.SAQUE, valor);
			return saldo;
		}else{
			throw new ContaInvalidaException(conta);
		}
	}

	public BigDecimal depositar(BigDecimal valor, Conta conta){

		if(contaService.isValidAccount(conta)) {
			BigDecimal saldo = conta.getSaldo().add(valor);
			conta.setSaldo(saldo);
			movimentacoesService.cadastrarOperacao(conta, Operacao.DEPOSITO, valor);
			return saldo;
		}else{
			throw new ContaInvalidaException(conta);
		}
	}


	private static void validarSaldoSaque(BigDecimal valor, Conta conta) {
		if(conta.getSaldo().compareTo(valor)<0){
			throw new SaldoInsuficienteException();
		}
	}
}
