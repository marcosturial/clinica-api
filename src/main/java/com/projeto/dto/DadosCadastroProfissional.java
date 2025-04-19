
package com.projeto.dto;

public class DadosCadastroProfissional {
    private String nome;
    private String especialidade;
    private Integer numeroConselho;
    private String ufConselho;
    private String email;

    public DadosCadastroProfissional(String nome, String especialidade, Integer numeroConselho, String ufConselho, String email) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.numeroConselho = numeroConselho;
        this.ufConselho = ufConselho;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public Integer getNumeroConselho() {
        return numeroConselho;
    }

    public String getUfConselho() {
        return ufConselho;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "DadosCadastroProfissional{" +
                "nome='" + nome + '\'' +
                ", especialidade=" + especialidade +
                '}';
    }
}
