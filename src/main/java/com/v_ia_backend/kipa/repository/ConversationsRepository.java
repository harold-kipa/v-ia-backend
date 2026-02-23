package com.v_ia_backend.kipa.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v_ia_backend.kipa.entity.Conversations;

@Repository
public interface ConversationsRepository extends JpaRepository<Conversations, Long> {
    List<Conversations> findByChatHistoryId_Id(Long userId);
}
