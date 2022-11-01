package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.Map;
import java.util.Map.Entry;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.utility.MoveSetSupplier;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code Pawn} class contains a constructor as well as methods for every
 * {@code Pawn}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Pawn extends Piece {

  private boolean isEnPassentable = false;
  private boolean isPromotable    = false;
  private boolean hasMoved        = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code isMoveRepeatable}, {@code moveSet}, {@code isEnPassantable},
   * {@code isPromotable} and {@code hasMoved}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param icon
   * @author PKamps
   */
  public Pawn(String name, char colour, String position, Image icon) {
    super(name, colour, 1, false, position, MoveSetSupplier.getPawnMoveSet(), icon);
  }

  public boolean isEnPassentable() {
    return isEnPassentable;
  }

  public void setEnPassentable(boolean isEnPassentable) {
    this.isEnPassentable = isEnPassentable;
  }

  public boolean isPromotable() {
    return isPromotable;
  }

  public void setPromotable(boolean isPromotable) {
    this.isPromotable = isPromotable;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  /**
   * Sets {@code isEnPassentable} of all {@code Pawn} objects of the same colour
   * to {@code false}.
   * 
   * @param currentGameState
   * @param colour
   */
  public static void resetEnPassant(Map<String, Piece> currentGameState, char colour) {
    for(Entry<String, Piece> entry : currentGameState.entrySet()) {
      if(entry.getValue() instanceof Pawn && entry.getValue().getColour() == colour) {
        Pawn pawn = (Pawn)entry.getValue();
        pawn.setEnPassentable(false);
      }
    }
  }

  @Override
  public void setPosition(String position) {
    if(position == null || position.equals(Piece.NOT_ON_FIELD)) {
      this.position = position;
    }
    else {
      checkForEnPassant(position);
      this.position = position;
      setHasMoved(true);
      checkForPromotion();
    }
  }

  /**
   * If {@code position} contains a one or an eight, set {@code isPromotable} to
   * {@code true}.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForPromotion() {
    if(!(getPosition() == null || getPosition().isEmpty())) {
      int posNumber = Character.getNumericValue(getPosition().charAt(1));
      if(posNumber == 1 || posNumber == 8) {
        setPromotable(true);
      }
    }
  }

  @Override
  public void createLegalMoveMap(Map<String, Piece> currentGameState) throws PieceOutOfBoundsException {
    if(getPosition() == null || getPosition().isEmpty()) {
      throw new PieceOutOfBoundsException();
    }
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Loop over every movevector in moveSet
    for(int i = 0; i < currentGameState.size(); i++) {
      // Set posLetterAsNumber and posNumber
      int posLetterAsNumber = getPosition().charAt(0);
      int posNumber = Character.getNumericValue(getPosition().charAt(1));
      // Distinguish between move-only, take-only and check-only
      switch(i) {
      case 0 -> {
        // Single move
        if(getColour() == 'b') {
          posLetterAsNumber += -1 * getMoveSet().get(i).get(0);
          posNumber += -1 * getMoveSet().get(i).get(1);
        }
        else {
          posLetterAsNumber += getMoveSet().get(i).get(0);
          posNumber += getMoveSet().get(i).get(1);
        }
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        System.out.println(fieldKey);
        if(posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND && posLetterAsNumber >= Field.LEFT_BOUND
            && posLetterAsNumber <= Field.RIGHT_BOUND && currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID)) {
          getLegalMoveMap().put(fieldKey, TRUE_STRING);
        }
      }
      case 1 -> {
        // Double move
        int moveModifier = 1;
        if(getColour() == 'b') {
          posLetterAsNumber += -1 * getMoveSet().get(i).get(0);
          posNumber += -1 * getMoveSet().get(i).get(1);
          moveModifier = -1 * moveModifier;
        }
        else {
          posLetterAsNumber += getMoveSet().get(i).get(0);
          posNumber += getMoveSet().get(i).get(1);
        }
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND && posLetterAsNumber >= Field.LEFT_BOUND
            && posLetterAsNumber <= Field.RIGHT_BOUND
            && getLegalMoveMap().containsKey(Character.toString(posLetterAsNumber) + (posNumber - moveModifier))
            && currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID) && !isHasMoved()) {
          getLegalMoveMap().put(fieldKey, TRUE_STRING);
        }
      }
      case 2, 5 -> {
        // Take
        if(getColour() == 'b') {
          posLetterAsNumber += -1 * getMoveSet().get(i).get(0);
          posNumber += -1 * getMoveSet().get(i).get(1);
        }
        else {
          posLetterAsNumber += getMoveSet().get(i).get(0);
          posNumber += getMoveSet().get(i).get(1);
        }
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND && posLetterAsNumber >= Field.LEFT_BOUND
            && posLetterAsNumber <= Field.RIGHT_BOUND && !currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID)
            && currentGameState.get(fieldKey).getColour() != getColour()) {
          getLegalMoveMap().put(fieldKey, HIT_STRING);
        }
      }
      case 3, 4 -> {
        // Check for en-passant take
        if(getColour() == 'b') {
          posLetterAsNumber += -1 * getMoveSet().get(i).get(0);
          posNumber += -1 * getMoveSet().get(i).get(1);
        }
        else {
          posLetterAsNumber += getMoveSet().get(i).get(0);
          posNumber += getMoveSet().get(i).get(1);
        }
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(posNumber >= Field.LOWER_BOUND && posNumber <= Field.UPPER_BOUND && posLetterAsNumber >= Field.LEFT_BOUND
            && posLetterAsNumber <= Field.RIGHT_BOUND && currentGameState.get(fieldKey) instanceof Pawn
            && currentGameState.get(fieldKey).getColour() != getColour()
            && ((Pawn)currentGameState.get(fieldKey)).isEnPassentable()) {
          if(getColour() == 'b') {
            int posNumberForEnPassant = posNumber - 1;
            String enPassantFieldKey = Character.toString(posLetterAsNumber) + posNumberForEnPassant;
            getLegalMoveMap().put(enPassantFieldKey, HIT_STRING);
          }
          else {
            int posNumberForEnPassant = posNumber + 1;
            String enPassantFieldKey = Character.toString(posLetterAsNumber) + posNumberForEnPassant;
            getLegalMoveMap().put(enPassantFieldKey, HIT_STRING);
          }
        }
      }
      }
    }
  }

  /**
   * If {@code Pawn} has moved two tiles, set {@code isEnPassantable} =
   * {@code true}. Otherwise it is set to {@code false}.
   * 
   * @param targetPosition
   * @return void
   * @author PKamps
   */
  public void checkForEnPassant(String targetPosition) {
    if(!(getPosition() == null || getPosition().isEmpty() || targetPosition == null || targetPosition.isEmpty())) {
      int posNumber = Character.getNumericValue(getPosition().charAt(1));
      int posNumberTargetPosition = Character.getNumericValue(targetPosition.charAt(1));
      if(Math.abs(posNumberTargetPosition - posNumber) == 2) {
        setEnPassentable(true);
      }
      else {
        setEnPassentable(false);
      }
    }
  }

}
