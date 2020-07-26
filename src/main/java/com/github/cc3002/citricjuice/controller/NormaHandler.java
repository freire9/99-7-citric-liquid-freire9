package com.github.cc3002.citricjuice.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NormaHandler implements PropertyChangeListener {
    private GameController gameController;


    /**
     * create a new handler for the norma of the player
     * @param gameController
     */
    public NormaHandler(GameController gameController){
        this.gameController=gameController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        gameController.onNorma((int) evt.getNewValue());
    }
}
