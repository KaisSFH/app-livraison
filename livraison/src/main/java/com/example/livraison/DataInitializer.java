package com.example.livraison;

import com.example.livraison.entity.*;
import com.example.livraison.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonnelRepository personnelRepository;
    private final PosteRepository posteRepository;
    private final ClientRepository clientRepository;
    private final CommandeRepository commandeRepository;
    private final LivraisonRepository livraisonRepository;
    private final MessageRepository messageRepository;

    public DataInitializer(PersonnelRepository personnelRepository, PosteRepository posteRepository,
                           ClientRepository clientRepository, CommandeRepository commandeRepository,
                           LivraisonRepository livraisonRepository, MessageRepository messageRepository) {
        this.personnelRepository = personnelRepository;
        this.posteRepository = posteRepository;
        this.clientRepository = clientRepository;
        this.commandeRepository = commandeRepository;
        this.livraisonRepository = livraisonRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Create Poste "LIVREUR"
        Poste livreurPoste = posteRepository.findByLibelleIgnoreCase("LIVREUR")
                .orElseGet(() -> {
                    Poste p = new Poste();
                    p.setLibelle("LIVREUR");
                    return posteRepository.save(p);
                });

        // Create Poste "CONTROLEUR"
        Poste controleurPoste = posteRepository.findByLibelleIgnoreCase("CONTROLEUR")
                .orElseGet(() -> {
                    Poste p = new Poste();
                    p.setLibelle("CONTROLEUR");
                    return posteRepository.save(p);
                });

        // Create or get Controleur ctrl/ctrl
        Personnel controleur = personnelRepository.findByLogin("ctrl")
                .orElseGet(() -> {
                    Personnel p = new Personnel();
                    p.setLogin("ctrl");
                    p.setMotP("ctrl");
                    p.setNompers("Controleur");
                    p.setPrenompers("Admin");
                    p.setAdrpers("Siège Tunis");
                    p.setVillepers("Tunis");
                    p.setTelpers("99887766");
                    p.setD_embauche(LocalDate.now());
                    p.setPoste(controleurPoste);
                    return personnelRepository.save(p);
                });

        // Create or get Livreur liv/liv (Tunis)
        Personnel livreur1 = personnelRepository.findByLogin("liv")
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
                    p.setLatitude(36.8065);
                    p.setLongitude(10.1815);
                    return personnelRepository.save(p);
                });

        // Create Livreur liv2 (Ariana)
        Personnel livreur2 = personnelRepository.findByLogin("liv2")
                .orElseGet(() -> {
                    Personnel p = new Personnel();
                    p.setLogin("liv2");
                    p.setMotP("liv2");
                    p.setNompers("Ben Salah");
                    p.setPrenompers("Ahmed");
                    p.setAdrpers("Avenue de l'Indépendance");
                    p.setVillepers("Ariana");
                    p.setTelpers("22334455");
                    p.setD_embauche(LocalDate.now());
                    p.setPoste(livreurPoste);
                    p.setLatitude(36.8625);
                    p.setLongitude(10.1956);
                    return personnelRepository.save(p);
                });

        // Create Livreur liv3 (Sousse)
        Personnel livreur3 = personnelRepository.findByLogin("liv3")
                .orElseGet(() -> {
                    Personnel p = new Personnel();
                    p.setLogin("liv3");
                    p.setMotP("liv3");
                    p.setNompers("Trabelsi");
                    p.setPrenompers("Sami");
                    p.setAdrpers("Route de la Plage");
                    p.setVillepers("Sousse");
                    p.setTelpers("55667788");
                    p.setD_embauche(LocalDate.now());
                    p.setPoste(livreurPoste);
                    p.setLatitude(35.8254);
                    p.setLongitude(10.6369);
                    return personnelRepository.save(p);
                });

        // Create Livreur liv4 (Sfax)
        Personnel livreur4 = personnelRepository.findByLogin("liv4")
                .orElseGet(() -> {
                    Personnel p = new Personnel();
                    p.setLogin("liv4");
                    p.setMotP("liv4");
                    p.setNompers("Karray");
                    p.setPrenompers("Mohamed");
                    p.setAdrpers("Centre Ville");
                    p.setVillepers("Sfax");
                    p.setTelpers("99001122");
                    p.setD_embauche(LocalDate.now());
                    p.setPoste(livreurPoste);
                    p.setLatitude(34.7406);
                    p.setLongitude(10.7603);
                    return personnelRepository.save(p);
                });
            
        // Create a Client if not exists
        Client client = clientRepository.findAll().stream().findFirst()
                .orElseGet(() -> {
                    Client c = new Client();
                    c.setNomclt("Carrefour Tunisie");
                    c.setPrenomclt("");
                    c.setTelclt("21699887766");
                    c.setAdrclt("La Marsa, Tunis");
                    c.setVilleclt("Tunis");
                    return clientRepository.save(c);
                });

        // Create some Commandes and Livraisons
        Personnel[] livreurs = {livreur1, livreur2, livreur3, livreur4};
        if (livraisonRepository.count() == 0) {
            double[] montants = {120.0, 85.5, 450.0, 210.0, 320.0, 150.0, 90.0, 300.0, 50.0, 400.0};
            String[] etats = {"Livré", "Échec", "En cours", "Livré", "Échec", "En cours", "En cours", "En cours", "Livré", "En cours"};
            
            for (int i = 0; i < 10; i++) {
                Commande c = new Commande();
                c.setClient(client);
                c.setDatecde(LocalDate.now().minusDays(i % 3));
                c.setEtatcde("Validée");
                c = commandeRepository.save(c);

                LivraisonCom l = new LivraisonCom();
                l.setCommande(c);
                l.setLivreur(livreurs[i % 4]);
                l.setDateliv(LocalDate.now().minusDays(i % 3));
                l.setEtatliv(etats[i]);
                l.setModepay("Espèces");
                l.setRemarque(i == 1 ? "Client absent" : "Livraison test " + (i+1));
                l.setMontant(montants[i]);
                livraisonRepository.save(l);
            }
        }

        // Create mock messages between controleur and each livreur
        if (messageRepository.count() == 0) {
            Personnel[] livreursForMsg = {livreur1, livreur2, livreur3, livreur4};
            String[][] msgPairs = {
                {"Bonjour, ta tournée se passe bien ?", "Oui, tout va bien merci !"},
                {"Client introuvable au n°14 rue Hassan II", "Réessaie dans 30 min, je note."},
                {"J'ai 3 livraisons en cours, heure estimée 16h", "Parfait, tiens-moi au courant."},
                {"Panne moteur, besoin d'assistance !", "On envoie quelqu'un, reste sur place."}
            };

            for (int i = 0; i < livreursForMsg.length; i++) {
                // Livreur → Controleur
                Message m1 = new Message();
                m1.setExpediteur(livreursForMsg[i]);
                m1.setDestinataire(controleur);
                m1.setContenu(msgPairs[i][0]);
                m1.setTypeMessage("CHAT");
                m1.setDateMessage(LocalDateTime.now().minusHours(2 - i));
                m1.setLu(false);
                messageRepository.save(m1);

                // Controleur → Livreur
                Message m2 = new Message();
                m2.setExpediteur(controleur);
                m2.setDestinataire(livreursForMsg[i]);
                m2.setContenu(msgPairs[i][1]);
                m2.setTypeMessage("CHAT");
                m2.setDateMessage(LocalDateTime.now().minusHours(2 - i).plusMinutes(5));
                m2.setLu(true);
                messageRepository.save(m2);
            }
        }
    }
}
