package com.sweet.chat.common;

import com.sweet.chat.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity @Table(name = "upload_files")
public class UploadFile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String originalFileName;
    @Column
    private String storedFileName;
    @Column
    private String fileExtension;
    @Column
    private Long fileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    private User uploader;

    @Enumerated(EnumType.STRING)
    private AccessType accessType = AccessType.PUBLIC;

    @Enumerated(EnumType.STRING)
    private UploadType uploadType = UploadType.ETC;

    public enum AccessType {
        PUBLIC, PRIVATE
    }

    public enum UploadType {
        PROFILE, CHAT, POST, COMMENT, ATTACHMENT, ETC
    }
}
