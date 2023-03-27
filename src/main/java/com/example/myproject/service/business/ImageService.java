package com.example.myproject.service.business;


import com.example.myproject.domain.entity.ImageEntity;
import com.example.myproject.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity createImage(String fileName) {
       return this.imageRepository.saveAndFlush(new ImageEntity().setName(fileName));
    }

}
