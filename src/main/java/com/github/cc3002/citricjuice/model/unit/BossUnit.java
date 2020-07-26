package com.github.cc3002.citricjuice.model.unit;

/**
 * This class represents a bossUnit in the game 99.7% Citric Liquid.
 */
public class BossUnit extends AbstractEnemy {

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
    public BossUnit(final String name, final int hp, final int atk, final int def,
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
    public void winStars(IUnit unit){
        unit.loseStarsToBossUnit(this);
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
        wildUnit.increaseStarsBy(currentStars/2);
        this.reduceStarsBy(currentStars/2);
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
        bossUnit.increaseStarsBy(currentStars/2);
        this.reduceStarsBy(currentStars/2);
    }

    /**
     * send a message to a unit and then the unit activates the corresponding method to give a victory
     *
     * @param unit
     *      the unit who recive the message of give a victory
     */
    @Override
    public void winVictory(IUnit unit) {
        unit.giveVictoryToBossUnit(this);
    }

    /**
     * increase the amount of victories of the player unit in 3
     *
     * @param playerUnit
     *      the player unit who recieve the victory
     */
    @Override
    public void giveVictoryToPlayerUnit(PlayerUnit playerUnit) {
        playerUnit.increaseVictoriesBy(3);
    }

    /**
     * increase the amount of victories of the wild unit in 3
     *
     * @param wildUnit
     *      the wild unit who recieve the victory
     */
    @Override
    public void giveVictoryToWildUnit(WildUnit wildUnit){
        wildUnit.increaseVictoriesBy(3);
    }

    /**
     * increase the amount of victories of the boss unit in 3
     *
     * @param bossUnit
     *      the boss unit who recieve the victory
     */
    @Override
    public void giveVictoryToBossUnit(BossUnit bossUnit){
        bossUnit.increaseVictoriesBy(3);
    }

    /**
     * Returns a copy of this character.
     */
    public BossUnit copy() {
        return new BossUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BossUnit)) {
            return false;
        }
        final BossUnit bossUnit = (BossUnit) o;
        return getMaxHP() == bossUnit.getMaxHP() &&
                getAtk() == bossUnit.getAtk() &&
                getDef() == bossUnit.getDef() &&
                getEvd() == bossUnit.getEvd() &&
                getStars() == bossUnit.getStars() &&
                getCurrentHP() == bossUnit.getCurrentHP() &&
                getName().equals(bossUnit.getName());
    }

}