package com.github.cc3002.citricjuice.controller;

public class Recovery extends TurnState {

    public void turnStart(){
        this.changeTurnState(new TurnStart());
    }

    public void receivingStars(){
        this.changeTurnState(new ReceivingStars());
    }
}
