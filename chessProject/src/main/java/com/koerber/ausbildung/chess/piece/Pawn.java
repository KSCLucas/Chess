package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * Die {@code Pawn}-Klasse stellt einen Constructor und wichtige Methoden für
 * jeden {@code Pawn}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Pawn extends Piece {

  private boolean isEnPassentable;
  private boolean isPromotable;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von {@code Piece} auf und setzt
   * {@code value}, {@code isMoveRepeatable}, {@code moveSet},
   * {@code isEnPassantable}, {@code isPromotable} und {@code hasMoved}.
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
   * Prüft, ob {@code this.position} 1 oder 8 beinhaltet. Setzt
   * {@code this.isPromotable} auf {@code true}, wenn es der Fall ist.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForPromotion() {

  }

  /**
   * Setzt {@code this.hasMoved} auf {@code true}, wenn sich
   * {@code this.position} ändert.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForFirstMove() {

  }

  /**
   * Überschreibt {@code createLegalMoveMap} von {@code Piece}.
   * 
   * @return void
   * @author PKamps
   * @see Piece.createLegalMoveMap
   */
  @Override
  public void createLegalMoveMap() {

  }

  /**
   * Prüft, ob sich der Pawn um zwei Felder bewegt hat. Umgesetzt mit der
   * Differenz von {@code this.position} und {@code targetPosition}.
   * 
   * @param targetPosition
   * @return void
   * @author PKamps
   */
  public void checkForEnPassant(String targetPosition) {

  }

}
