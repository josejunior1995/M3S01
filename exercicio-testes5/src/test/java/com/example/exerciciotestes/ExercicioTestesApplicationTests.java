package com.example.exerciciotestes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import com.example.exerciciotestes.service.ProdutoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
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
    public void testBuscaTodosProdutos() {
        // given
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Produto 1", 10.0));
        produtos.add(new Produto("Produto 2", 20.0));
        produtos.add(new Produto("Produto 3", 30.0));

        // mock do método findAll do repositório
        when(produtoRepository.findAll()).thenReturn(produtos);

        // executa o método buscaTodosProdutos
        List<Produto> produtosRetornados = produtoService.buscaTodosProdutos();

        // verifica se a lista de produtos retornada é igual à lista de produtos mockada
        assertEquals(produtos, produtosRetornados);

        // verifica se o método findAll do repositório foi chamado corretamente
        verify(produtoRepository).findAll();
    }

    @Test
    public void testBuscaProdutoPorId() {
        // given
        Long idProdutoExistente = 1L;
        Produto produtoExistente = new Produto("Produto 1", 10.0);

        // mock do método findById do repositório
        when(produtoRepository.findById(idProdutoExistente)).thenReturn(Optional.of(produtoExistente));

        // executa o método buscaProdutoPorId
        Produto produtoRetornado = produtoService.buscaProdutoPorId(idProdutoExistente);

        // verifica se o produto retornado é igual ao produto mockado
        assertEquals(produtoExistente, produtoRetornado);

        // verifica se o método findById do repositório foi chamado corretamente
        verify(produtoRepository).findById(idProdutoExistente);
    }
}
