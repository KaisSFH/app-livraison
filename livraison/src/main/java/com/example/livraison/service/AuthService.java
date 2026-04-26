package com.example.livraison.service;

import com.example.livraison.dto.LoginRequest;
import com.example.livraison.dto.LoginResponse;
import com.example.livraison.dto.RegisterRequest;
import com.example.livraison.entity.Personnel;
import com.example.livraison.entity.Poste;
import com.example.livraison.repository.PersonnelRepository;
import com.example.livraison.repository.PosteRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final PersonnelRepository personnelRepository;
    private final PosteRepository posteRepository;

    public AuthService(PersonnelRepository personnelRepository, PosteRepository posteRepository) {
        this.personnelRepository = personnelRepository;
        this.posteRepository = posteRepository;
    }

    public LoginResponse authenticate(LoginRequest request) {
        Optional<Personnel> user = personnelRepository.findByLoginAndMotP(request.getLogin(), request.getMotp());

        if (user.isPresent()) {
            Personnel p = user.get();
            String role = (p.getPoste() != null) ? p.getPoste().getLibelle() : "Inconnu";
            return new LoginResponse(true, p.getIdpers(), p.getNompers(), p.getPrenompers(), role);
        }

        return new LoginResponse(false, null, null, null, null);
    }

    public Map<String, Object> register(RegisterRequest request) {
        // 1. Validation des champs obligatoires
        if (request.getNom() == null || request.getNom().isEmpty()
                || request.getPrenom() == null || request.getPrenom().isEmpty()
                || request.getLogin() == null || request.getLogin().isEmpty()) {
            return Map.of("success", false, "message", "Tous les champs (Nom, Prénom, Email) sont obligatoires.");
        }

        // 2. Vérifier que le login n'existe pas déjà
        if (personnelRepository.findByLogin(request.getLogin()).isPresent()) {
            return Map.of("success", false, "message", "Ce login est déjà utilisé.");
        }

        // 3. Valider le format du mot de passe
        String password = request.getMotP();
        if (password == null || password.length() < 8
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[0-9].*")) {
            return Map.of("success", false, "message",
                    "Mot de passe invalide. Min 8 caractères, une majuscule et un chiffre requis.");
        }

        // 4. Trouver le poste Livreur
        Optional<Poste> posteOpt = posteRepository.findByLibelleIgnoreCase("Livreur");
        if (posteOpt.isEmpty()) {
            return Map.of("success", false, "message", "Erreur interne: poste Livreur introuvable.");
        }

        // 5. Créer et sauvegarder le nouvel utilisateur
        Personnel newPersonnel = new Personnel();
        newPersonnel.setNompers(request.getNom());
        newPersonnel.setPrenompers(request.getPrenom());
        newPersonnel.setLogin(request.getLogin());
        newPersonnel.setMotP(password);
        newPersonnel.setPoste(posteOpt.get());
        personnelRepository.save(newPersonnel);

        return Map.of("success", true, "message", "Compte créé avec succès.");
    }
}
