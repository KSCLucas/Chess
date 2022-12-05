package com.koerber.ausbildung.chess.gui;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.Converter;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.OnlyOneWinnerException;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * GUI class provides the entire GUI. It communicates with the Field & Player
 * class.
 * 
 * @author Lucas Noack, PKamps
 */
public class Gui {

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
  public static void undoLastTurn(Field field, History history) {
    String temp = history.getHistoryEntryList().get(history.getHistoryEntryList().size() - 1);
    String[] splitList = temp.split(" ");
    String startKey = splitList[1];
    String targetKey = splitList[3];
    field.getCurrentGameState().put(startKey, field.getCurrentGameState().get(targetKey));
    field.getCurrentGameState().remove(targetKey);
    field.decreaseCurrentTurn();
    history.getHistoryEntryList().remove(history.getHistoryEntryList().size() - 1);
    history.removeLastTurn();
    // TODO: player zurücksetzen
  }

  /**
   * Generates label map to show game state
   * 
   * @param turn
   */
  private static void generateMap(int turn, History history, JLabel[] currentGameStateLabels) {

    Map<String, Piece> historyMap = Converter.convertFENToMap(history.getFenOfTurn(turn));
    for(Entry<String, Piece> entry : historyMap.entrySet()) {
      if(entry.getValue() != null) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        currentGameStateLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setIcon(entry.getValue().getIcon());
      }
    }
    for(Entry<String, Piece> entry : historyMap.entrySet()) {

      entry.getValue().setMoveable(false);
    }
  }

  /**
   * Clears {@code currentGameStateLabels}
   */
  private static void clearLabels(JLabel[] currentGameStateLabels) {
    for(JLabel label : currentGameStateLabels) {
      label.setIcon(null);
    }
  }

  /**
   * Displays the game state selected in the history.
   */
  public static void jumpToSelectedFEN(String entryFen, History history, JLabel[] currentGameStateLabels) {
    if(entryFen != null) {
      Matcher matcher = Pattern.compile("\\d+").matcher(entryFen);
      matcher.find();
      int turn = Integer.valueOf(matcher.group()) - 1;
      history.setHistoryCounter(turn);
      clearLabels(currentGameStateLabels);
      generateMap(turn, history, currentGameStateLabels);
    }
  }

  /**
   * Goes from the history display back to the active game.
   */
  public static void jumptToLiveGame(Map<String, Piece> activeGameState, Field field, GuiFrame window) {
    showCurrentGameState(activeGameState, window.getCurrentGameStateLabels());
    window.getHistoryJList().setSelectedIndex(field.getCurrentTurn() - 2);
  }

  /**
   * Goes one (1) step/move forward in history.
   */
  public static void forwardInHistory(Field field, History history, GuiFrame window) {
    if(!(history.getHistoryCounter() >= history.getHistoryEntryList().size() - 1)) {
      history.setHistoryCounter(history.getHistoryCounter() + 1);
      int turn = history.getHistoryCounter();
      clearLabels(window.getCurrentGameStateLabels());
      window.getHistoryJList().setSelectedIndex(turn);
      if(turn == history.getHistoryEntryList().size()) {
        showCurrentGameState(field.getCurrentGameState(), window.getCurrentGameStateLabels());
      }
      else {
        generateMap(turn, history, window.getCurrentGameStateLabels());
      }
    }
  }

  /**
   * Goes back one (1) step/move in history.
   */
  public static void backwardInHistory(History history, GuiFrame window) {
    if(history.getHistoryCounter() > 0) {
      history.setHistoryCounter(history.getHistoryCounter() - 1);
      int turn = history.getHistoryCounter();
      clearLabels(window.getCurrentGameStateLabels());
      generateMap(turn, history, window.getCurrentGameStateLabels());
      window.getHistoryJList().setSelectedIndex(turn);

    }

  }

  /**
   * Takes the position data of the dragged figure and creates history entry
   * (start position -> target position | sprite of hit figure).
   */
  public static void createNewHistroyEntry(Field field, History history, JList<String> historyJList) {
    history.getHistoryEntryList().add(Converter.convertFENToHistory(field));
    DefaultListModel<String> model = new DefaultListModel<>();
    for(String entry : history.getHistoryEntryList()) {
      model.addElement(entry);
    }
    historyJList.setModel(model);
  }

  /**
   * Clears history field
   */
  public static void clearHistory(History history, JList<String> historyJList) {
    history.getHistoryEntryList().clear();
    history.getFens().clear();
    DefaultListModel<String> model = new DefaultListModel<>();
    historyJList.setModel(model);
  }

  /**
   * Dispays {@code currentGameState} on chessboard.
   */
  public static void showCurrentGameState(Map<String, Piece> gameState, JLabel[] currentGameStateLabels) {
    clearLabels(currentGameStateLabels);
    Map<String, Piece> currentGameStateTemp = gameState;
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
   */

  public static void highlightLegalMove(JLabel[] labels, Piece piece, ChessColour unlockedColour, Field field) {
    if(!(piece instanceof King)) {
      try {
        piece.createLegalMoveMap(field.getCurrentGameState());
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
   * @throws OnlyOneWinnerException
   * @comment game lock = every move illegal
   */
  public static void showWinnerPopup(Field field) throws OnlyOneWinnerException {
    field.checkForWinner();
    if(field.getWinner() == ChessColour.BLACK || field.getWinner() == ChessColour.WHITE) {
      JFrame jFrame = new JFrame();
      JOptionPane.showMessageDialog(jFrame, "Player " + field.getWinner() + " won!");
    }
  }

  /**
   * Displays the sprite of the beaten game pieces in the player area.
   */
  public static void displayTakenPieces() {

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
