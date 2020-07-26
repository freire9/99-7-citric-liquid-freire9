package com.github.cc3002.citricjuice.controller.states;

public class EndTurn extends TurnState {

    /**
     * change the canEndTurn parameter for security
     */
    public EndTurn(){
        this.canEndTurn=true;
    }

    @Override
    public void toTurnStart(){this.changeTurnState(new TurnStart());}

    @Override
    public boolean inEndTurn(){return true;}

    /**
     * return the name of the state
     * @return String
     */
    public String toString(){return "End Turn";}

    /**
     * method behind the logic of the end turn
     *
     * @throws InvalidActionException
     */
    public void end() throws InvalidActionException{
        super.end();
        toTurnStart();
    }
}
