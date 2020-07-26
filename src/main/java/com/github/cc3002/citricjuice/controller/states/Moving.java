package com.github.cc3002.citricjuice.controller.states;

public class Moving extends TurnState {

    /**
     * change the canMove parameter for security
     */
    public Moving(){
        this.canMove =true;
    }

    @Override
    public void toWaitFight(){this.changeTurnState(new WaitFight());}

    @Override
    public void toWaitHome(){this.changeTurnState(new WaitHome());}

    @Override
    public void toWaitPath(){this.changeTurnState(new WaitPath());}

    @Override
    public void toEndTurn(){this.changeTurnState(new EndTurn());}

    @Override
    public boolean inMoving(){return true;}

    /**
     * return the name of the state
     *
     * @return String
     */
    public String toString(){return "Moving";}

    /**
     * method that handle the logic behind the movement of a player
     *
     * @throws InvalidActionException
     */
    public void move() throws InvalidActionException {
        while (gameController.getRollNumber()>0){
            super.move();
            gameController.setRollNumber(gameController.getRollNumber()-1);
            if(gameController.getMeetPlayer()){
                gameController.setMeetPlayer(false);
                toWaitFight();
                return;
                //return?
            }
            if(gameController.getMeetHomePanel()){
                gameController.setMeetHomePanel(false);
                toWaitHome();
                return;
            }
            if(gameController.getMeetMultPanels()){
                gameController.setMeetMultPanels(false);
                toWaitPath();
                return;
            }

        }
        toEndTurn();//no more moves
    }
}
