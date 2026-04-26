package com.example.livraison.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Personnel")
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpers;
    private String nompers;
    private String prenompers;
    private String adrpers;
    private String villepers;
    private String telpers;
    private LocalDate d_embauche;
    
    @Column(unique = true, nullable = false)
    private String login;
    
    @Column(name = "motP", nullable = false)
    private String motP;

    @ManyToOne
    @JoinColumn(name = "codeposte")
    private Poste poste;
}
