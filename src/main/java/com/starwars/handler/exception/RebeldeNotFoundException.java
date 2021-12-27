package com.starwars.handler.exception;

public class RebeldeNotFoundException extends RuntimeException {

  public RebeldeNotFoundException(String nome) {
    super("Rebelde [ " + nome + " ] não encontrado!");
  }
}
