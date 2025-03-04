package com.hamlet.whatsappclone.payload.response;

import com.hamlet.whatsappclone.enums.MessageState;
import com.hamlet.whatsappclone.enums.MessageType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private String id;
    private String content;
    private MessageType messageType;
    private MessageState messageState;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    private byte [] media;
}
