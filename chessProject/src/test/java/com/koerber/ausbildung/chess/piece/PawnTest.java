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
 * Tests the {@code Pawn} class.
 * 
 * @author Lucas Noack, PKamps
 */
class PawnTest {

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} as reference. Expects {@code Pawn.getLegalMoveMap}
   * equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapCorrect")
  void createLegalMoveMapCorrectTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();

    Pawn opposingPawn = ObjectFactoryForTest.getPawn();
    opposingPawn.setId("p1b");
    opposingPawn.setColour(ChessColour.BLACK);
    opposingPawn.setPosition("B3");

    Map<String, String> correctMap = new TreeMap<>();
    correctMap.put("A3", "ttt");
    correctMap.put("A4", "ttt");
    correctMap.put("B3", "hhh");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", testPawn);
    testCurrentGameState.put("B3", opposingPawn);

    try {
      testPawn.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testPawn.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} as reference. Expects {@code Pawn.getLegalMoveMap}
   * equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapCorrectBlack")
  void createLegalMoveMapCorrectBlackTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setId("p1b");
    testPawn.setColour(ChessColour.BLACK);
    testPawn.setPosition("B3");
    testPawn.setHasMoved(true);

    Pawn opposingPawn = ObjectFactoryForTest.getPawn();

    Map<String, String> correctMap = new TreeMap<>();
    correctMap.put("A2", "hhh");
    correctMap.put("B2", "ttt");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", opposingPawn);
    testCurrentGameState.put("B3", testPawn);

    try {
      testPawn.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testPawn.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values and creates an empty
   * {@code legalMoveMap} as reference. Expects {@code Pawn.getLegalMoveMap}
   * equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyPosition")
  void createLegalMoveMapEmptyPositionTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition(null);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", testPawn);

    assertThrows(PieceOutOfBoundsException.class, () -> testPawn.createLegalMoveMap(testCurrentGameState));
  }

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} as reference. Expects {@code Pawn.getLegalMoveMap}
   * equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyMoveSet")
  void createLegalMoveMapEmptyMoveSetTest() {
    EmptyPiece testEmptyPiece = new EmptyPiece();

    Map<String, String> correctMap = new TreeMap<>();

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", testEmptyPiece);

    try {
      testEmptyPiece.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testEmptyPiece.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} with some null values as reference. Expects
   * {@code Pawn.getLegalMoveMap} equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapNullOnField")
  void createLegalMoveMapNullOnFieldTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", testPawn);
    testCurrentGameState.put("A3", null);
    testCurrentGameState.put("C1", null);
    testCurrentGameState.put("F7", null);

    assertThrows(NullPointerException.class, () -> testPawn.createLegalMoveMap(testCurrentGameState));
  }

  /**
   * Builds a {@code Pawn} object with test values, a few other opposing
   * {@code Pieces} and creates a {@code legalMoveMap} as reference. Expects
   * {@code Pawn.getLegalMoveMap} equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapEnPassant")
  void createLegalMoveMapEnPassantTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();

    Pawn opposingPawn = ObjectFactoryForTest.getPawn();
    opposingPawn.setId("p1b");
    opposingPawn.setColour(ChessColour.BLACK);
    opposingPawn.setPosition("B2");
    opposingPawn.setEnPassentable(true);
    opposingPawn.setHasMoved(true);

    Map<String, String> correctMap = new TreeMap<>();
    correctMap.put("A3", "ttt");
    correctMap.put("A4", "ttt");
    correctMap.put("B3", "hhh");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A2", testPawn);
    testCurrentGameState.put("B2", opposingPawn);

    try {
      testPawn.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testPawn.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} as reference. {@code setHasMoved} = {@code true}.
   * Expects {@code Pawn.getLegalMoveMap} equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
   * @author PKamps
   */
  @Test
  @DisplayName("createLegalMoveMapNoDoubleMove")
  void createLegalMoveMapNoDoubleMoveTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition("A6");
    testPawn.setHasMoved(true);

    Map<String, String> correctMap = new TreeMap<>();
    correctMap.put("A7", "ttt");

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    testCurrentGameState.put("A6", testPawn);

    try {
      testPawn.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testPawn.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values (e.g.
   * {@code Pawn.color = 'w' & Pawn.position = "A8"}) and expects
   * {@code Piece.isPromotable = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForPromotion()}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForPromotionPromotable")
  void checkForPromotionPromotableTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition("A8");
    testPawn.checkForPromotion();
    assertEquals(true, testPawn.isPromotable());
  }

  /**
   * Builds a {@code Pawn} object with test values (e.g.
   * {@code Pawn.color = 'w' & Pawn.position = "A3"}) and expects
   * {@code Piece.isPromotable = false}. The same is true for
   * {@code Pawn.postion = null}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForPromotion()}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForPromotionNotPromotable")
  void checkForPromotionNotPromotableTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition("A7");
    testPawn.checkForPromotion();
    assertEquals(false, testPawn.isPromotable());
  }

  /**
   * Builds a {@code Pawn} object with initial test values. Then set
   * {@code Pawn.position} to another legal value and expect
   * {@code Pawn.hasMoved = true}.
   * 
   * @param void
   * @return void
   * @tests {@code setPosition}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForFirstMoveSuccessTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition("A3");
    assertEquals("A3", testPawn.getPosition());
    assertEquals(true, testPawn.isHasMoved());
  }

  /**
   * Builds a {@code Pawn} object with initial test values. One does not change
   * {@code Pawn.position} and expects{@code Pawn.hasMoved = false}.
   * 
   * @param void
   * @return void
   * @tests {@code setPosition}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    assertEquals(false, testPawn.isHasMoved());
  }

  /**
   * Builds a {@code Pawn} object with initial test values. You change
   * {@code Pawn.position} by 2 fields and
   * expect{@code Pawn.isEnPassantable = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForEnPassantSuccessTest()}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForEnPassantSuccess")
  void checkForEnPassantSuccessTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.checkForEnPassant("A4");
    assertEquals(true, testPawn.isEnPassentable());
  }

  /**
   * Builds a {@code Pawn} object with initial test values. You change
   * {@code Pawn.position} by not 2 fields and
   * expect{@code Pawn.isEnPassantable = false}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForEnPassantSuccessTest()}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForEnPassantFailure")
  void checkForEnPassantFailureTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.checkForEnPassant("A3");
    assertEquals(false, testPawn.isEnPassentable());
  }
}
