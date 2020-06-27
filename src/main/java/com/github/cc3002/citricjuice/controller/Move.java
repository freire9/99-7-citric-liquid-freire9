package com.github.cc3002.citricjuice.controller;

public class Move extends TurnState {
    public void battle(){
        this.changeTurnState(new Battle());
    }
    //public void stop(){
      //  this.changeTurnState(new Stop());
    //}
    //public void chooseStop(){
       // this.changeTurnState(new HomeChooseStop());
    //}
    //public void choosingPath(){
      //  this.changeTurnState(new ChoosingPath());
    //}

}
