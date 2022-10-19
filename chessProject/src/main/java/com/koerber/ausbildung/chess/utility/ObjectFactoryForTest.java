package com.koerber.ausbildung.chess.utility;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Rook;

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
}
