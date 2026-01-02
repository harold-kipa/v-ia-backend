package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ChatHistoryRequest;
import com.v_ia_backend.kipa.entity.ChatHistory;

import java.util.List;

@Service
public interface ChatHistoryService {
    // ChatHistory getChatHistoryById(Long id);
    List<ChatHistory> getAllChatHistory();

    ChatHistory createChatHistory(ChatHistoryRequest request);
}
