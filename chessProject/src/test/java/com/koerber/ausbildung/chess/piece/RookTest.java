package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Rook} class.
 * 
 * @author Lucas Noack
 */
class RookTest {

  /**
   * Builds a {@code Rook} object with initial test values. Then set
   * {@code Rook.position} to another legal value and expect
   * {@code Rook.hasMoved = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForFirstMove()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForFirstMoveSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Rook} object with initial test values. Don't
   * change{@code Rook.position} and expect{@code Rook.hasMoved = false}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForFirstMove()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    fail("Not yet implemented");

  }

  /**
   * Builds {@code Rook} object with initial test values. All fields between Rook
   * and King are empty ("###"). It is expected that
   * {@code Rook.canCastle = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForCastleSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Rook} object with initial test values. Not all fields are
   * empty or Rook has already moved. It is expected that
   * {@code Rook.canCastel = false}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForCastleFailureTest() {
    fail("Not yet implemented");
  }
}
