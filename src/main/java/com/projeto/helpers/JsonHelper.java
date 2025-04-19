package com.projeto.helpers;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.projeto.dto.DadosCadastroProfissional;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    private JsonHelper() {}

    public static List<DadosCadastroProfissional> lerArquivoJson(Path caminhoArquivo) throws IOException {
        List<DadosCadastroProfissional> listaProfissionais = new ArrayList<>();
        try (JsonReader reader = new JsonReader(new FileReader(caminhoArquivo.toFile()));) {
            reader.beginArray();
            while (reader.hasNext()) {
                listaProfissionais.add(lerJsonProfissional(reader));
            }
            reader.endArray();
            return listaProfissionais;
        }

    }

    private static DadosCadastroProfissional lerJsonProfissional(JsonReader reader) throws IOException {
        Integer numeroConselho = null;
        String nome = null;
        String especialidade = null;
        String ufConselho = null;
        String email = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String registro = reader.nextName();
            switch (registro) {
                case "nome" -> nome = reader.nextString();
                case "especialidade" -> especialidade = reader.nextString();
                case "numeroConselho" -> numeroConselho = reader.nextInt();
                case "ufConselho" -> ufConselho = reader.nextString();
                case "email" -> email = reader.nextString();
                default -> reader.skipValue();
            }
        }
        reader.endObject();
        return new DadosCadastroProfissional(nome, especialidade, numeroConselho, ufConselho, email);
    }

    public static void escreverArquivoJson(Path caminhoArquivo, List<DadosCadastroProfissional> listaProfissionais) throws IOException {
        try (JsonWriter writer = new JsonWriter(new FileWriter(caminhoArquivo.toFile()));) {
            writer.setIndent("  ");
            writer.beginArray();
            for (DadosCadastroProfissional profissional : listaProfissionais) {
                escreverProfissional(writer, profissional);
            }
            writer.endArray();
        }
    }


    public static void escreverProfissional(JsonWriter writer, DadosCadastroProfissional profissional) throws IOException {
        writer.beginObject();
        writer.name("nome").value(profissional.getNome());
        writer.name("especialidade").value(profissional.getEspecialidade());
        writer.name("numeroConselho").value(profissional.getNumeroConselho());
        writer.name("ufConselho").value(profissional.getUfConselho());
        writer.name("email").value(profissional.getEmail());
        writer.endObject();
    }
}
