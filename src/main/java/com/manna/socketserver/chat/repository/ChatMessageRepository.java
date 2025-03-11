package com.manna.socketserver.chat.repository;

import com.manna.socketserver.chat.dto.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}