package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LigCdes")
@Data
public class LigneCommande {

    @EmbeddedId
    private LigneCommandeId id = new LigneCommandeId();

    private Integer qtecde;

    @ManyToOne
    @MapsId("nocde")
    @JoinColumn(name = "nocde")
    private Commande commande;

    @ManyToOne
    @MapsId("refart")
    @JoinColumn(name = "refart")
    private Article article;
}
