package com.example.livraison.controller;

import com.example.livraison.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/livreurs")
    public ResponseEntity<List<Map<String, Object>>> getStatsLivreurs() {
        return ResponseEntity.ok(dashboardService.getStatsByLivreur());
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Map<String, Object>>> getStatsClients() {
        return ResponseEntity.ok(dashboardService.getStatsByClient());
    }
}
