package com.github.cc3002.citricjuice.controller.states;

public class TurnStart extends TurnState {

    /**
     * change the canStart parameter for security
     */
    public TurnStart(){
        this.canStart=true;
    }

    @Override
    public void toRecovery(){this.changeTurnState(new Recovery());}

    @Override
    public void toMoving(){this.changeTurnState(new Moving());}

    @Override
    public void toSelectingCard(){this.changeTurnState(new SelectingCard());}

    @Override
    public boolean inTurnStart(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Turn Start";}

    /**
     * method that handle the logic behind the start of the turn
     *
     * @throws InvalidActionException
     */
    public void start() throws InvalidActionException {
        super.start();
        if (gameController.getTurnOwner().getCurrentHP()<=0) {
            toRecovery();
            return;
        }
        if (gameController.getWantToPlayCard()) {
            gameController.increaseCurrentPlayersStars((gameController.getChapter()/5)+1);
            toSelectingCard();
            return;
        }
        else {
            gameController.increaseCurrentPlayersStars((gameController.getChapter()/5)+1);
            gameController.rollDice();
            toMoving();
        }
    }
}
