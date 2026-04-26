package com.example.supervisionlivraisonmobile.model;

public class RegisterRequest {
    private String nom;
    private String prenom;
    private String login;
    private String motP;

    public RegisterRequest(String nom, String prenom, String login, String motP) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.motP = motP;
    }

    // Getters and Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotP() { return motP; }
    public void setMotP(String motP) { this.motP = motP; }
}
