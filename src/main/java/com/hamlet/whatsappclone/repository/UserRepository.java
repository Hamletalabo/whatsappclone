package com.hamlet.whatsappclone.repository;

import com.hamlet.whatsappclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
