package com.example.livraison.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private Integer expediteurId;
    private Integer destinataireId;
    private Integer nocde;
    private String contactClient;
    private String contenu;
    private String typeMessage;
}
