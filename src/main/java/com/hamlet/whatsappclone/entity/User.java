package com.hamlet.whatsappclone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseAuditingEntity{

    private static final int LAST_ACTIVE_INTERVAL = 5;
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime lastSeen;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatsAsSender;

    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatsAsReceiver;

    @Transient
    public boolean isUserOneLine(){
        // lastSeen = 10: 05
        // now 10: 09 --> online
        // now = 10:12 --> offline
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now()
                .minusMinutes(LAST_ACTIVE_INTERVAL));
    }
}
