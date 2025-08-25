package com.william.chat.mult.controller;

import com.william.chat.mult.dto.chat.MessageRec;
import com.william.chat.mult.dto.chat.ResponseRec;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/sendMessage") // se une a rota no setApplicationDestinationPrefixes
    @SendTo("/web-chat/message") // Aonde a mesagem é retornada para o front por meio de um subscribe
    public ResponseRec messageController(MessageRec obj){
        return new ResponseRec("User name:" +  obj.username() + " message :" + "{obj.message()}");
    }

    //@MessageMapping()
}
