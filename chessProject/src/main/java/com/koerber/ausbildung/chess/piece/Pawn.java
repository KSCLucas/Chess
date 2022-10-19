package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code Pawn} class contains a constructor as well as methods for every
 * {@code Pawn}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Pawn extends Piece {

  private boolean isEnPassentable;
  private boolean isPromotable;
  private boolean hasMoved;

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
    this.isEnPassentable = false;
    this.isPromotable = false;
    this.hasMoved = false;
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
   * @return void
   * @author PKamps
   * @see Piece.createLegalMoveMap
   */
  @Override
  public void createLegalMoveMap() {

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
