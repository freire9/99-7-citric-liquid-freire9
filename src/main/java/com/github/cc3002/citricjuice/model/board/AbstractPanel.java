package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.PlayerUnit;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractPanel implements IPanel {
    private final Set<IPanel> nextPanels = new HashSet<>();
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
    public void addNextPanel(final IPanel IPanel) { nextPanels.add(IPanel);
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNextPanels(), getClass());
    }

    /**
     * return the id of the panel
     */
    public int getId() { return id;}


}

