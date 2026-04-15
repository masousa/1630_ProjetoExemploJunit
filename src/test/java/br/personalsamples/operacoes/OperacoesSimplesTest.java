package br.personalsamples.operacoes;

import br.personalsamples.dominio.Conta;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperacoesSimplesTest {

	private OperacoesSimples operacoesSimples;

	@Test
	void shouldSubtractTheMoney(){
		operacoesSimples = new OperacoesSimples();
		Conta conta = new Conta();
		conta.setSaldo(BigDecimal.valueOf(40.40));
		BigDecimal resultado = operacoesSimples.sacar(BigDecimal.valueOf(40.40), conta);

		assertEquals(BigDecimal.valueOf(0.0), resultado);
	}
}
