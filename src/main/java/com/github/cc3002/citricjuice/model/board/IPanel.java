package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.PlayerUnit;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Interface for the panels
 */
public interface IPanel {

    void activatedBy(final @NotNull PlayerUnit playerUnit);
    Set<IPanel> getNextPanels();
    void addNextPanel(final IPanel IPanel);
    int getId();

}
