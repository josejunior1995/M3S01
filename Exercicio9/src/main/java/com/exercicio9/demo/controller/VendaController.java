package com.example.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Venda;

@RestController
public class VendaController {

    @PostMapping("/vendas")
    public void criarVenda(@RequestBody Venda venda) {
        // Lógica para salvar a venda
    }
}
