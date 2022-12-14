package com.koerber.ausbildung.chess;

import com.koerber.ausbildung.chess.piece.*;
import com.koerber.ausbildung.chess.utility.ChessColour;

public class ObjectFactoryForTest {
  public static Pawn getPawn() {
    Pawn testPiece = new Pawn(1, ChessColour.WHITE, "A2");

    return testPiece;
  }

  public static Rook getRook() {
    Rook testRook = new Rook(1, ChessColour.WHITE, "A1", 's');
    return testRook;
  }

  public static King getKing() {
    King testKing = new King(1, ChessColour.WHITE, "A5");
    return testKing;

  }

  public static Field getField() {
    Field testField = new Field();
    return testField;
  }

  public static Queen getQueen() {
    Queen testQueen = new Queen(1, ChessColour.WHITE, "A5");
    return testQueen;
  }
}
