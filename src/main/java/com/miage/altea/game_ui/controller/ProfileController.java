package com.miage.altea.game_ui.controller;


import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.game_ui.pokemonTypes.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    public SecurityContextHolder securityContextHolder;

    public TrainersService trainersService;

    @Autowired
    void setTrainersService(TrainersService trainersService) {
        this.trainersService = trainersService;
    }

    @GetMapping(value = "/profile")
    public ModelAndView profile(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        Trainer trainer =  trainersService.getTrainerEntity(principal.getUsername());
        var modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", trainer );
//        modelAndView.addObject("user", principal );
        return modelAndView;
    }

}
