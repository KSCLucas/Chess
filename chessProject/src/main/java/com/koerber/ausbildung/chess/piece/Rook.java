package com.koerber.ausbildung.chess.piece;

import java.util.Map;

import com.koerber.ausbildung.chess.utility.ChessColour;
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

  private char    castleSide;
  private boolean canCastle = false;
  private boolean hasMoved  = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code icon}, {@code isMoveRepeatable}, {@code castleSide},
   * {@code canCastle} and {@code hasMoved}.
   * 
   * @param id
   * @param colour
   * @param position
   * @param castleSide
   */
  public Rook(String id, ChessColour colour, String position, char castleSide) {
    super(id, colour, 5, true, position, MoveSetSupplier.getRookMoveSet(), IconSupplier.getIcon(colour,
        "src/main/resources/Sprites_in_small/rook_w_small.png", "src/main/resources/Sprites_in_small/rook_small.png"));
    this.castleSide = castleSide;
  }

  public char getCastleSide() {
    return castleSide;
  }

  public void setCastleSide(char castleSide) {
    this.castleSide = castleSide;
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
   * Checks all tiles next to the {@code Rook}, if {@code hasMoved} =
   * {@code false}. Sets {@code canCastle} = {@code true}, if every tile between
   * {@code King} and {@code Rook} is an {@code EmptyPiece}.
   * 
   * @param currentGameState
   */
  public void checkForCastle(Map<String, Piece> currentGameState) {
    int posLetterAsNumber = getPosition().charAt(0);
    int posNumber = Character.getNumericValue(getPosition().charAt(1));
    // Check for basic castle conditions
    if(inFieldBounds(posLetterAsNumber, posNumber) && !isHasMoved()) {
      // Determine the castle side
      int castleSideModifier = 1;
      if(getCastleSide() == 's') {
        castleSideModifier = -1;
      }
      // Check, if tiles next to the rook up to the king are empty and set
      // canCastle depending on it
      String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
      boolean repeatable = true;
      int castleSideMultiplier = 1;
      do {
        fieldKey = Character.toString(posLetterAsNumber + castleSideModifier * castleSideMultiplier) + posNumber;
        if(currentGameState.get(fieldKey) instanceof King
            && currentGameState.get(fieldKey).getColour().equals(getColour())) {
          setCanCastle(true);
          repeatable = false;
        }
        else if(!(currentGameState.get(fieldKey) instanceof EmptyPiece)
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
