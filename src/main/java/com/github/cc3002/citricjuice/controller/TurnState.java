package com.github.cc3002.citricjuice.controller;

public class TurnState {
    private GameController gameController;

    public void setGameController(GameController gameController){
        this.gameController=gameController;
    }

    public void changeTurnState(TurnState turnState){
        gameController.setTurnState(turnState);
    }
}
