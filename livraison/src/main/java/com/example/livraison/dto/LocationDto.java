package com.example.livraison.dto;



public class LocationDto {
    private Integer idpers;
    private String nompers;
    private String prenompers;
    private Double latitude;
    private Double longitude;
    private String villepers;
    private Integer commandesEnCours;

    // Getters and Setters
    public Integer getIdpers() { return idpers; }
    public void setIdpers(Integer idpers) { this.idpers = idpers; }
    public String getNompers() { return nompers; }
    public void setNompers(String nompers) { this.nompers = nompers; }
    public String getPrenompers() { return prenompers; }
    public void setPrenompers(String prenompers) { this.prenompers = prenompers; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getVillepers() { return villepers; }
    public void setVillepers(String villepers) { this.villepers = villepers; }
    public Integer getCommandesEnCours() { return commandesEnCours; }
    public void setCommandesEnCours(Integer commandesEnCours) { this.commandesEnCours = commandesEnCours; }
}
