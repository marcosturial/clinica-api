package com.projeto.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.projeto.dto.DadosCadastroProfissional;
import com.projeto.enums.Especialidade;
import com.projeto.helpers.JsonHelper;
import jakarta.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class ProfissionaisService {

    private static final Path CAMINHO_ARQUIVO = Path.of("data/profissionais.json");

    private static final Logger log = LoggerFactory.getLogger(ProfissionaisService.class);

    public List<DadosCadastroProfissional> getProfissionais() {
        return listarProfissionais();
    }

    public DadosCadastroProfissional cadastrarProfissional(DadosCadastroProfissional dto) {
        var profissionais = listarProfissionais();
        profissionais.add(dto);
        try {
            JsonHelper.escreverArquivoJson(CAMINHO_ARQUIVO, profissionais);
            return dto;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Erro ao cadastrar profissionais", e);
        }

    }


    private List<DadosCadastroProfissional> listarProfissionais() {

        List<DadosCadastroProfissional> listaProfissionais = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(CAMINHO_ARQUIVO)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<DadosCadastroProfissional>>() {
            }.getType();
            listaProfissionais = gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return listaProfissionais;
    }


    public List<DadosCadastroProfissional> getProfissionaisByNome(String nome) {
        var profissionais = this.listarProfissionais();
        return profissionais.stream()
                .filter(p ->
                {
                    String[] partesNome = p.getNome().trim().toLowerCase().split(" ");
                    return Arrays.stream(partesNome).anyMatch(parteNome -> parteNome.startsWith(nome.toLowerCase()));
                }).toList();
    }

    public List<DadosCadastroProfissional> getProfissionaisByEspecialidade(String especialidade) {
        var profissionais = this.listarProfissionais();
        return profissionais.stream().filter(p ->
                Especialidade.getEnumByNome(p.getEspecialidade()).equals(Especialidade.getEnumByNome(especialidade.toLowerCase())))
                .toList();
    }


}
