package com.example.exerciciotestes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import com.example.exerciciotestes.service.ClienteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDetelaClientePorId() {
        // given
        Long idClienteExistente = 1L;
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(idClienteExistente);

        // mock do método findById do repositório
        when(clienteRepository.findById(idClienteExistente)).thenReturn(Optional.of(clienteExistente));

        // executa o método detelaClientePorId
        clienteService.detelaClientePorId(idClienteExistente);

        // verifica se o método findById do repositório foi chamado corretamente
        verify(clienteRepository).findById(idClienteExistente);

        // verifica se o método deleteById do repositório foi chamado corretamente
        verify(clienteRepository).deleteById(idClienteExistente);
    }

    @Test
    public void testDetelaClientePorIdClienteNaoEncontrado() {
        // given
        Long idClienteInexistente = 1L;

        // mock do método findById do repositório
        when(clienteRepository.findById(idClienteInexistente)).thenReturn(Optional.empty());

        // executa o método detelaClientePorId e verifica se lança a exceção HttpClientErrorException
        assertThrows(HttpClientErrorException.class, () -> clienteService.detelaClientePorId(idClienteInexistente));

        // verifica se o método findById do repositório foi chamado corretamente
        verify(clienteRepository).findById(idClienteInexistente);

        // verifica se o método deleteById do repositório não foi chamado
        verify(clienteRepository, never()).deleteById(any());
    }
}
