package com.example.livraison.dto;



public class LoginResponse {
    private boolean success;
    private Integer idpers;
    private String nom;
    private String prenom;
    private String role;

    public LoginResponse() {}

    public LoginResponse(boolean success, Integer idpers, String nom, String prenom, String role) {
        this.success = success;
        this.idpers = idpers;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }

    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public Integer getIdpers() { return idpers; }
    public void setIdpers(Integer idpers) { this.idpers = idpers; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
