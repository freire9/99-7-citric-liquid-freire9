package com.github.cc3002.citricjuice.controller.states;

public class InvalidTransitionException extends Exception {
    /**
     * trigger a transition exception (an exception that occurs when trying to change from one state to another that is not legal to change to
     * @param message
     */
    public InvalidTransitionException(String message){
        super(message);
    }
}
