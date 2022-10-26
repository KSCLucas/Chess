package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

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
  private boolean canCastle;
  private boolean hasMoved;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code isMoveRepeatable}, {@code castleSide}, {@code canCastle} and
   * {@code hasMoved}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @param castleSide
   * @author PKamps
   */
  public Rook(String name, char colour, String position, Image skin, char castleSide) {
    super(name, colour, 5, true, position, MoveSetSupplier.getRookMoveSet(), skin);
    this.castleSide = castleSide;
    this.canCastle = false;
    this.hasMoved = false;
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

  /**
   * Checks all tiles next to the {@code Rook}, if {@code hasMoved} =
   * {@code false}. Sets {@code canCastle} = {@code true}, if every tile between
   * {@code King} and {@code Rook} is empty.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCastle() {

  }
}
