package com.inventory.service;

import com.inventory.dto.ResponseDto;
import com.inventory.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {
    ResponseDto saveImage(MultipartFile file) throws IOException;

    Optional<ImageEntity> getImage(Integer id);
}
