package com.hamlet.whatsappclone.service;

import com.hamlet.whatsappclone.payload.response.ChatResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ChatService {

    List<ChatResponse> getChatsByReceiverId(Authentication currentUser);
    String createChat (String senderId, String receiverId);
}
