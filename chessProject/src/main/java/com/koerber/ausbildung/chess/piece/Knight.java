package com.koerber.ausbildung.chess.piece;

import com.koerber.ausbildung.chess.utility.ChessColour;
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
   * @param id
   * @param colour
   * @param position
   */
  public Knight(String id, ChessColour colour, String position) {
    super(id, colour, 3, false, position, MoveSetSupplier.getKnightMoveSet(),
        IconSupplier.getIcon(colour, "src/main/resources/Sprites_in_small/knight_w_small.png",
            "src/main/resources/Sprites_in_small/knight_small.png"));
  }
}
