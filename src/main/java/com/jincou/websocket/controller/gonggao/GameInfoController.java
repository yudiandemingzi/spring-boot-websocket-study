package com.jincou.websocket.controller.gonggao;

import com.jincou.websocket.model.InMessage;
import com.jincou.websocket.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/*
 *模拟游戏公告
 */
@Controller
public class GameInfoController {

  //@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
  //如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    @MessageMapping("/gonggao/chat")
    @SendTo("/topic/game_chat")
    public OutMessage gameInfo(InMessage message){

        return new OutMessage(message.getContent());
    }

}
