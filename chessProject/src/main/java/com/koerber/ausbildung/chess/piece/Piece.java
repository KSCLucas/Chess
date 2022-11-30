package com.koerber.ausbildung.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.MoveVector;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code Piece} class contains a constructor as well as methods for every
 * {@code Piece}.
 * 
 * @author PKamps
 */
public abstract class Piece {

  public static final String  TRUE_STRING          = "ttt";
  public static final String  HIT_STRING           = "hhh";
  public static final String  NOT_ON_FIELD         = "xy";
  protected static final int  FIRST_CHAR_INDEX     = 0;
  protected static final int  SECOND_CHAR_INDEX    = 1;
  private String              id;
  private ChessColour         colour;
  private int                 value;
  private boolean             isMoveRepeatable;
  protected String            position;
  private List<MoveVector>    moveSet;
  private ImageIcon           icon;
  private List<MoveVector>    availableMoveVectors = new ArrayList<>();
  private Map<String, String> legalMoveMap         = new TreeMap<>();
  private boolean             moveable             = true;

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
      List<MoveVector> moveSet, ImageIcon icon) {
    this.id = id;
    this.colour = colour;
    this.value = value;
    this.isMoveRepeatable = isMoveRepeatable;
    this.position = position;
    this.moveSet = moveSet;
    this.icon = icon;
    availableMoveVectors.addAll(moveSet);
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

  public List<MoveVector> getMoveSet() {
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

  public boolean isMoveable() {
    return moveable;
  }

  public void setMoveable(boolean moveable) {
    this.moveable = moveable;
  }

  public List<MoveVector> getAvailableMoveVectors() {
    return this.availableMoveVectors;
  }

  public void setAvailableMoveVectors(List<MoveVector> availableMoveVectors) {
    this.availableMoveVectors = availableMoveVectors;
  }

  public void emptyAvailableMoveVectors() {
    getAvailableMoveVectors().clear();
  }

  public void setAvailableMoveVectorsToMoveSet() {
    setAvailableMoveVectors(new ArrayList<MoveVector>(getMoveSet()));
  }

  /**
   * Builds {@code fieldKey} given one {@code char} as {@code int} and one
   * {@code int}.
   * 
   * @param posLetterAsNumber
   * @param posNumber
   * @return {@code String} fieldKey
   */
  protected String getFieldKey(int posLetterAsNumber, int posNumber) {
    String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
    return fieldKey;
  }

  /**
   * Checks, whether the given Inputs are within the field bounds or not.
   * 
   * @param posLetterAsNumber
   * @param posNumber
   * @return {@code true} if both inputs are in field bounds. Otherwise it
   *         returns {@code false}
   */
  protected boolean inFieldBounds(int posLetterAsNumber, int posNumber) {
    return posLetterAsNumber >= Field.LEFT_BOUND && posLetterAsNumber <= Field.RIGHT_BOUND
        && posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND;
  }

  /**
   * Moves {@code Piece} to {@code targetPosition}, if {@code targetPosition} is
   * legal. Checks legality by checking the value for the key
   * {@code targetPosition}. If there is an opposing {@code Piece} on
   * {@code targetPosition}, it sets {@code position} of that {@code Piece} to
   * {@code HIT_STRING}.
   * 
   * @param currentGameState
   * @param targetPosition
   * @param unlockedColour
   * @return {@code true} if move is successful. Otherwise it returns
   *         {@code false}
   */
  public boolean movePiece(Map<String, Piece> currentGameState, String targetPosition, ChessColour unlockedColour) {
    if(targetPosition == null || !getLegalMoveMap().containsKey(targetPosition) || getColour() != unlockedColour) {
      return false;
    }
    else {
      currentGameState.remove(getPosition());
      setPosition(targetPosition);
      if(getLegalMoveMap().get(targetPosition).equals(HIT_STRING)) {
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
   * @param currentGameState
   * @throws PieceOutOfBoundsException
   */
  public void createLegalMoveMap(Map<String, Piece> currentGameState) throws PieceOutOfBoundsException {
    if(getPosition() == null || getPosition().isEmpty()) {
      throw new PieceOutOfBoundsException();
    }
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Is Piece moveable?
    if(isMoveable()) {
      // Loop over every move vector in availableMoveVectors
      for(MoveVector moveVector : getAvailableMoveVectors()) {
        int posLetterAsNumber = getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumber = Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX));
        if(!inFieldBounds(posLetterAsNumber, posNumber)) {
          throw new PieceOutOfBoundsException();
        }
        // Change content of legalMoveMap based on move vector i and
        // currentGameState
        boolean repeatLoop = true;
        do {
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          // Check for fieldKey still on Field
          if(inFieldBounds(posLetterAsNumber, posNumber)) {
            // Check for EmptyPiece
            String fieldKey = getFieldKey(posLetterAsNumber, posNumber);
            if(currentGameState.get(fieldKey) == null) {
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
}
