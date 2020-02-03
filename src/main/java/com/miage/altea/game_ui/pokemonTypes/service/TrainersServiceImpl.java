package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.dto.PokemonDto;
import com.miage.altea.game_ui.dto.TrainerWithPokemonTypeDto;
import com.miage.altea.game_ui.pokemonTypes.bo.Pokemon;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TrainersServiceImpl implements TrainersService {

    public RestTemplate restTemplate;
    public String trainerServiceUrl;
    public PokemonTypeServiceImpl pokemonTypeServiceImpl;

    @Override
    public List<Trainer> listTrainer() {
        return List.of(restTemplate.getForObject(trainerServiceUrl + "/trainers/", Trainer[].class));
    }

    @Override
    public TrainerWithPokemonTypeDto getTrainer(String name) {
        Trainer trainer = restTemplate.getForObject(trainerServiceUrl + "/trainers/" + name, Trainer.class);
        TrainerWithPokemonTypeDto t = new TrainerWithPokemonTypeDto();
        t.setName(trainer.getName());
        List<PokemonDto> team = new ArrayList<>();
        for(Pokemon p : trainer.getTeam()) {
            PokemonType pokemonTmp = pokemonTypeServiceImpl.getPokemon(p.getPokemonTypeId());
            PokemonDto pokemonDto = new PokemonDto(pokemonTmp.getName(), p.getLevel(), pokemonTmp.getId(), pokemonTmp.getBaseExperience(), pokemonTmp.getHeight(), pokemonTmp.getSprites(), pokemonTmp.getStats(), pokemonTmp.getWeight(), pokemonTmp.getTypes());
            team.add(pokemonDto);

        }
        t.setTeam(team);
        return t;
    }

    @Autowired
    void setPokemonTypeServiceImpl(PokemonTypeServiceImpl pokemonTypeServiceImpl) {
        this.pokemonTypeServiceImpl = pokemonTypeServiceImpl;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String trainerServiceUrl) {
        this.trainerServiceUrl = trainerServiceUrl;
    }
}
