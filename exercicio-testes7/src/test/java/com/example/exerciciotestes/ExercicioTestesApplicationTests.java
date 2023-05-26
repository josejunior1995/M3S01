package com.example.exerciciotestes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import com.example.exerciciotestes.service.ProdutoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

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
    public void testAtualizarProduto() {
        // given
        Long id = 1L;
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNomeProduto("Produto 1");
        produtoRequest.setValorProduto(10.0);

        Produto produtoAtual = new Produto("Produto Atual", 5.0);
        Produto produtoAtualizado = new Produto("Produto 1", 10.0);

        // mock do método findById do repositório
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoAtual));

        // mock do método save do repositório
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoAtualizado);

        // executa o método atualizarProduto
        Produto produtoRetornado = produtoService.atualizarProduto(id, produtoRequest);

        // verifica se o produto retornado é igual ao produto atualizado
        assertEquals(produtoAtualizado, produtoRetornado);

        // verifica se o método findById do repositório foi chamado corretamente
        verify(produtoRepository).findById(id);

        // verifica se o método save do repositório foi chamado corretamente
        verify(produtoRepository).save(any(Produto.class));
    }
}
