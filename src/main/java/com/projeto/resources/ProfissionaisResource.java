package com.projeto.resources;

import com.projeto.dto.DadosCadastroProfissional;
import com.projeto.enums.Especialidade;
import com.projeto.services.ProfissionaisService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestPath;

@Path("/profissionais")
public class ProfissionaisResource {

    @Inject
    ProfissionaisService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista todos os profissionais da clínica")
    public Response getProfissionais() {
        try {
            var profissionais = service.getProfissionais();
            return Response.ok(profissionais).build();
        } catch (RuntimeException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/nome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pesquisar profissionais pelo nome",
            description = "Lista os profissionais que contém o nome desejado. Alguma parte do nome deve começar com o texto enviado na requisição.")
    public Response getProfissionaisByNome(@RestPath String nome) {
        try {
            return Response.ok(service.getProfissionaisByNome(nome)).build();
        } catch (RuntimeException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Cadastrar novo profissional",
    description = """ 
            Cadastrar novo profissional informando nome, especialidade, número do conselho, UF do conselho e o email.
            Para conferir as especialidades, conferir a operação Listar Especialidades
            """)
    public Response cadastrarProfissional(DadosCadastroProfissional dto) {
        try {
            return Response.ok(service.cadastrarProfissional(dto)).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/especialidade/{especialidade}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Pesquisar profissionais de determinada especialidade",
            description = "Lista os profissionais que atendem na especialidade desejada. ")
    public Response getProfissionaisByEspecialidade(@RestPath String especialidade) {
        try {
            return Response.ok(service.getProfissionaisByEspecialidade(especialidade)).build();
        } catch (RuntimeException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/especialidades")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar especialidades", description = "Lista as especialidades disponíveis na clínica de saúde")
    public Response getEspecialidades() {
        try {
            return Response.ok(Especialidade.getEspecialidades()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
