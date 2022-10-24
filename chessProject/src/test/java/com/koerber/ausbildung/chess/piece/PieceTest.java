package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * Tests the {@code Piece} class.
 * 
 * @author Lucas Noack
 */
class PieceTest {

  /**
   * Builds {@code Piece} object with test values and expects a correct
   * {@code Piece.legalMoveMap} based on the test values.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
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
   * {@code Piece.legalMoveMap} with only illegal move options based on the test
   * values.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
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
   * {@code Piece.legalMoveMap} based on the test values. Randomly scatters null
   * pointers in the {@code legalMoveMap}. Expected is
   * {@code NullPointerException}.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
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
   * Expects {@code Piece.position =} target Position.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceLegalMove")
  void movePieceLegalMoveTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position can be freely selected (move must be executable).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceIllegalMove")
  void movePieceIllegalMoveTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position can be freely selected (move must not be
   *          executable).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceUnknownKey")
  void movePieceUnknownKeyTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position is outside the field (e.g. H12), also includes
   *          {@code null} as key.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToPiecePosition")
  void movePieceToPiecePositionTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position =} target position &
   * {@code enemyPiece.position = "xy"}.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment "xy" counts as not being on the pitch.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToEnemyPiece")
  void movePieceToEnemyPieceTest() {
    fail("Not yet implemented");
  }
}
