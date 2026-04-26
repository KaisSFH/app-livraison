package com.example.livraison;

import com.example.livraison.entity.*;
import com.example.livraison.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonnelRepository personnelRepository;
    private final PosteRepository posteRepository;
    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final LivraisonRepository livraisonRepository;

    public DataInitializer(PersonnelRepository personnelRepository, PosteRepository posteRepository,
                           ClientRepository clientRepository, CommandeRepository commandeRepository,
                           LivraisonRepository livraisonRepository) {
        this.personnelRepository = personnelRepository;
        this.posteRepository = posteRepository;
        this.clientRepository = clientRepository;
        this.commandeRepository = commandeRepository;
        this.livraisonRepository = livraisonRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create Poste if not exists
        Poste livreurPoste = posteRepository.findByLibelleIgnoreCase("LIVREUR")
                .orElseGet(() -> {
                    Poste p = new Poste();
                    p.setLibelle("LIVREUR");
                    return posteRepository.save(p);
                });

        // Create or get Livreur liv/liv
        Personnel livreur = personnelRepository.findByLogin("liv")
                .orElseGet(() -> {
                    Personnel p = new Personnel();
                    p.setLogin("liv");
                    p.setMotP("liv");
                    p.setNompers("Livreur");
                    p.setPrenompers("Test");
                    p.setAdrpers("Rue des Lilas");
                    p.setVillepers("Tunis");
                    p.setTelpers("12345678");
                    p.setD_embauche(LocalDate.now());
                    p.setPoste(livreurPoste);
                    return personnelRepository.save(p);
                });
            
        // Create a Client if not exists
        Client client = clientRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Client c = new Client();
                    c.setNomclt("Fathi");
                    c.setPrenomclt("Ali");
                    c.setTelclt("21699887766");
                    c.setAdrclt("Avenue Habib Bourguiba, Tunis");
                    c.setVilleclt("Tunis");
                    return clientRepository.save(c);
                });

        // Create some Commandes and Livraisons if none exist for this livreur
        if (livraisonRepository.findByLivreurIdpers(livreur.getIdpers()).isEmpty()) {
            for (int i = 0; i < 3; i++) {
                Commande c = new Commande();
                c.setClient(client);
                c.setDatecde(LocalDate.now());
                c.setEtatcde("Validée");
                c = commandeRepository.save(c);

                LivraisonCom l = new LivraisonCom();
                l.setCommande(c);
                l.setLivreur(livreur);
                l.setDateliv(LocalDate.now());
                l.setEtatliv("En cours");
                l.setModepay("Espèces");
                l.setRemarque("Livraison test " + (i+1));
                livraisonRepository.save(l);
            }
        }
    }
}
