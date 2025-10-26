package com.leolm.cursojunit5;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Calculadora calculadora = new Calculadora();
        int resultado = calculadora.soma(2, 3);
        System.out.println(resultado);
        System.out.println(calculadora.soma(3, 4));
        System.out.println(calculadora.soma(5, 6));

    }
}