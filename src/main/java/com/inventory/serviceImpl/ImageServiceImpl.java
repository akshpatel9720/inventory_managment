package com.inventory.serviceImpl;

import com.inventory.dto.ResponseDto;
import com.inventory.entity.ImageEntity;
import com.inventory.repo.ImageRepo;
import com.inventory.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepo imageRepo;

    @Override
    public ResponseDto saveImage(MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        imageRepo.save(image);
        return new ResponseDto(HttpStatus.OK.value(), "Image Uploaded successfullt", null);
    }

    @Override
    public Optional<ImageEntity> getImage(Integer id) {
        return imageRepo.findById(id);
    }


}
