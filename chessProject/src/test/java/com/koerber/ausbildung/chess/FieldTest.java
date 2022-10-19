package com.koerber.ausbildung.chess;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Field} class.
 * 
 * @author Lucas Noack
 */
class FieldTest {

  @Test
  void test() {
    fail("Not yet implemented");
  }

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
    fail("Not yet implemented");

  }

  /**
   * Builds a {@code Piece} object with test parameters and places it in a
   * sample hashmap. Using two position parameters (origin pos of the
   * {@code Piece} and destination pos of the {@code Piece}. Compare the two
   * positions after executing {@code updateHashmap} and expect the
   * {@code Piece} to be in the target pos and {@code "###"} in the source pos.
   * 
   * @param void
   * @return void
   * @tested {@code Player.updateHashmap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("updateHashmapSuccess")
  void updateHashmapSuccessTest() {
    fail("Not yet implemented");

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
    fail("Not yet implemented");

  }

  /**
   * Builds {@code Piece} with {@code this.currentTurn = 5}. Expects
   * {@code this.currentTurn = 6}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnSuccess")
  void increaseCurrentTurnSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds {@code Piece} object with {@code this.currentTurn = 0} and expects
   * {@code IllegalArgumentException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnBlockZeroTest")
  void increaseCurrentTurnBlockZeroTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds {@code Field} object with {@code this.currentTurn = -1} and
   * expects{@code IllegalArgumentException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnBlockNegative")
  void increaseCurrentTurnBlockNegativeTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds {@code Field} object with {@code this.currentTurn = null} and
   * expects a {@code NullPointerException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnNull")
  void increaseCurrentTurnNullTest() {
    fail("Not yet implemented");
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
    fail("Not yet implemented");
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
    fail("Not yet implemented");
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
    fail("Not yet implemented");
  }
}
