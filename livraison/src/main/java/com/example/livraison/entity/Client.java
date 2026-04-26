package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noclt;
    private String nomclt;
    private String prenomclt;
    private String adrclt;
    private String villeclt;
    private String code_postal;
    private String telclt;
    private String adrmail;
}
