package com.github.cc3002.citricjuice.model.unit;

/**
 * interface for the units
 */
public interface IUnit {

    /**
     * send a message to the unit and it depending on his reactionNumber is gonna defend or evade the attack
     *
     * @param unit
     *      the unit who recive the attack
     */
    void attack(IUnit unit);

    /**
     * return the reaction number
     */
    int getReactionNumber();

    /**
     * set the reaction number that is used to decide if the defending unit is gonna defend or evade the attack.
     * 0 means defend
     * anything else means evade
     *
     * @param newReaction
     *      the reaction number
     */
    void setReactionNumber(int newReaction);

    /**
     * depending of the reactionNumber this unit, this unit defend or evade the attacck of the attacking unit
     *
     * @param unit
     *      the unit who attack
     */
    void reaction(IUnit unit);

    /**
     * calculate the damage that this unit recieve from the attack
     *
     * @param unit
     *      the unit who attack
     */
    void defend(IUnit unit);

    /**
     * decide if this unit evade or not the attack of the unit who attack and calculate the damage in each case
     *
     * @param unit
     *      the unit who attack
     */
    void evade(IUnit unit);

    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    int roll();

    /**
     * Reduces this unit star count by a given amount.
     * <p>
     * The star count will must always be greater or equal to 0
     */
    void reduceStarsBy(final int amount);

    /**
     * Increases this unit star count by an amount.
     */
    void increaseStarsBy(final int amount);

    /**
     * Returns this unit star count.
     */
    int getStars();

    /**
     * Set's the seed for this player's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    void setSeed(final long seed);

    /**
     * Returns the character's name.
     */
    String getName();

    /**
     * Returns the character's max hit points.
     */
    int getMaxHP();

    /**
     * Returns the current character's attack points.
     */
    int getAtk();

    /**
     * Returns the current character's defense points.
     */
    int getDef();

    /**
     * Returns the current character's evasion points.
     */
    int getEvd();

    /**
     * Returns the current hit points of the character.
     */
    int getCurrentHP();

    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    void setCurrentHP(final int newHP);

    /**
     * send a message to a unit and then the unit activates the corresponding method to give stars to this unit
     * @param unit
     *      the unit who give an amount of his stars
     */
    void winStars(IUnit unit);

    /**
     * increase the amount of stars of the player unit in a certain amount of this unit
     *
     * @param playerUnit
     *      the player unit who recieve the stars
     */
    void loseStarsToPlayer(PlayerUnit playerUnit);

    /**
     * increase the amount of stars of the wild unit in certain amount of this unit
     *
     * @param wildUnit
     *      the wild unit who recieve the stars
     */
    void loseStarsToWildUnit(WildUnit wildUnit);

    /**
     * increase the amount of stars of the boss unit in certain amount of this unit
     *
     * @param bossUnit
     *      the boss unit who recieve the stars
     */
    void loseStarsToBossUnit(BossUnit bossUnit);

    /**
     * send a message to a unit and then the unit activates the corresponding method to give a victory
     *
     * @param unit
     *      the unit who recive the message of give a victory
     */
    void winVictory(IUnit unit);

    /**
     * increase the amount of victories of the player unit in certain amount
     *
     * @param playerUnit
     *      the player unit who recieve the victory
     */
    void giveVictoryToPlayerUnit(PlayerUnit playerUnit);

    /**
     * increase the amount of victories of the wild unit in certain amount
     *
     * @param wildUnit
     *       the wild unit who recieve the victory
     */
    void giveVictoryToWildUnit(WildUnit wildUnit);

    /**
     * increase the amount of victories of the boss unit in certain amount
     *
     * @param bossUnit
     *      the boss unit who recieve the victory
     */
    void giveVictoryToBossUnit(BossUnit bossUnit);

    /**
     * return the victories of the unit
     */
    int getVictories();
}
