package com.koerber.ausbildung.chess.piece;

import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
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
   * {@code icon}, {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param id
   * @param colour
   * @param position
   */
  public Queen(String id, ChessColour colour, String position) {
    super(id, colour, ChessPieceValue.QUEEN.value, true, position, MoveSetSupplier.getQueenMoveSet(),
        IconSupplier.getIcon(colour, "queen_small"));
  }
}
