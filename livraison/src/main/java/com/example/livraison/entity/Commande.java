package com.example.livraison.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
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

    // Getters and Setters
    public Integer getNocde() { return nocde; }
    public void setNocde(Integer nocde) { this.nocde = nocde; }
    public LocalDate getDatecde() { return datecde; }
    public void setDatecde(LocalDate datecde) { this.datecde = datecde; }
    public String getEtatcde() { return etatcde; }
    public void setEtatcde(String etatcde) { this.etatcde = etatcde; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
