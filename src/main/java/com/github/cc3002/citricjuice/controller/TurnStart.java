package com.github.cc3002.citricjuice.controller;

public class TurnStart extends TurnState {

    void recovery(){
        this.changeTurnState(new Recovery());
    }
}
