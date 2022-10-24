package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code King} class contains a constructor as well as methods for every
 * {@code King}.
 * 
 * @author PKamps
 * @see Piece
 */
public class King extends Piece {

  private boolean isInCheck = false;
  private boolean isCheckmate = false;
  private boolean canCastleShort = false;
  private boolean canCastleLong = false;
  private boolean hasMoved = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code isMoveRepeatable}, {@code moveSet}, {@code isInCheck},
   * {@code isCheckmate}, {@code canCastleShort}, {@code canCastleLong} and
   * {@code hasMoved}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @author PKamps
   */
  public King(String name, char colour, String position, Image skin) {
    super(name, colour, 999, false, position, MoveSetSupplier.getKingMoveSet(), skin);
  }

  public boolean isInCheck() {
    return isInCheck;
  }

  public void setInCheck(boolean isInCheck) {
    this.isInCheck = isInCheck;
  }

  public boolean isCheckmate() {
    return isCheckmate;
  }

  public void setCheckmate(boolean isCheckmate) {
    this.isCheckmate = isCheckmate;
  }

  public boolean isCanCastleShort() {
    return canCastleShort;
  }

  public void setCanCastleShort(boolean canCastleShort) {
    this.canCastleShort = canCastleShort;
  }

  public boolean isCanCastleLong() {
    return canCastleLong;
  }

  public void setCanCastleLong(boolean canCastleLong) {
    this.canCastleLong = canCastleLong;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  /**
   * Checks, if {@code hasMoved} = {@code false} and calls {@code canCastle} of
   * all {@code Rooks} of the same colour. Sets {@code canCastleShort} and
   * {@code canCastleLong} to {@code true}, if castleing is possible.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCastle() {

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
   * Checks, if {@code King} is in check.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCheck() {

  }

  /**
   * Checks, if {@code King} is in checkmate.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCheckmate() {

  }

}
