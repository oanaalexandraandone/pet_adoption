package com.example.adoption.web.controllers;


import com.example.adoption.data.models.Center;
import com.example.adoption.data.models.PasswordDTO;
import com.example.adoption.data.services.CenterService;
import com.example.adoption.data.services.RoleService;
import com.example.adoption.data.models.Role;
import com.example.adoption.web.errors.EmailAlreadyExistsException;
import com.example.adoption.web.errors.PasswordAlreadyUsedException;
import com.example.adoption.web.mail.RegistrationConfirmed;
import com.example.adoption.web.securityConfig.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Set;

@Controller
@RequestMapping("/centers")
@Slf4j
public class CenterController {

    CenterService centerService;
    RoleService roleService;
    RegistrationConfirmed mail;


    public CenterController(CenterService centerService, RoleService roleService, RegistrationConfirmed mail) {
        this.centerService = centerService;
        this.roleService=roleService;
        this.mail=mail;
    }

    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/register")
    public String registration(Model model){
        model.addAttribute("centerForm", new Center());
        return "centers/registrationCenter";
    }


    @PostMapping("/register")
    public ModelAndView registration(@ModelAttribute ("centerForm") Center center, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("centers/registrationCenter", "centerForm", center);
        }
        try {
            Role role = roleService.findByName("center");
            center.setRoles(Arrays.asList(role));
            role.setCenters(Arrays.asList(center));
            centerService.save(center);
            Authentication authentication= new UsernamePasswordAuthenticationToken(center, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(EmailAlreadyExistsException exception){
            //todo working, add show error message in HTML
            ModelAndView mvc= new ModelAndView("centers/registrationCenter", "centerForm", center);
            mvc.addObject("message", exception.getMessage());
            return mvc;
        }
        mail.send(center.getEmail());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ModelAndView("redirect:/home", "centerForm", center);
    }

    @GetMapping ("/all")
    public String getAllCenters(Model model) {
        Set<Center> results= centerService.findAll();
            model.addAttribute("selections", results);
            return "centers/listCenters";
    }


   @GetMapping("/{centerId}")
    public ModelAndView showCenter(@PathVariable int centerId) {
        ModelAndView mav = new ModelAndView("centers/centerPage");
        mav.addObject("center", centerService.findByID(centerId).get());
        return mav;
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model){
        model.addAttribute("passwordDTO", new PasswordDTO());
        return "centers/changePassword";
    }

    @PostMapping ("/changePassword")
    public ModelAndView changePassword(@Valid PasswordDTO passwordDTO){
        ModelAndView mvc;
        Center principal= new Center((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Center center= centerService.findByName(principal.getName()).get();

        try{
            if(! centerService.checkOldPassword(center, passwordDTO.getOldPassword())){
            mvc= new ModelAndView("centers/changePassword");
            String message = "incorrect password";
            mvc.addObject("error", message);
            return mvc;
        }
            if(centerService.newPasswordAlreadyUsed(passwordDTO.getNewPassword(), principal.getPassword())){
                throw new PasswordAlreadyUsedException ("The new password cannot be same as the last one. Try again with a different password");
            }

            centerService.changePassword(center, passwordDTO.getNewPassword());
            mvc= new ModelAndView("centers/centerPage", "center", center);
            mvc.addObject("passwordDTO", passwordDTO);
            return mvc;

        }catch(PasswordAlreadyUsedException exception){
            //todo show error mes on view
            mvc= new ModelAndView("centers/changePassword");
            mvc.addObject("error", exception.getMessage());
            return mvc;
        }
    }

    @GetMapping("/delete")
    public String deleteAccount(Model model){
        model.addAttribute("passwordDTO", new PasswordDTO());
        return "centers/delete";
    }


    @PostMapping("/delete")
    public String deleteAccount(Model model, @Valid PasswordDTO passwordDTO) {
        Center principal = new Center ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Center center= centerService.findByName(principal.getName()).get();
        if(centerService.checkOldPassword(center, passwordDTO.getOldPassword())) {
            centerService.deleteById(center.getID());
        } else
        {
            String message= "incorrect password";
            model.addAttribute("error", message);
        }
        return "centers/delete";
    }


    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }




}
