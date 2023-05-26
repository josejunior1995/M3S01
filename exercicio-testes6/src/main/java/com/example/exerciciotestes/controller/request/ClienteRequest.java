package com.example.exerciciotestes.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteRequest {

  private String nomeCliente;
  private Double saldoCliente;

  public ClienteRequest(String joão, String s, String s1) {

  }
}
