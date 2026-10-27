package com.gessica.api_clientes.controller;

import com.gessica.api_clientes.model.Cliente;
import com.gessica.api_clientes.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/clientes/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {

        return clienteService.buscarPorId(id);
    }

    @PutMapping("/clientes/{id}")
    public Cliente atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String tratarClienteNaoEncontrado(NoSuchElementException erro) {
        return erro.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> tratarErroDeValidacao(
            MethodArgumentNotValidException erro) {

        String mensagem = erro.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity.badRequest().body(mensagem);
    }
}

