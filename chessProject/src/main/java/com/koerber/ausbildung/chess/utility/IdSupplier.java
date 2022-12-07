package com.koerber.ausbildung.chess.utility;

import com.koerber.ausbildung.chess.piece.Bishop;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Knight;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.piece.Rook;

/**
 * Supplies constants for {@code class} and {@code colour} of type
 * {@code String}. Provides {@code id} related methods.
 * 
 * @author PKamps
 */
public abstract class IdSupplier {

  public static final String PAWN_ID_LETTER      = "p";
  public static final String KNIGHT_ID_LETTER    = "n";
  public static final String BISHOP_ID_LETTER    = "b";
  public static final String ROOK_ID_LETTER      = "r";
  public static final String QUEEN_ID_LETTER     = "q";
  public static final String KING_ID_LETTER      = "k";
  public static final String COLOUR_WHITE_STRING = "w";
  public static final String COLOUR_BLACK_STRING = "b";

  private static String getClassString(Piece piece) {
    String classString;
    if(piece instanceof Pawn) {
      classString = PAWN_ID_LETTER;
    }
    else if(piece instanceof Knight) {
      classString = KNIGHT_ID_LETTER;
    }
    else if(piece instanceof Bishop) {
      classString = BISHOP_ID_LETTER;
    }
    else if(piece instanceof Rook) {
      classString = ROOK_ID_LETTER;
    }
    else if(piece instanceof Queen) {
      classString = QUEEN_ID_LETTER;
    }
    else if(piece instanceof King) {
      classString = KING_ID_LETTER;
    }
    else {
      classString = null;
    }
    return classString;
  }

  /**
   * Builds an {@code Id} for a {@code Piece} depending on the {@code colour}
   * and {@code class} of it.
   * 
   * @param piece
   * @param idNum
   * @param pieceColour
   * @return String id
   */
  public static String getId(Piece piece, int idNum, ChessColour pieceColour) {
    return getClassString(piece) + idNum + (pieceColour == ChessColour.WHITE ? COLOUR_WHITE_STRING : COLOUR_BLACK_STRING);
  }
}
