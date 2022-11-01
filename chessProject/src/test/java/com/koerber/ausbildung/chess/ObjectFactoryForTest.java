package com.koerber.ausbildung.chess;

import com.koerber.ausbildung.chess.piece.*;
import com.koerber.ausbildung.chess.utility.ChessColour;

public class ObjectFactoryForTest {
  public static Pawn getPawn() {
    Pawn testPiece = new Pawn("p1w", ChessColour.WHITE, "A2");

    return testPiece;
  }

  public static Rook getRook() {
    Rook testRook = new Rook("r1w", ChessColour.WHITE, "A1", 's');
    return testRook;
  }

  public static King getKing() {
    King testKing = new King("k1w", ChessColour.WHITE, "A5");
    return testKing;

  }
  
  public static Field getField() {
    Field testField = new Field();
    return testField;
  }
}
