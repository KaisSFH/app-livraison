package com.example.supervisionlivraisonmobile.model;

public class LoginRequest {
    private String login;
    private String motp;

    public LoginRequest(String login, String motp) {
        this.login = login;
        this.motp = motp;
    }

    // Getters and Setters
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotp() { return motp; }
    public void setMotp(String motp) { this.motp = motp; }
}
