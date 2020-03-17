package com.miage.altea.game_ui.pokemonTypes.bo;

import java.util.List;

public class Trainer {

    private String name;

    private List<Pokemon> team;

    private String password;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public List<Pokemon> getTeam(){
        return this.team;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
