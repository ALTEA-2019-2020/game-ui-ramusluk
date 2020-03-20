package com.miage.altea.game_ui.converter;

import com.miage.altea.game_ui.dto.PokemonDto;
import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PokemonConverter {

    @Autowired
    public PokemonTypeServiceImpl pokemonTypeService;

    public PokemonDto PokemonToPokemonDto(Pokemon pokemon){
        PokemonType pokemonType = pokemonTypeService.getPokemon(pokemon.getPokemonTypeId());
        return PokemonDto.builder()
                .level(pokemon.getLevel())
                .height(pokemonType.getHeight())
                .name(pokemonType.getName())
                .stats(pokemonType.getStats())
                .id(pokemonType.getId())
                .sprites(pokemonType.getSprites())
                .types(pokemonType.getTypes())
                .weight(pokemonType.getWeight())
                .baseExperience(pokemonType.getBaseExperience())
                .build();
    }

}
