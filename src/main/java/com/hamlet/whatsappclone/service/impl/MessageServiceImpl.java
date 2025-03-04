package com.hamlet.whatsappclone.service.impl;

import com.hamlet.whatsappclone.entity.Chat;
import com.hamlet.whatsappclone.entity.Message;
import com.hamlet.whatsappclone.enums.MessageState;
import com.hamlet.whatsappclone.payload.request.MessageRequest;
import com.hamlet.whatsappclone.payload.response.MessageResponse;
import com.hamlet.whatsappclone.repository.ChatRepository;
import com.hamlet.whatsappclone.repository.MessageRepository;
import com.hamlet.whatsappclone.service.FileService;
import com.hamlet.whatsappclone.service.MessageMapper;
import com.hamlet.whatsappclone.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageMapper mapper;
    private final FileService fileService;

    @Override
    public void saveMessage(MessageRequest messageRequest) {

        Chat chat = chatRepository.findById(messageRequest.getChatId())
                .orElseThrow(()-> new EntityNotFoundException("Chat not found"));

        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setChat(chat);
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setMessageType(messageRequest.getMessageType());
        message.setState(MessageState.SENT);

        messageRepository.save(message);

        // todo notification
    }

    @Override
    public List<MessageResponse> findChatMessages(String chatId) {
        return messageRepository.findMessageByChatId(chatId)
                .stream()
                .map(mapper::toMessageResponse)
                .toList();
    }

    @Override
    @Transactional
    public void setMessagesToSeen(String chatId, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        //final String recipientId = getRecipientId(chat, authentication);

        messageRepository.setMessageToSeenByChatId(chatId, MessageState.SEEN);
    }

    @Override
    public void uploadMediaMessage(String chatId, MultipartFile file, Authentication authentication) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        final String senderId = getSenderId(chat, authentication);
        final String receiverId = getRecipientId(chat, authentication);

        final String filePath = fileService.save(file, senderId);
    }

    private String getSenderId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())){
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }

    private String getRecipientId(Chat chat, Authentication authentication) {
        if (chat.getSender().getId().equals(authentication.getName())){
            return chat.getRecipient().getId();
        }
        return chat.getSender().getId();
    }


}
