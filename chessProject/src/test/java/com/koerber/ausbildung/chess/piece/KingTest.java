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
   * @param void
   * @return void
   * @tests {@code setPosition}
   * @author Lucas Noack, PKamps
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
   * @param void
   * @return void
   * @tests {@code setPosition}
   * @author Lucas Noack, PKamps
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    King testKing = ObjectFactoryForTest.getKing();
    assertEquals(false, testKing.isHasMoved());

  }

  /**
   * Builds a {@code King} object with initial test values. Sets mock tower
   * value to @code true} for {@code Rook.canCastleSide = 's' / 'l'}. One
   * expects: {@code King.canCastleShort = true & King.canCastleLong = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCastle()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCheck()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCheck()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCheckmate()}
   * @author Lucas Noack
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
   * @param void
   * @return void
   * @tested {@code checkForCheckmate()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForCheckmateFailure")
  void checkForCheckmateFailureTest() {
    fail("Not yet implemented");
  }

}
