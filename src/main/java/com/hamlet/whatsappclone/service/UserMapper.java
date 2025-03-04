package com.hamlet.whatsappclone.service;

import com.hamlet.whatsappclone.entity.User;

import java.util.Map;

public interface UserMapper {

    User fromTokenAttributes(Map<String, Object> attributes);


}
