package com.sweet.chat.common.service;

import com.sweet.chat.common.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileDto uploadFile(MultipartFile file, String reqUserId) throws IOException;
}
