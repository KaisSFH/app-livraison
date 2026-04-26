package com.example.livraison.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
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
}
