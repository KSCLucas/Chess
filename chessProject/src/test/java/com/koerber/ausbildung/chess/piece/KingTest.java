package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * Tests the {@code King} class.
 * 
 * @author Lucas Noack, PKamps
 */
class KingTest {

  /**
   * Builds a {@code King} object with initial test values. Then set
   * {@code King.position} to another legal value and expect
   * {@code King.hasMoved = true}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForFirstMoveSuccessTest() {
    King testKing = ObjectFactoryForTest.getKing();
    testKing.setPosition("A4");
    assertEquals("A4", testKing.getPosition());
    assertEquals(true, testKing.isHasMoved());
  }

  /**
   * Builds a {@code King} object with initial test values. One does not change
   * {@code King.position} and expects{@code King.hasMoved = false}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    King testKing = ObjectFactoryForTest.getKing();
    assertEquals(false, testKing.isHasMoved());
  }

  /**
   * Builds a {@code King} object with initial test values. Sets mock tower
   * value to {@code true} for {@code Rook.canCastleSide = 's' / 'l'}. One
   * expects: {@code King.canCastleShort = true & King.canCastleLong = true}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleBothSidesSuccess")
  void checkForCastleBothSidesSuccessTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Rook testRookLong = new Rook(1, ChessColour.WHITE, "A1", Rook.CASTLE_SIDE_LONG);
    Rook testRookShort = new Rook(2, ChessColour.WHITE, "H1", Rook.CASTLE_SIDE_SHORT);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("A1", testRookLong);
    testCurrentGameState.put("H1", testRookShort);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(true, testRookLong.isCanCastle());
    assertEquals(true, testRookShort.isCanCastle());
    assertEquals("G1", testKing.getCastleKeyShort());
    assertEquals("C1", testKing.getCastleKeyLong());
  }

  /**
   * Builds a {@code King} object with initial test values. Sets mock tower
   * value to {@code true} for {@code Rook.canCastleSide = 'l'}. One expects:
   * {@code King.canCastleShort = false & King.canCastleLong = true}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleLongSideSuccess")
  void checkForCastleLongSideSuccessTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Rook testRookLong = new Rook(1, ChessColour.WHITE, "A1", Rook.CASTLE_SIDE_LONG);
    Rook testRookShort = new Rook(2, ChessColour.WHITE, "H1", Rook.CASTLE_SIDE_SHORT);
    Knight testKnight = new Knight(1, ChessColour.WHITE, "G1");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("A1", testRookLong);
    testCurrentGameState.put("H1", testRookShort);
    testCurrentGameState.put("G1", testKnight);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(true, testRookLong.isCanCastle());
    assertEquals(false, testRookShort.isCanCastle());
    assertEquals(null, testKing.getCastleKeyShort());
    assertEquals("C1", testKing.getCastleKeyLong());
  }

  /**
   * Builds a {@code King} object with initial test values. Sets mock tower
   * value to {@code true} for {@code Rook.canCastleSide = 'l'}. One expects:
   * {@code King.canCastleShort = false & King.canCastleLong = true}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleShortSideSuccess")
  void checkForCastleShortSideSuccessTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Rook testRookLong = new Rook(1, ChessColour.WHITE, "A1", Rook.CASTLE_SIDE_LONG);
    Rook testRookShort = new Rook(2, ChessColour.WHITE, "H1", Rook.CASTLE_SIDE_SHORT);
    Knight testKnight = new Knight(1, ChessColour.WHITE, "B1");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("A1", testRookLong);
    testCurrentGameState.put("H1", testRookShort);
    testCurrentGameState.put("B1", testKnight);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(false, testRookLong.isCanCastle());
    assertEquals(true, testRookShort.isCanCastle());
    assertEquals("G1", testKing.getCastleKeyShort());
    assertEquals(null, testKing.getCastleKeyLong());
  }

  /**
   * Builds a {@code King} object with initial test values. Sets mock tower
   * value to {@code false} for {@code Rook.canCastleSide = 's' / 'l'}. One
   * expects: {@code King.canCastleShort = false & King.canCastleLong = false}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleFailure")
  void checkForCastleFailureTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Rook testRookLong = new Rook(1, ChessColour.WHITE, "A1", Rook.CASTLE_SIDE_LONG);
    Rook testRookShort = new Rook(2, ChessColour.WHITE, "H1", Rook.CASTLE_SIDE_SHORT);
    Knight testKnightLong = new Knight(1, ChessColour.WHITE, "B1");
    Knight testKnightShort = new Knight(2, ChessColour.WHITE, "G1");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("A1", testRookLong);
    testCurrentGameState.put("H1", testRookShort);
    testCurrentGameState.put("B1", testKnightLong);
    testCurrentGameState.put("G1", testKnightShort);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(false, testRookLong.isCanCastle());
    assertEquals(false, testRookShort.isCanCastle());
    assertEquals(null, testKing.getCastleKeyShort());
    assertEquals(null, testKing.getCastleKeyLong());
  }

  /**
   * Builds a {@code King} object with initial test values and puts it in check.
   * One expects {@code King.isInCheck = true}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckSuccess")
  void checkForCheckSuccessTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E3");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(true, testKing.isInCheck());
  }

  /**
   * Builds a {@code King} object with initial test values and puts it in check.
   * One expects {@code King.isInCheck = true}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckJumpInFront")
  void checkForCheckJumpInFrontTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "C3");
    Knight testKnight = new Knight(1, ChessColour.WHITE, "B1");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("C3", opposingTestQueen);
    testCurrentGameState.put("B1", testKnight);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);
    try {
      testKnight.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    Map<String, String> testReferenceMap = new TreeMap<>();
    testReferenceMap.put("C3", Piece.HIT_STRING);
    testReferenceMap.put("D2", Piece.MOVE_STRING);

    assertEquals(true, testKing.isInCheck());
    assertEquals(testReferenceMap, testKnight.getLegalMoveMap());
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in check. One expects {@code King.isInCheck = false}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckFailure")
  void checkForCheckFailureTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E3");
    Pawn testPawn = new Pawn(1, ChessColour.WHITE, "E2");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);
    testCurrentGameState.put("E2", testPawn);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(false, testKing.isInCheck());
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in check. One expects {@code King.isInCheck = false}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckFailureWithTwoOpposingPieces")
  void checkForCheckFailureWithTwoOpposingPiecesTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E3");
    Pawn opposingPawn = new Pawn(1, ChessColour.BLACK, "E2");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);
    testCurrentGameState.put("E2", opposingPawn);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);

    assertEquals(false, testKing.isInCheck());
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in check. One expects {@code King.isInCheck = false}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckFailureWithAlliedRookInFront")
  void checkForCheckFailureWithAlliedRookInFrontTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E3");
    Rook testRook = new Rook(1, ChessColour.WHITE, "E2", Rook.CASTLE_SIDE_LONG);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);
    testCurrentGameState.put("E2", testRook);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    assertEquals(false, testKing.isInCheck());
    assertEquals(Piece.HIT_STRING, testRook.getLegalMoveMap().get("E3"));
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in check. One expects {@code King.isInCheck = false}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckFailureBlock")
  void checkForCheckFailureBlockTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E3");
    Bishop testBishop = new Bishop(1, ChessColour.WHITE, "E2");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);
    testCurrentGameState.put("E2", testBishop);

    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);
    try {
      testBishop.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    assertEquals(false, testKing.isInCheck());
    assertEquals(new TreeMap<String, String>(), testBishop.getLegalMoveMap());
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in check. One expects {@code King.isInCheck = false}.
   * 
   * @tests {@code checkForCheck()}
   */
  @Test
  @DisplayName("checkForCheckNoEnPassant")
  void checkForCheckNoEnPassantTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");
    Queen opposingTestQueen = new Queen(1, ChessColour.BLACK, "E7");
    Pawn testPawn = new Pawn(1, ChessColour.WHITE, "E5");
    Pawn opposingTestPawn = new Pawn(1, ChessColour.BLACK, "F7");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    testCurrentGameState.put("E1", testKing);
    testCurrentGameState.put("E3", opposingTestQueen);
    testCurrentGameState.put("E5", testPawn);
    testCurrentGameState.put("F7", opposingTestPawn);

    opposingTestPawn.setPosition("F5");
    testKing.checkForCheckAndCreateLegalMoveMap(testCurrentGameState);
    try {
      testPawn.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    assertEquals(false, testKing.isInCheck());
    assertEquals(null, testPawn.getLegalMoveMap().get("F6"));
    assertEquals(Piece.MOVE_STRING, testPawn.getLegalMoveMap().get("E6"));
    assertEquals(true, opposingTestPawn.isEnPassentable());
  }

  /**
   * Builds a {@code King} object with initial test values and puts it in
   * checkmate. One expects {@code King.isCheckmate = true}.
   * 
   * @tests {@code checkForCheckmate()}
   */
  @Test
  @DisplayName("checkForCeckmateSuccess")
  void checkForCheckmateSuccessTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");

    testKing.setInCheck(true);

    testKing.checkForCheckmate();

    assertEquals(true, testKing.isCheckmate());
  }

  /**
   * Builds a {@code King} object with initial test values and does not put it
   * in checkmate. One expects {@code King.isCheckmate = false}.
   * 
   * @tests {@code checkForCheckmate()}
   */
  @Test
  @DisplayName("checkForCheckmateFailure")
  void checkForCheckmateFailureTest() {
    King testKing = new King(1, ChessColour.WHITE, "E1");

    testKing.setInCheck(false);

    testKing.checkForCheckmate();

    assertEquals(false, testKing.isCheckmate());
  }
}
