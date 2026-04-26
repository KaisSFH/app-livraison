package com.example.livraison.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String login;
    private String motP;
}
