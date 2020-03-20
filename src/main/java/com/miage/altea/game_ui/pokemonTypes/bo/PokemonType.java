package com.miage.altea.game_ui.pokemonTypes.bo;

import java.util.List;

public class PokemonType {

    private int id;
    private int baseExperience;
    private int height;
    private String name;
    private Sprites sprites;
    private Stats stats;
    private int weight;
    private List<String> types;


    public void setId(int id) {
        this.id = id;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public int getId() {
        return id;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public Stats getStats() {
        return stats;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getTypes() {
        return types;
    }
}
