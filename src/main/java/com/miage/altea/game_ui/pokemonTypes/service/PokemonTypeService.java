package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {

    List<PokemonType> listPokemonsTypes();
    PokemonType getPokemon(int id);

}
