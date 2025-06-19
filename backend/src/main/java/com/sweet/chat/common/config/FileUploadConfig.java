package com.sweet.chat.common.config;

import com.sweet.chat.common.properties.FileConfigProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class FileUploadConfig {
    private final FileConfigProperties fileConfigProperties;

    @PostConstruct
    public void init() {
        File dir = new File(fileConfigProperties.getUploadDir());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
