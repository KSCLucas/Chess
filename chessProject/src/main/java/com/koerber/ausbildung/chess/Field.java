package com.koerber.ausbildung.chess;

import java.util.TreeMap;

/**
 * Serves as a gameclass that manages everything related to chess.
 * 
 * @author Toni Gropper, PKamps
 * @since 19.10.2022
 */
public class Field {

  public static final int         UPPER_BOUND      = 8;
  public static final int         LOWER_BOUND      = 1;
  public static final char        LEFT_BOUND       = 'A';
  public static final char        RIGHT_BOUND      = 'H';
  private TreeMap<String, Object> currentGameState = new TreeMap<>();
  private int                     currentTurn      = 1;
  private char                    whoWinner;

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
   * Sets game pieces in {@code currentGameState} to the starting positions of
   * {@code initialGameState}.
   */
  public void initializeMap() {

  }

  /**
   * Updates {@code currentGameState} with the completed move.
   */
  public void updateMap() {

  }

  /**
   * Sets {@code currentTurn} to initial value (=1).
   */
  public void resetCurrentTurn() {

  }

  /**
   * Increases {@code currentTurn} by 1.
   */
  public void increaseCurrentTurn() {

  }

  // TODO game loop needed???
  /**
   * Regulates the course of the chess game and calls all methods that are
   * required for the game.
   */
  public void gameLoop() {

  }

  /**
   * Checks, if a player has won and sets {@code whoWinner} to
   * {@code ChessColour.WHITE} or {@code ChessColour.BLACK}, calls the GUI
   * method {@code showWinnerPopup}. If none of the Kings are in checkmate then
   * {@code whoWinner = ChessColour.NONE} is set.
   */
  public void checkForWinner() {

  }
}
