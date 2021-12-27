package com.starwars.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class TraidorDTO implements Serializable {

  @NotNull(message = "O campo 'nome' não pode ser nulo ou vazio")
  private String nome;
}
