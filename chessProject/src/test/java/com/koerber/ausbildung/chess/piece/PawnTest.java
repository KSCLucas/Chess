package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.MoveVector;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * Tests the {@code Pawn} class.
 * 
 * @author Lucas Noack, PKamps
 */
class PawnTest {

  /**
   * Builds a {@code Pawn} object with test values and creates a
   * {@code legalMoveMap} as reference. Expects {@code getLegalMoveMap} equal to
   * {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
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
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
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
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
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
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyPosition")
  void createLegalMoveMapEmptyPositionTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.setPosition(null);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
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
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyMoveSet")
  void createLegalMoveMapEmptyMoveSetTest() {
    class EmptyMoveSetPiece extends Piece {

      public EmptyMoveSetPiece(int id, ChessColour colour, String position) {
        super(id, colour, 0, true, position, new ArrayList<MoveVector>(), null);
      }
    }

    EmptyMoveSetPiece testEmpty = new EmptyMoveSetPiece(999, null, "A2");

    Map<String, String> correctMap = new TreeMap<>();

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("A2", testEmpty);

    try {
      testEmpty.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
    }

    assertEquals(correctMap, testEmpty.getLegalMoveMap());
  }

  /**
   * Builds a {@code Pawn} object with test values, a few other opposing
   * {@code Pieces} and creates a {@code legalMoveMap} as reference. Expects
   * {@code Pawn.getLegalMoveMap} equal to {@code legalMoveMap}.
   * 
   * @tests {@code createLegalMoveMap} of {@code Pawn}
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
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
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
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
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
   * @tests {@code checkForPromotion}
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
   * @tests {@code checkForPromotion}
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
   * @tests {@code setPosition} of {@code Pawn}
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
   * @tests {@code setPosition} of {@code Pawn}
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
   * @tests {@code checkForEnPassant}
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
   * @tests {@code checkForEnPassant}
   */
  @Test
  @DisplayName("checkForEnPassantFailure")
  void checkForEnPassantFailureTest() {
    Pawn testPawn = ObjectFactoryForTest.getPawn();
    testPawn.checkForEnPassant("A3");
    assertEquals(false, testPawn.isEnPassentable());
  }

  /**
   * Builds two {@code Pawn} objects. One with colour 'w'. The other with colour
   * 'b'. Sets for both {@code isEnPassantable} = {@code true}. Expects
   * {@code isEnPassantable} = {@code false} of {@code Pawn} with colour 'w'.
   * 
   * @tests {@code resetEnPassant}
   */
  @Test
  @DisplayName("resetEnPassantSuccess")
  void resetEnPassantSuccessTest() {
    Pawn testPawn1 = ObjectFactoryForTest.getPawn();
    Pawn testPawn2 = ObjectFactoryForTest.getPawn();

    testPawn2.setPosition("C7");
    testPawn2.setColour(ChessColour.BLACK);
    testPawn1.setEnPassentable(true);
    testPawn2.setEnPassentable(true);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("A2", testPawn1);
    testCurrentGameState.put("C7", testPawn2);

    testPawn2.resetEnPassant(testCurrentGameState, ChessColour.BLACK);

    assertEquals(false, testPawn1.isEnPassentable());
    assertEquals(true, testPawn2.isEnPassentable());
  }

  /**
   * Builds to {@code Pawn} objects. One with {@code ChessColour.BLACK} and the
   * other one with {@code ChessColour.WHITE}. Sets one to
   * {@code isEnPassantable} = {@code true} and lets the other one move one tile
   * behind the other one.
   * 
   * @tests {@code movePiece} of {@code Pawn}
   */
  @Test
  @DisplayName("movePiecePawnSuccess")
  void movePiecePawnSuccessTest() {
    Pawn white = ObjectFactoryForTest.getPawn();
    Pawn black = ObjectFactoryForTest.getPawn();
    black.setColour(ChessColour.BLACK);
    black.setPosition("B2");
    black.setEnPassentable(true);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("A2", white);
    testCurrentGameState.put("B2", black);

    try {
      white.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    white.movePiece(testCurrentGameState, "B3", white.getColour());

    assertEquals(Piece.NOT_ON_FIELD, black.getPosition());
    assertEquals(null, testCurrentGameState.get("A2"));
    assertEquals(null, testCurrentGameState.get("B2"));
    assertEquals(white, testCurrentGameState.get("B3"));
  }

  /**
   * Builds to {@code Pawn} objects. Both with the same colour. Sets one to
   * {@code isEnPassantable} = {@code true} and lets the other one try to move
   * one tile behind the other one.
   * 
   * @tests {@code movePiece} of {@code Pawn}
   */
  @Test
  @DisplayName("movePiecePawnSameColourEnPassantable")
  void movePiecePawnSameColourEnPassantableTest() {
    Pawn white = ObjectFactoryForTest.getPawn();
    Pawn white2 = ObjectFactoryForTest.getPawn();
    white2.setPosition("B2");
    white2.setEnPassentable(true);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("A2", white);
    testCurrentGameState.put("B2", white2);

    try {
      white.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    boolean moved = white.movePiece(testCurrentGameState, "B3", white.getColour());

    assertEquals(false, moved);
    assertEquals(white, testCurrentGameState.get("A2"));
    assertEquals(white2, testCurrentGameState.get("B2"));
    assertEquals(null, testCurrentGameState.get("B3"));
  }

  /**
   * Builds to {@code Pawn} objects. One with {@code ChessColour.BLACK} and the
   * other one with {@code ChessColour.WHITE}. Lets the other one try to move
   * one tile behind the other one.
   * 
   * @tests {@code movePiece} of {@code Pawn}
   */
  @Test
  @DisplayName("movePiecePawnDifferentColourNotEnPassantableTest")
  void movePiecePawnDifferentColourNotEnPassantableTest() {
    Pawn white = ObjectFactoryForTest.getPawn();
    Pawn black = ObjectFactoryForTest.getPawn();
    black.setColour(ChessColour.BLACK);
    black.setPosition("B2");
    black.setEnPassentable(false);

    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("A2", white);
    testCurrentGameState.put("B2", black);

    try {
      white.createLegalMoveMap(testCurrentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    boolean moved = white.movePiece(testCurrentGameState, "B3", white.getColour());

    assertEquals(false, moved);
    assertEquals(white, testCurrentGameState.get("A2"));
    assertEquals(black, testCurrentGameState.get("B2"));
    assertEquals(null, testCurrentGameState.get("B3"));
  }
}
