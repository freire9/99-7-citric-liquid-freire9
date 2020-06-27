package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameControllerTest {

    private final static String PLAYER1_NAME = "Suguri";
    private final static String PLAYER2_NAME = "Pelos";
    private PlayerUnit suguri;
    private PlayerUnit pelos;
    private HomePanel homePanel;
    private DropPanel dropPanel;
    private BonusPanel bonusPanel;
    private NeutralPanel neutralPanel;
    private GameController gameController;
    private EncounterPanel encounterPanel;
    private BossPanel bossPanel;


    @BeforeEach
    public void setUp() {
        suguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
        pelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);
        homePanel = new HomePanel(1);
        dropPanel = new DropPanel(2);
        bonusPanel = new BonusPanel(3);
        neutralPanel = new NeutralPanel(4);
        encounterPanel = new EncounterPanel(12);
        bossPanel = new BossPanel(13);
        gameController = new GameController();
    }

    @Test
    public void constructorTest() {
        final var actualSuguri = gameController.createPlayer(PLAYER1_NAME, 4, 1, -1, 2, homePanel);
        final var actualPelos = gameController.createPlayer(PLAYER2_NAME, 10, 3, 2, 1, dropPanel);
        final var expectedSuguri = new PlayerUnit(PLAYER1_NAME, 4, 1, -1, 2);
        final var expectedPelos = new PlayerUnit(PLAYER2_NAME, 10, 3, 2, 1);

        assertEquals(expectedSuguri, actualSuguri);
        assertEquals(expectedPelos, actualPelos);
    }

    @Test
    public void panelsTest(){
        assertEquals(0,gameController.getPanels().size());
        final var actualPelos = gameController.createPlayer(PLAYER2_NAME, 10, 3, 2, 1, dropPanel);
        actualPelos.setActualPanel(homePanel);
        gameController.setPlayerHome(actualPelos,homePanel);
        assertEquals(gameController.getPlayerPanel(actualPelos),homePanel);
        IPanel panel1 = gameController.createBonusPanel(3);
        IPanel panel2= gameController.createDropPanel(2);
        IPanel panel3= gameController.createEncounterPanel(12);
        IPanel panel4=gameController.createHomePanel(1);
        IPanel panel5=gameController.createNeutralPanel(4);
        IPanel panel6=gameController.createBossPanel(13);
        assertEquals(6,gameController.getPanels().size());
        gameController.setNextPanel(panel1,panel2);
        assertEquals(bonusPanel,panel1);
        assertEquals(dropPanel,panel2);
        assertEquals(encounterPanel,panel3);
        assertEquals(homePanel,panel4);
        assertEquals(neutralPanel,panel5);
        assertEquals(bossPanel,panel6);
    }

    @Test
    public void normaCheckTest(){
        PlayerUnit pelos=gameController.createPlayer(PLAYER2_NAME, 4, 1, -1, 2, homePanel);
        gameController.setTurnOwner(pelos);
        assertEquals(pelos,gameController.getTurnOwner());
        gameController.setCurrPlayerNormaGoal(NormaGoal.STARS);
        assertEquals(NormaGoal.STARS,pelos.getNormaGoal());
        pelos.increaseStarsBy(10);
        gameController.normaCheck(pelos);
        assertEquals(2,pelos.getNormaLevel());
        pelos.increaseVictoriesBy(100);
        gameController.setCurrPlayerNormaGoal(NormaGoal.WINS);
        assertEquals(NormaGoal.WINS,pelos.getNormaGoal());
        gameController.normaCheck(pelos);
        assertEquals(3,pelos.getNormaLevel());
    }

    @Test
    public void movePlayerTest(){
        IPanel panel1 = gameController.createBonusPanel(3);
        IPanel panel2= gameController.createDropPanel(2);
        IPanel panel3= gameController.createEncounterPanel(12);
        PlayerUnit pelos=gameController.createPlayer(PLAYER2_NAME, 4, 1, -1, 2, panel1);
        PlayerUnit suguri=gameController.createPlayer(PLAYER1_NAME, 4, 1, -1, 2, panel3);

        gameController.setNextPanel(panel1,panel2);
        gameController.setNextPanel(panel2,panel3);
        gameController.setTurnOwner(pelos);
        gameController.movePlayer();
        assertEquals(pelos,gameController.getTurnOwner());
        assertEquals(panel3,pelos.getActualPanel());
    }

    @Test
    public void playerTest(){
        IPanel panel1 = gameController.createBonusPanel(3);
        IPanel panel2= gameController.createDropPanel(2);
        IPanel panel3= gameController.createEncounterPanel(12);
        PlayerUnit pelos=gameController.createPlayer(PLAYER2_NAME, 4, 1, -1, 2, panel1);
        PlayerUnit suguri=gameController.createPlayer(PLAYER1_NAME, 4, 1, -1, 2, panel3);

        gameController.setTurnOwner(suguri);
        assertEquals(suguri,gameController.getTurnOwner());
        assertEquals(2,gameController.getPlayers().size());
        suguri.normaClear();
        suguri.normaClear();
        suguri.normaClear();
        suguri.normaClear();
        suguri.normaClear();
        gameController.onNorma(6);
        assertEquals(6,suguri.getNormaLevel());
        assertEquals(1,suguri.winner);
    }

    @Test
    public void turnTest(){
        IPanel panel1 = gameController.createBonusPanel(3);
        PlayerUnit pelos=gameController.createPlayer(PLAYER2_NAME, 4, 1, -1, 2, panel1);
        assertEquals(1,gameController.getChapter());
        gameController.endTurn();
        assertEquals(2,gameController.getChapter());
        assertEquals(1,gameController.getPlayers().size());
    }




}


