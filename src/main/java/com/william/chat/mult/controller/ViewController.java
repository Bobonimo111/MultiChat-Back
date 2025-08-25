package com.william.chat.mult.controller;

import com.william.chat.mult.model.ChatModel;
import com.william.chat.mult.model.MessageModel;
import com.william.chat.mult.model.UserModel;
import com.william.chat.mult.repository.ChatRepository;
import com.william.chat.mult.repository.MessageRepository;
import com.william.chat.mult.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("application")
public class ViewController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatRepository chatRepository;

    @GetMapping("home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping("login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("chat")
    public ModelAndView chat(@RequestParam(name = "chatid",required = true) UUID chatId){
    //A variavel é utilizada apenas para controlar o acesso a pagina;

        ModelAndView mv = new ModelAndView("chat");
        return mv;
    }


}
