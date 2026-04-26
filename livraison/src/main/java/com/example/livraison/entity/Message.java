package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    
    private String contactClient;
    private String contenu;
    private String typeMessage;
    private LocalDateTime dateMessage;
    private Boolean lu = false;

    @ManyToOne
    @JoinColumn(name = "expediteur")
    private Personnel expediteur;

    @ManyToOne
    @JoinColumn(name = "destinataire")
    private Personnel destinataire;

    @ManyToOne
    @JoinColumn(name = "nocde")
    private Commande commande;
}
