package com.example.livraison.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Postes")
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeposte;
    private String libelle;
    private Integer indice;

    // Getters and Setters
    public Integer getCodeposte() { return codeposte; }
    public void setCodeposte(Integer codeposte) { this.codeposte = codeposte; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public Integer getIndice() { return indice; }
    public void setIndice(Integer indice) { this.indice = indice; }
}
