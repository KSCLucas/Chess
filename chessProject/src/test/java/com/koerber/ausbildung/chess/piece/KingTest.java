package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.ObjectFactoryForTest;

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
    fail("Not yet implemented");
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
    fail("Not yet implemented");
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
    fail("Not yet implemented");
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
    fail("Not yet implemented");
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
