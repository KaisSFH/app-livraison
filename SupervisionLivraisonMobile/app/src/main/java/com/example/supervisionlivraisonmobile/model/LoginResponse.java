package com.example.supervisionlivraisonmobile.model;

public class LoginResponse {
    private boolean success;
    private Integer idpers;
    private String nom;
    private String prenom;
    private String nomposte;

    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public Integer getIdpers() { return idpers; }
    public void setIdpers(Integer idpers) { this.idpers = idpers; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getNomposte() { return nomposte; }
    public void setNomposte(String nomposte) { this.nomposte = nomposte; }
}
