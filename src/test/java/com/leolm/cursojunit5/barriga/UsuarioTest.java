package com.leolm.cursojunit5.barriga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

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

    // para nomear um campo no teste basta passar esse parametro name
    @ParameterizedTest(name = "[{index}] - valida campo : {3}")
    @CsvSource(value = {
            "NULL,'leoncio',123456789012,id", // sem id
            "2,NULL,123456789012,nome", // sem nome
            "3,'leoncio',NULL,senha", // sem senha
            "3,'leoncio',,senha" // sem senha porem utilizando o default null value que é vazio
    /*
     * Please note that unquoted empty values will always be converted to null
     * references regardless of the value of this nullValues attribute; whereas, a
     * quoted empty string will be treated as an emptyValue().
     * src:
     * https://stackoverflow.com/questions/65207427/junit-5-how-to-pass-in-multiple-
     * null-values-for-csvsource
     */
    }, nullValues = { "NULL" })
    public void deveRejeitarUsuarioSemSenha(Long id, String nome, String senha, String nomeCampoEmTeste) {
        Usuario usuario = UsuarioBuilder
                .umUsuario()
                .withId(id)
                .withName(nome)
                .withSenha(senha)
                .build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        boolean campoEstaInvalido = ViolationHandler.fieldHasViolations(nomeCampoEmTeste, violations);
        Assertions.assertTrue(!violations.isEmpty() && campoEstaInvalido);
    }

    // para nomear um campo no teste basta passar esse parametro name
    @ParameterizedTest(name = "[{index}] - valida campo : {3}")
    @CsvFileSource(files = { "src/test/resources/CamposObrigatoriosUsuario.csv" }, nullValues = {
            "NULL" }, useHeadersInDisplayName = true
    // numLinesToSkip = 1 // utilizado para pular linhas do csv
    )
    public void deveRejeitarUsuarioSemSenhaComValoresImportadosDoCSV(Long id, String nome, String senha,
            String nomeCampoEmTeste) {
        Usuario usuario = UsuarioBuilder
                .umUsuario()
                .withId(id)
                .withName(nome)
                .withSenha(senha)
                .build();

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);
        ViolationHandler.showViolations(violations);
        boolean campoEstaInvalido = ViolationHandler.fieldHasViolations(nomeCampoEmTeste, violations);
        Assertions.assertTrue(!violations.isEmpty() && campoEstaInvalido);
    }

}
