package com.koerber.ausbildung.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code Piece} class contains a constructor as well as methods for every
 * {@code Piece}.
 * 
 * @author PKamps
 */
public abstract class Piece {

  public static final String       TRUE_STRING  = "ttt";
  public static final String       HIT_STRING   = "hhh";
  public static final String       NOT_ON_FIELD = "xy";
  private String                   id;
  private ChessColour              colour;
  private int                      value;
  private boolean                  isMoveRepeatable;
  protected String                 position;
  private List<ArrayList<Integer>> moveSet;
  private ImageIcon                icon;
  private Map<String, String>      legalMoveMap = new TreeMap<>();

  /**
   * Parameterized constructor for a {@code Piece}.
   * 
   * @param id
   * @param colour
   * @param value
   * @param isMoveRepeatable
   * @param position
   * @param moveSet
   * @param icon
   */
  public Piece(String id, ChessColour colour, int value, boolean isMoveRepeatable, String position,
      List<ArrayList<Integer>> moveSet, ImageIcon icon) {
    this.id = id;
    this.colour = colour;
    this.value = value;
    this.isMoveRepeatable = isMoveRepeatable;
    this.position = position;
    this.moveSet = moveSet;
    this.icon = icon;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ChessColour getColour() {
    return colour;
  }

  public void setColour(ChessColour colour) {
    this.colour = colour;
  }

  public int getValue() {
    return value;
  }

  public boolean isMoveRepeatable() {
    return isMoveRepeatable;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public List<ArrayList<Integer>> getMoveSet() {
    return moveSet;
  }

  public ImageIcon getIcon() {
    return icon;
  }

  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }

  public Map<String, String> getLegalMoveMap() {
    return legalMoveMap;
  }

  public void setLegalMoveMap(Map<String, String> legalMoveMap) {
    this.legalMoveMap = legalMoveMap;
  }

  /**
   * Checks, whether the given Inputs are within the field bounds or not.
   * 
   * @param posLetterAsNumber
   * @param posNumber
   * @return {@code true} if both inputs are in field bounds. Otherwise it
   *         returns {@code false}
   */
  protected static boolean inFieldBounds(int posLetterAsNumber, int posNumber) {
    if(posLetterAsNumber >= Field.LEFT_BOUND || posLetterAsNumber <= Field.RIGHT_BOUND || posNumber >= Field.LOWER_BOUND
        || posNumber <= Field.UPPER_BOUND) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Moves {@code Piece} to {@code targetPosition}, if {@code targetPosition} is
   * legal. Checks legality by checking the value for the key
   * {@code targetPosition}. If there is an opposing {@code Piece} on
   * {@code targetPosition}, it sets {@code position} of that {@code Piece} to
   * {@code HIT_STRING}.
   * 
   * @param targetPosition
   * @return {@code true} if move is successful. Otherwise it returns
   *         {@code false}
   */
  public boolean movePiece(Map<String, Piece> currentGameState, String targetPosition) {
    if(targetPosition == null || !getLegalMoveMap().containsKey(targetPosition)
        || !currentGameState.containsKey(targetPosition)) {
      return false;
    }
    else {
      currentGameState.put(getPosition(), new EmptyPiece());
      setPosition(targetPosition);
      if(getLegalMoveMap().get(targetPosition) == HIT_STRING) {
        currentGameState.get(targetPosition).setPosition(NOT_ON_FIELD);
      }
      currentGameState.put(targetPosition, this);
      return true;
    }
  }

  /**
   * Provides a Map based on {@code position} with the following values:
   * {@code TRUE_STRING} for legal and {@code HIT_STRING} for an opposing
   * takeable {@code Piece}.
   * 
   * @throws PieceOutOfBoundsException
   */
  public void createLegalMoveMap(Map<String, Piece> currentGameState) throws PieceOutOfBoundsException {
    if(getPosition() == null || getPosition().isEmpty()) {
      throw new PieceOutOfBoundsException();
    }
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Loop over every move vector in moveSet
    for(ArrayList<Integer> moveVector : getMoveSet()) {
      int posLetterAsNumber = getPosition().charAt(0);
      int posNumber = Character.getNumericValue(getPosition().charAt(1));
      if(!inFieldBounds(posLetterAsNumber, posNumber)) {
        throw new PieceOutOfBoundsException();
      }
      // Change content of legalMoveMap based on move vector i and
      // currentGameState
      boolean repeatLoop = true;
      do {
        posLetterAsNumber += moveVector.get(0);
        posNumber += moveVector.get(1);
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        // Check for fieldKey still on Field
        if(inFieldBounds(posLetterAsNumber, posNumber)) {
          // Check for EmptyPiece
          if(currentGameState.get(fieldKey) instanceof EmptyPiece) {
            getLegalMoveMap().put(fieldKey, TRUE_STRING);
          }
          // Check for opposing Piece
          else if(!currentGameState.get(fieldKey).getColour().equals(getColour())) {
            getLegalMoveMap().put(fieldKey, HIT_STRING);
            repeatLoop = false;
          }
          else {
            repeatLoop = false;
          }
        }
        else {
          repeatLoop = false;
        }
      } while(isMoveRepeatable() && repeatLoop);
    }
  }
}
