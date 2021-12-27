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
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "item")
@Getter
@Setter
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  private String nome;

  private int pontos;

  @ManyToOne
  @JoinColumn(name = "id_inventario", nullable = false)
  private Inventario inventario;
}
