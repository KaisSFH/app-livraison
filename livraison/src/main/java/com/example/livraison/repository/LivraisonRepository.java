package com.example.livraison.repository;

import com.example.livraison.entity.LivraisonCom;
import com.example.livraison.entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface LivraisonRepository extends JpaRepository<LivraisonCom, Integer> {
    
    // Pour le contrôleur : Livraisons par date
    List<LivraisonCom> findByDateliv(LocalDate date);

    // Pour le livreur : Livraisons assignées pour une date précise
    List<LivraisonCom> findByLivreurAndDateliv(Personnel livreur, LocalDate date);

    // Statistiques par statut pour une date donnée
    @Query("SELECT l.etatliv, COUNT(l) FROM LivraisonCom l WHERE l.dateliv = :date GROUP BY l.etatliv")
    List<Object[]> countByEtatlivAndDateliv(LocalDate date);

    @Query("SELECT l.livreur.nompers, l.etatliv, COUNT(l) FROM LivraisonCom l GROUP BY l.livreur.nompers, l.etatliv")
    List<Object[]> countLivraisonsByLivreurAndEtat();

    @Query("SELECT l.commande.client.nomclt, l.etatliv, COUNT(l) FROM LivraisonCom l GROUP BY l.commande.client.nomclt, l.etatliv")
    List<Object[]> countLivraisonsByClientAndEtat();

    List<LivraisonCom> findByLivreurIdpers(Integer livreurId);
}
