package com.example.exerciciotestes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.exerciciotestes.repository.ProdutoRepository;
import com.example.exerciciotestes.service.ProdutoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletaProdutoPorId() {
        // given
        Long id = 1L;

        // executa o método deletaProdutoPorId
        produtoService.deletaProdutoPorId(id);

        // verifica se o método deleteById do repositório foi chamado corretamente com o id fornecido
        verify(produtoRepository).deleteById(id);
    }
}
