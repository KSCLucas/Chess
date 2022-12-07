package com.koerber.ausbildung.chess.piece;

import java.util.Map;

import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code Rook} class contains a constructor as well as methods for every
 * {@code Rook}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Rook extends Piece {

  public static final char CASTLE_SIDE_SHORT = 's';
  public static final char CASTLE_SIDE_LONG  = 'l';
  private final char       castleSide;
  private boolean          canCastle         = false;
  private boolean          hasMoved          = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code icon}, {@code isMoveRepeatable}, {@code castleSide},
   * {@code canCastle} and {@code hasMoved}.
   * 
   * @param idNum
   * @param colour
   * @param position
   * @param castleSide
   */
  public Rook(int idNum, ChessColour colour, String position, char castleSide) {
    super(idNum, colour, ChessPieceValue.ROOK.value, true, position, MoveSetSupplier.getRookMoveSet(),
        IconSupplier.getIcon(colour, "rook_small"));
    this.castleSide = castleSide;
  }

  public char getCastleSide() {
    return castleSide;
  }

  public boolean isCanCastle() {
    return canCastle;
  }

  public void setCanCastle(boolean canCastle) {
    this.canCastle = canCastle;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  @Override
  public void setPosition(String position) {
    this.position = position;
    setHasMoved(true);
  }

  /**
   * Checks all tiles next to the {@code Rook}, if {@code hasMoved = false}.
   * Sets {@code canCastle = true}, if every tile between {@code King} and
   * {@code Rook} is {@code null}.
   * 
   * @param currentGameState
   */
  public void checkForCastle(Map<String, Piece> currentGameState) {
    int posLetterAsNumber = getPosLetterAsNumber(getPosition());
    int posNumber = getPosNumber(getPosition());
    // Check for basic castle conditions
    if(inFieldBounds(posLetterAsNumber, posNumber) && !isHasMoved()) {
      // Determine the castle side
      int castleSideModifier = getCastleSide() == CASTLE_SIDE_SHORT ? -1 : 1;
      // Check, if tiles next to the rook up to the king are empty and set
      // canCastle depending on it
      boolean repeatable = true;
      int castleSideMultiplier = 1;
      do {
        String fieldKey = getFieldKey(posLetterAsNumber + castleSideModifier * castleSideMultiplier, posNumber);
        if(currentGameState.get(fieldKey) instanceof King king && king.getColour() == getColour()) {
          setCanCastle(true);
          repeatable = false;
        }
        else if(currentGameState.get(fieldKey) != null
            || !inFieldBounds(posLetterAsNumber + castleSideModifier * castleSideMultiplier, posNumber)) {
          setCanCastle(false);
          repeatable = false;
        }
        else {
          castleSideMultiplier += 1;
        }
      } while(repeatable);
    }
    else {
      setCanCastle(false);
    }
  }
}
