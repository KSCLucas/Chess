package com.koerber.ausbildung.chess.piece;

import java.awt.Image;

import com.koerber.ausbildung.chess.utility.MoveSetSupplier;

/**
 * The {@code Queen} class contains a constructor every {@code Queen}.
 * 
 * @author PKamps
 * @see Piece
 */
public class Queen extends Piece {

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
  public Queen(String name, char colour, String position) {
    super(name, colour, 9, true, position, MoveSetSupplier.getQueenMoveSet(), icon);
  }

}
