package com.example.livraison.repository;

import com.example.livraison.entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PosteRepository extends JpaRepository<Poste, Integer> {
    Optional<Poste> findByLibelleIgnoreCase(String libelle);
}
