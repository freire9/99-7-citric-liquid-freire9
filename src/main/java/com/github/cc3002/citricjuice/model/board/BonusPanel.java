package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends AbstractPanel {

    /**
     * creates a new panel
     *
     * @param id
     *      the id of the panel
     */
    public BonusPanel(int id) {super(id);}

    /**
     * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
     * norma level and three.
     */
    @Override
    public void activatedBy(@NotNull PlayerUnit playerUnit) {
        playerUnit.increaseStarsBy(playerUnit.roll() * Math.min(playerUnit.getNormaLevel(), 3));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BonusPanel)) {
            return false;
        }
        final BonusPanel bonusPanel = (BonusPanel) o;
        return getId() == bonusPanel.getId();
    }
}


