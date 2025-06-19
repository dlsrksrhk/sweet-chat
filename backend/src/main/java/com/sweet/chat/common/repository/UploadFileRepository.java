package com.sweet.chat.common.repository;

import com.sweet.chat.common.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
}