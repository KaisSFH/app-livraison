package com.example.supervisionlivraisonmobile.model;

public class Delivery {
    private Integer nocde;
    private String dateliv;
    private String modepay;
    private String etatliv;
    private String remarque;
    private String nomClient;
    private String prenomClient;
    private String telClient;
    private String villeClient;
    private String adresseClient;
    private Double montant;
    private String livreur; // Added to support mock data if needed

    public Delivery() {}

    public Delivery(Integer nocde, String prenomClient, String adresseClient, String etatliv, String livreur) {
        this.nocde = nocde;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.etatliv = etatliv;
        this.livreur = livreur;
    }

    // Getters and Setters
    public Integer getNocde() { return nocde; }
    public void setNocde(Integer nocde) { this.nocde = nocde; }
    public String getDateliv() { return dateliv; }
    public void setDateliv(String dateliv) { this.dateliv = dateliv; }
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
    
    public String getLivreur() { return livreur; }
    public void setLivreur(String livreur) { this.livreur = livreur; }

    public String getFullClientName() {
        return (prenomClient != null ? prenomClient : "") + " " + (nomClient != null ? nomClient : "");
    }
}
