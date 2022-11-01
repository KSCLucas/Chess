package com.koerber.ausbildung.chess;

import com.koerber.ausbildung.chess.piece.*;

public class ObjectFactoryForTest {
  public static Pawn getPawn() {
    Pawn testPiece = new Pawn("p1w", 'w', "A2", null);

    return testPiece;
  }

  public static Rook getRook() {
    Rook testRook = new Rook("r1w", 'w', "A1", null, 's');
    return testRook;
  }

  public static King getKing() {
    King testKing = new King("k1w", 'w', "A5", null);
    return testKing;

  }
  
  public static Field getField() {
    Field testField = new Field();
    return testField;
  }
  
  public static Queen getQueen() {
    Queen testQueen = new Queen("q1w", 'w', "A5", null);
    return testQueen;
  }
}
