package com.github.cc3002.citricjuice.controller.states;

public class WaitPath extends TurnState {

    /**
     * change the canDecidePath parameter for security
     */
    WaitPath(){this.canDecidePath=true;
    }

    @Override
    public void toMoving(){this.changeTurnState(new Moving());}

    @Override
    public boolean inWaitPath(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Wait Path";}

    /**
     * method to handle the logic of the decition of taking a path when meeting multiples next panels
     *
     * @throws InvalidActionException
     */
    public void decidePath() throws InvalidActionException{
        super.decidePath();
        toMoving();
    }
}
