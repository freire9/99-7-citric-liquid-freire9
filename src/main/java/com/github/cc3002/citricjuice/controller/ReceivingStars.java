package com.github.cc3002.citricjuice.controller;

public class ReceivingStars extends TurnState {

    public void playCard(){
        this.changeTurnState(new PlayCard());
    }
}
