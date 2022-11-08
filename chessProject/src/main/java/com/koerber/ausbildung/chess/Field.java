package com.koerber.ausbildung.chess;

import java.util.TreeMap;

/**
 * Serves as a gameclass that manages everything related to chess.
 * 
 * @comment base-structure, currently final not initialized.
 * @author Toni Gropper
 * @since 19.10.2022
 */
public class Field {
  
  public static final int UPPER_BOUND = 8;
  public static final int LOWER_BOUND = 1;
  public static final char LEFT_BOUND = 'A';
  public static final char RIGHT_BOUND = 'H';
  private TreeMap<String, Object>       currentGameState;
  private int                           currentTurn;
  private char                          whoWinner;


  public TreeMap<String, Object> getCurrentGameState() {
    return currentGameState;
  }

  public void setCurrentGameState(TreeMap<String, Object> currentGameState) {
    this.currentGameState = currentGameState;
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public void setCurrentTurn(int currentTurn) {
    this.currentTurn = currentTurn;
  }

  public char getWhoWinner() {
    return whoWinner;
  }

  public void setWhoWinner(char whoWinner) {
    this.whoWinner = whoWinner;
  }

  /**
   * sets game pieces in this.currentGameState to the starting positions of
   * this.initialGameState.
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void initializeTreemap() {

  }

  /**
   * updates this.currentGameState with the completed move.
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void updateTreeMap() {

  }

  /**
   * sets this.currentTurn to initial value (=1).
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void resetCurrentTurn() {

  }

  /**
   * increases this.currentTurn by 1
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void increaseCurrentTurn() {

  }

  /**
   * regulates the course of the chess game and calls all methods that are
   * required for the game.
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void gameLoop() {

  }

  /**
   * checks whether either player has won and sets this.whoWinner to 'w' or 'b';
   * calls the GUI method showWinnerPopup. If none of the Kings are in checkmate
   * then this.whoWinner ='n' (n=none) is set.
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void checkForWinner() {

  }
}
