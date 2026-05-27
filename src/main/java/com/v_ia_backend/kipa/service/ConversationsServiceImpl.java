package com.v_ia_backend.kipa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ConversationsRequest;
import com.v_ia_backend.kipa.entity.Conversations;
import com.v_ia_backend.kipa.repository.ConversationsRepository;
import com.v_ia_backend.kipa.service.interfaces.ConversationsService;


@Service
public class ConversationsServiceImpl implements ConversationsService {
    @Value("${status.deleted}")
    private Long delete;
    @Value("${status.active}")
    private Long active;

    private final ConversationsRepository conversationsRepository;
    private final ChatHistoryServiceImpl chatHistory;
    public ConversationsServiceImpl(ConversationsRepository conversationsRepository, ChatHistoryServiceImpl chatHistory) {
        this.conversationsRepository = conversationsRepository;
        this.chatHistory = chatHistory;
    }
    public Conversations createConversations(ConversationsRequest request){
        Conversations conversations = new Conversations();
        conversations.setFrom(request.getFrom().trim());
        
        conversations.setText(request.getText());
        conversations.setChatHistoryId(chatHistory.getChatHistoryById(request.getChatHistoryId()));
        
        return conversationsRepository.save(conversations);
    }

    public List<Conversations> getConversationsByChatHistoryId(Long userId) {
        return conversationsRepository.findByChatHistoryId_Id(userId);
    }

    public List<Conversations> getAllConversations() {
        return conversationsRepository.findAll();
    }
}
