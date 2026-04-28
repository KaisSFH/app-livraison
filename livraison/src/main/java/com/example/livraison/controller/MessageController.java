package com.example.livraison.controller;

import com.example.livraison.dto.MessageRequest;
import com.example.livraison.entity.Message;
import com.example.livraison.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin("*")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request) {
        return ResponseEntity.ok(messageService.sendMessage(request));
    }

    @GetMapping("/livreur/{idLivreur}")
    public ResponseEntity<List<Message>> getMessagesForLivreur(@PathVariable Integer idLivreur) {
        return ResponseEntity.ok(messageService.getMessagesForUser(idLivreur));
    }

    @GetMapping("/controleur/{idControleur}")
    public ResponseEntity<List<Message>> getMessagesForControleur(@PathVariable Integer idControleur) {
        return ResponseEntity.ok(messageService.getMessagesForUser(idControleur));
    }

    @GetMapping("/conversation/{userId}")
    public ResponseEntity<List<Message>> getConversation(@PathVariable Integer userId) {
        return ResponseEntity.ok(messageService.getConversation(userId));
    }
}
