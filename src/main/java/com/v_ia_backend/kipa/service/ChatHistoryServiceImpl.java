package com.v_ia_backend.kipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ChatHistoryRequest;
import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.ChatHistory;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.repository.ChatHistoryRepository;
import com.v_ia_backend.kipa.service.interfaces.ChatHistoryService;


@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {
    @Value("${status.deleted}")
    private Long delete;
    @Value("${status.active}")
    private Long active;

    private final MessageSource messageSource;
    private final ChatHistoryRepository ChatHistoryRepository;
    private final UserServiceImpl userService;
    public ChatHistoryServiceImpl(MessageSource messageSource, ChatHistoryRepository ChatHistoryRepository, UserServiceImpl userService) {
        this.messageSource = messageSource;
        this.ChatHistoryRepository = ChatHistoryRepository;
        this.userService = userService;
    }
    public ChatHistory createChatHistory(ChatHistoryRequest request){
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setChatHistoryName(request.getChatHistoryName());
        chatHistory.setConversationIdentifier(request.getConversationIdentifier());
        chatHistory.setUserId(userService.getUserById(request.getUserId()));
        
        return ChatHistoryRepository.save(chatHistory);
    }
    public List<ChatHistory> getAllChatHistory() {
        List<ChatHistory> chatHistory = ChatHistoryRepository.findAll();
        return chatHistory;
    }
}
