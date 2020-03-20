package com.miage.altea.game_ui.converter;

import com.miage.altea.game_ui.dto.TrainerWithPokemonTypeDto;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainerConverter {

    @Autowired
    private PokemonConverter PokemonToPokemonDto;

    public TrainerWithPokemonTypeDto TrainerToTrainerWithPokemonTypeDto(Trainer trainer){
        return TrainerWithPokemonTypeDto.builder()
                .name(trainer.getName())
                .team(trainer.getTeam().stream().map(pokemon -> PokemonToPokemonDto.PokemonToPokemonDto(pokemon)).collect(Collectors.toList()))
                .build();
    }

    public List<TrainerWithPokemonTypeDto> TrainerListToTrainerWithPokemonTypeDtoList(List<Trainer> trainerList){
        return trainerList.stream().map(trainer -> this.TrainerToTrainerWithPokemonTypeDto(trainer)).collect(Collectors.toList());
    }

}
