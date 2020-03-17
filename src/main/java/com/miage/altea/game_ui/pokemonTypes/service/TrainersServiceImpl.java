package com.miage.altea.game_ui.pokemonTypes.service;

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

    @Override
    @Cacheable(value="trainer")
    @Retryable
    public List<Trainer> listTrainer() {
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));
    }

    @Override
    @Cacheable(value="trainer")
    @Retryable
    public TrainerWithPokemonTypeDto getTrainer(String name) {
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
        TrainerWithPokemonTypeDto t = new TrainerWithPokemonTypeDto();
        t.setName(trainer.getName());
        List<PokemonDto> team = new ArrayList<>();
        for (Pokemon p : trainer.getTeam()) {
            PokemonType pokemonTmp = pokemonTypeServiceImpl.getPokemon(p.getPokemonTypeId());
            PokemonDto pokemonDto = new PokemonDto(
                    pokemonTmp.getName(),
                    p.getLevel(),
                    pokemonTmp.getId(),
                    pokemonTmp.getBaseExperience(),
                    pokemonTmp.getHeight(),
                    pokemonTmp.getSprites(),
                    pokemonTmp.getStats(),
                    pokemonTmp.getWeight(),
                    pokemonTmp.getTypes());
            team.add(pokemonDto);

        }
        t.setTeam(team);
        return t;
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
        Principal principal = (Principal) auth.getPrincipal();
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class))
                .stream()
                .filter(trainer -> !principal.getName().equals(trainer.getName()))
                .collect(Collectors.toList());
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
