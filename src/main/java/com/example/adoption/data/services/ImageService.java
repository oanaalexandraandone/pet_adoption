package com.example.adoption.data.services;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    void saveImage(int petID, MultipartFile file);

}
