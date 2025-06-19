package com.sweet.chat.common.api;

import com.sweet.chat.common.dto.FileDto;
import com.sweet.chat.common.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/api/files/profile")
    public ResponseEntity<FileDto> uploadProfileImage(
            @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(file, userDetails.getUsername())); // 프론트는 이 이름을 저장해두면 됨
    }
}
