package com.example.livraison.repository;

import com.example.livraison.entity.LigneCommande;
import com.example.livraison.entity.LigneCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandeId> {
    List<LigneCommande> findByCommandeNocde(Integer nocde);
}
