package com.william.chat.mult.controller;

import com.william.chat.mult.object.MessageRec;
import com.william.chat.mult.object.ResponseRec;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/message")
    @SendTo("/topics/message")
    public ResponseRec messageController(MessageRec obj){
        return new ResponseRec(STR."User name: \{obj.username()} message : \{obj.message()}");
    }
}
