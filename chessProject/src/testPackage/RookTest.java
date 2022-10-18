package testPackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code Rook}-Klasse
 * 
 * @author Lucas Noack
 */
class RookTest {

  /**
   * Baut ein {@code Rook}-Objekt mit Ausgangstestwerten. Dann setzt man
   * {@code Rook.position} auf einen andere legalen Wert und erwartet, dass
   * {@code Rook.hasMoved = true}
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
   * Baut ein {@code Rook}-Objekt mit Ausgangstestwerten. Man ändert
   * {@code Rook.position} nicht und erwartet, dass{@code Rook.hasMoved = false}
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
   * Baut {@code Rook}-Objekt mit Ausgangstestwerten. Alle Felder zwischen Rook
   * und King sind leer ("###"). Man erwartet, dass
   * {@code Rook.canCastle = true}
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
   * Baut ein {@code Rook}-Objekt mit Ausgangstestwerten. Nicht alle Felder sind
   * leer oder Rook hat sich schon bewegt. Man erwartet, dass
   * {@code Rook.canCastel = false}
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
