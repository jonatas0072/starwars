package com.starwars.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.util.UUID;

@Entity(name = "localizacao")
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Localizacao extends EntidadeBase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  private String latitude;

  private String longitude;

  @JoinColumn(name = "nome_base")
  private String nomeGalaxia;

  @JoinColumn(name = "nome_base")
  private String nomeBase;
}
