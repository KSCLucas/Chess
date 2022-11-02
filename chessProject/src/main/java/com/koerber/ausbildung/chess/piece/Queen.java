package com.koerber.ausbildung.chess.piece;

import com.koerber.ausbildung.chess.utility.ChessColour;
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
    super(id, colour, 9, true, position, MoveSetSupplier.getQueenMoveSet(),
        IconSupplier.getIcon(colour, "src/main/resources/Sprites_in_small/queen_w_small.png",
            "src/main/resources/Sprites_in_small/queen_small.png"));
  }
}
