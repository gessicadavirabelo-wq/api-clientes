package com.gessica.api_clientes;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarClienteComSucesso() throws Exception {
        String clienteJson = """
                {
                    "nome": "Cliente Teste Automatizado",
                    "email": "teste.automatizado@example.com",
                    "telefone": "34999998888"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Cliente Teste Automatizado"))
                .andExpect(jsonPath("$.email").value("teste.automatizado@example.com"))
                .andExpect(jsonPath("$.telefone").value("34999998888"));
    }

    @Test
    void deveRejeitarClienteComEmailInvalido() throws Exception {
        String clienteJson = """
                {
                    "nome": "Cliente Teste",
                    "email": "email-invalido",
                    "telefone": "34999998888"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRejeitarClienteComNomeVazio() throws Exception {
        String clienteJson = """
                {
                    "nome": "",
                    "email": "cliente.teste@example.com",
                    "telefone": "34999998888"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveListarClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }


    @Test
    void deveBuscarClientePorId() throws Exception {
        String clienteJson = """
                {
                    "nome": "Cliente Teste Busca",
                    "email": "busca@example.com",
                    "telefone": "34999998888"
                }
                """;

        MvcResult resultado = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andReturn();

        String resposta = resultado.getResponse().getContentAsString();

        String id = resposta.replaceAll("(?s).*\"id\"\\s*:\\s*(\\d+).*", "$1");

        mockMvc.perform(get("/clientes/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Long.parseLong(id)))
                .andExpect(jsonPath("$.nome").value("Cliente Teste Busca"));
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        String clienteJson = """
                {
                    "nome": "Cliente Antes",
                    "email": "antes@example.com",
                    "telefone": "34999998888"
                }
                """;

        MvcResult resultado = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andReturn();

        String resposta = resultado.getResponse().getContentAsString();
        String id = resposta.replaceAll("(?s).*\"id\"\\s*:\\s*(\\d+).*", "$1");

        String clienteAtualizado = """
                {
                    "nome": "Cliente Atualizado",
                    "email": "atualizado@example.com",
                    "telefone": "34988887777"
                }
                """;

        mockMvc.perform(put("/clientes/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteAtualizado))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Long.parseLong(id)))
                .andExpect(jsonPath("$.nome").value("Cliente Atualizado"))
                .andExpect(jsonPath("$.email").value("atualizado@example.com"))
                .andExpect(jsonPath("$.telefone").value("34988887777"));
    }

    @Test
    void deveExcluirCliente() throws Exception {

        String resposta = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Maria",
                                    "email": "maria@teste.com",
                                    "telefone": "34999999999"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = resposta.replaceAll(
                "(?s).*\"id\"\\s*:\\s*(\\d+).*", "$1");

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                        .delete("/clientes/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/clientes/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoAtualizarClienteInexistente() throws Exception {
        mockMvc.perform(put("/clientes/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Maria",
                                    "email": "maria@teste.com",
                                    "telefone": "34999999999"
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornar404AoBuscarClienteInexistente() throws Exception {
        mockMvc.perform(get("/clientes/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRejeitarClienteComTelefoneInvalido() throws Exception {
        String clienteJson = """
                {
                    "nome": "Cliente Teste",
                    "email": "cliente.teste@example.com",
                    "telefone": "123"
                }
                """;

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void deveRetornar404AoExcluirClienteInexistente() throws Exception {
        mockMvc.perform(
                        org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                                .delete("/clientes/999999"))
                .andExpect(status().isNotFound());
    }

}

