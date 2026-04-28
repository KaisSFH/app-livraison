package com.example.livraison.controller;

import com.example.livraison.dto.LocationDto;
import com.example.livraison.entity.Personnel;
import com.example.livraison.repository.PersonnelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personnel")
@CrossOrigin("*")
public class LocationController {

    private final PersonnelRepository personnelRepository;
    private final com.example.livraison.repository.LivraisonRepository livraisonRepository;

    public LocationController(PersonnelRepository personnelRepository, com.example.livraison.repository.LivraisonRepository livraisonRepository) {
        this.personnelRepository = personnelRepository;
        this.livraisonRepository = livraisonRepository;
    }

    @PutMapping("/{id}/location")
    public ResponseEntity<Void> updateLocation(@PathVariable Integer id, @RequestBody LocationDto locationDto) {
        return personnelRepository.findById(id).map(personnel -> {
            personnel.setLatitude(locationDto.getLatitude());
            personnel.setLongitude(locationDto.getLongitude());
            personnelRepository.save(personnel);
            return ResponseEntity.ok().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/livreurs/locations")
    public ResponseEntity<List<LocationDto>> getLivreursLocations() {
        List<LocationDto> locations = personnelRepository.findAll().stream()
                .filter(p -> p.getPoste() != null && "Livreur".equalsIgnoreCase(p.getPoste().getLibelle()))
                .filter(p -> p.getLatitude() != null && p.getLongitude() != null)
                .map(p -> {
                    LocationDto dto = new LocationDto();
                    dto.setIdpers(p.getIdpers());
                    dto.setNompers(p.getNompers());
                    dto.setPrenompers(p.getPrenompers());
                    dto.setLatitude(p.getLatitude());
                    dto.setLongitude(p.getLongitude());
                    dto.setVillepers(p.getVillepers());
                    
                    // Calculate "Commandes en cours"
                    long enCours = livraisonRepository.findByLivreurIdpers(p.getIdpers()).stream()
                            .filter(l -> "En cours".equalsIgnoreCase(l.getEtatliv()))
                            .count();
                    dto.setCommandesEnCours((int) enCours);
                    
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/livreurs")
    public ResponseEntity<List<LocationDto>> getAllLivreurs() {
        List<LocationDto> livreurs = personnelRepository.findAll().stream()
                .filter(p -> p.getPoste() != null && "Livreur".equalsIgnoreCase(p.getPoste().getLibelle()))
                .map(p -> {
                    LocationDto dto = new LocationDto();
                    dto.setIdpers(p.getIdpers());
                    dto.setNompers(p.getNompers());
                    dto.setPrenompers(p.getPrenompers());
                    dto.setVillepers(p.getVillepers());
                    long enCours = livraisonRepository.findByLivreurIdpers(p.getIdpers()).stream()
                            .filter(l -> "En cours".equalsIgnoreCase(l.getEtatliv()))
                            .count();
                    dto.setCommandesEnCours((int) enCours);
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(livreurs);
    }
}
