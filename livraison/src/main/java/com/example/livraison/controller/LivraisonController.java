package com.example.livraison.controller;

import com.example.livraison.dto.LivraisonDto;
import com.example.livraison.dto.UpdateStatusRequest;
import com.example.livraison.service.LivraisonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/livraisons")
@CrossOrigin("*")
public class LivraisonController {

    private final LivraisonService livraisonService;

    public LivraisonController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }

    @GetMapping("/today")
    public List<LivraisonDto> getTodayDeliveries() {
        return livraisonService.getTodayDeliveries();
    }

    @GetMapping("/livreur/{id}")
    public List<LivraisonDto> getMyDeliveries(@PathVariable Integer id) {
        return livraisonService.getMyDeliveries(id);
    }

    @GetMapping
    public List<LivraisonDto> getAllDeliveries() {
        return livraisonService.getAllDeliveries();
    }

    @PutMapping("/{nocde}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Integer nocde, @RequestBody UpdateStatusRequest request) {
        boolean updated = livraisonService.updateStatus(nocde, request);
        if (updated) {
            return ResponseEntity.ok(Map.of("success", true, "message", "Statut mis à jour"));
        }
        return ResponseEntity.status(404).body(Map.of("success", false, "message", "Livraison non trouvée"));
    }
}
