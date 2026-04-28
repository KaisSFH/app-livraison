package com.example.livraison.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
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

    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "codeposte")
    private Poste poste;

    // Getters and Setters
    public Integer getIdpers() { return idpers; }
    public void setIdpers(Integer idpers) { this.idpers = idpers; }
    public String getNompers() { return nompers; }
    public void setNompers(String nompers) { this.nompers = nompers; }
    public String getPrenompers() { return prenompers; }
    public void setPrenompers(String prenompers) { this.prenompers = prenompers; }
    public String getAdrpers() { return adrpers; }
    public void setAdrpers(String adrpers) { this.adrpers = adrpers; }
    public String getVillepers() { return villepers; }
    public void setVillepers(String villepers) { this.villepers = villepers; }
    public String getTelpers() { return telpers; }
    public void setTelpers(String telpers) { this.telpers = telpers; }
    public LocalDate getD_embauche() { return d_embauche; }
    public void setD_embauche(LocalDate d_embauche) { this.d_embauche = d_embauche; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotP() { return motP; }
    public void setMotP(String motP) { this.motP = motP; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public Poste getPoste() { return poste; }
    public void setPoste(Poste poste) { this.poste = poste; }
}
