package com.github.cc3002.citricjuice.controller.states;

public class SelectingCard extends TurnState {

    /**
     * change the canPlayCard parameter for security
     */
    public SelectingCard(){
        this.canPlayCard=true;
    }

    @Override
    public void toMoving(){this.changeTurnState(new Moving());}

    @Override
    public boolean inSelectingCard(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Selecting Card";}

}
