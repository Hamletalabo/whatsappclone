package com.hamlet.whatsappclone.service.impl;

import com.hamlet.whatsappclone.entity.User;
import com.hamlet.whatsappclone.service.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserMapperImpl implements UserMapper {
    @Override
    public User fromTokenAttributes(Map<String, Object> attributes) {

        User user = new User();
        if (attributes.containsKey("sub")){
            user.setId(attributes.get("sub").toString());
        }

        if (attributes.containsKey("given_name")){
            user.setFirstname(attributes.get("given_name").toString());
        }else if (attributes.containsKey("nickname")){
            user.setFirstname(attributes.get("nickname").toString());
        }

        if (attributes.containsKey("family_name")){
            user.setLastname((attributes.get("family_name").toString()));
        }

        if (attributes.containsKey("email")){
            user.setEmail((attributes.get("email").toString()));
        }

        user.setLastSeen(LocalDateTime.now());

        return user;
    }
}
