package com.leolm.cursojunit5.barriga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.leolm.cursojunit5.barriga.builders.UsuarioBuilder;
import com.leolm.cursojunit5.barriga.validations.ViolationHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

@DisplayName("Dominio : Usuário")
public class UsuarioTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName(value = "Deve criar um usuário valido")
    void deveCriarUmUsuarioValido() {
        Usuario usuario = UsuarioBuilder.umUsuario().valido().build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        assertTrue(violations.isEmpty());
    }

    @Test
    @DisplayName(value = "Deve criar um usuário valido com os valores iniciais")
    void deveCriarUmUsuarioValidoComValoresInicias() {

        Long id = 123L;
        String nome = "Leoncio";
        String email = "leoncio@gmail.com";
        String senha = "123456789012";
        Usuario usuario = UsuarioBuilder.umUsuario().valido().build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);

        Assertions.assertAll("Usuario com valores iniciais",
                () -> assertEquals(id, usuario.getId()),
                () -> assertEquals(nome, usuario.getNome()),
                () -> assertEquals(email, usuario.getEmail()),
                () -> assertEquals(senha, usuario.getSenha()),
                () -> assertTrue(violations.isEmpty()));
    }

    @Test
    public void deveRejeitarUsuarioSemNome() {
        String nomeCampoEmTeste = "nome";
        Usuario usuario = UsuarioBuilder
                .umUsuario()
                .valido()
                .withName("")
                .build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        boolean campoEstaInvalido = ViolationHandler.fieldHasViolations(nomeCampoEmTeste, violations);
        Assertions.assertTrue(!violations.isEmpty() && campoEstaInvalido);
    }

    @Test
    public void deveRejeitarUsuarioSemEmail() {
        String nomeCampoEmTeste = "email";
        Usuario usuario = UsuarioBuilder
                .umUsuario()
                .valido()
                .withEmail("")
                .build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        boolean campoEstaInvalido = ViolationHandler.fieldHasViolations(nomeCampoEmTeste, violations);
        Assertions.assertTrue(!violations.isEmpty() && campoEstaInvalido);
    }

    @Test
    public void deveRejeitarUsuarioSemSenha() {
        String nomeCampoEmTeste = "senha";
        Usuario usuario = UsuarioBuilder
                .umUsuario()
                .valido()
                .withSenha("")
                .build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        boolean campoEstaInvalido = ViolationHandler.fieldHasViolations(nomeCampoEmTeste, violations);
        Assertions.assertTrue(!violations.isEmpty() && campoEstaInvalido);
    }

}
