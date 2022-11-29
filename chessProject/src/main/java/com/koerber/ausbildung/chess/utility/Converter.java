package com.koerber.ausbildung.chess.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

          Piece newPiece = getNewPiece(value, fieldKey, '1');
          posOfPiece.put(fieldKey, newPiece);
          rows = rows.substring(3);
        }

        column++;
      }
    }
    for(Entry<String, Piece> entry : posOfPiece.entrySet()) {
      System.out.println(entry);
    }
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
    ChessColour colour = "w".equals(pieceColour) ? ChessColour.WHITE : ChessColour.BLACK;
    if("p".equals(pieceType)) {

      Pawn pawn = new Pawn(pieceId, colour, pos);
      return pawn;
    }
    else if("r".equals(pieceType)) {
      Rook rook = new Rook(pieceId, colour, pos, 'l');
      return rook;
    }
    else if("n".equals(pieceType)) {
      Knight knight = new Knight(pieceId, colour, pos);
      return knight;
    }
    else if("b".equals(pieceType)) {
      Bishop bishop = new Bishop(pieceId, colour, pos);
      return bishop;
    }
    else if("q".equals(pieceType)) {
      Queen queen = new Queen(pieceId, colour, pos);
      return queen;
    }
    else if("k".equals(pieceType)) {
      King king = new King(pieceId, colour, pos);
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
  public static String convertFENToHistory() {
    String takenPieceId = "";

    String historyEntry = (Field.getCurrentTurn()-1) + "  " + startPosition + "  >>>  " + targetPosition + "  " + takenPieceId;
    return historyEntry;
  }
}
