package com.koerber.ausbildung.chess.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.Converter;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * GUI class provides the entire GUI. It communicates with the Field & Player
 * class.
 * 
 * @author Lucas Noack
 */
public class Gui {

  private String    posClickedPiece;
  public static int historyCounter;

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
   * Resets the score before the last move of the game.
   */
  public static void undoLastTurn() {
    String temp = History.historyEntryList.get(History.historyEntryList.size() - 1);
    String[] splitList = temp.split(" ");
    String startKey = splitList[1];
    String targetKey = splitList[3];
    Field.getCurrentGameState().put(startKey, Field.getCurrentGameState().get(targetKey));
    Field.getCurrentGameState().remove(targetKey);
    Field.decreaseCurrentTurn();
    History.historyEntryList.remove(History.historyEntryList.size() - 1);
    History.removeLastTurn();
    // TODO: player zurücksetzen
  }

  /**
   * Generates label map to show game state
   * 
   * @param turn
   */
  private static void generateMap(int turn) {

    Map<String, Piece> historyMap = Converter.convertFENToMap(History.getFenOfTurn(turn));
    for(Entry<String, Piece> entry : historyMap.entrySet()) {
      if(entry.getValue() != null) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        GuiFrame.currentGameStateLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setIcon(entry.getValue().getIcon());
      }
    }
    for(Entry<String, Piece> entry : historyMap.entrySet()) {

      entry.getValue().setMoveable(false);
    }
  }

  /**
   * Clears {@code currentGameStateLabels}
   */
  private static void clearLabels() {
    for(JLabel label : GuiFrame.currentGameStateLabels) {
      label.setIcon(null);
    }
  }

  /**
   * Displays the game state selected in the history.
   */
  public static void jumpToSelectedFEN(String entryFen) {
    if(entryFen != null) {
      clearLegalMoveMap();
      Matcher matcher = Pattern.compile("\\d+").matcher(entryFen);
      matcher.find();
      int turn = Integer.valueOf(matcher.group()) - 1;
      Gui.historyCounter = turn;
      clearLabels();
      generateMap(turn);

    }
  }

  /**
   * Goes from the history display back to the active game.
   */
  public static void jumptToLiveGame() {
    clearLegalMoveMap();
    showCurrentGameState();
    GuiFrame.historyJList.setSelectedIndex(Field.getCurrentTurn() - 2);
  }

  /**
   * Goes one (1) step/move forward in history.
   */
  public static void forwardInHistory() {
    clearLegalMoveMap();
    if(!(historyCounter >= History.historyEntryList.size() - 1)) {
      historyCounter++;
      int turn = historyCounter;
      GuiFrame.historyJList.setSelectedIndex(turn);
      clearLabels();
      generateMap(turn);
    }
  }

  private static void clearLegalMoveMap() {
    Field.getCurrentGameState().entrySet().stream().forEach(x -> x.getValue().getLegalMoveMap().clear());
  }

  /**
   * Goes back one (1) step/move in history.
   */
  public static void backwardInHistory() {
    clearLegalMoveMap();

    if(historyCounter > 0) {
      historyCounter--;
      int turn = historyCounter;
      clearLabels();
      generateMap(turn);
      GuiFrame.historyJList.setSelectedIndex(turn);

    }

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
  public static void createNewHistroyEntry() {
    History.historyEntryList.add(Converter.convertFENToHistory());
    DefaultListModel<String> model = new DefaultListModel<>();
    for(String entry : History.historyEntryList) {
      model.addElement(entry);
    }
    GuiFrame.historyJList.setModel(model);
  }

  /**
   * Clears history field
   */
  public static void clearHistory() {
    History.historyEntryList.clear();
    History.fens.clear();
    DefaultListModel<String> model = new DefaultListModel<>();
    GuiFrame.historyJList.setModel(model);
  }

  /**
   * Dispays {@code currentGameState} on chessboard.
   * 
   * @return
   */
  public static void showCurrentGameState() {
    clearLabels();
    Map<String, Piece> currentGameStateTemp = Field.getCurrentGameState();
    for(Entry<String, Piece> entry : currentGameStateTemp.entrySet()) {
      if(entry.getValue() != null) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        GuiFrame.currentGameStateLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setIcon(entry.getValue().getIcon());

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

  public static void highlightLegalMove(JLabel[] labels, Piece piece) {

    try {
      piece.createLegalMoveMap(Field.getCurrentGameState());
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

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
