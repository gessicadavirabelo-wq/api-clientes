package com.gessica.api_clientes.service;

import java.util.List;

import com.gessica.api_clientes.model.Cliente;
import com.gessica.api_clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
    }


    public Cliente atualizar(Long id, Cliente dadosAtualizados) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Cliente não encontrado com ID: " + id));

        cliente.setNome(dadosAtualizados.getNome());
        cliente.setEmail(dadosAtualizados.getEmail());
        cliente.setTelefone(dadosAtualizados.getTelefone());

        return clienteRepository.save(cliente);
    }
    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Cliente não encontrado com ID: " + id));

        clienteRepository.delete(cliente);
    }

}
