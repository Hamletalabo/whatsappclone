package com.hamlet.whatsappclone.service.impl;

import com.hamlet.whatsappclone.entity.Chat;
import com.hamlet.whatsappclone.payload.response.ChatResponse;
import com.hamlet.whatsappclone.service.ChatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ChatMapperImpl implements ChatMapper {

    @Override
    public ChatResponse ChatResponse(Chat chat, String userId) {
        return ChatResponse.builder()
                .id(chat.getId())
                .name(chat.getChatName(userId))
                .unreadCount(chat.getUnreadMessages(userId))
                .lastMessage(chat.getLastMessage())
                .isRecipientOnline(chat.getRecipient().isUserOneLine())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .build();
    }
}
