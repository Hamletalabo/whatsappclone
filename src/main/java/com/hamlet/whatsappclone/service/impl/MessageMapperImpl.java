package com.hamlet.whatsappclone.service.impl;

import com.hamlet.whatsappclone.entity.Message;
import com.hamlet.whatsappclone.payload.response.MessageResponse;
import com.hamlet.whatsappclone.service.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .content(message.getContent())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .messageType(message.getMessageType())
                .messageState(message.getState())
                .createdAt(message.getCreatedDate())
                // todo read the media file
                .build();
    }
}
