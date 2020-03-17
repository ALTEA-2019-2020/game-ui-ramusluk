package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements  PokemonTypeService{


    public RestTemplate restTemplate;
    public String pokemonServiceUrl;

    @Cacheable("pokemon-type")
    @Retryable
    public List<PokemonType> listPokemonsTypes() {
        return List.of(restTemplate.getForObject(pokemonServiceUrl + "pokemon-types/", PokemonType[].class));
    }

    @Override
    @Retryable
    @Cacheable("pokemon-type")
    public PokemonType getPokemon(int id) {
        return restTemplate.getForObject(pokemonServiceUrl + "pokemon-types/"+id, PokemonType.class);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}
