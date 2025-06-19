package com.sweet.chat.common.service;

import com.sweet.chat.common.UploadFile;
import com.sweet.chat.common.UploadFile.UploadType;
import com.sweet.chat.common.dto.FileDto;
import com.sweet.chat.common.exception.FileUploadFailureException;
import com.sweet.chat.common.properties.FileConfigProperties;
import com.sweet.chat.common.repository.UploadFileRepository;
import com.sweet.chat.user.domain.User;
import com.sweet.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {
    private final FileConfigProperties fileConfigProperties;
    private final UploadFileRepository uploadFileRepository;
    private final UserRepository userRepository;

    public FileDto uploadFile(MultipartFile file, String reqUserId) throws IOException {
        String uploadDir = fileConfigProperties.getUploadDir();
        Long maxFileSize = fileConfigProperties.getMaxByteSize();
        if (file.isEmpty()) {
            throw new FileUploadFailureException("파일이 비어 있습니다.");
        }

        if (file.getSize() > maxFileSize) {
            throw new FileUploadFailureException("파일이 너무 큽니다.");
        }

        // 파일명 및 확장자 보안 검사
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = StringUtils.getFilenameExtension(originalFilename);
        if (extension == null || extension.isEmpty()) {
            throw new FileUploadFailureException("파일 확장자가 없습니다.");
        }

        extension = extension.toLowerCase(Locale.ROOT);

        if (!List.of("jpg", "jpeg", "png", "gif", "webp").contains(extension)) {
            throw new FileUploadFailureException("허용되지 않은 파일 형식입니다.");
        }

        // 디렉터리 traversal 방지
        if (originalFilename.contains("..")) {
            throw new FileUploadFailureException("파일명에 '..'을 포함할 수 없습니다.");
        }

        // 파일 저장
        String filename = UUID.randomUUID() + "." + extension;
        Path filePath = Paths.get(uploadDir, filename);
        Files.copy(file.getInputStream(), filePath);

        User reqUser = userRepository.findByLoginId(reqUserId)
                .orElseThrow(() -> new FileUploadFailureException("사용자를 찾을 수 없습니다."));

        UploadFile uploadFile = UploadFile.builder()
                .originalFileName(originalFilename)
                .storedFileName(filename)
                .fileExtension(extension)
                .fileSize(file.getSize())
                .uploader(reqUser)
                .uploadType(UploadType.PROFILE)
                .build();

        uploadFileRepository.save(uploadFile);

        return FileDto.from(uploadFile);
    }
}
