package com.sweet.chat.common.dto;

import com.sweet.chat.common.UploadFile;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String originalFileName;
    private String filePath;
    private Long fileSize;

    public static FileDto from(UploadFile uploadFile) {
        return FileDto.builder()
                .id(uploadFile.getId())
                .originalFileName(uploadFile.getOriginalFileName())
                .filePath(uploadFile.getStoredFileName())
                .fileSize(uploadFile.getFileSize())
                .build();
    }
}
