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

  private boolean enPassantable = false;
  private boolean promotable    = false;
  private boolean hasMoved      = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code icon}, {@code isMoveRepeatable}, {@code moveSet},
   * {@code isEnPassantable}, {@code isPromotable} and {@code hasMoved}.
   * 
   * @param idNum
   * @param colour
   * @param position
   */
  public Pawn(int idNum, ChessColour colour, String position) {
    super(idNum, colour, ChessPieceValue.PAWN.value, false, position,
        colour == ChessColour.WHITE ? MoveSetSupplier.getPawnWhiteMoveSet() : MoveSetSupplier.getPawnBlackMoveSet(),
        IconSupplier.getIcon(colour, "pawn_small"));
  }

  public boolean isEnPassentable() {
    return enPassantable;
  }

  public void setEnPassentable(boolean isEnPassentable) {
    this.enPassantable = isEnPassentable;
  }

  public boolean isPromotable() {
    return promotable;
  }

  public void setPromotable(boolean isPromotable) {
    this.promotable = isPromotable;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  /**
   * Sets {@code isEnPassentable} of all {@code Pawn} objects of a different
   * colour to {@code false}.
   * 
   * @param currentGameState
   * @param colour
   */
  public void resetEnPassant(Map<String, Piece> currentGameState, ChessColour colour) {
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
      int posNumber = getPosNumber(getPosition());
      if(posNumber == Field.LOWER_BOUND && getColour() == ChessColour.BLACK
          || posNumber == Field.UPPER_BOUND && getColour() == ChessColour.WHITE) {
        setPromotable(true);
      }
    }
  }

  @Override
  public boolean movePiece(Map<String, Piece> currentGameState, String targetPosition, ChessColour unlockedColour) {
    if(targetPosition == null || !getLegalMoveMap().containsKey(targetPosition) || getColour() != unlockedColour) {
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
    // Is Pawn moveable?
    if(isMoveable()) {
      // Clear legalMoveMap
      getLegalMoveMap().clear();
      // Loop over every movevector in availableMoveVectors
      for(int i = 0; i < getAvailableMoveVectors().size(); i++) {
        // Set posLetterAsNumber and posNumber
        int colourModifier = getColour() == ChessColour.BLACK ? -1 : 1;
        int posLetterAsNumber = getPosLetterAsNumber(getPosition());
        int posNumber = getPosNumber(getPosition());
        MoveVector moveVector = getAvailableMoveVectors().get(i);
        // Distinguish between move-only, take-only and check-only
        switch(i) {
        case 0 -> {
          // Single move
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber) && currentGameState.get(fieldKey) == null) {
            getLegalMoveMap().put(fieldKey, MOVE_STRING);
          }
        }
        case 1 -> {
          // Double move
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber)
              && getLegalMoveMap().containsKey(Character.toString(posLetterAsNumber) + (posNumber - colourModifier))
              && currentGameState.get(fieldKey) == null && !isHasMoved()) {
            getLegalMoveMap().put(fieldKey, MOVE_STRING);
          }
        }
        case 2, 5 -> {
          // Take
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          if(inFieldBounds(posLetterAsNumber, posNumber) && currentGameState.get(fieldKey) != null
              && currentGameState.get(fieldKey).getColour() != getColour()) {
            getLegalMoveMap().put(fieldKey, HIT_STRING);
          }
        }
        case 3, 4 -> {
          // Check for en-passant take
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
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
