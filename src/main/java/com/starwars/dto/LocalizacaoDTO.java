package com.starwars.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LocalizacaoDTO implements Serializable {

  private String latitude;

  private String longitude;

  @JsonProperty(value = "nome_galaxia")
  private String nomeGalaxia;

  @JsonProperty(value = "nome_base")
  private String nomeBase;
}
