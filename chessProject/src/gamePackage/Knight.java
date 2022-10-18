package gamePackage;

import java.awt.Image;

/**
 * Die {@code Knight}-Klasse stellt einen Constructor für jeden {@code Knight}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Knight extends Piece {

  /**
   * Ruft parametisierten Constructor von {@code Piece} auf und setzt
   * {@code value}, {@code isMoveRepeatable} und {@code moveSet}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @author PKamps
   * @see Piece
   */
  public Knight(String name, char colour, String position, Image skin) {
    super(name, colour, 3, false, position, MoveSetSupplier.supplyKnightMoveSet(), skin);
  }

}
