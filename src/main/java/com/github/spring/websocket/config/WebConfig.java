package com.github.spring.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.github.spring.websocket.endpoint.EchoHandler;
import com.github.spring.websocket.interceptor.HandshakeInterceptor;

@Configuration  
//@EnableWebMvc  
@EnableWebSocket//开启websocket  
public class WebConfig implements WebSocketConfigurer {  
  @Autowired
  private EchoHandler echoHandler;
  @Autowired
  private HandshakeInterceptor handshakeInterceptor;
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
      registry.addHandler(echoHandler, "/echo").addInterceptors(handshakeInterceptor);; //提供符合W3C标准的Websocket数据  
      registry.addHandler(echoHandler, "/sockjs/echo").addInterceptors(handshakeInterceptor).withSockJS();//提供符合SockJS的数据  
  }
}