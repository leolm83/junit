package com.leolm.cursojunit5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculadoraTest {

    private Calculadora calculadora;

    private static int counter = 0;

    @BeforeEach
    public void setup() {
        counter += 1;
        System.out.println("^^^");
        calculadora = new Calculadora();

    }

    @AfterEach
    public void teardown() {
        System.out.println("###");
        calculadora = new Calculadora();

    }

    @BeforeAll
    public static void start() {
        System.out.println("---- STARTING TESTS ----");
    }

    @AfterAll
    public static void finish() {
        System.out.println(counter);
    }

    @Test
    public void testSomar_numeroPositivo() {
        int resultado = calculadora.soma(2, 3);
        Assertions.assertEquals(5, resultado);
    }

    @Test
    public void deveRetornarNumeroInteiroNaDivisao() {
        float resultado = calculadora.dividir(6, 2);
        Assertions.assertEquals(3, resultado);
    }

    @Test
    public void deveRetornarNumeroNegativoNaDivisao() {
        float resultado = calculadora.dividir(-6, 2);
        Assertions.assertEquals(-3, resultado);
    }

    @Test
    public void deveRetornarNumeroDecimalNaDivisao() {
        float resultado = calculadora.dividir(10, 3);
        float delta = 0.01f;
        Assertions.assertEquals(3.33, resultado, delta);
    }

    @Test
    public void deveRetornarNumeroComNoMaximoDiferencaDoDeltaNaDivisao() {
        float resultado = calculadora.dividir(10, 3);
        float delta = 0.01f;
        Assertions.assertNotEquals(3.35, resultado, delta);
    }

    @Test
    public void deveRetornarZeroComNumeradorZeroNaDivisao() {
        Calculadora calculadora = new Calculadora();
        float resultado = calculadora.dividir(0, 3);
        Assertions.assertEquals(0, resultado);
    }

    @Test
    public void deveDarExcecaoComDenominadorZeroNaDivisao() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculadora.dividir(10, 0));
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_Junit4() {
        try {
            float resultado = 10 / 0;
            System.out.println(resultado);
            Assertions.fail("Deveria ter sido lançado uma exceção na divisão");
        } catch (ArithmeticException e) {
            Assertions.assertEquals("/ by zero", e.getMessage());
        }
    }

    @Test
    public void deveLancarExcecaoQuandoDividirPorZero_Junit5() {

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            calculadora.dividir(10, 0);
        });
        Assertions.assertEquals("/ by zero", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "Teste1", "Teste2", "Teste3" })
    public void testStrings(String param) {
        System.out.println(param);
        Assertions.assertNotNull(param);
    }

    @ParameterizedTest
    @CsvSource(value = { "6,2,3", "6,-2,-3", "10,3,3.33", "0,2,0" })
    public void deveDividirCorretamente(int numerador, int denominador, float resultadoEsperado) {
        float delta = 0.01f;
        float resultado = calculadora.dividir(numerador, denominador);
        Assertions.assertEquals(resultadoEsperado, resultado, delta);
    }

}
