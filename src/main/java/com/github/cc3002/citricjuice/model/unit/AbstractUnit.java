package com.github.cc3002.citricjuice.model.unit;
import java.util.Random;

/**
 * This class represents a abstractUnit in the game 99.7% Citric Liquid.
 */
public abstract class AbstractUnit implements IUnit {
    private final Random random;
    private final String name;
    private final int maxHP;
    protected int atk;
    protected int def;
    protected int evd;
    private int stars;
    private int currentHP;
    private int victories;
    private int reactionNumber;
    private boolean ko;

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
    public AbstractUnit(final String name, final int hp, final int atk, final int def,
                        final int evd){
        this.name = name;
        this.maxHP = currentHP = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        random = new Random();
        victories=0;
        stars=0;
    }

    /**
     * send a message to the unit and it depending on his reactionNumber is gonna defend or evade the attack
     *
     * @param unit
     *      the unit who recive the attack
     */
    public void attack(IUnit unit){unit.reaction(this);}

    /**
     * return the reaction number
     */
    public int getReactionNumber(){
        return reactionNumber;
    }

    /**
     * set the reaction number that is used to decide if the defending unit is gonna defend or evade the attack.
     * 0 means defend
     * anything else means evade
     *
     * @param newReaction
     *      the reaction number
     */
    public void setReactionNumber(int newReaction){
        reactionNumber=newReaction;
    }

    /**
     * depending of the reactionNumber this unit, this unit defend or evade the attacck of the attacking unit
     *
     * @param unit
     *      the unit who attack
     */
    public void reaction(IUnit unit){
        if (reactionNumber==0){
            this.defend(unit);
        }
        else{
            this.evade(unit);
        }
    }


    /**
     * calculate the damage that this unit recieve from the attack
     *
     * @param unit
     *      the unit who attack
     */
    public void defend(IUnit unit){
        int theoricDamage = unit.roll() + unit.getAtk() - (this.roll() + this.getDef());
        int effectiveDamage = Math.max(theoricDamage, 1);
        this.setCurrentHP(this.getCurrentHP()-effectiveDamage);
    }

    /**
     * decide if this unit evade or not the attack of the unit who attack and calculate the damage in each case
     *
     * @param unit
     *      the unit who attack
     */
    public void evade(IUnit unit){
        int theoricDamage = unit.roll() + unit.getAtk();
        if ( (this.roll() + this.getEvd()) <= theoricDamage ){
            this.setCurrentHP(this.getCurrentHP() - theoricDamage);
        }
    }

    /**
     * send a message to a unit and then the unit activates the corresponding method to give stars to this unit
     * @param unit
     *      the unit who give an amount of his stars
     */
    public abstract void winStars(IUnit unit);


    /**
     * increase the amount of stars of the player unit in a certain amount of this unit
     *
     * @param playerUnit
     *      the player unit who recieve the stars
     */
    public abstract void loseStarsToPlayer(PlayerUnit playerUnit);

    /**
     * increase the amount of stars of the wild unit in certain amount of this unit
     *
     * @param wildUnit
     *      the wild unit who recieve the stars
     */
    public abstract void loseStarsToWildUnit(WildUnit wildUnit);

    /**
     * increase the amount of stars of the boss unit in certain amount of this unit
     *
     * @param bossUnit
     *      the boss unit who recieve the stars
     */
    public abstract void loseStarsToBossUnit(BossUnit bossUnit);

    /**
     * send a message to a unit and then the unit activates the corresponding method to give a victory
     *
     * @param unit
     *      the unit who recive the message of give a victory
     */
    public abstract void winVictory(IUnit unit);

    /**
     * increase the amount of victories of the player unit in certain amount
     *
     * @param playerUnit
     *      the player unit who recieve the victory
     */
    public abstract void giveVictoryToPlayerUnit(PlayerUnit playerUnit);

    /**
     * increase the amount of victories of the wild unit in certain amount
     *
     * @param wildUnit
     *       the wild unit who recieve the victory
     */
    public abstract void giveVictoryToWildUnit(WildUnit wildUnit);

    /**
     * increase the amount of victories of the boss unit in certain amount
     *
     * @param bossUnit
     *      the boss unit who recieve the victory
     */
    public abstract void giveVictoryToBossUnit(BossUnit bossUnit);


    /**
     * Returns a uniformly distributed random value in [1, 6]
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
            * Reduces this unit star count by a given amount.
            * <p>
   * The star count will must always be greater or equal to 0
            */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Increases this unit star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Returns this unit star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * return the victories of the unit
     */
    public int getVictories(){
        return victories;
    }


    /**
     * increase the actual victories in the amount
     *
     * @param amount
     *      the amount of victories
     */
    public void increaseVictoriesBy(final int amount) {
        victories += amount;
    }

    /**
     * Set's the seed for this player's random number generator.
     * <p>
     * The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHP() {
        return maxHP;
    }

    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }

    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }


    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHP() {
        return currentHP;
    }

    /**
     * Sets the current character's hit points.
     * <p>
     * The character's hit points have a constraint to always be between 0 and maxHP, both inclusive.
     */
    public void setCurrentHP(final int newHP) {
        this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
    }


    public boolean getKo() {
        return ko;
    }

    public void setKo(boolean isKo){ko=isKo;}
}
