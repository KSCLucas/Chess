package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * Die {@code Rook}-Klasse stellt einen Constructor und wichtige Methoden für
 * jeden {@code Rook}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Rook extends Piece {

  private char    castleSide;
  private boolean canCastle;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von {@code Piece} auf und setzt
   * {@code value}, {@code isMoveRepeatable}, {@code castleSide},
   * {@code canCastle} und {@code hasMoved}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @param castleSide
   * @author PKamps
   */
  public Rook(String name, char colour, String position, Image skin, char castleSide) {
    super(name, colour, 5, true, position, MoveSetSupplier.supplyRookMoveSet(), skin);
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
   * Prüft, ob {@code this.hasMoved} = {@code false}, falls ja, werden die
   * Felder neben dem Turm geprüft. Falls alle Felder zwischen Rook und King
   * leer sind, wird {@code this.canCastle} = {@code true} gesetzt.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCastle() {

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
}
