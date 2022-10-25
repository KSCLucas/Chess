package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Map;

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
   * @param skin
   * @author PKamps
   */
  public Pawn(String name, char colour, String position, Image skin) {
    super(name, colour, 1, false, position, MoveSetSupplier.getPawnMoveSet(), skin);
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
   * If {@code position} contains a one or an eight, set {@code isPromotable} to
   * {@code true}.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForPromotion() {

  }

  /**
   * Sets {@code hasMoved} to {@code true} when {@code position} changes.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForFirstMove() {

  }

  /**
   * Overrides {@code createLegalMoveMap} of {@code Piece}.
   * 
   * @param currentGameState
   * @return void
   * @author PKamps
   * @see Piece.createLegalMoveMap
   */
  @Override
  public void createLegalMoveMap(Map<String, Piece> currentGameState) throws PieceOutOfBoundsException {
    if(getPosition() == null || getPosition().isEmpty()) {
      throw new PieceOutOfBoundsException();
    } // TODO implement colour difference and vertical field bounds
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
        posLetterAsNumber += getMoveSet().get(i).get(0);
        posNumber += getMoveSet().get(i).get(1);
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID)) {
          getLegalMoveMap().put(fieldKey, TRUE_STRING);
        }
      }
      case 1 -> {
        // Double move
        posLetterAsNumber += getMoveSet().get(i).get(0);
        posNumber += getMoveSet().get(i).get(1);
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(getLegalMoveMap().containsKey(Character.toString(posLetterAsNumber) + (posNumber - 1))
            && currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID) && !isHasMoved()) {
          getLegalMoveMap().put(fieldKey, TRUE_STRING);
        }
      }
      case 2, 5 -> {
        // Take
        posLetterAsNumber += getMoveSet().get(i).get(0);
        posNumber += getMoveSet().get(i).get(1);
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(!currentGameState.get(fieldKey).getId().equals(EmptyPiece.ID)
            && currentGameState.get(fieldKey).getColour() != getColour()) {
          getLegalMoveMap().put(fieldKey, HIT_STRING);
        }
      }
      case 3, 4 -> {
        // Check for en-passant
        posLetterAsNumber += getMoveSet().get(i).get(0);
        posNumber += getMoveSet().get(i).get(1);
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        if(currentGameState.get(fieldKey) instanceof Pawn && currentGameState.get(fieldKey).getColour() != getColour()
            && ((Pawn)currentGameState.get(fieldKey)).isEnPassentable()) {
          int posNumberForEnPassant = posNumber + 1;
          String enPassantFieldKey = Character.toString(posLetterAsNumber) + posNumberForEnPassant;
          getLegalMoveMap().put(enPassantFieldKey, HIT_STRING);
        }
      }
      }
    }
  }

  /**
   * If {@code Pawn} has moved two tiles, set {@code isEnPassantable} =
   * {@code true}.
   * 
   * @param targetPosition
   * @return void
   * @author PKamps
   */
  public void checkForEnPassant(String targetPosition) {

  }

}
