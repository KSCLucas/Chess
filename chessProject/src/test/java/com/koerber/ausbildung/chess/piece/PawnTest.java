package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code Pawn}-Klasse
 * 
 * @author Lucas Noack
 */
class PawnTest {
  /**
   * Baut ein {@code Pawn}-Objekt mit Testwerten (z.B.
   * {@code Pawn.colour = 'w' & Pawn.position = "A8"} und erwartet, dass
   * {@code Piece.isPromotable = true}
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
   * Baut ein {@code Pawn}-Objekt mit Testwerten (z.B.
   * {@code Pawn.colour = 'w' & Pawn.position = "A3"} und erwartet, dass
   * {@code Piece.isPromotable = false}. Das gleiche gilt für
   * {@code Pawn.postion = null}
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
   * Baut ein {@code Pawn}-Objekt mit Ausgangstestwerten. Dann setzt man
   * {@code Pawn.position} auf einen andere legalen Wert und erwartet, dass
   * {@code Pawn.hasMoved = true}
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
   * Baut ein {@code Pawn}-Objekt mit Ausgangstestwerten. Man ändert
   * {@code Pawn.position} nicht und erwartet, dass{@code Pawn.hasMoved = false}
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
   * Baut ein {@code Pawn}-Objekt mit Ausgangstestwerten. Man ändert
   * {@code Pawn.position} um 2 Felder und erwartet,
   * dass{@code Pawn.isEnPassantable = true}
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
   * Baut ein {@code Pawn}-Objekt mit Ausgangstestwerten. Man ändert
   * {@code Pawn.position} um nicht 2 Felder und erwartet,
   * dass{@code Pawn.isEnPassantable = false}
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
