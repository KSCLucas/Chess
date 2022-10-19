package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * Die {@code King}-Klasse stellt einen Constructor und wichtige Methoden für
 * jeden {@code King}.
 * 
 * @author PKamps
 * @see Piece
 */
public class King extends Piece {

  private boolean isInCheck;
  private boolean isCheckmate;
  private boolean canCastleShort;
  private boolean canCastleLong;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von {@code Piece} auf und setzt
   * {@code value}, {@code isMoveRepeatable}, {@code moveSet},
   * {@code isInCheck}, {@code isCheckmate},
   * {@code canCastleShort}, {@code canCastleLong} und
   * {@code hasMoved}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @author PKamps
   */
  public King(String name, char colour, String position, Image skin) {
    super(name, colour, 999, false, position, MoveSetSupplier.supplyKingMoveSet(), skin);
    this.isInCheck = false;
    this.isCheckmate = false;
    this.canCastleShort = false;
    this.canCastleLong = false;
    this.hasMoved = false;
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
   * Prüft, ob {@code this.hasMoved} = {@code false} und ruft {@code canCastle}
   * der Rooks der selben Farbe auf. Setzt danach {@code this.canCastleShort}
   * und {@code this.canCastleLong} entsprechend auf {@code true}, falls Castle
   * möglich ist.
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

  /**
   * Prüft, ob King im Schach steht.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCheck() {

  }

  /**
   * Prüft, ob King Schachmatt ist.
   * 
   * @return void
   * @author PKamps
   */
  public void checkForCheckmate() {

  }

}
