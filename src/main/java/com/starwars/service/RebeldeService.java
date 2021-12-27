package com.starwars.service;

import com.starwars.dto.LocalizacaoDTO;
import com.starwars.dto.RebeldeDTO;
import com.starwars.dto.TraidorDTO;
import com.starwars.model.Rebelde;

public interface RebeldeService {

  Rebelde adicionarRebelde(RebeldeDTO rebeldeDTO);

  void atualizarLocalizacao(LocalizacaoDTO localizacaoDTO, String nome);

  void denunciarRebelde(TraidorDTO traidorDTO);

  int porcentagemTraidores();

  int porcentagemRebeldes();

  int quantidadeMediaRecursosPorRebelde();

  int pontosPerdidosDevidoTraidores();
}
