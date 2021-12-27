package com.starwars.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity(name = "rebelde")
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Rebelde extends EntidadeBase {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  private String nome;

  private int idade;

  private String genero;

  private int traidor;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "id_localizacao", referencedColumnName = "uuid")
  private Localizacao localizacao;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "id_inventario", referencedColumnName = "uuid")
  private Inventario inventario;
}
