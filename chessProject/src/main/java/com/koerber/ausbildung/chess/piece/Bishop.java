package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * Die {@code Bishop}-Klasse stellt einen Constructor für jeden {@code Bishop}.
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
    super(name, colour, 3, true, position, MoveSetSupplier.getBishopMoveSet(), skin);
  }

}
