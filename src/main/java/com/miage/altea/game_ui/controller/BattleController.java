package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.TrainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller

public class BattleController {

    public TrainersService trainersService;

    @GetMapping("/fight/{opponent}")
    public ModelAndView fight(Principal principal, @PathVariable String opponent){
        var modelAndView = new ModelAndView("fight");

        modelAndView.addObject("trainerName", principal.getName());
        modelAndView.addObject("opponentName", opponent);

        return modelAndView;
    }


    @Autowired
    void setTrainersService(TrainersService trainersService) {
        this.trainersService = trainersService;
    }
}
