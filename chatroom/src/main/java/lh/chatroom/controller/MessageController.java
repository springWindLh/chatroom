package lh.chatroom.controller;

import com.alibaba.fastjson.JSON;
import lh.chatroom.form.SingleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by lh on 2016/7/6.
 */
@Controller
@MessageMapping("/message")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/room")
//    @SendTo("/topic/notice")
    public void roomMessageHandler(String singleMessage) {
        SingleMessage message = JSON.parseObject(singleMessage, SingleMessage.class);
        messagingTemplate.convertAndSend("/topic/room/" + message.getRoomId(), message.getContent());
    }

    @MessageMapping("/user")
    public void userMessageHandler(String singleMessage) {
        SingleMessage message = JSON.parseObject(singleMessage, SingleMessage.class);
        messagingTemplate.convertAndSend("/topic/user/" + message.getRoomId(), message.getContent());
    }
}
