package com.example.livraison.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class LigneCommandeId implements Serializable {
    private Integer nocde;
    private String refart;
}
