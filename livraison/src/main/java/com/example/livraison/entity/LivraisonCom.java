package com.example.livraison.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LivraisonCom")
public class LivraisonCom {
    @Id
    private Integer nocde;
    
    private LocalDate dateliv;
    private String modepay;
    private String etatliv;
    private String remarque;
    private Double montant; // Nouveau champ pour le montant collecté

    @OneToOne
    @MapsId
    @JoinColumn(name = "nocde")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "livreur")
    private Personnel livreur;

    // Getters and Setters
    public Integer getNocde() { return nocde; }
    public void setNocde(Integer nocde) { this.nocde = nocde; }
    public LocalDate getDateliv() { return dateliv; }
    public void setDateliv(LocalDate dateliv) { this.dateliv = dateliv; }
    public String getModepay() { return modepay; }
    public void setModepay(String modepay) { this.modepay = modepay; }
    public String getEtatliv() { return etatliv; }
    public void setEtatliv(String etatliv) { this.etatliv = etatliv; }
    public String getRemarque() { return remarque; }
    public void setRemarque(String remarque) { this.remarque = remarque; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
    public Personnel getLivreur() { return livreur; }
    public void setLivreur(Personnel livreur) { this.livreur = livreur; }
}
