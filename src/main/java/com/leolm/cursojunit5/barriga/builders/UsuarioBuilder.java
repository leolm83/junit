package com.leolm.cursojunit5.barriga.builders;

import com.leolm.cursojunit5.barriga.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioBuilder {

    private Long id;
    private String nome;
    private String email;
    private String senha;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder umUsuario() {
        UsuarioBuilder builder = new UsuarioBuilder();
        // inicializarDadosValidosPadroes(builder);
        return builder;
    }

    public UsuarioBuilder valido() {
        this.id = 123L;
        this.nome = "Leoncio";
        this.email = "leoncio@gmail.com";
        this.senha = "123456789012";
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nome, email, senha);
    }

    public UsuarioBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder withName(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder withSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
