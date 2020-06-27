package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

public class EncounterPanel extends AbstractPanel{
    /**
     * creates a new panel
     *
     * @param id the id of the panel
     */
    public EncounterPanel(int id) {
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
        if (!(o instanceof EncounterPanel)) {
            return false;
        }
        final EncounterPanel encounterPanel = (EncounterPanel) o;
        return getId() == encounterPanel.getId();
    }
}
