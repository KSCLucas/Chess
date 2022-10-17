package gamePackage;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Die King-Klasse stellt einen Constructor und wichtige Methoden für jeden
 * King.
 * 
 * @author PKamps
 */
public class King extends Piece {
  
  // TODO Spezifisches {@code moveSet} mit MoveSetBuilder (noch zu bauen)
  // produzieren.
  private boolean isInCheck;
  private boolean isCheckmate;
  private boolean canCastleShort;
  private boolean canCastleLong;
  private boolean hasMoved;

  /**
   * Ruft parametisierten Constructor von Piece auf und setzt
   * {@code this.isInCheck}, {@code this.isCheckmate},
   * {@code this.canCastleShort}, {@code this.canCastleLong} und
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
  public King(char name, char colour, int value, boolean isMoveRepeatable, String position,
      ArrayList<ArrayList<Integer>> moveSet, Image skin) {
    super(name, colour, value, isMoveRepeatable, position, moveSet, skin);
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
