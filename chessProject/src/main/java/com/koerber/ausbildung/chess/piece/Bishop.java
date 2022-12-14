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
   * @param idNum
   * @param colour
   * @param position
   */
  public Bishop(int idNum, ChessColour colour, String position) {
    super(idNum, colour, ChessPieceValue.BISHOP.value, true, position, MoveSetSupplier.getBishopMoveSet(),
        IconSupplier.getIcon(colour, "bishop_small"));
  }
}
