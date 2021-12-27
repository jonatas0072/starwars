package com.starwars.utils.convert;

import com.starwars.dto.LocalizacaoDTO;
import com.starwars.dto.RebeldeDTO;
import com.starwars.model.Localizacao;
import com.starwars.model.Rebelde;

public class RebeldeConvert {
  public static Rebelde convertToRebelde(RebeldeDTO rebeldeDTO) {
    return Rebelde.newBuilder()
        .nome(rebeldeDTO.getNome())
        .idade(rebeldeDTO.getIdade())
        .genero(rebeldeDTO.getGenero())
        .localizacao(convertToLocalizacao(rebeldeDTO.getLocalizacaoDTO()))
        .build();
  }

  public static Localizacao convertToLocalizacao(LocalizacaoDTO localizacaoDTO) {
    return Localizacao.newBuilder()
        .latitude(localizacaoDTO.getLatitude())
        .longitude(localizacaoDTO.getLongitude())
        .nomeBase(localizacaoDTO.getNomeBase())
        .nomeGalaxia(localizacaoDTO.getNomeGalaxia())
        .build();
  }
}
