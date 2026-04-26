package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Articles")
public class Article {
    @Id
    private String refart;
    private String designation;
    private Double prixA;
    private Double prixV;
    private Integer codetva;
    private String categorie;
    private Integer qtestk;
}
