package com.example.livraison.entity;

import jakarta.persistence.*;

@Entity
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

    // Getters and Setters
    public Integer getNoclt() { return noclt; }
    public void setNoclt(Integer noclt) { this.noclt = noclt; }
    public String getNomclt() { return nomclt; }
    public void setNomclt(String nomclt) { this.nomclt = nomclt; }
    public String getPrenomclt() { return prenomclt; }
    public void setPrenomclt(String prenomclt) { this.prenomclt = prenomclt; }
    public String getAdrclt() { return adrclt; }
    public void setAdrclt(String adrclt) { this.adrclt = adrclt; }
    public String getVilleclt() { return villeclt; }
    public void setVilleclt(String villeclt) { this.villeclt = villeclt; }
    public String getCode_postal() { return code_postal; }
    public void setCode_postal(String code_postal) { this.code_postal = code_postal; }
    public String getTelclt() { return telclt; }
    public void setTelclt(String telclt) { this.telclt = telclt; }
    public String getAdrmail() { return adrmail; }
    public void setAdrmail(String adrmail) { this.adrmail = adrmail; }
}
