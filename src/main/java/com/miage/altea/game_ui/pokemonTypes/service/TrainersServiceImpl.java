package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.converter.TrainerConverter;
import com.miage.altea.game_ui.dto.PokemonDto;
import com.miage.altea.game_ui.dto.TrainerWithPokemonTypeDto;
import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainersServiceImpl implements TrainersService {

    public RestTemplate restTemplate;
    public String trainerServiceUrl;
    public PokemonTypeServiceImpl pokemonTypeServiceImpl;
    @Autowired
    private TrainerConverter trainerConverter;

    @Override
    @Cacheable(value="trainer")
    @Retryable
    public List<Trainer> listTrainer() {
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));
    }

    @Override
    @Retryable
    public List<TrainerWithPokemonTypeDto> listTrainerDto() {
        return trainerConverter.TrainerListToTrainerWithPokemonTypeDtoList(this.listTrainer());
    }

    @Override
    @Cacheable(value="trainerDTO")
    @Retryable
    public TrainerWithPokemonTypeDto getTrainer(String name) {
        TrainerWithPokemonTypeDto trainer = trainerConverter.TrainerToTrainerWithPokemonTypeDto(restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class));
        return trainer;
    }

    @Override
    @Cacheable(value="trainer")
    @Retryable
    public Trainer getTrainerEntity(String name) {
        return restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
    }

    @Override
    @Cacheable(value="trainer")
    @CacheEvict(value="trainer")
    @Scheduled(fixedDelay = 60)
    @Retryable
    public List<Trainer> getAllTrainers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) auth.getPrincipal();
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class))
                .stream()
                .filter(trainer -> !principal.getUsername().equals(trainer.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainerWithPokemonTypeDto> getAllTrainersDto() {
        return this.trainerConverter.TrainerListToTrainerWithPokemonTypeDtoList(this.getAllTrainers());
    }

    @Autowired
    public void setPokemonTypeServiceImpl(PokemonTypeServiceImpl pokemonTypeServiceImpl) {
        this.pokemonTypeServiceImpl = pokemonTypeServiceImpl;
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    public void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
}
