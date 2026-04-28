package com.example.livraison.dto;

import java.time.LocalDate;

public class LivraisonDto {
    private Integer nocde;
    private LocalDate dateliv;
    private Integer idLivreur;
    private String modepay;
    private String etatliv;
    private String remarque;
    private String nomClient;
    private String prenomClient;
    private String telClient;
    private String villeClient;
    private String adresseClient;
    private Double montant;

    // Getters and Setters
    public Integer getNocde() { return nocde; }
    public void setNocde(Integer nocde) { this.nocde = nocde; }
    public LocalDate getDateliv() { return dateliv; }
    public void setDateliv(LocalDate dateliv) { this.dateliv = dateliv; }
    public Integer getIdLivreur() { return idLivreur; }
    public void setIdLivreur(Integer idLivreur) { this.idLivreur = idLivreur; }
    public String getModepay() { return modepay; }
    public void setModepay(String modepay) { this.modepay = modepay; }
    public String getEtatliv() { return etatliv; }
    public void setEtatliv(String etatliv) { this.etatliv = etatliv; }
    public String getRemarque() { return remarque; }
    public void setRemarque(String remarque) { this.remarque = remarque; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
    public String getPrenomClient() { return prenomClient; }
    public void setPrenomClient(String prenomClient) { this.prenomClient = prenomClient; }
    public String getTelClient() { return telClient; }
    public void setTelClient(String telClient) { this.telClient = telClient; }
    public String getVilleClient() { return villeClient; }
    public void setVilleClient(String villeClient) { this.villeClient = villeClient; }
    public String getAdresseClient() { return adresseClient; }
    public void setAdresseClient(String adresseClient) { this.adresseClient = adresseClient; }
    public Double getMontant() { return montant; }
    public void setMontant(Double montant) { this.montant = montant; }
}
