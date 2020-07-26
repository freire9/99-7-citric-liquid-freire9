package com.github.cc3002.citricjuice.controller.states;

public class WaitHome extends TurnState {

    /**
     * change te canDecideToStopHome parameter for security
     */
    public WaitHome(){
        this.canDecideToStopHome=true;
    }

    @Override
    public void toEndTurn(){this.changeTurnState(new EndTurn());}

    @Override
    public void toMoving(){this.changeTurnState(new Moving());}

    @Override
    public boolean inWaitHome(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Wait Home";}

    /**
     * method to handle the logic of the decition of stop at the turn owner home panel or not
     *
     * @throws InvalidActionException
     */
    public void decideToStopHome() throws InvalidActionException{
        super.decideToStopHome();
        if(gameController.getWantStopHome()){
            toEndTurn();
        }
        else{
            toMoving();
        }
    }
}
