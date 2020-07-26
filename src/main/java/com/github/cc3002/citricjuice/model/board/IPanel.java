package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Interface for the panels
 */
public interface IPanel {

    /**
     * Executes the appropriate action to the player according to the panel type.
     *
     * @param playerUnit
     */
    void activatedBy(final @NotNull PlayerUnit playerUnit);

    /**
     * Returns a copy of this panel's next ones.
     *
     * @return Set<IPanel>
     */
    Set<IPanel> getNextPanels();

    /**
     * Adds a new adjacent panel to this one.
     *
     * @param IPanel
     *      the panel to be added.
     */
    void addNextPanel(final IPanel IPanel);

    /**
     * return the id of the panel
     *
     * @return
     */
    int getId();

    /**
     * return the list that contains the players in this panel
     *
     * @return
     */
    List<PlayerUnit> getPlayers();

    /**
     * add a player unit to the panel list of players
     *
     * @param playerUnit
     */
    void addPlayerOnPanel(PlayerUnit playerUnit);

    /**
     * remove player unit from the panel list of players
     *
     * @param playerUnit
     */
    void removePlayerOnPanel(PlayerUnit playerUnit);
}
