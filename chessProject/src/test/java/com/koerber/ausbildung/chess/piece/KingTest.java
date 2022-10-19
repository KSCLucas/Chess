package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code King}-Klasse
 * 
 * @author Lucas Noack
 */
class KingTest {

  /**
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Dann setzt man
   * {@code King.position} auf einen andere legalen Wert und erwartet, dass
   * {@code King.hasMoved = true}
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Man ändert
   * {@code King.position} nicht und erwartet, dass{@code King.hasMoved = false}
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Setzt Mockturmwert auf
   * {@code true} für {@code Rook.canCastleSide = 's' / 'l'}. Man erwartet:
   * {@code King.canCastleShort = true & King.canCastleLong = true}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Setzt Mockturmwert auf
   * {@code true} für {@code Rook.canCastleSide = 'l'}. Man erwartet:
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Setzt Mockturmwert auf
   * {@code true} für {@code Rook.canCastleSide = 's'}. Man erwartet:
   * {@code King.canCastleShort = true & King.canCastleLong = false}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten. Setzt Mockturmwert auf
   * {@code false} für {@code Rook.canCastleSide = 's' / 'l'}. Man erwartet:
   * {@code King.canCastleShort = false & King.canCastleLong = false}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten und setzt diesen ins
   * Schach. Man erwartet {@code King.isInCheck = true}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten und setzt diesen nicht
   * ins Schach. Man erwartet {@code King.isInCheck = false}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten und setzt diesen ins
   * Schachmatt. Man erwartet {@code King.isCheckmate = true}.
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
   * Baut ein {@code King}-Objekt mit Ausgangstestwerten und setzt diesen nicht
   * ins Schachmatt. Man erwartet {@code King.isCheckmate = false}.
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
