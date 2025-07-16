package com.william.chat.mult.controller;

import com.william.chat.mult.object.MessageObj;
import com.william.chat.mult.object.ResponseObj;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/message")
    @SendTo("/topics/message")
    public ResponseObj messageController(MessageObj obj){
        return new ResponseObj(obj.getMessage());
    }
}
