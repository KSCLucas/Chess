package com.koerber.ausbildung.chess.gui;

/**
 * GUI class provides the entire GUI. It communicates with the Field & Player
 * class.
 * 
 * @comment Ersterstellung
 * @author Lucas Noack
 */
public class GUI {

  private String posClickedPiece;

  /**
   * Constructor for GUI class
   * 
   * @param String
   * @return void
   * @author Lucas Noack
   */
  public GUI(String posClickedPiece) {
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
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void startNewGame() {

  }

  /**
   * Resets the score before the last move of the game.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void undoLastTurn() {

  }

  /**
   * Displays the game state selected in the history.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void jumpToSelctedFEN() {

  }

  /**
   * Goes from the history display back to the active game.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void jumptToLiveGame() {

  }

  /**
   * Goes one (1) step/move forward in history.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void forwardInHistory() {

  }

  /**
   * Goes back one (1) step/move in history.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void backwardInHistory() {

  }

  /**
   * Based on the {@code Field.currentGameState} hashmap, the position and
   * sprite of the game pieces are read out and displayed on the respective
   * field.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void hashmapToGameState() {

  }

  /**
   * Returns coordinates of the clicked (drag) field as string.
   * 
   * @param void
   * @return String
   * @author Lucas Noack
   */
  public String getClickedFieldString() {
    return null;
  }

  /**
   * Returns coordinates of the released click (drop) field as string.
   * 
   * @param void
   * @return String
   * @author Lucas Noack
   */
  public String getReleasedFieldString() {
    return null;
  }

  /**
   * Takes the position data of the dragged figure and creates history entry
   * (start position -> target position | sprite of hit figure).
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void createNewHistroyEntry() {

  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void highlightLegalMove() {

  }

  /**
   * Displays the winner as a popup and lures the game, only history viewable.
   * 
   * @param void
   * @return void
   * @comment game lock = every move illegal
   * @author Lucas Noack
   */
  public void showWinnerPopup() {

  }

  /**
   * Displays the sprite of the beaten game pieces in the player area.
   * 
   * @param void
   * @return void
   * @author Lucas Noack
   */
  public void displayTakenPieces() {

  }

  /**
   * Asks for player names of the respective pages and occupies Player.name.
   * 
   * @param void
   * @return void
   * @comment Default names: WHITE & BLACK
   * @author Lucas Noack
   */
  public void askForPlayerName() {

  }
}
