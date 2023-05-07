package com.example.exerciciotestes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;

import com.example.exerciciotestes.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ClienteServiceTest {

    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteService = new ClienteService(clienteRepository);
    }

    @Test
    public void testAtualizarCliente() {
        //cria um cliente
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setCpf("123.456.789-00");
        cliente.setEmail("joao@teste.com");

        //configura o mock do repositório
        doReturn(Optional.of(cliente)).when(clienteRepository).findById(1L);
        doReturn(cliente).when(clienteRepository).save(any());

        Cliente clienteAtualizado = clienteService.atualizarCliente(1L, "João da Silva", "111.222.333-44", "joao.silva@teste.com");

        assertNotNull(clienteAtualizado);
        assertEquals("João da Silva", clienteAtualizado.getNome());
        assertEquals("111.222.333-44", clienteAtualizado.getCpf());
        assertEquals("joao.silva@teste.com", clienteAtualizado.getEmail());


        //verifica se o método findById do repositório foi chamado corretamente
        verify(clienteRepository).findById(1L);

        //verifica se o método save do repositório foi chamado corretamente
        verify(clienteRepository).save(any());
    }
}
