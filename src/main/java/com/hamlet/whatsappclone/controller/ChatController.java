package com.hamlet.whatsappclone.controller;

import com.hamlet.whatsappclone.payload.response.ChatResponse;
import com.hamlet.whatsappclone.payload.response.CreateChatResponse;
import com.hamlet.whatsappclone.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<CreateChatResponse> createChat(@RequestParam(name = "sender-id") String senderId,
                                                         @RequestParam(name = "receiver-id") String receiverId

    ){
        final String chatId = chatService.createChat(senderId, receiverId);
        CreateChatResponse response = CreateChatResponse.builder()
                .response(chatId)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ChatResponse>> getChatsByReceiver(Authentication authentication){
        return ResponseEntity.ok(chatService.getChatsByReceiverId(authentication));
    }
}
