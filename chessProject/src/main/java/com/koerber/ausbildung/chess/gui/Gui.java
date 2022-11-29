package com.koerber.ausbildung.chess.gui;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * GUI class provides the entire GUI. It communicates with the Field & Player
 * class.
 * 
 * @author Lucas Noack
 */
public class Gui {

  private String posClickedPiece;

  /**
   * Constructor for GUI class.
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
   * Converts HashMap Keyvalue to LabelArray Index.
   * 
   * @param columnValue
   * @param rowValue
   * @return
   */
  protected static int getIndex(int columnValue, int rowValue) {
    return (columnValue + (-8 * rowValue) + 63);

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
  public static void createNewHistroyEntry(JList<String> historyList) {
    DefaultListModel<String> model = new DefaultListModel<>();
    History fen = new History();
    fen.addEntry(
        "r1wn1wb1wq1wk1wb2wn2wr2w/p1wp2wp3wp4wp5wp6wp7wp8w/########################/########################/########################/########################/p1bp2bp3bp4bp5bp6bp7bp8b/r1bn1bb1bq1bk1bb2bn2br2b/1.w.###");
    fen.addEntry(
        "r1wn1wb1wq1wk1wb2wn2wr2w/p1wp2wp3wp4wp5wp6wp7wp8w/########################/########################/########################/########################/p1bp2bp3bp4bp5bp6bp7bp8b/r1bn1bb1bq1bk1bb2bn2br2b/1.w.###");

    for(int i = 0; i < fen.getFens().size(); i++) {
      model.addElement(fen.getFenOfTurn(i));
    }
    historyList.setModel(model);
  }

  /**
   * Dispays {@code currentGameState} on chessboard.
   * 
   * @return
   */
  public static void showCurrentGameState(JLabel[] currentGameStateLabels) {
    for(JLabel label : currentGameStateLabels) {
      label.setIcon(null);
    }
    Map<String, Piece> currentGameStateTemp = Field.getCurrentGameState();
    for(Entry<String, Piece> entry : currentGameStateTemp.entrySet()) {
      if(entry.getValue() != null) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        currentGameStateLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setIcon(entry.getValue().getIcon());

      }

    }
  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   * 
   * @return
   * @return
   */

  public static void highlightLegalMove(JLabel[] labels, Piece piece, ChessColour unlockedColour) {
    if(!(piece instanceof King)) {
      try {
        piece.createLegalMoveMap(Field.getCurrentGameState());
      }
      catch(PieceOutOfBoundsException e) {
        e.printStackTrace();
      }
    }
    if(piece.getColour() == unlockedColour) {
      for(Map.Entry<String, String> entry : piece.getLegalMoveMap().entrySet()) {
        if(entry.getValue().equals(Piece.TRUE_STRING)) {
          int columnAsNumber = entry.getKey().charAt(0) - 64;
          int rowAsNumber = entry.getKey().charAt(1) - 48;
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(GuiFrame.LIGHT_GREEN);
        }
        if(entry.getValue().equals(Piece.HIT_STRING)) {
          int columnAsNumber = entry.getKey().charAt(0) - 64;
          int rowAsNumber = entry.getKey().charAt(1) - 48;
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(GuiFrame.LIGHT_RED);
        }
      }
    }
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
  public static void askForPlayerName(JLabel player1Label, JLabel player2Label) {
    int maxNameLength = 16;
    // Ask for player names
    String whiteName = (String)JOptionPane.showInputDialog(null, "ENTER PLAYER NAME (WHITE):", "Player Name White",
        JOptionPane.PLAIN_MESSAGE, IconSupplier.getIcon(ChessColour.WHITE, "knight_small"), null, null);
    String blackName = (String)JOptionPane.showInputDialog(null, "ENTER PLAYER NAME (BLACK):", "Player Name Black",
        JOptionPane.PLAIN_MESSAGE, IconSupplier.getIcon(ChessColour.BLACK, "knight_small"), null, null);
    // Check for nameing conditions
    if(whiteName == null || whiteName.isEmpty() || whiteName.isBlank() || whiteName.length() > maxNameLength) {
      whiteName = "WHITE";
    }
    if(blackName == null || blackName.isEmpty() || blackName.isBlank() || blackName.length() > maxNameLength) {
      blackName = "BLACK";
    }
    // Modify name labels
    player1Label.setText(whiteName);
    player2Label.setText(blackName);
  }
}
