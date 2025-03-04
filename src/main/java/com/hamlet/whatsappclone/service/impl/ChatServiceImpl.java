package com.hamlet.whatsappclone.service.impl;

import com.hamlet.whatsappclone.entity.Chat;
import com.hamlet.whatsappclone.entity.User;
import com.hamlet.whatsappclone.payload.response.ChatResponse;
import com.hamlet.whatsappclone.repository.ChatRepository;
import com.hamlet.whatsappclone.repository.UserRepository;
import com.hamlet.whatsappclone.service.ChatMapper;
import com.hamlet.whatsappclone.service.ChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;


    @Override
    @Transactional(readOnly = true)
    public List<ChatResponse> getChatsByReceiverId(Authentication currentUser) {

        final String userId = currentUser.getName();

        return chatRepository.findChatBySenderId(userId)
                .stream()
                .map(chat -> chatMapper.ChatResponse(chat, userId))
                .toList();
    }

    @Override
    public String createChat(String senderId, String receiverId) {

        Optional<Chat> existingChat = chatRepository.findChatByReceiverAndSender(senderId, receiverId);
        if (existingChat.isPresent()){
            return existingChat.get().getId();
        }

        User sender = userRepository.findPublicId(senderId)
                .orElseThrow(()-> new EntityNotFoundException("User with id " + senderId + "not found"));
        User receiver = userRepository.findPublicId(receiverId)
                .orElseThrow(()-> new EntityNotFoundException("User with id " + receiverId + "not found"));

        Chat chat = new Chat();
        chat.setSender(sender);
        chat.setRecipient(receiver);

        Chat savedChat = chatRepository.save(chat);

        return savedChat.getId();
    }


}
