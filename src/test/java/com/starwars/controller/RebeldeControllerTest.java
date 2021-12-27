package com.starwars.controller;

import com.starwars.model.Rebelde;
import com.starwars.repository.RebeldeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RebeldeControllerTest {

  private final String CADASTRAR_REBELDE_JSON =
      Files.readString(Path.of("src/test/resources/rebelde/cadastrar-rebelde.json"));
  private final String ATUALIZAR_REBELDE_JSON =
      Files.readString(Path.of("src/test/resources/rebelde/atualizar-rebelde.json"));
  private final String DENUNCIAR_REBELDE_JSON =
      Files.readString(Path.of("src/test/resources/rebelde/denunciar-rebelde.json"));
  @Autowired private MockMvc mockMvc;
  @Autowired private RebeldeRepository rebeldeRepository;

  public RebeldeControllerTest() throws IOException {}

  @Test
  @Transactional
  void deveCadastrarRebeldeComSucesso() throws Exception {
    criarRebelde(CADASTRAR_REBELDE_JSON);

    Rebelde rebelde = rebeldeRepository.findByNome("Jonatas");
    assertEquals("Jonatas", rebelde.getNome());
  }

  @Test
  @Transactional
  void deveAtualizarALocalizacaoDoRebeldeComSucesso() throws Exception {
    criarRebelde(CADASTRAR_REBELDE_JSON);

    mockMvc
        .perform(
            put("/api/v1/atualizar-localizacao?nome=Jonatas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ATUALIZAR_REBELDE_JSON))
        .andExpect(status().isOk());

    var rebeldeSalvo = rebeldeRepository.findByNome("Jonatas");

    assertEquals("vanda", rebeldeSalvo.getLocalizacao().getNomeGalaxia());
  }

  @Test
  @Transactional
  void deveDenunciarRebeldeComSucesso() throws Exception {
    criarRebelde(CADASTRAR_REBELDE_JSON);
    var rebeldeSalvoAntes = rebeldeRepository.findByNome("Jonatas");
    assertEquals(0, rebeldeSalvoAntes.getTraidor());

    mockMvc
        .perform(
            patch("/api/v1/denunciar-traidor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(DENUNCIAR_REBELDE_JSON))
        .andExpect(status().isOk());

    var rebeldeSalvoDepois = rebeldeRepository.findByNome("Jonatas");

    assertEquals(1, rebeldeSalvoDepois.getTraidor());
  }

  private void criarRebelde(String cadastrar_rebelde_json) throws Exception {
    mockMvc
        .perform(
            post("/api/v1/adicionar-rebelde")
                .contentType(MediaType.APPLICATION_JSON)
                .content(CADASTRAR_REBELDE_JSON))
        .andExpect(status().isCreated());
  }
}
