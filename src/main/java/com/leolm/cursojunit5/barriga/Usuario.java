package com.leolm.cursojunit5.barriga;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class Usuario {
    @NotNull
    @Positive
    private final Long id;
    @NotBlank
    @Size(min = 2, max = 255)
    private final String nome;
    @Email
    @NotBlank
    private final String email;
    @NotBlank
    @Size(min = 12, max = 50)
    private final String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

}
