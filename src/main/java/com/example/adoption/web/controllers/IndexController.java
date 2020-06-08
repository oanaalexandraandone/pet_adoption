package com.example.adoption.web.controllers;

import com.example.adoption.data.models.Center;
import com.example.adoption.data.models.Pet;
import com.example.adoption.data.models.PetType;
import com.example.adoption.data.services.CenterService;
import com.example.adoption.data.services.PetService;
import com.example.adoption.data.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class IndexController {

    public IndexController(PetService petService, PetTypeService petTypeService, CenterService centerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.centerService = centerService;
    }

    private PetService petService;
    private PetTypeService petTypeService;
    private CenterService centerService;


    @ModelAttribute("pets")
    Collection<Pet> getAllPets(){
        return petService.findAll();
    }

    @ModelAttribute("centers")
    Collection<Center> getAllCenters(){
        return centerService.findAll();
    }

    @ModelAttribute ("petTypes")
    Collection<PetType> getAllPetTypes(){
        return petTypeService.findAll();
    }


    @RequestMapping({"", "/", "/home", "/index"})
    public String index(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
