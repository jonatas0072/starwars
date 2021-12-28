package com.starwars.service.impl;

import com.starwars.dto.LocalizacaoDTO;
import com.starwars.dto.RebeldeDTO;
import com.starwars.dto.TraidorDTO;
import com.starwars.handler.exception.RebeldeNotFoundException;
import com.starwars.model.Inventario;
import com.starwars.model.Item;
import com.starwars.model.Rebelde;
import com.starwars.repository.RebeldeRepository;
import com.starwars.service.RebeldeService;
import com.starwars.utils.convert.RebeldeConvert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
public class RebeldeServiceImpl implements RebeldeService {

  private final String ITEM_PADRAO_1 = "ARMA";
  private final String ITEM_PADRAO_2 = "AGUA";
  private RebeldeRepository rebeldeRepository;

  @Override
  public Rebelde adicionarRebelde(RebeldeDTO rebeldeDTO) {
    log.info("[BEGIN] - adicionarRebelde() - Salvando Rebelde");

    Rebelde rebelde = RebeldeConvert.convertToRebelde(rebeldeDTO);

    rebelde.setInventario(construirInventario());

    Rebelde rebeldeSalvo = this.rebeldeRepository.save(rebelde);

    log.info(
        "[END] - adicionarRebelde() - Rebelde salvo com o ID :[" + rebeldeSalvo.getUuid() + "]");
    return rebeldeSalvo;
  }

  private Inventario construirInventario() {
    Random gerador = new Random();
    List<String> itens = Arrays.asList("ARMA", "MUNICAO", "AGUA", "COMIDA");

    Map<String, Integer> map = new HashMap<>();
    map.put("ARMA", 4);
    map.put("MUNICAO", 3);
    map.put("AGUA", 2);
    map.put("COMIDA", 1);

    String item1Selecionado = itens.get(gerador.nextInt(4));
    String item2Selecionado = itens.get(gerador.nextInt(4));
    List<Item> listaItens = new ArrayList<>();
    var item1 = map.get(item1Selecionado);
    var item2 = map.get(item2Selecionado);

    if ((item1 + item2) != 6) {
      listaItens.add(Item.newBuilder().nome(ITEM_PADRAO_1).build());
      listaItens.add(Item.newBuilder().nome(ITEM_PADRAO_2).build());
      return Inventario.newBuilder().itens(listaItens).build();
    }

    listaItens.add(Item.newBuilder().nome(item1Selecionado).build());
    listaItens.add(Item.newBuilder().nome(item2Selecionado).build());

    return Inventario.newBuilder().itens(listaItens).build();
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
