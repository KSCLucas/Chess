package com.koerber.ausbildung.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Player} class.
 * 
 * @author Lucas Noack
 */
class PlayerTest {

  /**
   * Give positive test value ({@code Piece.value}) of correct data type and
   * expect {@code Player.score} + test value.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreCorrectInput")
  void increaseScoreCorrectInputTest() {
    fail("Not yet implemented");
  }

  /**
   * Give wrong data type and expect error message
   * ({@code IllegalArgumentException}).
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreWrongDataType")
  void increaseScoreWrongDataTypeTest() {
    fail("Not yet implemented");
  }

  /**
   * Enter {@code null} and expect error message ({@code NullPointerException}).
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreNull")
  void increaseScoreNullTest() {
    fail("Not yet implemented");
  }

  /**
   * Enter negative test value ( {@code Piece.value}) and expect
   * {@code Player.score} + test value.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @comment solve with {@code math.abs()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreNegative")
  void increaseScoreNegativeTest() {
    fail("Not yet implemented");
  }

  /**
   * Give test object of {@code Piece} class and expect it to be successfully
   * added to {@code Player.takenPieces}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.addTakenPiece()}
   * @comment Testable by expected properties (e.g. {@code Piece.name}).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("addTakenPieceCorrectInput")
  void addTakenPieceCorrectInputTest() {
    fail("Not yet implemented");
  }

  /**
   * Enter {@code null} and expect error message ({@code NullPointerException}).
   * 
   * @param void
   * @return void
   * @tested {@code Player.addTakenPiece()}
   * @comment Testable by expected properties (e.g. {@code Piece.name})
   * @author Lucas Noack
   */
  @Test
  @DisplayName("addTakenPieceNull")
  void addTakenPieceNullTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds {@code Player} object with test values and expect that after
   * performing {@code setToInitPlayer.score} to 0 and
   * {@code Player.takenPieces} is empty.
   * 
   * @param void
   * @return void
   * @tested {@code Player.setToInit()}
   * @comment {@code Player.name} and {@code Player.color} remain the same and
   *          are not reset / possibly watch out for
   *          {@code NullPointerException} on {@code Player.takenPiece}.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("setToInitSuccess")
  void setToInitSuccessTest() {
    fail("Not yet implemented");

  }

}
