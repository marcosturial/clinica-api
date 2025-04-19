package com.projeto.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public enum Especialidade {
    ORTO("Ortopedia"),
    OTORRINO("Otorrinolaringologia"),
    OFTALMO("Oftalmologia"),
    CARDIO("Cardiologia"),
    NEFRO("Nefroologia"),
    NEURO("Neurologia"),
    ;

    private static final Logger log = LoggerFactory.getLogger(Especialidade.class);
    private final String nome;

    Especialidade(String nome) {
        this.nome = nome;
    }


    public static Especialidade getEnumByNome(String nome) {
        for (Especialidade e : Especialidade.values()) {
            if (e.nome.toLowerCase().contains(nome.toLowerCase())) {
                return e;
            }
        }
        return null;
    }

    public static List<String> getEspecialidades() {
        List<String> listaEspecialidades = new ArrayList<>();
        for (Especialidade e : Especialidade.values()) {
            listaEspecialidades.add(e.getNome());
        }
        return listaEspecialidades;
    }

    public String getNome() {
        return nome;
    }
}
