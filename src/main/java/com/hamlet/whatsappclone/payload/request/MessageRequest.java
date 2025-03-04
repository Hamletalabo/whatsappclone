package com.hamlet.whatsappclone.payload.request;

import com.hamlet.whatsappclone.enums.MessageType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {

    private String content;
    private String senderId;
    private String receiverId;
    private MessageType messageType;
    private String chatId;


}
