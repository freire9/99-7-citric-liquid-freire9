package com.github.cc3002.citricjuice.controller;

public class DiceRoll extends TurnState {
    public void move(){
        this.changeTurnState(new Move());
    }
}
