package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "LivraisonCom")
public class LivraisonCom {
    @Id
    private Integer nocde;
    
    private LocalDate dateliv;
    private String modepay;
    private String etatliv;
    private String remarque;

    @OneToOne
    @MapsId
    @JoinColumn(name = "nocde")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "livreur")
    private Personnel livreur;
}
