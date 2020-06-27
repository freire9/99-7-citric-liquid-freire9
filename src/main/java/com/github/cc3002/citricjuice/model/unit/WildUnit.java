package com.github.cc3002.citricjuice.model.unit;

/**
 * This class represents a wildUnit in the game 99.7% Citric Liquid.
 */
public class WildUnit extends AbstractEnemy {

    /**
     * Creates a new character.
     *
     * @param name
     *     the character's name.
     * @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public WildUnit(final String name, final int hp, final int atk, final int def,
                    final int evd) {
        super(name, hp, atk, def, evd);
    }

    /**
     * send a message to a unit and then the unit activates the corresponding method to give stars to this unit
     *
     * @param unit
     *      the unit who recieve the message of give an amount of his stars
     */
    @Override
    public void winStars(IUnit unit) {
        unit.loseStarsToWildUnit(this);
    }

    /**
     * increase the amount of stars of the player unit in the amount of stars of this unit
     *
     * @param playerUnit
     *      the player unit who recieve the stars
     */
    @Override
    public void loseStarsToPlayer(PlayerUnit playerUnit) {
        int currentStars = this.getStars();
        playerUnit.increaseStarsBy(currentStars);
        this.reduceStarsBy(currentStars);
    }

    /**
     * increase the amount of stars of the wild unit in stars/2 of this unit
     *
     * @param wildUnit
     *      the wild unit who recieve the stars
     */
    @Override
    public void loseStarsToWildUnit(WildUnit wildUnit) {
        int currentStars = this.getStars();
        wildUnit.increaseStarsBy(currentStars / 2);
        this.reduceStarsBy(currentStars / 2);
    }

    /**
     * increase the amount of stars of the boss unit in stars/2 of this unit
     *
     * @param bossUnit
     *      the boss unit who recieve the stars
     */
    @Override
    public void loseStarsToBossUnit(BossUnit bossUnit) {
        int currentStars = this.getStars();
        bossUnit.increaseStarsBy(currentStars / 2);
        this.reduceStarsBy(currentStars / 2);

    }

    /**
     * send a message to a unit and then the unit activates the corresponding method to give a victory
     *
     * @param unit
     *      the unit who recive the message of give a victory
     */
    @Override
    public void winVictory(IUnit unit) {
        unit.giveVictoryToWildUnit(this);
    }

    /**
     * increase the amount of victories of the player unit in 1
     *
     * @param playerUnit
     *      the player unit who recieve the victory
     */
    @Override
    public void giveVictoryToPlayerUnit(PlayerUnit playerUnit) {
        playerUnit.increaseVictoriesBy(1);
    }

    /**
     * increase the amount of victories of the wild unit in 1
     *
     * @param wildUnit
     *      the wild unit who recieve the victory
     */
    @Override
    public void giveVictoryToWildUnit(WildUnit wildUnit){
        wildUnit.increaseVictoriesBy(1);
    }

    /**
     * increase the amount of victories of the boss unit in 1
     *
     * @param bossUnit
     *      the boss unit who recieve the victory
     */
    @Override
    public void giveVictoryToBossUnit(BossUnit bossUnit){
        bossUnit.increaseVictoriesBy(1);
    }

    /**
     * Returns a copy of this character.
     */
    public WildUnit copy() {
        return new WildUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WildUnit)) {
            return false;
        }
        final WildUnit wildUnit = (WildUnit) o;
        return getMaxHP() == wildUnit.getMaxHP() &&
                getAtk() == wildUnit.getAtk() &&
                getDef() == wildUnit.getDef() &&
                getEvd() == wildUnit.getEvd() &&
                getStars() == wildUnit.getStars() &&
                getCurrentHP() == wildUnit.getCurrentHP() &&
                getName().equals(wildUnit.getName());
    }
}