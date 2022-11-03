package com.koerber.ausbildung.chess.utility;

/**
 * Provides {@code values} for every {@code Piece}.
 * 
 * @author PKamps
 */
public enum ChessPieceValue {

  PAWN(1), BISHOP(3), KING(999), KNIGHT(3), QUEEN(9), ROOK(5);

  private ChessPieceValue(int value) {
  }
}
