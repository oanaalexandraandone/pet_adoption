package com.example.adoption.web.controllers;

import com.example.adoption.data.models.Pet;
import com.example.adoption.data.models.Center;
import com.example.adoption.data.models.PetType;
import com.example.adoption.data.services.ImageService;
import com.example.adoption.data.services.PetService;
import com.example.adoption.data.services.PetTypeService;
import com.example.adoption.data.services.CenterService;
import com.example.adoption.web.securityConfig.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/pets")
public class PetController {

    PetService petService;
    CenterService centerService;
    PetTypeService petTypeService;
    ImageService imageService;

    public PetController(PetService petService, CenterService centerService, PetTypeService petTypeService, ImageService imageService) {
        this.petService = petService;
        this.centerService = centerService;
        this.petTypeService = petTypeService;
        this.imageService= imageService;
    }

    @ModelAttribute("types")
    Collection<PetType> getAllPetTypes(){
        return petTypeService.findAll();
    }

    @ModelAttribute("allPets")
    Collection<Pet> getAllPets(){
        return petService.findAll();
    }

/*    @ModelAttribute("center")
    public Center findCenter(@PathVariable("centerID") int centerID) {
        return centerService.findByID(centerID).get();
    }*/

    @GetMapping("/new")
    public String addNewPet(Model model){
        Pet pet = new Pet();
        model.addAttribute("pet", pet);
        return "pets/newPet";
    }

    @PostMapping("/new")
    public String addNewPet( @Valid Pet pet, BindingResult result, Model model){
        //todo reject duplicated values
        Center principal = new Center ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Center center=centerService.findByName(principal.getName()).get();
        center.getPets().add(pet);
        pet.setCenter(center);
        if(result.hasErrors()){
            model.addAttribute("error", pet);
            return "pets/newPet";
        }else {
            petService.save(pet);
        }
        String redirectURl= pet.getID()+ "/pictures";
        return "redirect: " + redirectURl;
    }

    @GetMapping("/find")
    public String initFindForm(Model model){
        model.addAttribute("pet", new Pet());
        //return "pets/findForm";
        return "home";
    }
    
    @PostMapping("/find")
    public String findPetByType(Pet pet, BindingResult result, Model model){
        System.out.println("gets in pots find meth");
        if(pet.getPetType()==null){
            result.rejectValue("petType", "pet type not provided");
            return "home";
            //return "pets/findForm";
        }
        List<Pet> results= petService.findAllByPetType(pet.getPetTypeName());
        if (results.isEmpty()){
            result.rejectValue("petType", "No pet available");
            System.out.println("erooooor");
            return "home";
            //return "pets/findForm";
        } else
            model.addAttribute("selections", results);
        System.out.println("we got some results");
        return "pets/listPets";
    }

    @PostMapping("/delete")
    public void deletePet(@Valid Pet pet){
        petService.deleteById(pet.getID());
    }

    @GetMapping("/{id}")
    public ModelAndView petPage(@PathVariable int id){
        ModelAndView mvc= new ModelAndView("pets/petPage");
        mvc.addObject("pet", petService.findByID(id).get());
        return mvc;
    }





}
