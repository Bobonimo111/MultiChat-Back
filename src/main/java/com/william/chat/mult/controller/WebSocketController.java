package com.william.chat.mult.controller;

import com.william.chat.mult.dto.chat.MessageRec;
import com.william.chat.mult.dto.chat.ResponseRec;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/message")
    @SendTo("/topics/message")
    public ResponseRec messageController(MessageRec obj){
        return new ResponseRec("User name:" +  obj.username() + " message :" + "{obj.message()}");
    }
}
