package com.weigh.verification.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xuyang
 */
@Data
public class FileEntity {
    private String hash;
    private MultipartFile file;
}
