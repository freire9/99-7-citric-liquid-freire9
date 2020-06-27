package com.github.cc3002.citricjuice.controller;

public class PlayCard extends TurnState {
    public void diceRoll(){
        this.changeTurnState(new DiceRoll());
    }
   // public cardPlayed(){
        //this.changeTurnState(new CardPlayed());
    //}
}
