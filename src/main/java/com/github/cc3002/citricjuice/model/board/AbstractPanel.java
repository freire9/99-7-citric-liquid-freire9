package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AbstractPanel implements IPanel {
    private final Set<IPanel> nextPanels = new HashSet<>();
    private final List<PlayerUnit> playersOnPanel= new ArrayList<>();
    private final int id;


    /**
     * creates a new panel
     *
     * @param id
     *      the id of the panel
     */
    public AbstractPanel(int id){
        this.id=id;
    }

    /**
     * Executes the appropriate action to the player according to this panel's type.
     */
    abstract public void activatedBy(final @NotNull PlayerUnit playerUnit);


    /**
     * Returns a copy of this panel's next ones.
     */
    public Set<IPanel> getNextPanels() {
        return Set.copyOf(nextPanels);
    }


    /**
     * Adds a new adjacent panel to this one.
     *
     * @param IPanel
     *     the panel to be added.
     */
    public void addNextPanel(final IPanel IPanel) {
        if (IPanel.equals(this)){

        }
        else{
            nextPanels.add(IPanel);
        }

    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNextPanels(), getClass());
    }

    /**
     * return the id of the panel
     */
    public int getId() { return id;}


    /**
     * return the list that contains the players in this panel
     *
     * @return List<PlayerUnit>
     */
    public List<PlayerUnit> getPlayers() {
        return playersOnPanel;
    }


    /**
     *add a player unit to the panel list of players
     *
     * @param playerUnit
     */
    public void addPlayerOnPanel(PlayerUnit playerUnit) {
        playersOnPanel.add(playerUnit);
    }


    /**
     * remove player unit from the panel list of players
     *
     * @param playerUnit
     */
    public void removePlayerOnPanel(PlayerUnit playerUnit){
        playersOnPanel.remove(playerUnit);
    }


}

