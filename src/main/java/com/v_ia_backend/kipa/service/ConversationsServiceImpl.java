package com.v_ia_backend.kipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ConversationsRequest;
import com.v_ia_backend.kipa.dto.request.UsersRequest;
import com.v_ia_backend.kipa.entity.Conversations;
import com.v_ia_backend.kipa.entity.Users;
import com.v_ia_backend.kipa.repository.ConversationsRepository;
import com.v_ia_backend.kipa.service.interfaces.ConversationsService;


@Service
public class ConversationsServiceImpl implements ConversationsService {
    @Value("${status.deleted}")
    private Long delete;
    @Value("${status.active}")
    private Long active;

    private final MessageSource messageSource;
    private final ConversationsRepository conversationsRepository;
    private final UserServiceImpl userService;
    public ConversationsServiceImpl(MessageSource messageSource, ConversationsRepository conversationsRepository, UserServiceImpl userService) {
        this.messageSource = messageSource;
        this.conversationsRepository = conversationsRepository;
        this.userService = userService;
    }
    public Conversations createConversations(ConversationsRequest request){
        Conversations conversations = new Conversations();
        conversations.setChatHistoryName(request.getChatHistoryName());
        conversations.setConversationIdentifier(request.getConversationIdentifier());
        conversations.setUserId(userService.getUserById(request.getUserId()));
        
        return conversationsRepository.save(conversations);
    }

    public List<Conversations> getConversationsByUserId(Long userId) {
        List<Conversations> conversations = conversationsRepository.findByUserId_Id(userId);
        return conversations;
    }
    
    public List<Conversations> getAllConversations() {
        List<Conversations> conversations = conversationsRepository.findAll();
        return conversations;
    }
}
