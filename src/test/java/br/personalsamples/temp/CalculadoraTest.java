package br.personalsamples.temp;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

	@Test
	void shouldMultiplyWithNoErrors(){
		assertEquals(40, calculadora.multiplicacao(5,8));
		assertEquals(-24, calculadora.multiplicacao(-3, 8));
	}
	@ParameterizedTest
	@MethodSource(value="streamNumbers")
	void shouldMultiplyBunchOfNumbersWithNoErrors(int expected, int number1, int number2){
		assertEquals(expected, calculadora.multiplicacao(number1, number2));
	}
	private static Stream<Arguments> streamNumbers(){
		return Stream.of(
				Arguments.of(40,5,8),
				Arguments.of(-24,-3,8)
		);
	}

}
