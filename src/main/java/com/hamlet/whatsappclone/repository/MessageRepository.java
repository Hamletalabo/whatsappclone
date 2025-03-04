package com.hamlet.whatsappclone.repository;

import com.hamlet.whatsappclone.constants.MessageConstants;
import com.hamlet.whatsappclone.entity.Chat;
import com.hamlet.whatsappclone.entity.Message;
import com.hamlet.whatsappclone.enums.MessageState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(name = MessageConstants.FIND_MESSAGE_BY_CHAT_ID)
    List<Message> findMessageByChatId(String chatId);

    @Query(name = MessageConstants.SET_MESSAGES_TO_SEEN_BY_CHAT)
    @Modifying
    void setMessageToSeenByChatId(@Param("chatId")String chat, @Param("newState") MessageState messageState);
}
