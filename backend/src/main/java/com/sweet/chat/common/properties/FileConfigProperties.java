package com.sweet.chat.common.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file")
public class FileConfigProperties {
    private String uploadDir;
    private String maxSize;

    public Long getMaxByteSize() {
        if (maxSize.toUpperCase().endsWith("MB")) {
            return Long.parseLong(maxSize.replaceAll("[^0-9]", "")) * 1024 * 1024;
        } else if (maxSize.toUpperCase().endsWith("KB")) {
            return Long.parseLong(maxSize.replaceAll("[^0-9]", "")) * 1024;
        }
        return Long.parseLong(maxSize);
    }
}
