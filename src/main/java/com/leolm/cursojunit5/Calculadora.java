package com.leolm.cursojunit5;

public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public float dividir(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("/ by zero");
        }
        return (float) a / (float) b;
    }
}
