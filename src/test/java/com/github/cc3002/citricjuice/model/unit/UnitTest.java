package com.github.cc3002.citricjuice.model.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the units of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @author Daniel Freire Fernández
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class UnitTest {
  private final static String PLAYER1_NAME = "Suguri";
  private final static String PLAYER2_NAME = "Pelos";
  private final static String WILD1_NAME = "Pelusa";
  private final static String WILD2_NAME = "Napoleon";
  private final static String BOSS1_NAME = "Freezer";
  private final static String BOSS2_NAME = "DemonKing";
  private PlayerUnit suguri;
  private PlayerUnit pelos;
  private WildUnit pelusa;
  private WildUnit napoleon;
  private BossUnit freezer;
  private BossUnit demonKing;
  @BeforeEach
  public void setUp() {
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);
    pelusa = new WildUnit(WILD1_NAME, 3, 1, 1, 2);
    napoleon = new WildUnit(WILD2_NAME, 4, 2, 1, 1);
    freezer = new BossUnit(BOSS1_NAME, 12, 4, 3, 2);
    demonKing = new BossUnit(BOSS2_NAME, 13, 5, 4, 4);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    final var expectedPelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);
    assertEquals(expectedSuguri, suguri);
    assertEquals(expectedPelos, pelos);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    assertNotEquals(suguri, o);
    assertNotEquals(pelos, o);
    assertEquals(suguri, suguri);
    assertEquals(pelos, pelos);
    final var expectedSuguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    final var expectedPelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);
    assertEquals(expectedSuguri, suguri);
    assertEquals(expectedPelos, pelos);
  }

  @Test
  public void hitPointsTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    assertEquals(pelos.getMaxHP(), pelos.getCurrentHP());
    suguri.setCurrentHP(2);
    pelos.setCurrentHP(6);
    assertEquals(2, suguri.getCurrentHP());
    assertEquals(6, pelos.getCurrentHP());
    suguri.setCurrentHP(-1);
    pelos.setCurrentHP(-2);
    assertEquals(0, suguri.getCurrentHP());
    assertEquals(0, pelos.getCurrentHP());
    suguri.setCurrentHP(5);
    pelos.setCurrentHP(12);
    assertEquals(4, suguri.getCurrentHP());
    assertEquals(10, pelos.getCurrentHP());
  }

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    pelos.normaClear();
    pelos.normaClear();
    assertEquals(2, suguri.getNormaLevel());
    assertEquals(3, pelos.getNormaLevel());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    final var expectedPelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);
    final var expectedPelusa = new WildUnit(WILD1_NAME, 3, 1, 1, 2);
    final var expectedNapoleon = new WildUnit(WILD2_NAME, 4, 2, 1, 1);
    final var expectedFreezer = new BossUnit(BOSS1_NAME, 12, 4, 3, 2);
    final var expectedDemonKing = new BossUnit(BOSS2_NAME, 13, 5, 4, 4);
    final var actualSuguri = suguri.copy();
    final var actualPelos = pelos.copy();
    final var actualPelusa = pelusa.copy();
    final var actualNapoleon = napoleon.copy();
    final var actualFreezer = freezer.copy();
    final var actualDemonKing = demonKing.copy();

    // Checks that the copied player have the same parameters as the original
    assertEquals(expectedSuguri, actualSuguri);
    assertEquals(expectedPelos, actualPelos);
    assertEquals(expectedPelusa,actualPelusa);
    assertEquals(expectedNapoleon,actualNapoleon);
    assertEquals(expectedFreezer,actualFreezer);
    assertEquals(expectedDemonKing,actualDemonKing);

    // Checks that the copied player doesn't reference the same object
    assertNotSame(expectedSuguri, actualSuguri);
    assertNotSame(expectedPelos, actualPelos);
    assertNotSame(expectedPelusa,actualPelusa);
    assertNotSame(expectedNapoleon,actualNapoleon);
    assertNotSame(expectedFreezer,actualFreezer);
    assertNotSame(expectedDemonKing,actualDemonKing);
  }


  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
                       - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
               && suguri.getCurrentHP() <= suguri.getMaxHP(),
               suguri.getCurrentHP() + "is not a valid HP value"
               + System.lineSeparator() + "Test failed with random seed: "
               + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
               roll + "is not in [1, 6]" + System.lineSeparator()
               + "Test failed with random seed: " + testSeed);
  }
  // endregion

  @RepeatedTest(500)
  public void defendTest(){
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 3, -1, 2);
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.defend(suguri);
    assertTrue(pelos.getCurrentHP() >= 6 && pelos.getCurrentHP() <= 9);
  }

  @RepeatedTest(500)
  public void evadeTest(){
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 3, -1, 2);
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.evade(suguri);
    assertTrue((pelos.getCurrentHP() >= 1 && pelos.getCurrentHP() <= 6) ||(pelos.getCurrentHP() == 10));
  }

  @RepeatedTest(500)
  public void attackTest(){
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 3, -1, 2);
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.setReactionNumber(0); //defend mode
    suguri.attack(pelos);
    assertTrue(pelos.getCurrentHP() >= 6 && pelos.getCurrentHP() <= 9);
    pelos.setCurrentHP(10);
    pelos.setReactionNumber(1); // evade mode
    suguri.attack(pelos);
    assertTrue((pelos.getCurrentHP() >= 1 && pelos.getCurrentHP() <= 6) ||(pelos.getCurrentHP() == 10));
  }

  @Test
  public void getReactionNumberTest(){
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 3, -1, 2);
    assertEquals(0,suguri.getReactionNumber());
    suguri.setReactionNumber(1);
    assertEquals(1,suguri.getReactionNumber());
  }

  @Test
  public void winStarsTest(){
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    pelusa = new WildUnit(WILD1_NAME, 3, 1, 1, 2);
    napoleon = new WildUnit(WILD2_NAME, 4, 2, 1, 1);
    freezer = new BossUnit(BOSS1_NAME, 12, 4, 3, 2);
    demonKing = new BossUnit(BOSS2_NAME, 13, 5, 4, 4);

    pelos.increaseStarsBy(6);
    suguri.increaseStarsBy(12);
    pelusa.increaseStarsBy(8);
    napoleon.increaseStarsBy(4);
    freezer.increaseStarsBy(10);
    demonKing.increaseStarsBy(14);

    //wins of stars of player from wild
    pelos.winStars(pelusa);
    assertEquals(14, pelos.getStars());
    assertEquals(0, pelusa.getStars());

    //win of stars of player from boss
    pelos.winStars(freezer);
    assertEquals(24,pelos.getStars());
    assertEquals(0,freezer.getStars());

    //win of stars of boss from wild
    freezer.increaseStarsBy(10);
    pelusa.increaseStarsBy(8);
    freezer.winStars(pelusa);
    assertEquals(14,freezer.getStars());
    assertEquals(4, pelusa.getStars());

    //win of stars of wild from boss
    pelusa.winStars(freezer);
    assertEquals(11,pelusa.getStars());
    assertEquals(7,freezer.getStars());

    //win of stars of wild from player
    pelusa.winStars(pelos);
    assertEquals(12,pelos.getStars());
    assertEquals(23,pelusa.getStars());

    //win of stars of boss from player
    freezer.winStars(pelos);
    assertEquals(6,pelos.getStars());
    assertEquals(13,freezer.getStars());

    //win of stars of player from player
    pelos.winStars(suguri);
    assertEquals(12,pelos.getStars());
    assertEquals(6,suguri.getStars());

    //win of stars of wild from wild
    pelusa.winStars(napoleon);
    assertEquals(25,pelusa.getStars());
    assertEquals(2,napoleon.getStars());

    //win of stars of boss from boss
    freezer.winStars(demonKing);
    assertEquals(20,freezer.getStars());
    assertEquals(7,demonKing.getStars());
  }

  @Test
  public void winVictoryTest(){
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    suguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
    pelusa = new WildUnit(WILD1_NAME, 3, 1, 1, 2);
    napoleon = new WildUnit(WILD2_NAME, 4, 2, 1, 1);
    freezer = new BossUnit(BOSS1_NAME, 12, 4, 3, 2);
    demonKing = new BossUnit(BOSS2_NAME, 13, 5, 4, 4);

    assertEquals(0,pelos.getVictories());
    assertEquals(0,suguri.getVictories());
    assertEquals(0,pelusa.getVictories());
    assertEquals(0,napoleon.getVictories());
    assertEquals(0, freezer.getVictories());
    assertEquals(0,demonKing.getVictories());

    //wins of victories of player from wild
    pelos.winVictory(pelusa);
    assertEquals(1, pelos.getVictories());

    //win of victories of player from boss
    pelos.winVictory(freezer);
    assertEquals(4,pelos.getVictories());

    //win of victories of boss from wild
    freezer.winVictory(pelusa);
    assertEquals(1,freezer.getVictories());

    //win of victories of wild from boss
    pelusa.winVictory(freezer);
    assertEquals(3,pelusa.getVictories());

    //win of victories of wild from player
    pelusa.winVictory(pelos);
    assertEquals(5,pelusa.getVictories());

    //win of victories of boss from player
    freezer.winVictory(pelos);
    assertEquals(3,freezer.getVictories());

    //win of victories of player from player
    pelos.winVictory(suguri);
    assertEquals(6,pelos.getVictories());

    //win of victories of wild from wild
    pelusa.winVictory(napoleon);
    assertEquals(6,pelusa.getVictories());

    //win of victories of boss from boss
    freezer.winVictory(demonKing);
    assertEquals(6,freezer.getVictories());
  }

  @Test
  public void setAtkTest(){
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.setAtk(5);
    assertEquals(5,pelos.getAtk());
  }

  @Test
  public void setDefTest(){
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.setDef(7);
    assertEquals(7,pelos.getDef());
  }

  @Test
  public void setEvdTest(){
    pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 4, 3);
    pelos.setEvd(2);
    assertEquals(2,pelos.getEvd());
  }
}
