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
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message-center")
//    @SendTo("/topic/notice")
    public void messageHandler(String singleMessage) {
        SingleMessage message = JSON.parseObject(singleMessage,SingleMessage.class);
        messagingTemplate.convertAndSend("/topic/notice/" + message.getRoomId(), message.getContent());
    }
}
