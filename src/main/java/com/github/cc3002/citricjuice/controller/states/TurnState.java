package com.github.cc3002.citricjuice.controller.states;

import com.github.cc3002.citricjuice.controller.GameController;

public class TurnState {
    protected GameController gameController;
    protected boolean canPlayCard = false;
    protected boolean canRecovery = false;
    protected boolean canRollDice = false;
    protected boolean canMove = false;
    protected boolean canStart=false;
    protected boolean canDecideToFight=false;
    protected boolean canDecideToStopHome =false;
    protected boolean canEndTurn =false;
    protected boolean canDecidePath=false;
    protected boolean canFight=false;


    /**
     * set the game controller to this class (te context of the state)
     *
     * @param gameController the game controller
     */
    public void setGameController(GameController gameController){
        this.gameController=gameController;
    }

    /**
     * change the actual turn state for a new turn state
     *
     * @param turnState the turnState class to change to
     */
    public void changeTurnState(TurnState turnState){
        gameController.setTurnState(turnState);
    }


    /**
     * method to change to the battle phase state
     *
     * @throws InvalidTransitionException
     */
    public void toBattlePhase() throws InvalidTransitionException {
        throw new InvalidTransitionException("can't change to battle phase");
    }

    /**
     * method to change to the end turn state
     *
     * @throws InvalidTransitionException
     */
    public void toEndTurn() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to end turn");
    }

    /**
     * method to change to the moving state
     *
     * @throws InvalidTransitionException
     */
    public void toMoving() throws InvalidTransitionException{
        throw new InvalidTransitionException("cant' change to moving");
    }

    /**
     * method to change to the recovery state
     *
     * @throws InvalidTransitionException
     */
    public void toRecovery() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to recovery");
    }

    /**
     * method to change to the selecting card state
     *
     * @throws InvalidTransitionException
     */
    public void toSelectingCard() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to selecting card");
    }

    /**
     * method to change to the turn start state
     *
     * @throws InvalidTransitionException
     */
     public void toTurnStart() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to turn start");
     }

    /**
     * method to change to the wait fight state
     *
     * @throws InvalidTransitionException
     */
     public void toWaitFight() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to wait fight");
     }

    /**
     * method to change to the wait home state
     *
     * @throws InvalidTransitionException
     */
     public void toWaitHome() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to wait home");
     }

    /**
     * method to change to the waith path state
     *
     * @throws InvalidTransitionException
     */
     public void toWaitPath() throws InvalidTransitionException{
        throw new InvalidTransitionException("can't change to wait path");
     }

    /**
     * method to determine if the actual state is the battle phase state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inBattlePhase(){ return false;}


    /**
     * method to determine if the actual state is the end turn state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inEndTurn() { return false; }

    /**
     *method to determine if the actual state is the moving state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inMoving() { return false; }

    /**
     * method to determine if the actual state is the recovery state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inRecovery() { return false; }

    /**
     * method to determine if the actual state is the selecting card state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inSelectingCard() { return false; }

    /**
     * method to determine if the actual state is the turn start state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inTurnStart(){ return false; }

    /**
     * method to determine if the actual state is the wait fight state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inWaitFight() { return false; }

    /**
     * method to determine if the actual state is the wait home state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inWaitHome() { return false; }

    /**
     * method to determine if the actual state is the wait path state (overrided in the classes that extend this)
     *
     * @return boolean
     */
    public boolean inWaitPath() { return false; }

    /**
     * method to handle the exception of a invalid recovery action and trigger the recovery if it is legal
     *
     * @throws InvalidActionException
     */
    public void recovery() throws InvalidActionException{
        if(!canRecovery){
            throw new InvalidActionException("You can't recovery now.");
        }
        gameController.recovery();
    }

    /**
     * method to handle the exception of a invalid roll action and trigger the roll if it is legal
     *
     * @throws InvalidActionException
     */
    public void roll() throws InvalidActionException{
        if(!(gameController.getHasntRoll() && canRollDice)){
            throw new InvalidActionException("You can't roll now");
        }
        gameController.rollDice();
    }

    /**
     * method to handle the exception of a invalid move action and trigger the move if it is legal
     *
     * @throws InvalidActionException
     */
    public void move() throws InvalidActionException{
        if(!canMove){
            throw new InvalidActionException("You can't move now");
        }
        else{
            gameController.movePlayer();
        }

    }

    /**
     * method to handle the exception of a invalid start action
     *
     * @throws InvalidActionException
     */
    public void start() throws InvalidActionException {
        if(!canStart){
            throw new InvalidActionException("You can't start now");
        }
    }

    /**
     * method to handle the exception of a invalid decideFightOrNot action
     *
     * @throws InvalidActionException
     */
    public void decideFightOrNot() throws InvalidActionException{
        if(!canDecideToFight){
            throw new InvalidActionException("You can't decide to fight now");
        }
    }

    /**
     * method to handle the exception of a invalid decideToStopHome action
     *
     * @throws InvalidActionException
     */
    public void decideToStopHome() throws InvalidActionException {
        if(!canDecideToStopHome){
            throw new InvalidActionException("You can't decide to stop at home panel now");
        }
    }

    /**
     * method to handle the exception of a invalid end action and trigger the end turn method if it is legal
     *
     * @throws InvalidActionException
     */
    public void end() throws InvalidActionException {
        if(!canEndTurn){
            throw new InvalidActionException("You can't end turn now");
        }
        else{
            gameController.endTurn();
        }
    }

    /**
     * method to handle the exception of a invalid decidePath action and trigger the method to decide the path if it is legal
     *
     * @throws InvalidActionException
     */
    public void decidePath() throws InvalidActionException {
        if(!canDecidePath){
            throw new InvalidActionException("You can't decide path now");
        }
        else{
            gameController.decidePathToGo();
        }
    }

    /**
     * method to handle the exception of a invalid battle action and trigger the battle method if it is legal
     *
     * @throws InvalidActionException
     */
    public void battle() throws InvalidActionException{
        if(!canFight){
            throw new InvalidActionException("You can't fight now");
        }
        else{
            gameController.battlePlayer();
        }
    }
}
