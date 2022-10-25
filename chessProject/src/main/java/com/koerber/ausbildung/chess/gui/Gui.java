package com.koerber.ausbildung.chess.gui;

/**
 * GUI class provides the entire GUI. It communicates with the Field & Player
 * class.
 * 
 * @author Lucas Noack
 */
public class Gui {

  private String posClickedPiece;

  /**
   * Constructor for GUI class
   * 
   * @param String
   */
  public Gui(String posClickedPiece) {
    this.posClickedPiece = posClickedPiece;
  }

  public String getPosClickedPiece() {
    return posClickedPiece;
  }

  public void setPosClickedPiece(String posClickedPiece) {
    this.posClickedPiece = posClickedPiece;
  }

  /**
   * Sets all game pieces to initial pos, clears history, resets player data
   * (points, pieces beaten, NOT color and name).
   */
  public void startNewGame() {

  }

  /**
   * Resets the score before the last move of the game.
   */
  public void undoLastTurn() {

  }

  /**
   * Displays the game state selected in the history.
   */
  public void jumpToSelectedFEN() {

  }

  /**
   * Goes from the history display back to the active game.
   */
  public void jumptToLiveGame() {

  }

  /**
   * Goes one (1) step/move forward in history.
   */
  public void forwardInHistory() {

  }

  /**
   * Goes back one (1) step/move in history.
   */
  public void backwardInHistory() {

  }

  /**
   * Based on the {@code Field.currentGameState} hashmap, the position and
   * sprite of the game pieces are read out and displayed on the respective
   * field.
   */
  public void hashmapToGameState() {

  }

  /**
   * Returns coordinates of the clicked (drag) field as string.
   * 
   * @return String
   */
  public String getClickedFieldString() {
    return null;
  }

  /**
   * Returns coordinates of the released click (drop) field as string.
   * 
   * @return String
   */
  public String getReleasedFieldString() {
    return null;
  }

  /**
   * Takes the position data of the dragged figure and creates history entry
   * (start position -> target position | sprite of hit figure).
   */
  public void createNewHistroyEntry() {

  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   */
  public void highlightLegalMove() {

  }

  /**
   * Displays the winner as a popup and lures the game, only history viewable.
   * 
   * @comment game lock = every move illegal
   */
  public void showWinnerPopup() {

  }

  /**
   * Displays the sprite of the beaten game pieces in the player area.
   */
  public void displayTakenPieces() {

  }

  /**
   * Asks for player names of the respective pages and occupies Player.name.
   * 
   * @comment Default names: WHITE & BLACK
   */
  public void askForPlayerName() {

  }

}
