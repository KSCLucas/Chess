package com.koerber.ausbildung.chess.utility;

import java.util.Map;
import java.util.TreeMap;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.piece.Bishop;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Knight;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.piece.Rook;

/**
 * Serves as a collection of functions for various converter methods *
 * 
 * @comment MapToFEN needs to add the takenPieces to the end of the FEN-String
 *          FENToMap needs to work with the taken pieces at the end of the
 *          FEN-String missing ENTToHistory
 * @author Toni Gropper
 * @since 19.10.2022
 */
public class Converter {

  private static String startPosition;
  private static String targetPosition;

  /**
   * converts TreeMap containing objects to a string in FEN notation and creates
   * a value ### for empty keys*
   * 
   * @param Map
   * @return String
   * @author Toni Gropper
   */
  public static String convertMapToFEN(Map<String, Piece> currentGameState) {

    String fen = "";
    for(int i = Field.LOWER_BOUND; i <= Field.UPPER_BOUND; i++) {
      for(int j = Field.LEFT_BOUND; j <= Field.RIGHT_BOUND; j++) {
        String fieldKey = Character.toString(j) + i;
        if(currentGameState.containsKey(fieldKey)) {
          fen += currentGameState.get(fieldKey).getId();
        }
        else {
          fen += "###";
        }
      }
      fen += "/";
    }
    return fen;
  }

  public static String getStartPosition() {
    return startPosition;
  }

  public static void setStartPosition(String startPosition) {
    Converter.startPosition = startPosition;
  }

  public static String getTargetPosition() {
    return targetPosition;
  }

  public static void setTargetPosition(String targetPosition) {
    Converter.targetPosition = targetPosition;
  }

  /**
   * separates an incoming FEN-String into many 3 digit Strings at the same time
   * they get a key
   * 
   * @param String
   * @return Map
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public static Map<String, Piece> convertFENToMap(String gameState) {

    Map<String, Piece> posOfPiece = new TreeMap<>();

    int row = 0;
    for(String rows : gameState.split("/")) {
      row++;
      char column = 'A';
      while(rows.length() != 0) {
        String fieldKey = Character.toString(column) + row;
        String value = rows.substring(0, 3);
        if(!value.equals("###")) {

          Piece newPiece = getNewPiece(value, fieldKey, Rook.CASTLE_SIDE_SHORT);
          posOfPiece.put(fieldKey, newPiece);
        }
        rows = rows.substring(3);

        column++;
      }
    }
    // for(Entry<String, Piece> entry : posOfPiece.entrySet()) {
    // System.out.println(entry);
    // }
    return posOfPiece;

  }

  /**
   * @param String pieceId, String pos, char castleside
   * @return Object Piece
   * @throws IllegalArgumentException
   * @comment
   * @author Toni Gropper
   */
  public static Piece getNewPiece(String pieceId, String pos, char castleSideSide) {
    String pieceType = pieceId.substring(0, 1);
    String pieceColour = pieceId.substring(2, 3);
    ChessColour colour = IdSupplier.COLOUR_WHITE_STRING.equals(pieceColour) ? ChessColour.WHITE : ChessColour.BLACK;
    if(IdSupplier.PAWN_ID_LETTER.equals(pieceType)) {

      Pawn pawn = new Pawn(9, colour, pos);
      return pawn;
    }
    else if(IdSupplier.ROOK_ID_LETTER.equals(pieceType)) {
      Rook rook = new Rook(3, colour, pos, 'l');
      return rook;
    }
    else if(IdSupplier.KNIGHT_ID_LETTER.equals(pieceType)) {
      Knight knight = new Knight(3, colour, pos);
      return knight;
    }
    else if(IdSupplier.BISHOP_ID_LETTER.equals(pieceType)) {
      Bishop bishop = new Bishop(3, colour, pos);
      return bishop;
    }
    else if(IdSupplier.QUEEN_ID_LETTER.equals(pieceType)) {
      Queen queen = new Queen(2, colour, pos);
      return queen;
    }
    else if(IdSupplier.KING_ID_LETTER.equals(pieceType)) {
      King king = new King(2, colour, pos);
      return king;
    }
    else {

      throw new IllegalArgumentException(pieceType);
    }

  }

  /**
   * converts two history fens to a history entry ("start position" -> "target
   * position")
   * 
   * @param String, String
   * @return String
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public static String convertFENToHistory(Field field) {
    // String takenPieceId = "";
    // TODO implement takenPieceId logic

    String historyEntry = (field.getCurrentTurn() - 1) + "  " + startPosition + "  >>>  "
        + targetPosition /*
                          * + "  " + takenPieceId
                          */;
    return historyEntry;
  }
}
