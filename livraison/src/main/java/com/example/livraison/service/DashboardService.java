package com.example.livraison.service;

import com.example.livraison.repository.LivraisonRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final LivraisonRepository livraisonRepository;

    public DashboardService(LivraisonRepository livraisonRepository) {
        this.livraisonRepository = livraisonRepository;
    }

    public List<Map<String, Object>> getStatsByLivreur() {
        List<Object[]> results = livraisonRepository.countLivraisonsByLivreurAndEtat();
        return results.stream().map(res -> {
            Map<String, Object> map = new HashMap<>();
            map.put("livreur", res[0]);
            map.put("etat", res[1]);
            map.put("count", res[2]);
            return map;
        }).toList();
    }

    public List<Map<String, Object>> getStatsByClient() {
        List<Object[]> results = livraisonRepository.countLivraisonsByClientAndEtat();
        return results.stream().map(res -> {
            Map<String, Object> map = new HashMap<>();
            map.put("client", res[0]);
            map.put("etat", res[1]);
            map.put("count", res[2]);
            return map;
        }).toList();
    }
}
