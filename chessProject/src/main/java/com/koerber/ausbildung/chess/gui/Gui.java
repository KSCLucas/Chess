package com.koerber.ausbildung.chess.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JLabel;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.piece.Piece;
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
  public void createNewHistroyEntry() {

  }

  /**
   * Dispays {@code currentGameState} on chessboard.
   * 
   * @return
   */
  public static JLabel[] showCurrentGameState() {
    // create test map start
    // TODO:remove test
    // Knight tempKnight = new Knight("n1b", ChessColour.BLACK, "D4");
    // Rook tempRook = new Rook("n1w", ChessColour.WHITE, "F5", 'l');
    // Rook tempRook1 = new Rook("n1w", ChessColour.WHITE, "F3", 'l');
    // King tempKing = new King("k1b", ChessColour.BLACK, "B7");
    //
    // Map<String, Piece> currentGameStateTemp = new TreeMap<String, Piece>();
    // for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
    // for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
    // currentGameStateTemp.put(Character.toString(i) + String.valueOf(j),
    // null);
    // }
    // }
    // currentGameStateTemp.put("D4", tempKnight);
    // currentGameStateTemp.put("F5", tempRook);
    // currentGameStateTemp.put("F3", tempRook1);
    // currentGameStateTemp.put("B7", tempKing);
    // // test end

    Field testField = new Field();
    testField.initializeMap();

    JLabel[] currentGameStateLabels = new JLabel[64];
    MouseListener m1 = new MouseListener() {
      JLabel       lastEntered    = new JLabel();
      JLabel       tempImageLabel = new JLabel();
      List<JLabel> deleteLabels   = new ArrayList<>();
      boolean      mousePressed   = false;
      @Override
      public void mouseClicked(MouseEvent e) {

      }

      @Override
      public void mousePressed(MouseEvent e) {
        mousePressed = true;
        System.out.println(e.getComponent().getName());
        tempImageLabel = (JLabel)e.getComponent();

        deleteLabels.add(((JLabel)e.getComponent()));

        // TODO Auto-generated method stub
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        System.out.println(lastEntered.getName());
        lastEntered = (JLabel)e.getComponent();
        ((JLabel)e.getComponent()).setIcon(tempImageLabel.getIcon());
        if(deleteLabels.size() != 1) {
          for(int i = 0; i < deleteLabels.size() - 1; i++) {
            deleteLabels.get(i).setIcon(null);
          }
        }
        mousePressed = false;
        deleteLabels.clear();
        // TODO Auto-generated method stub
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        if(mousePressed) {
          lastEntered = (JLabel)e.getComponent();

          if(tempImageLabel.getIcon() != null) {
            ((JLabel)e.getComponent()).setIcon(tempImageLabel.getIcon());
          }
          deleteLabels.add(((JLabel)e.getComponent()));
          // check if still JLabel (if (instanceoff))
          // TODO Auto-generated method stub
        }
      }

      @Override
      public void mouseExited(MouseEvent e) {
        // ((JLabel)e.getComponent()).setIcon(null);
        // TODO Auto-generated method stub
      }
    };
    for(int i = 0; i < 64; i++) {
      currentGameStateLabels[i] = new JLabel();
      currentGameStateLabels[i].addMouseListener(m1);
      // currentGameStateLabels[i].setOpaque(true);
      // if(i >= 0 && i < 8) {
      // currentGameStateLabels[i].setName(xAxis.get(i) + 8);
      // }
      // if(i >= 8 && i < 16) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 8) + 7);
      // }
      // if(i >= 16 && i < 24) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 16) + 6);
      // }
      // if(i >= 24 && i < 32) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 24) + 5);
      // }
      // if(i >= 32 && i < 40) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 32) + 4);
      // }
      // if(i >= 40 && i < 48) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 40) + 3);
      // }
      // if(i >= 48 && i < 56) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 48) + 2);
      // }
      // if(i >= 56 && i < 64) {
      // currentGameStateLabels[i].setName(xAxis.get(i - 56) + 1);
      // }

    }
    Map<String, Piece> currentGameStateTemp = testField.getCurrentGameState();
    for(Entry<String, Piece> entry : currentGameStateTemp.entrySet()) {
      if(entry.getValue() != null) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        currentGameStateLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setIcon(entry.getValue().getIcon());
      }

    }
    return currentGameStateLabels;

  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   * 
   * @return
   */
  public static JLabel[] highlightLegalMove(Piece piece, Map<String, Piece> currentGameState) {
    // TODO remove example

    // functional code
    try {
      piece.createLegalMoveMap(currentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    Map<String, String> legalMoveMapTemp = new TreeMap<>(piece.getLegalMoveMap());
    JLabel[] legalMoveLabels = new JLabel[64];
    for(int i = 0; i < 64; i++) {
      legalMoveLabels[i] = new JLabel();
      legalMoveLabels[i].setText("");
    }

    for(Map.Entry<String, String> entry : legalMoveMapTemp.entrySet()) {
      if(entry.getValue().equals(Piece.TRUE_STRING)) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(GuiFrame.LIGHT_GREEN);
      }
      if(entry.getValue().equals(Piece.HIT_STRING)) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(GuiFrame.LIGHT_RED);
      }
    }
    return legalMoveLabels;
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
