package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Pawn} class.
 * 
 * @author Lucas Noack
 */
class PawnTest {
  /**
   * Builds a {@code Pawn} object with test values (e.g.
   * {@code Pawn.color = 'w' & Pawn.position = "A8"} and expects
   * {@code Piece.isPromotable = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForPromotion()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForPromotionPromotable")
  void checkForPromotionPromotableTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Pawn} object with test values (e.g.
   * {@code Pawn.color = 'w' & Pawn.position = "A3"} and expects
   * {@code Piece.isPromotable = false}. The same is true for
   * {@code Pawn.postion = null}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForPromotion()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForPromotionNotPromotable")
  void checkForPromotionNotPromotableTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Pawn} object with initial test values. Then set
   * {@code Pawn.position} to another legal value and expect
   * {@code Pawn.hasMoved = true}.
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
   * Builds a {@code Pawn} object with initial test values. One does not change
   * {@code Pawn.position} and expects{@code Pawn.hasMoved = false}.
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
   * Builds a {@code Pawn} object with initial test values. You change
   * {@code Pawn.position} by 2 fields and
   * expect{@code Pawn.isEnPassantable = true}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForEnPassantSuccessTest()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForEnPassantSuccess")
  void checkForEnPassantSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Pawn} object with initial test values. You change
   * {@code Pawn.position} by not 2 fields and
   * expect{@code Pawn.isEnPassantable = false}.
   * 
   * @param void
   * @return void
   * @tested {@code checkForEnPassantSuccessTest()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForEnPassantFailure")
  void checkForEnPassantFailureTest() {
    fail("Not yet implemented");
  }
}
