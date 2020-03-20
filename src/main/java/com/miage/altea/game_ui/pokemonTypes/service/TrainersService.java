package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.dto.TrainerWithPokemonTypeDto;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;

import java.util.List;

public interface TrainersService {

    List<Trainer> listTrainer();
    List<TrainerWithPokemonTypeDto> listTrainerDto();
    TrainerWithPokemonTypeDto getTrainer(String name);
    Trainer getTrainerEntity(String name);
    List<Trainer> getAllTrainers();
    List<TrainerWithPokemonTypeDto> getAllTrainersDto();
}
