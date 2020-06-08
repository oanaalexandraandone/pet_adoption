package com.example.adoption.web.controllers;


import com.example.adoption.data.models.Pet;
import com.example.adoption.data.services.ImageService;
import com.example.adoption.data.services.PetService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    PetService petService;
    ImageService imageService;

    public ImageController(PetService petService, ImageService imageService) {
        this.petService = petService;
        this.imageService = imageService;
    }


    @GetMapping("/pets/{id}/pictures")
    public String uploadForm(@PathVariable int id, Model model){
        model.addAttribute("pet", petService.findByID(id).get());
        return "pets/petPage";
    }


    @PostMapping("/pets/{id}/pictures")
    public String saveUploadedImage(@PathVariable int id, @Valid MultipartFile image){
        imageService.saveImage(id, image);
        return "home";
    }


    @GetMapping("/pets/{id}/PetPicture")
    public void showPicture(@PathVariable int id, HttpServletResponse response) throws IOException {

        Pet pet= petService.findByID(id).get();
        if (pet.getImage()!=null){
            byte[] byteArray = new byte[pet.getImage().length];
            int i = 0;

            for (Byte wrappedByte : pet.getImage()){
                byteArray[i++] = wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

}
