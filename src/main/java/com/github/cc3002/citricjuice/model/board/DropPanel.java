package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class DropPanel extends AbstractPanel {

    /**
     * creates a new panel
     *
     * @param id
     *      the id of the panel
     */
    public DropPanel(int id) {super(id);}

    /**
     * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
     */
    @Override
    public void activatedBy(final @NotNull PlayerUnit playerUnit){
        playerUnit.reduceStarsBy(playerUnit.roll() * playerUnit.getNormaLevel());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DropPanel)) {
            return false;
        }
        final DropPanel dropPanel = (DropPanel) o;
        return getId() == dropPanel.getId();
    }
}