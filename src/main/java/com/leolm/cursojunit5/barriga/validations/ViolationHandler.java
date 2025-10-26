package com.leolm.cursojunit5.barriga.validations;

import java.util.Objects;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class ViolationHandler {
    public static <T> void showViolations(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            System.out.println("EXIBINDO VALIDACOES QUE FALHARAM");
            for (ConstraintViolation<T> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            System.out.println("FIM DOS DADOS INVALIDOS");
        }
    }

    public static <T> boolean fieldHasViolations(String fieldName, Set<ConstraintViolation<T>> violations) {

        if (Objects.isNull(fieldName)) {
            throw new IllegalArgumentException("field name cannot be null");
        }

        for (ConstraintViolation<T> violation : violations) {
            System.out.println("O seguinte campo está invalido : " + violation.getPropertyPath());
            if (fieldName.equalsIgnoreCase(violation.getPropertyPath().toString())) {
                return true;
            }
        }
        return false;
    }
}
