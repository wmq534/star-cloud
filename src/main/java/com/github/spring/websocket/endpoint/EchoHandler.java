package com.github.spring.websocket.endpoint;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@Service
public class EchoHandler extends TextWebSocketHandler {  
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    super.afterConnectionEstablished(session);
    System.out.println("afterConnectionEstablished");
  }
  @Override  
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
    super.handleTextMessage(session, message);
    System.out.println("message="+message.toString());
    TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
    session.sendMessage(returnMessage);  
  }  
  
}  