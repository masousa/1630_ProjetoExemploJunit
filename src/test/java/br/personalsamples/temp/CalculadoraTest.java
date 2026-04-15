package br.personalsamples.temp;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

	private Calculadora calculadora;


	@BeforeEach
	void setup(){
		calculadora = new Calculadora();
		System.out.println("setup chamado");
	}

	@AfterEach
	void finish(){
		System.out.println("finish chamado");
	}

	@Test
	void shouldSumTheNumbersWithNoErrors(){

		int resultado = calculadora.soma(3,4);

		assertEquals(7, resultado);
	}

	@Test
	 void shouldThrowAnErrorWhenANumberIsDividedByZero(){
		ArithmeticException exception =  Assertions.assertThrows(ArithmeticException.class,
				()-> calculadora.divisao(50,0));
		assertTrue(exception.getMessage().contains("by zero"));
	}

	@Test
	void shouldThrownAnErrorWhenSumWithTheMaxIntegerValue(){
		assertFalse(calculadora.soma(50, Integer.MAX_VALUE) > 0);
	}
}
