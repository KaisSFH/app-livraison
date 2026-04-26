package com.example.livraison.service;

import com.example.livraison.dto.MessageRequest;
import com.example.livraison.entity.Commande;
import com.example.livraison.entity.Message;
import com.example.livraison.entity.Personnel;
import com.example.livraison.repository.CommandeRepository;
import com.example.livraison.repository.MessageRepository;
import com.example.livraison.repository.PersonnelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonnelRepository personnelRepository;
    private final CommandeRepository commandeRepository;

    public MessageService(MessageRepository messageRepository, PersonnelRepository personnelRepository, CommandeRepository commandeRepository) {
        this.messageRepository = messageRepository;
        this.personnelRepository = personnelRepository;
        this.commandeRepository = commandeRepository;
    }

    public Message sendMessage(MessageRequest request) {
        Message msg = new Message();
        msg.setContenu(request.getContenu());
        msg.setTypeMessage(request.getTypeMessage());
        msg.setContactClient(request.getContactClient());
        msg.setDateMessage(LocalDateTime.now());
        msg.setLu(false);

        if (request.getExpediteurId() != null) {
            Optional<Personnel> exp = personnelRepository.findById(request.getExpediteurId());
            exp.ifPresent(msg::setExpediteur);
        }
        
        if (request.getDestinataireId() != null) {
            Optional<Personnel> dest = personnelRepository.findById(request.getDestinataireId());
            dest.ifPresent(msg::setDestinataire);
        }

        if (request.getNocde() != null) {
            Optional<Commande> cmd = commandeRepository.findById(request.getNocde());
            cmd.ifPresent(msg::setCommande);
        }

        return messageRepository.save(msg);
    }

    public List<Message> getMessagesForUser(Integer userId) {
        return messageRepository.findByDestinataireIdpers(userId);
    }
}
