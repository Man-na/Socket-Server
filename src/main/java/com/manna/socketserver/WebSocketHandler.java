package com.manna.socketserver;

import com.manna.socketserver.dto.Message;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message clientMessage = objectMapper.readValue(message.getPayload(), Message.class);

        Message responseMessage = new Message(clientMessage.getId(), "Echo: " + clientMessage.getMessage());
        String responsePayload = objectMapper.writeValueAsString(responseMessage);

        session.sendMessage(new TextMessage(responsePayload));
    }

}