package com.oujiong.websocket.controller.ptp;


import com.oujiong.websocket.model.InMessage;
import com.oujiong.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * 功能描述：简单版单人聊天
 * 这里没有用到@SendTo("/topic/game_chat")来指定订阅地址，而是通过SimpMessagingTemplate来指定
 */
@Controller
public class PTPContoller {

    @Autowired
    private WebSocketService ws;

    @MessageMapping("/ptp/single/chat")
    public void singleChat(InMessage message) {
        ws.sendChatMessage(message);
    }

}