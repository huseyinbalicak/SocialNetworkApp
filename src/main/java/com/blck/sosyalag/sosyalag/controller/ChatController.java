package com.blck.sosyalag.sosyalag.controller;

import com.blck.sosyalag.sosyalag.model.Chat;
import com.blck.sosyalag.sosyalag.model.User;
import com.blck.sosyalag.sosyalag.service.ChatService;
import com.blck.sosyalag.sosyalag.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private  final UserService userService;


    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String chat(@Param("rec") String rec, Model model, @ModelAttribute("user") User user) {
        model.addAttribute("rec_email", rec);
        model.addAttribute("sen_email", user.getEmail());
        User tempUser = userService.fetchUserByEmail(rec);
        model.addAttribute("rec_name", tempUser.getFullName());
        return "chat";
    }

    @GetMapping("/get-message")
    @ResponseBody
    public List<Chat> getMessage(@Param("sen") String sender,
                                 @Param("rec") String receiver)
    {
        List<Chat> chats1=chatService.findChats(sender,receiver);
        List <Chat> chats2=chatService.findChats(receiver,sender);
        List<Chat> allChats = new ArrayList<>();
        allChats.addAll(chats1);
        allChats.addAll(chats2);
        return allChats;
    }

    @PostMapping("/save-message")
    @ResponseBody
    public String saveMessage(@Param("sen") String sender,
                              @Param("rec") String receiver,
                              @Param("msg") String msg)
    {
        Chat ps= new Chat(sender,receiver,msg);
        chatService.save(ps);
        return "done";
    }










}
