package com.hamlet.whatsappclone.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file, String senderId);
}
