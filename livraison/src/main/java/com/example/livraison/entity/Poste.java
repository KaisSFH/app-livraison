package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Postes")
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeposte;
    private String libelle;
    private Integer indice;
}
