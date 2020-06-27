package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class BossPanel extends AbstractPanel{

    /**
     * creates a new panel
     *
     * @param id the id of the panel
     */
    public BossPanel(int id) {
        super(id);
    }

    @Override
    public void activatedBy(@NotNull PlayerUnit playerUnit) {
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BossPanel)) {
            return false;
        }
        final BossPanel bossPanel = (BossPanel) o;
        return getId() == bossPanel.getId();
    }
}
