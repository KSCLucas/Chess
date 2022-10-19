package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Piece} class.
 * 
 * @author Lucas Noack
 */
class PieceTest {

  @Test
  @DisplayName("")
  void test() {
    fail("Not yet implemented");
  }

  /**
   * Builds {@code Piece} object with test values and expects a correct
   * {@code Piece.legalMoveMap} based on the test values.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("createLegalMoveMapCorrect")
  void createLegalMoveMapCorrectTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code PieceOutOfBoundsException} based on the test values.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyPosition")
  void createLegalMoveMapEmptyPositionTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code Piece.legalMoveMap} with only illegal move options based on the test
   * values.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("createLegalMoveMapEmptyMoveset")
  void createLegalMoveMapEmptyMovesetTest() {
    fail("Not yet implemented");
  }

  /**
   * Builds a {@code Piece} object with test values and expects a
   * {@code Piece.legalMoveMap} based on the test values. Randomly scatters null
   * pointers in the {@code legalMoveMap}. Expected is
   * {@code NullPointerException}.
   * 
   * @param void
   * @return void
   * @tested {@code createLegalMoveMap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("createLegalMoveMapNullOnField")
  void createLegalMoveMapNullOnFieldTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position =} target Position.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceLegalMove")
  void movePieceLegalMoveTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position can be freely selected (move must be executable).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceIllegalMove")
  void movePieceIllegalMoveTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position can be freely selected (move must not be
   *          executable).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceUnknownKey")
  void movePieceUnknownKeyTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position} not to change.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position is outside the field (e.g. H12), also includes
   *          {@code null} as key.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToPiecePosition")
  void movePieceToPiecePositionTest() {
    fail("Not yet implemented");
  }

  /**
   * Expects {@code Piece.position =} target position &
   * {@code enemyPiece.position = "xy"}.
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment "xy" counts as not being on the pitch.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToEnemyPiece")
  void movePieceToEnemyPieceTest() {
    fail("Not yet implemented");
  }
}
