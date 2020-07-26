package com.github.cc3002.citricliquid.gui;

import com.github.cc3002.citricjuice.controller.GameController;
import com.github.cc3002.citricjuice.model.board.IPanel;
import com.github.cc3002.citricjuice.model.unit.PlayerUnit;
import com.github.cc3002.citricliquid.gui.nodes.MovableNodeBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;


/**
 * @author Ignacio Slater Muñoz.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class CitricLiquid extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";
  private final GameController gameController=new GameController();
  private PlayerUnit player1Yellow;
  private PlayerUnit player2Red;
  private PlayerUnit player3Blue;
  private PlayerUnit player4Green;
  private final Group root = new Group();
  private Label stateLabel;
  private Label rollNumberLabel;
  private Label turnOwnerLabel;
  private Label hpLabel;
  private Label starsLabel;
  private Label normaLevelLabel;
  private Label normaGoalLabel;
  private Label chapterLabel;
  private Label numberToRecoveryLabel;
  private Label positionPlayer1Label;
  private Label positionPlayer2Label;
  private Label positionPlayer3Label;
  private Label positionPlayer4Label;


  /**
   * methot to start all the view in a window
   * @param stage
   * @throws FileNotFoundException
   */
  @Override
  public void start(@NotNull Stage stage) throws FileNotFoundException {
    stage.setTitle("99.7% Citric Liquid");
    stage.setResizable(false);
    createGame();
    Scene scene=createScene();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * method to create all the game and set the configuration of the map
   */
  private void createGame(){
    //rectangulo principal
    gameController.createHomePanel(0);//home panel del player4 (verde)
    gameController.createNeutralPanel(1);
    gameController.createEncounterPanel(2);
    gameController.createBonusPanel(3);
    gameController.createHomePanel(4);//home panel del player2 (rojo)
    gameController.createDropPanel(5);
    gameController.createNeutralPanel(6);
    gameController.createNeutralPanel(7);
    gameController.createEncounterPanel(8);
    gameController.createBonusPanel(9);
    gameController.createHomePanel(10);//home panel del player1 (amarillo)
    gameController.createNeutralPanel(11);
    gameController.createBonusPanel(12);
    gameController.createEncounterPanel(13);
    gameController.createHomePanel(14);//home panel del player3 (azul)
    gameController.createNeutralPanel(15);
    gameController.createDropPanel(16);
    gameController.createBonusPanel(17);
    gameController.createNeutralPanel(18);
    gameController.createEncounterPanel(19);

    //linea vertical
    gameController.createNeutralPanel(20);
    gameController.createBossPanel(21);
    gameController.createDropPanel(22);

    List<IPanel> list1 = new ArrayList<>();
    List<IPanel> list2 = new ArrayList<>();

    for(IPanel panel: gameController.getPanels()){
      list1.add(panel);
    }
    for (IPanel panel:gameController.getPanels()){
      list2.add(panel);
    }
    for(IPanel panel : list2){
      int i=panel.getId();
      list1.set(i,panel);
    }

    for(int i=0;i<19;i++){
      list1.get(i).addNextPanel(list1.get(i+1));
    }
    list1.get(19).addNextPanel(list1.get(0));//cerramos el rectangulo

    //conectamos linea vertical
    list1.get(17).addNextPanel(list1.get(20));
    list1.get(20).addNextPanel(list1.get(21));
    list1.get(21).addNextPanel(list1.get(22));
    list1.get(22).addNextPanel(list1.get(7));

    //seteo de donde parten
    player1Yellow=gameController.createPlayer("player1Yellow",10,5,4,1,list1.get(0));//0
    player2Red=gameController.createPlayer("player2Red",12,3,7,1,list1.get(14));//14
    player3Blue=gameController.createPlayer("player3Blue",8,8,4,3,list1.get(4));//4
    player4Green=gameController.createPlayer("player4Green",9,6,8,2,list1.get(10));

    //seteo de sus home panels
    player1Yellow.setHomePanel(list1.get(10));
    player2Red.setHomePanel(list1.get(4));
    player3Blue.setHomePanel(list1.get(14));
    player4Green.setHomePanel(list1.get(0));

  }

  /**
   * method to create the view of the game
   *
   * @return Scene
   * @throws FileNotFoundException
   */
  private Scene createScene() throws FileNotFoundException {
    Scene scene = new Scene(root,1280,720);
    var jugador1 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "peonAmarilloFinal.png")
            .setPosition(40, 110)
            .setSize(50, 50)
            .build();
    var jugador2 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "peonRojoFinal.png")
            .setPosition(830, 110)
            .setSize(50, 50)
            .build();
    var jugador3 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "peonVerdeFinal.png")
            .setPosition(830, 580)
            .setSize(50, 50)
            .build();
    var jugador4 = new MovableNodeBuilder(scene).setImagePath(RESOURCE_PATH + "peonAzulFinal.png")
            .setPosition(40, 580)
            .setSize(50, 50)
            .build();
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "tablero.jpg")));



    root.getChildren().add(background);
    root.getChildren().add(setupPlayer1RollButton());
    root.getChildren().add(setupPlayer1AttackButton());
    root.getChildren().add(setupPlayer1StartButton());
    root.getChildren().add(setupPlayer1EvadeButton());
    root.getChildren().add(setupPlayer1KeepFightingButton());
    root.getChildren().add(setupPlayer1StopHomeButton());
    root.getChildren().add(setupPlayer1DontStopHomeButton());
    root.getChildren().add(setupPlayer1HorizontalPathButton());
    root.getChildren().add(setupPlayer1VerticalPathButton());
    root.getChildren().add(setupPlayer1MoveButton());
    root.getChildren().add(setupPlayer1EndTurnButton());

    root.getChildren().add(setupPlayer1DontWantToFightButton());
    root.getChildren().add(setupPlayer1WantToFightButtonButton());
    root.getChildren().add(setupPlayer1RecoveryButton());

    root.getChildren().add(jugador1.getNode());
    root.getChildren().add(jugador2.getNode());
    root.getChildren().add(jugador3.getNode());
    root.getChildren().add(jugador4.getNode());

    stateLabel=createLabel(930,1);
    rollNumberLabel=createLabel(930,21);
    turnOwnerLabel=createLabel(930,41);
    hpLabel=createLabel(430,1);
    starsLabel=createLabel(430,21);
    normaLevelLabel=createLabel(430,41);
    normaGoalLabel=createLabel(630,1);
    chapterLabel=createLabel(630,21);
    numberToRecoveryLabel=createLabel(630,41);
    positionPlayer1Label=createLabel(50,1);
    positionPlayer2Label=createLabel(250,1);
    positionPlayer3Label=createLabel(50,21);
    positionPlayer4Label=createLabel(250,21);


    startAnimator();
    return scene;
  }

  /**
   * method to handle the details of the game (stadistics)
   */
  private void startAnimator() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int rollNumber = gameController.getRollNumber();
        String stateName = gameController.getCurrentStateName();
        String turnOwnerName=gameController.getTurnOwnerName();
        String turnOwnerHp=String.valueOf(gameController.getTurnOwner().getCurrentHP());
        String turnOwnerStars=String.valueOf(gameController.getTurnOwner().getStars());
        String turnOwnerNormaLevel=String.valueOf(gameController.getTurnOwner().getNormaLevel());
        String turnOwnerNormaGoal=String.valueOf(gameController.getTurnOwner().getNormaGoal());
        String chapter=String.valueOf(gameController.getChapter());
        String numberToRecovery=String.valueOf(gameController.getNumberToRecovery());
        String player1Position=String.valueOf(gameController.getPlayers().get(0).getActualPanel().getId());
        String player2Position=String.valueOf(gameController.getPlayers().get(1).getActualPanel().getId());
        String player3Position=String.valueOf(gameController.getPlayers().get(2).getActualPanel().getId());
        String player4Position=String.valueOf(gameController.getPlayers().get(3).getActualPanel().getId());
        rollNumberLabel.setText("Roll Number: " + rollNumber);
        stateLabel.setText("State: " + stateName);
        turnOwnerLabel.setText("Turn Owner: " + turnOwnerName);
        hpLabel.setText("TurnOwnerHP: " + turnOwnerHp);
        starsLabel.setText("Turn Owner Stars: " + turnOwnerStars);
        normaLevelLabel.setText("Turn Owner Norma Level: " + turnOwnerNormaLevel);
        normaGoalLabel.setText("Turn Owner Norma Goal: " + turnOwnerNormaGoal);
        chapterLabel.setText("Chapter: " + chapter);
        numberToRecoveryLabel.setText("Number to Recovery: " + numberToRecovery);
        positionPlayer1Label.setText("Player 1 (Yellow) Position: " + player1Position);
        positionPlayer2Label.setText("Player 2 (Red) Position: " + player2Position);
        positionPlayer3Label.setText("Player 3 (Blue) Position: " + player3Position);
        positionPlayer4Label.setText("Player 4 (Green) Position: " + player4Position);
      }
    };
    timer.start();
  }

  /**
   * method to create labels with it dimensions
   * @param xPos
   * @param yPos
   * @return
   */
  private @NotNull Label createLabel(int xPos, int yPos) {
    Label label = new Label();
    label.setLayoutX(xPos);
    label.setLayoutY(yPos);
    root.getChildren().add(label);
    return label;
  }

  /**
   * method to create the roll button
   * @return Button
   */
  private @NotNull Button setupPlayer1RollButton() {
    Button button = new Button("Roll Dice");
    button.setLayoutX(930);
    button.setLayoutY(550);
    button.setOnAction(event->gameController.tryToRoll());
    button.setFocusTraversable(false);
    //button.setOnAction(CitricLiquid::playSound);
    return button;
  }

  /**
   * method to create the move button
   * @return Button
   */
  private @NotNull Button setupPlayer1MoveButton() {
    Button button = new Button("Move");
    button.setLayoutX(1000);
    button.setLayoutY(550);
    button.setOnAction(event->gameController.tryToMove());
    button.setFocusTraversable(false);
    //button.setOnAction(CitricLiquid::playSound);
    return button;
  }

  /**
   * method to create the end turn button
   * @return Button
   */
  private @NotNull Button setupPlayer1EndTurnButton() {
    Button button = new Button("End Turn");
    button.setLayoutX(1060);
    button.setLayoutY(550);
    button.setOnAction(event->gameController.tryToEnd());
    button.setFocusTraversable(false);
    //button.setOnAction(CitricLiquid::playSound);
    return button;
  }

  /**
   * method to create the attack button
   * @return Button
   */
  private @NotNull Button setupPlayer1AttackButton() {
    Button button = new Button("Attack");
    button.setLayoutX(1140);
    button.setLayoutY(550);
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the start button
   * @return Button
   */
  private @NotNull Button setupPlayer1StartButton() {
    Button button = new Button("Start");
    button.setLayoutX(930);
    button.setLayoutY(510);
    button.setOnAction(event->gameController.tryToStart());
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the evade button
   * @return Button
   */
  private @NotNull Button setupPlayer1EvadeButton() {
    Button button = new Button("Evade");
    button.setLayoutX(1100);
    button.setLayoutY(510);
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the keep fighting button
   * @return Button
   */
  private @NotNull Button setupPlayer1KeepFightingButton() {
    Button button = new Button("Keep Fighting");
    button.setLayoutX(1150);
    button.setLayoutY(510);
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the stop at home button
   * @return Button
   */
  private @NotNull Button setupPlayer1StopHomeButton() {
    Button button = new Button("Stop Home");
    button.setLayoutX(930);
    button.setLayoutY(470);
    button.setOnAction(event->gameController.tryToStopAtHome(1));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the dont stop at home button
   * @return Button
   */
  private @NotNull Button setupPlayer1DontStopHomeButton() {
    Button button = new Button("Don't Stop Home");
    button.setLayoutX(930);
    button.setLayoutY(430);
    button.setOnAction(event->gameController.tryToStopAtHome(0));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the horizontal path decition button
   * @return Button
   */
  private @NotNull Button setupPlayer1HorizontalPathButton() {
    Button button = new Button("Vertical Path");
    button.setLayoutX(1000);
    button.setLayoutY(510);
    button.setOnAction(event->gameController.tryToDecidePath(0));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to  create the vertical path decition button
   * @return Button
   */
  private @NotNull Button setupPlayer1VerticalPathButton() {
    Button button = new Button("Horizontal Path");
    button.setLayoutX(1010);
    button.setLayoutY(470);
    button.setOnAction(event->gameController.tryToDecidePath(1));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the dont want to fight button
   * @return Button
   */
  private @NotNull Button setupPlayer1DontWantToFightButton() {
    Button button = new Button("Don't want to fight");
    button.setLayoutX(1140);
    button.setLayoutY(430);
    button.setOnAction(event->gameController.tryToDecideFight(0));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the want to fight button
   * @return Button
   */
  private @NotNull Button setupPlayer1WantToFightButtonButton() {
    Button button = new Button("Want to Fight");
    button.setLayoutX(1040);
    button.setLayoutY(430);
    button.setOnAction(event->gameController.tryToDecideFight(1));
    button.setFocusTraversable(false);
    return button;
  }

  /**
   * method to create the recovery button
   * @return Button
   */
  private @NotNull Button setupPlayer1RecoveryButton() {
    Button button = new Button("Try to Recovery");
    button.setLayoutX(1120);
    button.setLayoutY(470);
    button.setFocusTraversable(false);
    button.setOnAction(event->gameController.tryToRecovery());
    return button;
  }
}
