package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.dto.request.ChatHistoryRequest;
import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.ChatHistory;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.service.ChatHistoryServiceImpl;

@RestController
@RequestMapping("/chat")
public class ChatHistoryController {

    @Autowired
    private ChatHistoryServiceImpl chatHistoryService;

    public ChatHistoryController(ChatHistoryServiceImpl chatHistoryService) {
        this.chatHistoryService = chatHistoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createChatHistoryController(@RequestBody ChatHistoryRequest request) {
        ChatHistory chatHistory = chatHistoryService.createChatHistory(request);
        return ResponseEntity.ok(chatHistory);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllChatHistoryController() {
        return ResponseEntity.ok(chatHistoryService.getAllChatHistory());
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<Object> getAllChatHistoryController(@PathVariable Long id) {
        return ResponseEntity.ok(chatHistoryService.getChatHistoryByUserId(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateChatHistoryController(@PathVariable Long id, @RequestBody ChatHistoryRequest request) {
        ChatHistory updatedChatHistory = chatHistoryService.updateChatHistory(request, id);
        return ResponseEntity.ok(updatedChatHistory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteChatHistoryByIdController(@PathVariable Long id){
        return ResponseEntity.ok(chatHistoryService.deleteChatHistoryById(id));
    }

}
