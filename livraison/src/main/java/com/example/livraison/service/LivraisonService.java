package com.example.livraison.service;

import com.example.livraison.dto.LivraisonDto;
import com.example.livraison.dto.UpdateStatusRequest;
import com.example.livraison.entity.LigneCommande;
import com.example.livraison.entity.LivraisonCom;
import com.example.livraison.entity.Personnel;
import com.example.livraison.repository.LigneCommandeRepository;
import com.example.livraison.repository.LivraisonRepository;
import com.example.livraison.repository.PersonnelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivraisonService {

    private final LivraisonRepository livraisonRepository;
    private final PersonnelRepository personnelRepository;
    private final LigneCommandeRepository ligneCommandeRepository;

    public LivraisonService(LivraisonRepository livraisonRepository, 
                            PersonnelRepository personnelRepository,
                            LigneCommandeRepository ligneCommandeRepository) {
        this.livraisonRepository = livraisonRepository;
        this.personnelRepository = personnelRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    public List<LivraisonDto> getTodayDeliveries() {
        return livraisonRepository.findByDateliv(LocalDate.now())
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<LivraisonDto> getDeliveriesByLivreur(Integer livreurId) {
        Optional<Personnel> livreur = personnelRepository.findById(livreurId);
        if (livreur.isPresent()) {
            return livraisonRepository.findByLivreurAndDateliv(livreur.get(), LocalDate.now())
                    .stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return List.of();
    }

    public List<LivraisonDto> getMyDeliveries(Integer livreurId) {
        return livraisonRepository.findByLivreurIdpers(livreurId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<LivraisonDto> getAllDeliveries() {
        return livraisonRepository.findAll()
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private LivraisonDto convertToDto(LivraisonCom entity) {
        LivraisonDto dto = new LivraisonDto();
        dto.setNocde(entity.getNocde());
        dto.setDateliv(entity.getDateliv());
        dto.setEtatliv(entity.getEtatliv());
        dto.setRemarque(entity.getRemarque());
        dto.setModepay(entity.getModepay());

        if (entity.getLivreur() != null) {
            dto.setIdLivreur(entity.getLivreur().getIdpers());
        }

        if (entity.getCommande() != null && entity.getCommande().getClient() != null) {
            var client = entity.getCommande().getClient();
            dto.setNomClient(client.getNomclt());
            dto.setPrenomClient(client.getPrenomclt());
            dto.setTelClient(client.getTelclt());
            dto.setVilleClient(client.getVilleclt());
            dto.setAdresseClient(client.getAdrclt());
        }

        // Calcul du montant total
        List<LigneCommande> lignes = ligneCommandeRepository.findByCommandeNocde(entity.getNocde());
        double total = lignes.stream()
                .mapToDouble(l -> l.getQtecde() * (l.getArticle() != null ? l.getArticle().getPrixV() : 0.0))
                .sum();
        dto.setMontant(total);

        return dto;
    }

    public boolean updateStatus(Integer nocde, UpdateStatusRequest request) {
        Optional<LivraisonCom> livraisonOpt = livraisonRepository.findById(nocde);
        if (livraisonOpt.isPresent()) {
            LivraisonCom livraison = livraisonOpt.get();
            livraison.setEtatliv(request.getEtatliv());
            livraison.setRemarque(request.getRemarque());
            livraisonRepository.save(livraison);
            return true;
        }
        return false;
    }
}
