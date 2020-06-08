package com.example.adoption.data.services;

import com.example.adoption.data.models.Pet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    PetService petService;

    public ImageServiceImpl(PetService petService) {
        this.petService = petService;
    }
    @Transactional
    @Override
    public void saveImage(int petID, MultipartFile file) {

        try{
            Pet pet= petService.findByID(petID).get();
            Byte[] pictureBytes= new Byte[file.getBytes().length];
            int i=0;
            for(byte b: file.getBytes()){
                pictureBytes[i++]=b;
            }
            pet.setImage(pictureBytes);
            petService.save(pet);
        } catch (IOException e) {
            //todo get better impl here
            e.printStackTrace();
        }
    }

}
