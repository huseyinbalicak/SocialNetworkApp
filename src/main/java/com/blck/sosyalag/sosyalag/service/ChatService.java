package com.blck.sosyalag.sosyalag.service;

import com.blck.sosyalag.sosyalag.model.Chat;
import com.blck.sosyalag.sosyalag.repo.ChatRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepo chatRepo;


    public ChatService(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    public List<Chat> findChats(String sen,String rec)
    {
        return chatRepo.findBySenderAndReceiver(sen,rec);
    }

    public void save(Chat chat) {

        chatRepo.save(chat);
    }
}
