package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nocde;
    
    private LocalDate datecde;
    private String etatcde;

    @ManyToOne
    @JoinColumn(name = "noclt")
    private Client client;
}
