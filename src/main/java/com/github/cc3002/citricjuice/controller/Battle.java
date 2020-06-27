package com.github.cc3002.citricjuice.controller;

public class Battle extends TurnState {
    public void startFight(){

    }

    public void move(){
        this.changeTurnState(new Move());
    }
}
