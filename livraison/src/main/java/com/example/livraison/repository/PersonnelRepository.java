package com.example.livraison.repository;

import com.example.livraison.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    Optional<Personnel> findByLoginAndMotP(String login, String motP);
    Optional<Personnel> findByLogin(String login);
}
