package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.ObjectFactoryForTest;

/**
 * Tests the {@code Rook} class.
 * 
 * @author Lucas Noack, PKamps
 */
class RookTest {

  /**
   * Builds a {@code Rook} object with initial test values. Then set
   * {@code Rook.position} to another legal value and expect
   * {@code Rook.hasMoved = true}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForFirstMoveSuccessTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    testRook.setPosition("A3");
    assertEquals("A3", testRook.getPosition());
    assertEquals(true, testRook.isHasMoved());
  }

  /**
   * Builds a {@code Rook} object with initial test values. Don't
   * change{@code Rook.position} and expect{@code Rook.hasMoved = false}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    assertEquals(false, testRook.isHasMoved());

  }

  /**
   * Builds {@code Rook} object with initial test values. All fields between Rook
   * and King are empty ("###"). It is expected that
   * {@code Rook.canCastle = true}.
   * 
   * @tests {@code checkForCastle()}
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
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForCastleFailureTest() {
    fail("Not yet implemented");
  }
}
