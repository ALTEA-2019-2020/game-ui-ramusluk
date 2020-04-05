package com.miage.altea.game_ui.controller;


import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class PokemonTypeController {

    public PokemonTypeService pokemonTypeService;

    @GetMapping(value = "/pokedex")
    public ModelAndView pokedex(){
        var modelAndView = new ModelAndView("pokedex");
        modelAndView.addObject("pokemonTypes", pokemonTypeService.listPokemonsTypes());
        return modelAndView;
    }


    @Autowired
    void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}
