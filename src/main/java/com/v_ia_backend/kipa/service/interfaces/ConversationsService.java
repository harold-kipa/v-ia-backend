package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ConversationsRequest;
import com.v_ia_backend.kipa.entity.Conversations;

import java.util.List;

@Service
public interface ConversationsService {
    // Conversations getConversationsById(Long id);
    List<Conversations> getAllConversations();
    List<Conversations> getConversationsByUserId(Long userId);
    Conversations createConversations(ConversationsRequest request);
}
