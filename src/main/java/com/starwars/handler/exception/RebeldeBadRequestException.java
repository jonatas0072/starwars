package com.starwars.handler.exception;

public class RebeldeBadRequestException extends RuntimeException {
  public RebeldeBadRequestException() {
    super("Ocorreu um erro na sua requisição");
  }
}
