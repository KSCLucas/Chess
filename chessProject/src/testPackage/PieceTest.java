package testPackage;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code Piece}-Klasse
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
   * Baut {@code Piece}-Objekt mit Testwerten und erwartet auf Basis der
   * Testwerte eine korrekte {@code Piece.legalMoveMap}
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
   * Baut ein {@code Piece}-Objekt mit Testwerten und erwarte auf Basis der
   * Testwerte eine {@code PieceOutOfBoundsException}
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
   * Baut ein {@code Piece}-Objekt mit Testwerten und erwarte auf Basis der
   * Testwerte eine {@code Piece.legalMoveMap} mit nur illegalen
   * Zugmöglichkeiten
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
   * Baut ein {@code Piece}-Objekt mit Testwerten und erwarte auf Basis der
   * Testwerte eine {@code Piece.legalMoveMap}. Es werden zufaellig Nullpointer
   * in der {@code legalMoveMap} gestreut. Erwartet wird
   * {@code NullPointerException}
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
   * Erwartet, dass {@code Piece.position =} target Position.
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
   * Erwartet, dass sich {@code Piece.position} nicht aendert
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position kann frei gewaehlt werden (Move muss durchfuehrbar
   *          sein).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceIllegalMove")
  void movePieceIllegalMoveTest() {
    fail("Not yet implemented");
  }

  /**
   * Erwartet, dass sich {@code Piece.position} nicht aendert
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position kann frei gewaehlt werden (Move darf nicht
   *          durchfuehrbar sein).
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceUnknownKey")
  void movePieceUnknownKeyTest() {
    fail("Not yet implemented");
  }

  /**
   * Erwartet, dass sich {@code Piece.position} nicht aendert
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment target Position liegt ausßerhalb des Spielfelds (z.B. H12),
   *          schließt auch {@code null} als Key ein.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToPiecePosition")
  void movePieceToPiecePositionTest() {
    fail("Not yet implemented");
  }

  /**
   * Erwartet, dass sich {@code Piece.position =} target position &
   * {@code enemyPiece.position = "xy"}
   * 
   * @param void
   * @return void
   * @tested {@code movePiece()}
   * @comment "xy" zaehlt als nicht auf dem Spielfeld befindlich
   * @author Lucas Noack
   */
  @Test
  @DisplayName("movePieceToEnemyPiece")
  void movePieceToEnemyPieceTest() {
    fail("Not yet implemented");
  }
}
