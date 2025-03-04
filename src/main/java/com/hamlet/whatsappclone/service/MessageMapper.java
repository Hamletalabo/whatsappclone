package com.hamlet.whatsappclone.service;

import com.hamlet.whatsappclone.entity.Message;
import com.hamlet.whatsappclone.payload.response.MessageResponse;

public interface MessageMapper {

    MessageResponse toMessageResponse(Message message);
}
