package com.starwars.repository;

import com.starwars.model.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, UUID> {
  Rebelde findByNome(String nome);
}
