package br.personalsamples.operacoes;

import br.personalsamples.dominio.Conta;
import br.personalsamples.dominio.Operacao;
import br.personalsamples.excecoes.ContaInvalidaException;
import br.personalsamples.servico.ContaService;
import br.personalsamples.servico.MovimentacoesService;
import br.personalsamples.validadores.SaldoInsuficienteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OperacoesSimplesTest {

	private OperacoesSimples operacoesSimples;
	private ContaService contaService;
	private MovimentacoesService movimentacoesService;

	@BeforeEach
	void setup(){
		contaService = mock(ContaService.class);
		movimentacoesService = mock(MovimentacoesService.class);
		operacoesSimples = new OperacoesSimples(contaService, movimentacoesService);
		lenient().when(contaService.isValidAccount(ArgumentMatchers.any(Conta.class)))
				.thenReturn(true);
	}

	@Test
	void shouldWithdrawWithNoErrors(){

		Conta conta = mock(Conta.class);
		when(conta.getSaldo()).thenReturn(BigDecimal.valueOf(40.40));

		BigDecimal resultado = operacoesSimples.sacar(BigDecimal.valueOf(40.40), conta);

		assertEquals(BigDecimal.valueOf(0.0), resultado);

		verify(movimentacoesService, times(1))
				.cadastrarOperacao(any(Conta.class), eq(Operacao.SAQUE), eq(BigDecimal.valueOf(40.40)));
	}

	@DisplayName("Should throw an error when there is no budget in the withdraw feature")
	@Test
	void shouldThrowAnErrorNoBudgetToWithdraw(){
		Conta conta = new Conta();
		conta.setSaldo(BigDecimal.valueOf(40));
		Assertions.assertThrows(SaldoInsuficienteException.class,
				()-> operacoesSimples.sacar(BigDecimal.valueOf(50), conta));
	}

	@DisplayName("Should throw an error when the customer account is invalid")
	@Test
	void shouldThrowAnErrorWhenClientIsInvalid(){
		Conta conta = new Conta();
		when(contaService.isValidAccount(ArgumentMatchers.any(Conta.class)))
				.thenReturn(false);
		assertThrows(ContaInvalidaException.class,
				()->operacoesSimples.sacar(BigDecimal.valueOf(100), conta));
	}

	@DisplayName("Should make a deposit with no errors")
	@Test
	void shouldMakeDepositNoErrors(){
		Conta conta = new Conta();
		conta.setSaldo(BigDecimal.TEN);
		BigDecimal resultado = operacoesSimples.depositar(BigDecimal.TEN, conta);
		assertEquals(BigDecimal.valueOf(20), resultado);
		verify(movimentacoesService, times(1))
				.cadastrarOperacao(conta, Operacao.DEPOSITO, BigDecimal.TEN);
		assertEquals(BigDecimal.valueOf(20), conta.getSaldo());
	}
}
