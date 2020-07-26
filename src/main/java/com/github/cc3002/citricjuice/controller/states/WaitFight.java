package com.github.cc3002.citricjuice.controller.states;

public class WaitFight extends TurnState {

    /**
     * change the canDecideToFight parameter for security
     */
    public WaitFight(){
        this.canDecideToFight=true;
    }

    @Override
    public void toBattlePhase(){this.changeTurnState(new BattlePhase());}

    @Override
    public void toMoving(){this.changeTurnState(new Moving());}

    @Override
    public boolean inWaitFight(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Wait Fight";}

    /**
     * method to handle the logic of the decition of fight or not when meeting other players in the same panel
     *
     * @throws InvalidActionException
     */
    public void decideFightOrNot() throws InvalidActionException{
        super.decideFightOrNot();
        if(gameController.getWantFight()){
            gameController.setNumberOfPlayersToFight(gameController.getNumberOfPlayersToFight()-1);
            toBattlePhase();
        }
        else{
            gameController.setMeetPlayer(false);
            toMoving();
    }
    }
}
