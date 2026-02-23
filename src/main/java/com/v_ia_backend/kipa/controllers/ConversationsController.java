package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.dto.request.ConversationsRequest;
import com.v_ia_backend.kipa.entity.Conversations;
import com.v_ia_backend.kipa.service.ConversationsServiceImpl;

@RestController
@RequestMapping("/conversations")
public class ConversationsController {

    @Autowired
    private ConversationsServiceImpl chatHistoryService;

    public ConversationsController(ConversationsServiceImpl chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createConversationsController(@RequestBody ConversationsRequest request) {
        Conversations chatHistory = chatHistoryService.createConversations(request);
        return ResponseEntity.ok(chatHistory);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllConversationsController() {
        return ResponseEntity.ok(chatHistoryService.getAllConversations());
    }

    @GetMapping("/get/chat/{id}")
    public ResponseEntity<Object> getConversationsByChatHistoryIdController(@PathVariable Long id) {
        return ResponseEntity.ok(chatHistoryService.getConversationsByChatHistoryId(id));
    }
}
