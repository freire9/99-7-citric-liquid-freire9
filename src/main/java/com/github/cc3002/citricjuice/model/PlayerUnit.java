package com.github.cc3002.citricjuice.model;

/**
 * This class represents a playerUnit in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @author Daniel Freire Fernández
 * @version 1.0.6-rc.3
 * @since 1.0
 */
public class PlayerUnit extends AbstractUnit{

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
  public PlayerUnit(final String name, final int hp, final int atk, final int def,
                    final int evd) { super(name, hp, atk, def, evd);
  }

  /**
   * send a message to a unit and then the unit activates the corresponding method to give stars to this unit
   *
   * @param unit
   *      the unit who recieve the message of give an amount of his stars
   */
  @Override
  public void winStars(IUnit unit){
    unit.loseStarsToPlayer(this);
  }

  /**
   * increase the amount of stars of the player unit in stars/2 of this unit
   *
   * @param playerUnit
   *      the player unit who recieve the stars
   */
  @Override
  public void loseStarsToPlayer(PlayerUnit playerUnit) {
    int currentStars = this.getStars();
    playerUnit.increaseStarsBy(currentStars/2);
    this.reduceStarsBy(currentStars/2);

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
  public void winVictory(IUnit unit){
    unit.giveVictoryToPlayerUnit(this);
  }

  /**
   * increase the amount of victories of the player unit in 2
   *
   * @param playerUnit
   *      the player unit who recieve the victory
   */
  @Override
  public void giveVictoryToPlayerUnit(PlayerUnit playerUnit){
    playerUnit.increaseVictoriesBy(2);
  }

  /**
   * increase the amount of victories of the wild unit in 2
   *
   * @param wildUnit
   *      the wild unit who recieve the victory
   */
  @Override
  public void giveVictoryToWildUnit(WildUnit wildUnit){
    wildUnit.increaseVictoriesBy(2);
  }

  /**
   * increase the amount of victories of the boss unit in 2
   *
   * @param bossUnit
   *      the boss unit who recieve the victory
   */
  @Override
  public void giveVictoryToBossUnit(BossUnit bossUnit){
    bossUnit.increaseVictoriesBy(2);
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlayerUnit)) {
      return false;
    }
    final PlayerUnit playerUnit = (PlayerUnit) o;
    return getMaxHP() == playerUnit.getMaxHP() &&
           getAtk() == playerUnit.getAtk() &&
           getDef() == playerUnit.getDef() &&
           getEvd() == playerUnit.getEvd() &&
           getNormaLevel() == playerUnit.getNormaLevel() &&
           getStars() == playerUnit.getStars() &&
           getCurrentHP() == playerUnit.getCurrentHP() &&
           getName().equals(playerUnit.getName());
  }

  /**
   * Returns a copy of this character.
   */
  public PlayerUnit copy() {
    return new PlayerUnit(this.getName(), this.getMaxHP(), this.getAtk(), this.getDef(), this.getEvd());
  }


  /**
   * change the actual atk
   *
   * @param amount
   *     the new atk
   */
  public void setAtk(int amount){
    this.atk = amount;
  }

  /**
   * change the actual def
   *
   * @param amount
   *     the new def
   */
  public void setDef(int amount){
    this.def = amount;
  }

  /**
   * change the actual evd
   *
   * @param amount
   *     the new evd
   */
  public void setEvd(int amount){
    this.evd = amount;
  }


}
