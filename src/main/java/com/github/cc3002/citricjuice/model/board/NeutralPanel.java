package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class NeutralPanel extends AbstractPanel {

    /**
     * creates a new panel
     *
     * @param id
     *      the id of the panel
     */
    public NeutralPanel(int id) {super(id);}

    /**
     * Do nothing
     *
     * @param playerUnit
     *          the player who activate the panel
     */
    @Override
    public void activatedBy(@NotNull PlayerUnit playerUnit) { }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NeutralPanel)) {
            return false;
        }
        final NeutralPanel neutralPanel = (NeutralPanel) o;
        return getId() == neutralPanel.getId();
    }
}
