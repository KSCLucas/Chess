package com.koerber.ausbildung.chess.piece;

import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
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
   * {@code icon}, {@code isMoveRepeatable} and {@code moveSet}.
   * 
   * @param id
   * @param colour
   * @param position
   */
  public Bishop(String id, ChessColour colour, String position) {
    super(id, colour, ChessPieceValue.BISHOP.value, true, position, MoveSetSupplier.getBishopMoveSet(),
        IconSupplier.getIcon(colour, "src/main/resources/Sprites_in_small/bishop_w_small.png",
            "src/main/resources/Sprites_in_small/bishop_small.png"));
  }
}
