package com.github.cc3002.citricjuice.controller.states;

public class Recovery extends TurnState {

    /**
     * change the canRecovery and canRollDice parameters for security
     */
    public Recovery(){
        this.canRecovery=true;
        this.canRollDice=true;
    }

    @Override
    public void toTurnStart(){this.changeTurnState(new TurnStart());}

    @Override
    public void toEndTurn(){this.changeTurnState(new EndTurn());}

    @Override
    public boolean inRecovery(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Recovery";}

    /**
     * method that handle the logic behind the recovery of a player
     *
     * @throws InvalidActionException
     */
    @Override
    public void recovery() throws InvalidActionException{
        super.recovery();
        if(gameController.getTurnOwner().getCurrentHP()==gameController.getTurnOwner().getMaxHP()){
            toTurnStart();
        }
        else{
            toEndTurn();
        }
    }

}
