package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * Tests the {@code Piece} class.
 * 
 * @author Lucas Noack, PKamps
 */
class PieceTest {

  /**
   * Builds {@code Piece} object with test values and expects a correct
   * {@code legalMoveMap} based on the test values.
   * 
   * @tested {@code createLegalMoveMap()}
   */
  @Test
  @DisplayName("createLegalMoveMapCorrect")
  void createLegalMoveMapCorrectTest() {
    Rook testRook = ObjectFactoryForTest.getRook();

    Map<String, String> correctMap = new TreeMap<>();
    correctMap.put("A2", "ttt");
    correctMap.put("A3", "ttt");
    correctMap.put("A4", "ttt");
    correctMap.put("A5", "ttt");
    correctMap.put("A6", "ttt");
    correctMap.put("A7", "ttt");
    correctMap.put("A8", "ttt");
    correctMap.put("B1", "ttt");
    correctMap.put("C1", "ttt");
    correctMap.put("D1", "ttt");
    correctMap.put("E1", "ttt");
    correctMap.put("F1", "ttt");
    correctMap.put("G1", "ttt");
    correctMap.put("H1", "ttt");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A1", testRook);

    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    assertEquals(correctMap, testRook.getLegalMoveMap());
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code PieceOutOfBoundsException} based on the test values.
   * 
   * @tests {@code createLegalMoveMap()}
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyPosition")
  void createLegalMoveMapEmptyPositionTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    testRook.setPosition(null);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A1", testRook);

    assertThrows(PieceOutOfBoundsException.class, () -> testRook.createLegalMoveMap(testCurrentGameState));
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code legalMoveMap} with only illegal move options based on the test
   * values.
   * 
   * @tests {@code createLegalMoveMap()}
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyMoveset")
  void createLegalMoveMapEmptyMovesetTest() {
    EmptyPiece testEmptyPiece = new EmptyPiece();
    testEmptyPiece.setPosition("A1");

    Map<String, String> correctMap = new TreeMap<>();

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A1", testEmptyPiece);

    try {
      testEmptyPiece.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }

    assertEquals(correctMap, testEmptyPiece.getLegalMoveMap());
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code legalMoveMap} based on the test values. Randomly scatters null
   * pointers in the {@code legalMoveMap}. Expected is
   * {@code NullPointerException}.
   * 
   * @tests {@code createLegalMoveMap()}
   */
  @Test
  @DisplayName("createLegalMoveMapNullOnField")
  void createLegalMoveMapNullOnFieldTest() {
    Rook testRook = ObjectFactoryForTest.getRook();

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A1", testRook);
    testCurrentGameState.put("C1", null);
    testCurrentGameState.put("H8", null);
    testCurrentGameState.put("E3", null);
    assertThrows(NullPointerException.class, () -> testRook.createLegalMoveMap(testCurrentGameState));
  }

  /**
   * Expects {@code position} = {@code targetPosition}.
   * 
   * @tests {@code movePiece()}
   */
  @Test
  @DisplayName("movePieceLegalMove")
  void movePieceLegalMoveTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    String legalTargetPosition = "C1";
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put(testRook.getPosition(), testRook);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }
    testRook.movePiece(testCurrentGameState, legalTargetPosition);
    assertEquals(legalTargetPosition, testRook.getPosition());
    assertEquals("r1w", testCurrentGameState.get(legalTargetPosition).getId());
    assertEquals(EmptyPiece.ID, testCurrentGameState.get("A1").getId());
  }

  /**
   * Expects {@code position} not to change.
   * 
   * @tests {@code movePiece()}
   * @comment target Position can be freely selected (move must be executable).
   */
  @Test
  @DisplayName("movePieceIllegalMove")
  void movePieceIllegalMoveTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    String illegalTargetPosition = "B2";
    String initialPosition = testRook.getPosition();
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put(testRook.getPosition(), testRook);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }
    testRook.movePiece(testCurrentGameState, illegalTargetPosition);
    assertEquals(initialPosition, testRook.getPosition());
    assertEquals("r1w", testCurrentGameState.get(testRook.getPosition()).getId());
  }

  /**
   * Expects {@code position} not to change.
   * 
   * @tests {@code movePiece()}
   * @comment target Position can be freely selected (move must not be
   *          executable).
   */
  @Test
  @DisplayName("movePieceUnknownKey")
  void movePieceUnknownKeyTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    String unknownTargetPosition = null;
    String initialPosition = testRook.getPosition();
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put(testRook.getPosition(), testRook);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }
    testRook.movePiece(testCurrentGameState, unknownTargetPosition);
    assertEquals(initialPosition, testRook.getPosition());
    assertEquals("r1w", testCurrentGameState.get(testRook.getPosition()).getId());
  }

  /**
   * Expects {@code position} not to change.
   * 
   * @tests {@code movePiece()}
   * @comment target Position is outside the field (e.g. H12), also includes
   *          {@code null} as key.
   */
  @Test
  @DisplayName("movePieceToPiecePosition")
  void movePieceToPiecePositionTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    String pieceTargetPosition = testRook.getPosition();
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put(testRook.getPosition(), testRook);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }
    testRook.movePiece(testCurrentGameState, pieceTargetPosition);
    assertEquals(pieceTargetPosition, testRook.getPosition());
  }

  /**
   * Expects {@code Piece.position =} target position &
   * {@code enemyPiece.position = "xy"}.
   * 
   * @tests {@code movePiece()}
   * @comment "xy" counts as not being on the field.
   */
  @Test
  @DisplayName("movePieceToEnemyPiece")
  void movePieceToEnemyPieceTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    String initialPosition = testRook.getPosition();
    String enemyPiecePosition = "A8";
    Rook opposingTestRook = ObjectFactoryForTest.getRook();
    opposingTestRook.setColour(ChessColour.BLACK);
    opposingTestRook.setPosition(enemyPiecePosition);
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put(testRook.getPosition(), testRook);
    testCurrentGameState.put(opposingTestRook.getPosition(), opposingTestRook);
    try {
      testRook.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }
    testRook.movePiece(testCurrentGameState, enemyPiecePosition);
    assertEquals("r1w", testCurrentGameState.get(enemyPiecePosition).getId());
    assertEquals("xy", opposingTestRook.getPosition());
    assertEquals(EmptyPiece.ID, testCurrentGameState.get(initialPosition).getId());
  }
}
