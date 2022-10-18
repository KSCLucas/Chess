package gamePackage;

import java.awt.Image;

/**
 * Die {@code Queen}-Klasse stellt einen Constructor für jede {@code Queen}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Queen extends Piece {

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
  public Queen(String name, char colour, String position, Image skin) {
    super(name, colour, 9, true, position, MoveSetSupplier.supplyQueenMoveSet(), skin);
  }

}
