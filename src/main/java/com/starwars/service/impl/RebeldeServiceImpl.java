package com.starwars.service.impl;

import com.starwars.dto.LocalizacaoDTO;
import com.starwars.dto.RebeldeDTO;
import com.starwars.dto.TraidorDTO;
import com.starwars.handler.exception.RebeldeNotFoundException;
import com.starwars.model.Rebelde;
import com.starwars.repository.RebeldeRepository;
import com.starwars.service.RebeldeService;
import com.starwars.utils.convert.RebeldeConvert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
public class RebeldeServiceImpl implements RebeldeService {

  private RebeldeRepository rebeldeRepository;

  @Override
  public Rebelde adicionarRebelde(RebeldeDTO rebeldeDTO) {
    log.info("[BEGIN] - adicionarRebelde() - Salvando Rebelde");

    Rebelde rebelde = RebeldeConvert.convertToRebelde(rebeldeDTO);
    Rebelde rebeldeSalvo = this.rebeldeRepository.save(rebelde);

    log.info(
        "[END] - adicionarRebelde() - Rebelde salco com o ID :[" + rebeldeSalvo.getUuid() + "]");
    return rebeldeSalvo;
  }

  @Override
  public void atualizarLocalizacao(LocalizacaoDTO localizacaoDTO, String nome) {
    log.info("[BEGIN] - atualizarLocalizacao() - Atualizando Rebelde : [" + nome + "]");
    var rebelde = buscaRebelde(nome);

    rebelde.setLocalizacao(RebeldeConvert.convertToLocalizacao(localizacaoDTO));

    this.rebeldeRepository.save(rebelde);
    log.info("[END] - atualizarLocalizacao()");
  }

  private Rebelde buscaRebelde(String nome) {
    var rebelde = this.rebeldeRepository.findByNome(nome);
    if (Objects.isNull(rebelde)) {
      throw new RebeldeNotFoundException(nome);
    }
    return rebelde;
  }

  @Override
  public void denunciarRebelde(TraidorDTO traidorDTO) {
    log.info("[BEGIN] - denunciarRebelde() :" + traidorDTO.getNome());
    var rebelde = buscaRebelde(traidorDTO.getNome());

    if (Objects.isNull(rebelde)) {
      throw new RebeldeNotFoundException(traidorDTO.getNome());
    }

    rebelde.setTraidor(rebelde.getTraidor() + 1);

    rebeldeRepository.save(rebelde);

    log.info("[END] - denunciarRebelde()");
  }

  @Override
  public int porcentagemTraidores() {
    return 0;
  }

  @Override
  public int porcentagemRebeldes() {
    return 0;
  }

  @Override
  public int quantidadeMediaRecursosPorRebelde() {
    return 0;
  }

  @Override
  public int pontosPerdidosDevidoTraidores() {
    return 0;
  }
}
