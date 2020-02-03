package com.miage.altea.game_ui.dto;

import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

import java.util.List;
import java.util.Map;

public class TrainerWithPokemonTypeDto {

    private String name;

    private List<PokemonDto> team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonDto> getTeam() {
        return team;
    }

    public void setTeam(List<PokemonDto> team) {
        this.team = team;
    }
}
