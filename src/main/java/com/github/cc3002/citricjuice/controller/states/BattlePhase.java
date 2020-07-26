package com.github.cc3002.citricjuice.controller.states;

public class BattlePhase extends TurnState {

    /**
     * change the canFight parameter for security
     */
    public BattlePhase(){
        this.canFight=true;
    }

    @Override
    public void toWaitFight(){this.changeTurnState(new WaitFight());}

    @Override
    public void toEndTurn(){this.changeTurnState(new EndTurn());}

    @Override
    public boolean inBattlePhase(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "BattlePhase";}

    /**
     * method that handle the logic behind the battles
     *
     * @throws InvalidActionException
     */
    public void battle() throws InvalidActionException{
        super.battle();

        if(gameController.getTurnOwner().getCurrentHP()>0 && gameController.getNumberOfPlayersToFight()>0){
            toWaitFight();
        }
        else{
            toEndTurn();
        }
    }
}
