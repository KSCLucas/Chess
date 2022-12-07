package com.koerber.ausbildung.chess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.OnlyOneWinnerException;

/**
 * Tests the {@code Field} class.
 * 
 * @author Lucas Noack, PKamps
 */
class FieldTest {

  /**
   * Builds a {@code Field} object with a {@code currentGameState} hashmap that
   * is not the same as the startmap. The resulting hashmap is expected to be
   * equal to the start position hashmap.
   * 
   * @param void
   * @return void
   * @tested {@code Field.initializeHashmap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("initializeHashmapSuccess")
  void initializeHashmapSuccessTest() {
    Field testField = ObjectFactoryForTest.getField();
    testField.initializeMap();
    assertEquals(true, testField.getCurrentGameState().containsKey("A1"));
    assertEquals(true, testField.getCurrentGameState().containsKey("A2"));
    assertEquals(true, testField.getCurrentGameState().containsKey("H8"));
    assertEquals(true, testField.getCurrentGameState().containsKey("H7"));
  }

  /**
   * Builds {@code Field} object with {@code this.currentTurn != 1}. Expects
   * {@code this.currentTurn = 1}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.resetCurrentTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("resetCurrentTurnSuccess")
  void resetCurrentTurnSuccessTest() {
    Field testField = ObjectFactoryForTest.getField();
    testField.increaseCurrentTurn();
    testField.increaseCurrentTurn();
    testField.increaseCurrentTurn();
    testField.resetCurrentTurn();
    assertEquals(1, testField.getCurrentTurn());
  }

  /**
   * Builds {@code Piece} with {@code this.currentTurn = 1}. Expects
   * {@code this.currentTurn = 2}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnSuccess")
  void increaseCurrentTurnSuccessTest() {
    Field testField = ObjectFactoryForTest.getField();
    testField.increaseCurrentTurn();
    assertEquals(2, testField.getCurrentTurn());
  }

  /**
   * Builds {@code Field} object with {@code this.currentTurn = -1} and
   * expects{@code IllegalArgumentException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.decreaseCurrentTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("decreaseCurrentTurnBlockZero")
  void decreaseCurrentTurnBlockZeroTest() {
    Field testField = ObjectFactoryForTest.getField();
    testField.decreaseCurrentTurn();
    testField.decreaseCurrentTurn();
    testField.decreaseCurrentTurn();
    assertEquals(1, testField.getCurrentTurn());
    ;
  }

  /**
   * Builds a {@code King} object that sets {@code this.isInCheckmate = true}
   * and {@code this.color = 'w'}. Expects {@code Field.whoWinner = 'b'}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerSuccess")
  void checkForWinnerSuccessTest() {
    Field testField = ObjectFactoryForTest.getField();
    King testKingW = ObjectFactoryForTest.getKing();
    testKingW.setCheckmate(true);
    King testKingB = ObjectFactoryForTest.getKing();
    testKingB.setId("k1b");
    testKingB.setPosition("B6");
    testKingB.setColour(ChessColour.BLACK);

    Map<String, Piece> testCurrentGameState = new TreeMap<>();
    testCurrentGameState.put(testKingW.getPosition(), testKingW);
    testCurrentGameState.put(testKingB.getPosition(), testKingB);
    // testField.setCurrentGameState(testCurrentGameState);
    // TODO: Fix static

    try {
      testField.checkForWinner();
    }
    catch(OnlyOneWinnerException e) {
      e.printStackTrace();
    }
    assertEquals(ChessColour.BLACK, testField.getWinner());
  }

  /**
   * Builds 2 {@code King} objects where {@code this.isInCheckmate = false} and
   * {@code this.color = 'w' & 'b'}. Expects that{@code Field.whoWinner = 'n'}
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerNoCheckmate")
  void checkForWinnerNoCheckmateTest() {
    Field testField = ObjectFactoryForTest.getField();
    King testKingW = ObjectFactoryForTest.getKing();
    King testKingB = ObjectFactoryForTest.getKing();
    testKingB.setId("k1b");
    testKingB.setColour(ChessColour.BLACK);
    testKingB.setPosition("B6");

    Map<String, Piece> testCurrentGameState = new TreeMap<>();
    testCurrentGameState.put(testKingW.getPosition(), testKingW);
    testCurrentGameState.put(testKingB.getPosition(), testKingB);
    // testField.setCurrentGameState(testCurrentGameState);
    // TODO: Fix static

    try {
      testField.checkForWinner();
    }
    catch(OnlyOneWinnerException e) {
      e.printStackTrace();
    }
    assertEquals(null, testField.getWinner());
  }

  /**
   * Builds 2 {@code King} objects where {@code this.isInCheckmate = true} and
   * {@code this.color = 'w' & 'b'}. Expects a {@code OnlyOneWinnerException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerBothKingsCheckmate")
  void checkForWinnerBothKingsCheckmateTest() {
    Field testField = ObjectFactoryForTest.getField();
    King testKingW = ObjectFactoryForTest.getKing();
    testKingW.setCheckmate(true);
    King testKingB = ObjectFactoryForTest.getKing();
    testKingB.setId("k1b");
    testKingB.setColour(ChessColour.BLACK);
    testKingB.setCheckmate(true);
    testKingB.setPosition("B6");

    Map<String, Piece> testCurrentGameState = new TreeMap<>();
    testCurrentGameState.put(testKingW.getPosition(), testKingW);
    testCurrentGameState.put(testKingB.getPosition(), testKingB);
    // testField.setCurrentGameState(testCurrentGameState);
    // TODO: Fix static

    assertThrows(OnlyOneWinnerException.class, () -> testField.checkForWinner());
  }
}
