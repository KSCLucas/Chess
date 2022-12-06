package com.koerber.ausbildung.chess.gui;

import java.awt.Component;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.piece.Bishop;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Knight;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.piece.Rook;
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
   * 
   * @param field object
   * @param history object
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
   * @param history object
   * @param currentGameStateLabels
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
    // set movable false for all pieces, as is shall not be possible while
    // showing history entries
    for(Entry<String, Piece> entry : historyMap.entrySet()) {

      entry.getValue().setMoveable(false);
    }
  }

  /**
   * Clears {@code currentGameStateLabels}
   * 
   * @param currentGameStateLabels
   */
  private static void clearLabels(JLabel[] currentGameStateLabels) {
    for(JLabel label : currentGameStateLabels) {
      label.setIcon(null);
    }
  }

  /**
   * Displays the game state selected in the history.
   * 
   * @param entryFen
   * @param history
   * @param currentGameStateLabels
   */
  public static void jumpToSelectedFEN(String entryFen, History history, JLabel[] currentGameStateLabels) {
    if(entryFen != null) {
      // gives turn int for selected fen
      Matcher matcher = Pattern.compile("\\d+").matcher(entryFen);
      matcher.find();
      // -1 to get index
      int turn = Integer.valueOf(matcher.group()) - 1;
      history.setHistoryCounter(turn);
      clearLabels(currentGameStateLabels);
      generateMap(turn, history, currentGameStateLabels);
    }
  }

  /**
   * Goes from the history display back to the active game.
   * 
   * @param activeGameState
   * @param field
   * @param window
   */
  public static void jumptToLiveGame(Map<String, Piece> activeGameState, Field field, GuiFrame window) {
    showCurrentGameState(activeGameState, window.getCurrentGameStateLabels());
    window.getHistoryJList().setSelectedIndex(field.getCurrentTurn() - 2);
  }

  /**
   * Goes one (1) step/move forward in history.
   * 
   * @param field
   * @param history
   * @param window
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
   * 
   * @param history
   * @param window
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
   * 
   * @param field
   * @param history
   * @param historyJList
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
   * 
   * @param history
   * @param historyJList
   */
  public static void clearHistory(History history, JList<String> historyJList) {
    history.getHistoryEntryList().clear();
    history.getFens().clear();
    DefaultListModel<String> model = new DefaultListModel<>();
    historyJList.setModel(model);
  }

  /**
   * Displays {@code currentGameState} on chessboard.
   * 
   * @param gameState
   * @param currentGameStateLabels
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
   * 
   * @param labels
   * @param piece
   * @param unlockedColour
   * @param field
   */

  public static void highlightLegalMove(JLabel[] labels, Piece piece, ChessColour unlockedColour, Field field) {
    if(!(piece instanceof King)) {
      // build legal move map for selected piece
      try {
        piece.createLegalMoveMap(field.getCurrentGameState());
      }
      catch(PieceOutOfBoundsException e) {
        e.printStackTrace();
      }
    }
    if(piece.getColour() == unlockedColour) {
      for(Map.Entry<String, String> entry : piece.getLegalMoveMap().entrySet()) {
        // for legal moves color label green
        if(entry.getValue().equals(Piece.TRUE_STRING)) {
          int columnAsNumber = entry.getKey().charAt(0) - 64;
          int rowAsNumber = entry.getKey().charAt(1) - 48;
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
          labels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(GuiFrame.LIGHT_GREEN);
        }
        // for hittable enemies color label red
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
   * @param field
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
   * @param player1Label
   * @param player2Label
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

  /**
   * creates a piece object based on int
   * 
   * @param choice
   * @param currentGameState
   * @param pawn
   */
  private static void analyseChoice(int choice, Map<String, Piece> currentGameState, Pawn pawn) {
    String positionKey = pawn.getPosition();
    switch(choice) {
    case 0 -> {
      currentGameState.put(positionKey,
          new Knight("n3" + (pawn.getColour() == ChessColour.WHITE ? "w" : "b"), pawn.getColour(), positionKey));
    }
    case 1 -> {
      currentGameState.put(positionKey,
          new Bishop("b3" + (pawn.getColour() == ChessColour.WHITE ? "w" : "b"), pawn.getColour(), positionKey));
    }
    case 2 -> {
      currentGameState.put(positionKey, new Rook("r3" + (pawn.getColour() == ChessColour.WHITE ? "w" : "b"),
          pawn.getColour(), positionKey, Rook.CASTLE_SIDE_SHORT));
      currentGameState.get(positionKey).setPosition(positionKey);
    }
    case 3 -> {
      currentGameState.put(positionKey,
          new Queen("q2" + (pawn.getColour() == ChessColour.WHITE ? "w" : "b"), pawn.getColour(), positionKey));
    }
    }
  }

  /**
   * Displays promotion selection popup and places new {@code Piece} on
   * {@code currentGameState} on {@code Pawn position}.
   * 
   * @param parent
   * @param currentGameState
   * @param pawn
   */
  public static void showPromotionSelection(Component parent, Map<String, Piece> currentGameState, Pawn pawn) {
    ImageIcon[] options = new ImageIcon[4];
    options[0] = IconSupplier.getIcon(pawn.getColour(), "knight_small");
    options[1] = IconSupplier.getIcon(pawn.getColour(), "bishop_small");
    options[2] = IconSupplier.getIcon(pawn.getColour(), "rook_small");
    options[3] = IconSupplier.getIcon(pawn.getColour(), "queen_small");
    int choice = JOptionPane.showOptionDialog(parent, "CHOOSE PIECE TO PROMOTE INTO:", "Promotion",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[3]);
    analyseChoice(choice, currentGameState, pawn);
  }
}
