package com.miage.altea.game_ui.dto;

import lombok.Builder;

import java.util.List;

@Builder
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
