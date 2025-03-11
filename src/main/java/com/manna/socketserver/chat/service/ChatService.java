package com.manna.socketserver.chat.service;

import com.manna.socketserver.chat.dto.ChatMessage;

public interface ChatService {
    void saveChatMessage(ChatMessage chatMessage);
}