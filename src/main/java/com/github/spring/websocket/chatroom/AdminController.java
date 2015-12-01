package com.github.spring.websocket.chatroom;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

@Controller
public class AdminController {
 
    static Logger logger = LoggerFactory.getLogger(AdminController.class);
 
    @Autowired(required = false)
    private WebSocketService websocketService;
 
    @Bean
    public SystemWebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
 
 
    @RequestMapping("/auditing")
    @ResponseBody
    public String auditing(HttpServletRequest request,String username){
        //无关代码都省略了
        int unReadNewsCount = websocketService.getUnReadNews(username);
        systemWebSocketHandler().sendMessageToUser(username, new TextMessage(unReadNewsCount + ""));
        return null;
    }
}