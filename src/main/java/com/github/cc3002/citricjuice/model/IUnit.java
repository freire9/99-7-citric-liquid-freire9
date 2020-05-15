package com.github.cc3002.citricjuice.model;

/**
 * interface for the units
 */
public interface IUnit {

    void attack(IUnit unit);
    int getReactionNumber();
    void setReactionNumber(int newReaction);
    void reaction(IUnit unit);
    void defend(IUnit unit);
    void evade(IUnit unit);
    int roll();
    void reduceStarsBy(final int amount);
    void increaseStarsBy(final int amount);
    int getStars();
    void setSeed(final long seed);
    String getName();
    int getMaxHP();
    int getAtk();
    int getDef();
    int getEvd();
    int getNormaLevel();
    void normaClear();
    int getCurrentHP();
    void setCurrentHP(final int newHP);
    void winStars(IUnit unit);
    void loseStarsToPlayer(PlayerUnit playerUnit);
    void loseStarsToWildUnit(WildUnit wildUnit);
    void loseStarsToBossUnit(BossUnit bossUnit);
    void winVictory(IUnit unit);
    void giveVictoryToPlayerUnit(PlayerUnit playerUnit);
    void giveVictoryToWildUnit(WildUnit wildUnit);
    void giveVictoryToBossUnit(BossUnit bossUnit);
    int getVictories();
}
