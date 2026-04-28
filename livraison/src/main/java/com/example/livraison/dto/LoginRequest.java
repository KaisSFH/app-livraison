package com.example.livraison.dto;



public class LoginRequest {
    private String login;
    private String motp;

    // Getters and Setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotp() { return motp; }
    public void setMotp(String motp) { this.motp = motp; }
}
