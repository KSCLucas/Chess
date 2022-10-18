package gamePackage;

import java.awt.Image;

/**
 * Die {@code Bishop}-Klasse stellt einen Constructor f�r jeden {@code Bishop}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Bishop extends Piece {

  /**
   * Ruft parametisierten Constructor von {@code Piece} auf und setzt
   * {@code value}, {@code isMoveRepeatable} und {@code moveSet}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @author PKamps
   */
  public Bishop(String name, char colour, String position, Image skin) {
    super(name, colour, 3, true, position, MoveSetSupplier.supplyBishopMoveSet(), skin);
  }

}