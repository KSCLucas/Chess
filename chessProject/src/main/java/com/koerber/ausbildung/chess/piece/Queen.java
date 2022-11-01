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
   * {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param name
   * @param colour
   * @param position
   * @param icon
   * @author PKamps
   */
  public Queen(String name, ChessColour colour, String position) {
    super(name, colour, 9, true, position, MoveSetSupplier.getQueenMoveSet(),
        IconSupplier.getIcon(colour, "src/main/resources/Sprites_in_small/queen_w_small.png",
            "src/main/resources/Sprites_in_small/queen_small.png"));
  }

}
