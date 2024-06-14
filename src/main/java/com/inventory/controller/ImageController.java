package com.inventory.controller;

import com.inventory.dto.ResponseDto;
import com.inventory.entity.ImageEntity;
import com.inventory.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    private ResponseDto saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.saveImage(file);
    }

    @GetMapping
    private ResponseEntity<?> getImage(@RequestParam("id") Integer id) {
        Optional<ImageEntity> image = imageService.getImage(id);
        if (image.isPresent()) {
            ImageEntity imageEntity = image.get();
            String contenttype = determineContent(imageEntity.getName());
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contenttype)).body(new ByteArrayResource(imageEntity.getData()));
        }
        return ResponseEntity.notFound().build();
    }

    private String determineContent(String name) {
        if (name.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else {
            return "application/octet-stream";
        }
    }
}
