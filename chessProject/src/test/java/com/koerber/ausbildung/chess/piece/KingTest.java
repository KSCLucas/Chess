package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.utility.ChessColour;

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
  @DisplayName("checkForCeckSuccess")
  void checkForCeckSuccessTest() {
    fail("Not yet implemented");
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
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code King} object with initial test values and puts it in
   * checkmate. One expects {@code King.isCheckmate = true}.
   * 
   * @tests {@code checkForCheckmate()}
   */
  @Test
  @DisplayName("checkForCeckmateSuccess")
  void checkForCeckmateSuccessTest() {
    fail("Not yet implemented");
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
    fail("Not yet implemented");
  }
}
