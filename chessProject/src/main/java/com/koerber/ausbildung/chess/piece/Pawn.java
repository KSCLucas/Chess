package com.koerber.ausbildung.chess.piece;

import java.util.Map;
import java.util.Map.Entry;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.MoveSetSupplier;
import com.koerber.ausbildung.chess.utility.MoveVector;
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
   * {@code icon}, {@code isMoveRepeatable}, {@code moveSet},
   * {@code isEnPassantable}, {@code isPromotable} and {@code hasMoved}.
   * 
   * @param id
   * @param colour
   * @param position
   */
  public Pawn(String id, ChessColour colour, String position) {
    super(id, colour, ChessPieceValue.PAWN.value, false, position, MoveSetSupplier.getPawnMoveSet(),
        IconSupplier.getIcon(colour, "pawn_small"));
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
   * Sets {@code isEnPassentable} of all {@code Pawn} objects of a different colour
   * to {@code false}.
   * 
   * @param currentGameState
   * @param colour
   */
  public static void resetEnPassant(Map<String, Piece> currentGameState, ChessColour colour) {
    for(Entry<String, Piece> entry : currentGameState.entrySet()) {
      if(entry.getValue() instanceof Pawn pawn && pawn.getColour() != colour) {
        pawn.setEnPassentable(false);
      }
    }
  }

  @Override
  public void setPosition(String position) {
    if(position == null || position.equals(NOT_ON_FIELD)) {
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
   */
  public void checkForPromotion() {
    if(!(getPosition() == null || getPosition().isEmpty())) {
      int posNumber = Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX));
      if(posNumber == Field.LOWER_BOUND && getColour() == ChessColour.BLACK
          || posNumber == Field.UPPER_BOUND && getColour() == ChessColour.WHITE) {
        setPromotable(true);
      }
    }
  }

  @Override
  public boolean movePiece(Map<String, Piece> currentGameState, String targetPosition) {
    if(targetPosition == null || !getLegalMoveMap().containsKey(targetPosition)) {
      return false;
    }
    else {
      currentGameState.remove(getPosition());
      setPosition(targetPosition);
      int posLetterAsNumber = getPosition().charAt(FIRST_CHAR_INDEX);
      int posNumber = getColour() == ChessColour.BLACK
          ? (Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX))) + 1
          : (Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX))) - 1;
      String fieldKey = getFieldKey(posLetterAsNumber, posNumber);
      if(currentGameState.get(fieldKey) instanceof Pawn enPassantablePawn) {
        if(enPassantablePawn.isEnPassentable() && enPassantablePawn.getColour() != getColour()) {
          enPassantablePawn.setPosition(NOT_ON_FIELD);
          currentGameState.remove(fieldKey);
        }
      }
      if(getLegalMoveMap().get(targetPosition).equals(HIT_STRING) && currentGameState.get(targetPosition) != null) {
        currentGameState.get(targetPosition).setPosition(NOT_ON_FIELD);
      }
      currentGameState.put(targetPosition, this);
      return true;
    }
  }

  @Override
  public void createLegalMoveMap(Map<String, Piece> currentGameState) throws PieceOutOfBoundsException {
    if(getPosition() == null || getPosition().isEmpty()) {
      throw new PieceOutOfBoundsException();
    }
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Is Pawn moveable?
    if(isMoveable()) {
      // Loop over every movevector in moveSet
      for(int i = 0; i < getMoveSet().size(); i++) {
        // Set posLetterAsNumber and posNumber
        int colourModifier = getColour() == ChessColour.BLACK ? -1 : 1;
        int posLetterAsNumber = getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumber = Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX));
        MoveVector moveVector = getMoveSet().get(i);
        // Distinguish between move-only, take-only and check-only
        switch(i) {
        case 0 -> {
          // Single move
          posLetterAsNumber += colourModifier * moveVector.getX();
          posNumber += colourModifier * moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber) && currentGameState.get(fieldKey) == null) {
            getLegalMoveMap().put(fieldKey, TRUE_STRING);
          }
        }
        case 1 -> {
          // Double move
          posLetterAsNumber += colourModifier * moveVector.getX();
          posNumber += colourModifier * moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber)
              && getLegalMoveMap().containsKey(Character.toString(posLetterAsNumber) + (posNumber - colourModifier))
              && currentGameState.get(fieldKey) == null && !isHasMoved()) {
            getLegalMoveMap().put(fieldKey, TRUE_STRING);
          }
        }
        case 2, 5 -> {
          // Take
          posLetterAsNumber += colourModifier * moveVector.getX();
          posNumber += colourModifier * moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber) && currentGameState.get(fieldKey) != null
              && currentGameState.get(fieldKey).getColour() != getColour()) {
            getLegalMoveMap().put(fieldKey, HIT_STRING);
          }
        }
        case 3, 4 -> {
          // Check for en-passant take
          posLetterAsNumber += colourModifier * moveVector.getX();
          posNumber += colourModifier * moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber) && currentGameState.get(fieldKey) instanceof Pawn pawn
              && pawn.getColour() != getColour() && pawn.isEnPassentable()) {
            int posNumberForEnPassant = getColour() == ChessColour.BLACK ? posNumber - 1 : posNumber + 1;
            String enPassantFieldKey = getFieldKey(posLetterAsNumber, posNumberForEnPassant);
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
   */
  public void checkForEnPassant(String targetPosition) {
    if((getPosition() != null && !getPosition().isEmpty()) || (targetPosition != null && !targetPosition.isEmpty())) {
      int posNumber = Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX));
      int posNumberTargetPosition = Character.getNumericValue(targetPosition.charAt(SECOND_CHAR_INDEX));
      setEnPassentable(Math.abs(posNumberTargetPosition - posNumber) == 2);
    }
  }
}
