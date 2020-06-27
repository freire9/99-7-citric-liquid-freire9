package com.github.cc3002.citricjuice.model.unit;

public abstract class AbstractEnemy extends AbstractUnit{
    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param hp   the initial (and max) hit points of the character.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */
    public AbstractEnemy(String name, int hp, int atk, int def, int evd) {
        super(name, hp, atk, def, evd);
    }
}
