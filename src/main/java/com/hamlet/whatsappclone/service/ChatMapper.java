package com.hamlet.whatsappclone.service;

import com.hamlet.whatsappclone.entity.Chat;
import com.hamlet.whatsappclone.payload.response.ChatResponse;

public interface ChatMapper {

    ChatResponse ChatResponse(Chat chat, String userId);
}
