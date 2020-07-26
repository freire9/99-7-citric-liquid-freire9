package com.github.cc3002.citricjuice.controller.states;

public class InvalidActionException extends Exception {
    /**
     * trigger a action exception (an exception that occurs when trying to use a method from one state that is not legal to use in that moment
     * @param message
     */
    public InvalidActionException(final String message) {
        super(message);
    }
}
