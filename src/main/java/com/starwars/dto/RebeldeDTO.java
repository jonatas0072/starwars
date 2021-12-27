package com.starwars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RebeldeDTO implements Serializable {

  private String nome;

  private int idade;

  private String genero;

  private int traidor;

  @JsonProperty(value = "localizacao")
  private LocalizacaoDTO localizacaoDTO;
}
