package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code Bishop} class contains a constructor every {@code Bishop}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Bishop extends Piece {

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param icon
   * @author PKamps
   */
  public Bishop(String name, char colour, String position) {
    super(name, colour, 3, true, position, MoveSetSupplier.getBishopMoveSet(), icon);
  }

}
