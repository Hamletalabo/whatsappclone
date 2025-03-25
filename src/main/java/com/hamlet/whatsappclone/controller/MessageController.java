package com.hamlet.whatsappclone.controller;

import com.hamlet.whatsappclone.payload.request.MessageRequest;
import com.hamlet.whatsappclone.payload.response.MessageResponse;
import com.hamlet.whatsappclone.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMessage(@RequestBody MessageRequest message){
        messageService.saveMessage(message);
    }

    @PostMapping(value = "/upload-media", consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadMedia(@RequestParam("chat-id") String chatId,
                            @RequestParam("file")MultipartFile file, Authentication authentication{
        messageService.uploadMediaMessage(chatId, file, authentication);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setMessagesToSeen(@RequestParam("chat-id") String chatId, Authentication authentication){
        messageService.setMessagesToSeen(chatId, authentication);
    }

    @GetMapping("/chat/{chat-id}")
    public ResponseEntity<List<MessageResponse>> getMessage(@PathVariable("chat-id") String chatId){
        return ResponseEntity.ok(messageService.findChatMessages(chatId));
    }
}
