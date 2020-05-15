package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends AbstractPanel {

    /**
     * creates a new panel
     *
     * @param id
     *      the id of the panel
     */
    public HomePanel(int id) {super(id);}

    /**
     * Restores a player's HP in 1.
     */
    @Override
    public void activatedBy(@NotNull PlayerUnit playerUnit) {
        playerUnit.setCurrentHP(playerUnit.getCurrentHP() + 1);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HomePanel)) {
            return false;
        }
        final HomePanel homePanel = (HomePanel) o;
        return getId() == homePanel.getId();
    }

}

