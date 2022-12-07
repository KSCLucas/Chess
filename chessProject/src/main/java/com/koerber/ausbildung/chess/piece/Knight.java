package com.koerber.ausbildung.chess.piece;

import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
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
   * {@code icon}, {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param idNum
   * @param colour
   * @param position
   */
  public Knight(int idNum, ChessColour colour, String position) {
    super(idNum, colour, ChessPieceValue.KNIGHT.value, false, position, MoveSetSupplier.getKnightMoveSet(),
        IconSupplier.getIcon(colour, "knight_small"));
  }
}
