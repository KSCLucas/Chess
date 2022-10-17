package gamePackage;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Die Rook-Klasse stellt einen Constructor und wichtige Methoden für jeden
 * Rook.
 * 
 * @author PKamps
 */
public class Rook extends Piece {
  
  // TODO Spezifisches {@code moveSet} mit MoveSetBuilder (noch zu bauen)
  // produzieren.
  private char    castleSide;
  private boolean canCastle;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von Piece auf und setzt
   * {@code this.castleSide}, {@code this.canCastle} und {@code this.hasMoved}.
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
  public Rook(char name, char colour, int value, boolean isMoveRepeatable, String position,
      ArrayList<ArrayList<Integer>> moveSet, Image skin, char castleSide) {
    super(name, colour, value, isMoveRepeatable, position, moveSet, skin);
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
