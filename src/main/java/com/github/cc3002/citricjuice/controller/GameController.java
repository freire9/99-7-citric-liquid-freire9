package com.github.cc3002.citricjuice.controller;

import com.github.cc3002.citricjuice.model.NormaGoal;
import com.github.cc3002.citricjuice.model.board.*;
import com.github.cc3002.citricjuice.model.unit.BossUnit;
import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import com.github.cc3002.citricjuice.model.unit.WildUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameController {
    private Set<IPanel> panels = new HashSet<>(); //para paneles del tablero
    private List<PlayerUnit> players= new ArrayList(); //lista de jugadores, de aqui su orden representa el orden de los turnos
    private int chapter=1;
    private PlayerUnit turnOwner;
    private int counter=0; //para los indices de los jugadores en la lista players
    private List<Integer> starList= new ArrayList<>();
    private List<Integer> winsList= new ArrayList<>();
    private NormaHandler normaHandler= new NormaHandler(this);
    private TurnState turnState;


    /**
     * Creates a new game controller
     */
    public GameController(){
        this.setTurnState(new TurnStart());

        starList.add(10);
        starList.add(30);
        starList.add(70);
        starList.add(120);
        starList.add(200);

        winsList.add(2);
        winsList.add(5);
        winsList.add(9);
        winsList.add(14);
    }


    /**
     * set the TurnState param of the game controller
     * @param turnState
     */
    public void setTurnState(TurnState turnState){
        this.turnState=turnState;
        turnState.setGameController(this);
    }


    /**
     * creates a new BonusPanel and add it to the panels list
     * @param id
     * @return BonusPanel
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel newBonusPanel= new BonusPanel(id);
        panels.add(newBonusPanel);
        return newBonusPanel;
    }


    /**
     * creates a new BossPanel and add it to the panels list
     * @param id
     * @return BossPanel
     */
    public BossPanel createBossPanel(int id) {
        BossPanel newBossPanel= new BossPanel(id);
        panels.add(newBossPanel);
        return newBossPanel;
    }


    /**
     * creates a new Drop Panel and add it to the panels list
     * @param id
     * @return DropPanel
     */
    public DropPanel createDropPanel(int id) {
        DropPanel newDropPanel = new DropPanel(id);
        panels.add(newDropPanel);
        return newDropPanel;
    }


    /**
     * creates a new EncounterPanel and add it to the panels list
     * @param id
     * @return EncounterPanel
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel newEncounterPanel = new EncounterPanel(id);
        panels.add(newEncounterPanel);
        return newEncounterPanel;
    }


    /**
     * creates a new HomePanel and add it to the panels list
     * @param id
     * @return HomePanel
     */
    public HomePanel createHomePanel(int id) {
        HomePanel newHomePanel= new HomePanel(id);
        panels.add(newHomePanel);
        return newHomePanel;
    }


    /**
     * creates a new NeutralPanel and add it to the panels list
     * @param id
     * @return NeutralPanel
     */
    public NeutralPanel createNeutralPanel(int id) {
        NeutralPanel newNeutralPanel = new NeutralPanel(id);
        panels.add(newNeutralPanel);
        return new NeutralPanel(id);
    }


    /**
     * creates a new PlayerUnit, set the actual panel of that unit, add a observer to the norma of the player
     * and add it to the player list
     *
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @param panel
     * @return PlayerUnit
     */
    public PlayerUnit createPlayer(String name, int hitPoints, int attack, int defense, int evasion, IPanel panel) {
        PlayerUnit newPlayerUnit = new PlayerUnit(name,hitPoints,attack,defense,evasion);
        newPlayerUnit.setActualPanel(panel);
        panel.addPlayerOnPanel(newPlayerUnit);
        newPlayerUnit.addNormaListener(normaHandler);
        players.add(newPlayerUnit);
        turnOwner=players.get(0);
        return newPlayerUnit;
    }


    /**
     * creates a  new WildUnit
     *
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return WildUnit
     */
    public WildUnit createWildUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new WildUnit(name,hitPoints,attack,defense,evasion);
    }


    /**
     * creates a new BossUnit
     *
     * @param name
     * @param hitPoints
     * @param attack
     * @param defense
     * @param evasion
     * @return BossUnit
     */
    public BossUnit createBossUnit(String name, int hitPoints, int attack, int defense, int evasion) {
        return new BossUnit(name,hitPoints,attack,defense,evasion);
    }


    /**
     * set panel1 next to panel
     *
     * @param panel
     * @param panel1
     */
    public void setNextPanel(IPanel panel, IPanel panel1) {
        panel.addNextPanel(panel1);
    }


    /**
     * return the list that contains all the panels of the game
     *
     * @return Set<Ipanel>
     */
    public Set<IPanel> getPanels() {
        return panels;
    }


    /**
     * return the list that contains all the players of the game
     *
     * @return List<PlayerUnit>
     */
    public List<PlayerUnit> getPlayers(){ return players;}


    /**
     * method that moves the player across the panels of the board stopping in a few speciffics situation
     */
    public void movePlayer(){
            //pasa por su home panel
            if (turnOwner.getActualPanel().equals(turnOwner.getHomePanel())){
                normaCheck(turnOwner);
                return;
            }
            //pasa por un panel con algun jugador en el
            if (turnOwner.getActualPanel().getPlayers().size()>1){
                turnOwner.getActualPanel().activatedBy(turnOwner);
                return;
            }
            //cae en un panel que tiene mas de un panel siguiente
            if(turnOwner.getActualPanel().getNextPanels().size()>1){
                turnOwner.getActualPanel().activatedBy(turnOwner);
                return;
            }

            if (!turnOwner.getActualPanel().getNextPanels().iterator().hasNext()){
                return;
            }
            IPanel actualPanel = turnOwner.getActualPanel();
            actualPanel.removePlayerOnPanel(turnOwner);
            IPanel nextPanel = turnOwner.getActualPanel().getNextPanels().iterator().next();
            turnOwner.setActualPanel(nextPanel);
            nextPanel.addPlayerOnPanel(turnOwner);
            this.movePlayer();
    }


    /**
     * return the player owner of the turn
     *
     * @return PlayerUnit
     */
    public PlayerUnit getTurnOwner() {
        return turnOwner;
    }


    /**
     * return the panel that the unit is actually in
     *
     * @param unit
     * @param <T>
     * @return PlayerUnit
     */
    public <T extends PlayerUnit> IPanel getPlayerPanel(T unit) {
        return unit.getActualPanel();
    }


    /**
     * set the turn owner norma goal
     *
     * @param goal
     */
    public void setCurrPlayerNormaGoal(NormaGoal goal) {
        turnOwner.setNormaGoal(goal);
    }


    /**
     * set the unit home panel
     *
     * @param unit
     * @param panel
     */
    public void setPlayerHome(PlayerUnit unit, HomePanel panel) {
        unit.setHomePanel(panel);
    }


    /**
     * return the actual chapter of the game
     *
     * @return int
     */
    public int getChapter() {
        return chapter;
    }


    /**
     * end the actual turn (change the actual owner of the turn to the next player in the players list)
     */
    public void endTurn() {
        counter+=1;
        int numberOfPlayers=players.size();
        int turnoNumero=counter%numberOfPlayers;
        setTurnOwner(players.get(turnoNumero));
        if(turnoNumero==0){
            chapter++;
        }

    }


    /**
     * set the actual turn owner
     *
     * @param turnOwner
     */
    public void setTurnOwner(PlayerUnit turnOwner) {
        this.turnOwner = turnOwner;
    }


    /**
     * realize norma check of the unit ( comprobes if the player reaches his actual norma goal)
     *
     * @param unit
     */
    public void normaCheck(PlayerUnit unit){
        if (unit.getNormaLevel()==1){
            if(unit.getStars()>=10){
                unit.normaClear();
            }
            return;
        }
        if (unit.getNormaGoal().equals(NormaGoal.STARS)){
            if (unit.getStars()>=starList.get(unit.getNormaLevel()-2)){
                unit.normaClear();
            }
            return;
        }
        if (unit.getNormaGoal().equals(NormaGoal.WINS)){
            if (unit.getVictories()>=winsList.get(unit.getNormaLevel()-2)){
                unit.normaClear();
            }
            return;
        }
    }


    /**
     * the method that recieve the information of the observer and check if the player reaches norma 6 (win the game)
     * and if it win set the winner param of the playet to 1 (winner of the game)
     *
     * @param newValue
     */
    public void onNorma(int newValue) {
        if (newValue==6){
            turnOwner.winner=1;
        }
    }
}
