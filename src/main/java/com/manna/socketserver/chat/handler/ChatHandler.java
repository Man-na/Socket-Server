package com.manna.socketserver.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.manna.socketserver.chat.dto.ChatMessage;
import com.manna.socketserver.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatMessage clientChatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

        chatService.saveChatMessage(clientChatMessage);

        ChatMessage responseChatMessage = new ChatMessage(
                clientChatMessage.getId(),
                clientChatMessage.getChatRoomId(),
                clientChatMessage.getSenderId(),
                "Echo: " + clientChatMessage.getContent(),
                LocalDateTime.now()
        );
        String responsePayload = objectMapper.writeValueAsString(responseChatMessage);

        session.sendMessage(new TextMessage(responsePayload));
    }
}