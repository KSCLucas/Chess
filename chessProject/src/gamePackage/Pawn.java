package gamePackage;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Die Pawn-Klasse stellt einen Constructor und wichtige Methoden für jeden
 * Pawn.
 * 
 * @author PKamps
 */
public class Pawn extends Piece {

  // TODO Spezifisches {@code moveSet} mit MoveSetBuilder (noch zu bauen)
  // produzieren.
  private boolean isEnPassentable;
  private boolean isPromotable;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von Piece auf und setzt
   * {@code this.isEnPassantable}, {@code this.isPromotable} und
   * {@code this.hasMoved}.
   * 
   * @param name
   * @param colour
   * @param value
   * @param isMoveRepeatable
   * @param position
   * @param moveSet
   * @param skin
   * @author PKamps
   */
  public Pawn(char name, char colour, int value, boolean isMoveRepeatable, String position,
      ArrayList<ArrayList<Integer>> moveSet, Image skin) {
    super(name, colour, value, isMoveRepeatable, position, moveSet, skin);
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
   * Prüft, ob sich der Pawn um zwei Felder bewegt hat. Umgesetzt mit der
   * Differenz von {@code this.position} und {@code targetPosition}.
   * 
   * @param targetPosition
   * @return void
   * @author PKamps
   */
  public void checkForenPassant(String targetPosition) {

  }

}
