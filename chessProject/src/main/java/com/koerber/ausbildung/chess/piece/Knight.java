package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code Knight} class contains a constructor every {@code Knight}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Knight extends Piece {

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param skin
   * @author PKamps
   * @see Piece
   */
  public Knight(String name, char colour, String position, Image skin) {
    super(name, colour, 3, false, position, MoveSetSupplier.getKnightMoveSet(), skin);
  }

}
