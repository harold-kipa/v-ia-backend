package com.v_ia_backend.kipa.service.interfaces;

import org.springframework.stereotype.Service;

import com.v_ia_backend.kipa.dto.request.ChatHistoryRequest;
import com.v_ia_backend.kipa.entity.ChatHistory;
import com.v_ia_backend.kipa.modelview.ResponseMessage;

import java.util.List;

@Service
public interface ChatHistoryService {
    // ChatHistory getChatHistoryById(Long id);
    List<ChatHistory> getAllChatHistory();
    List<ChatHistory> getChatHistoryByUserId(Long userId);
    ChatHistory createChatHistory(ChatHistoryRequest request);
    ChatHistory getChatHistoryById(Long Id);
    ResponseMessage deleteChatHistoryById(Long id);
    ChatHistory updateChatHistory(ChatHistoryRequest request, Long id);
}
