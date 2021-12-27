package com.starwars.controller;

import com.starwars.dto.LocalizacaoDTO;
import com.starwars.dto.RebeldeDTO;
import com.starwars.dto.TraidorDTO;
import com.starwars.model.Rebelde;
import com.starwars.service.RebeldeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
@Api(value = "API Rebelde")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class RebeldeController {

  private RebeldeService rebeldeService;

  @PostMapping("adicionar-rebelde")
  @ApiOperation(value = "Adiciona rebelde")
  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Rebelde salvo com sucesso!"),
      })
  private ResponseEntity<Object> adicionarRebelde(@RequestBody RebeldeDTO rebeldeDTO) {
    Rebelde rebelde = this.rebeldeService.adicionarRebelde(rebeldeDTO);

    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/{uuid}")
                .buildAndExpand(rebelde.getUuid())
                .toUri())
        .build();
  }

  @PutMapping("atualizar-localizacao")
  @ApiOperation(value = "Atualiza localização do rebelde")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Localização atualizada com sucesso!"),
        @ApiResponse(code = 404, message = "Rebelde [ {nome} ] não encontrado!"),
      })
  private ResponseEntity<Object> atualizarLocalizacao(
      @RequestBody LocalizacaoDTO localizacaoDTO, @RequestParam(name = "nome") String nome) {
    this.rebeldeService.atualizarLocalizacao(localizacaoDTO, nome);

    return ResponseEntity.ok().build();
  }

  @PatchMapping("denunciar-traidor")
  @ApiOperation("Denuncia traidor")
  @ApiResponses(
      value = {
        @ApiResponse(code = 200, message = "Traidor denunciado com sucesso!"),
        @ApiResponse(code = 400, message = "Ocorreu um erro na sua requisição!"),
        @ApiResponse(code = 404, message = "Rebelde [ {nome} ] não encontrado!"),
      })
  private ResponseEntity<Object> denunciarTraidor(@RequestBody @Validated TraidorDTO traidorDTO) {
    this.rebeldeService.denunciarRebelde(traidorDTO);
    return ResponseEntity.ok().build();
  }
}
