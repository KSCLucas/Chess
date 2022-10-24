package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code Piece} class contains a constructor as well as methods for every
 * {@code Piece}.
 * 
 * @author PKamps
 */
public abstract class Piece {

  public static final String       TRUE_STRING  = "ttt";
  public static final String       FALSE_STRING = "fff";
  public static final String       HIT_STRING   = "hhh";
  private String                   id;
  private char                     colour;
  private int                      value;
  private boolean                  isMoveRepeatable;
  private String                   position;
  private List<ArrayList<Integer>> moveSet;
  private Image                    icon;
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
   * @author PKamps
   */
  public Piece(String id, char colour, int value, boolean isMoveRepeatable, String position,
      List<ArrayList<Integer>> moveSet, Image icon) {
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

  public char getColour() {
    return colour;
  }

  public void setColour(char colour) {
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

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

  public Map<String, String> getLegalMoveMap() {
    return legalMoveMap;
  }

  public void setLegalMoveMap(Map<String, String> legalMoveMap) {
    this.legalMoveMap = legalMoveMap;
  }

  /**
   * Calls the {@code createLegalMoveMap} method. Moves {@code Piece} to
   * {@code targetPosition}, if {@code targetPosition} is legal. Checks legality
   * by checking the value for the key {@code targetPosition}. If there is an
   * opposing {@code Piece} on {@code targetPosition}, it sets {@code position}
   * of that {@code Piece} to "xy".
   * 
   * @param targetPosition
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
   */
  public void movePiece(String targetPosition) throws PieceOutOfBoundsException {

  }

  /**
   * Provides a HashMap based on {@code position} with the following values:
   * "fff" for not legal, "ttt" for legal and "hhh" for an opposing takeable
   * {@code Piece}.
   * 
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
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
      if(posLetterAsNumber < Field.LEFT_BOUND || posLetterAsNumber > Field.RIGHT_BOUND || posNumber < Field.LOWER_BOUND
          || posNumber > Field.UPPER_BOUND) {
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
        if(posLetterAsNumber >= Field.LEFT_BOUND && posLetterAsNumber <= Field.RIGHT_BOUND
            && posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND) {
          // Check for EmptyPiece
          if(currentGameState.get(fieldKey).getId().equals("###")) {
            getLegalMoveMap().put(fieldKey, TRUE_STRING);
          }
          // Check for opposing Piece
          else if(currentGameState.get(fieldKey).getColour() != getColour()) {
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
