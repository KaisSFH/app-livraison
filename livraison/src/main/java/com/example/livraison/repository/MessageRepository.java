package com.example.livraison.repository;

import com.example.livraison.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByDestinataireIdpers(Integer destinataireId);
    List<Message> findByExpediteurIdpersOrDestinataireIdpersOrderByDateMessageAsc(Integer expediteurId, Integer destinataireId);
}
