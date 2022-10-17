package testPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code Player}-Klasse
 * 
 * @comment import fehlt noch
 * @author Lucas Noack
 */
public class PlayerTest {

  /**
   * Geben positiven Testwert ({@code Piece.value}) richtigen Datentyps und
   * erwarten {@code Player.score} + Testwert
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreCorrectInput")
  public void increaseScoreCorrectInputTest() {

  }

  /**
   * Geben falschen Datentyp und erwarten Fehlermeldung ({@code
   * IllegalArgumentException})
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreWrongDataType")
  public void increaseScoreWrongDataTypeTest() {

  }

  /**
   * Geben {@code null} und erwarten Fehlermeldung
   * ({@code NullPointerException})
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreNull")
  public void increaseScoreNullTest() {

  }

  /**
   * Geben negativen Testwert ( {@code Piece.value}) und erwarten {@code
   * Player.score} + Testwert
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseScore()}
   * @comment geloest durch {@code math.abs()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseScoreNegative")
  public void increaseScoreNegativeTest() {

  }

  /**
   * Geben Testobjekt der Klasse {@code Piece} und erwarten, dass es erfolgreich
   * zu {@code Player.takenPieces} hinzugefügt wird
   * 
   * @param void
   * @return void
   * @tested {@code Player.addTakenPiece()}
   * @comment Testbar durch erwartete Eigenschaften (z.B. {@code Piece.name})
   * @author Lucas Noack
   */
  @Test
  @DisplayName("addTakenPieceCorrectInput")
  public void addTakenPieceCorrectInputTest() {

  }

  /**
   * geben {@code null} und erwarten Fehlermeldung (
   * {@code NullPointerException})
   * 
   * @param void
   * @return void
   * @tested {@code Player.addTakenPiece()}
   * @comment Testbar durch erwartete Eigenschaften (z.B. {@code Piece.name})
   * @author Lucas Noack
   */
  @Test
  @DisplayName("addTakenPieceNull")
  public void addTakenPieceNullTest() {

  }

  /**
   * Baut {@code Player}-Objekt mit Testwerten und erwarten, dass nach
   * durchfuehren von {@code setToInitPlayer.score} auf 0 und
   * {@code Player.takenPieces} leer ist
   * 
   * @param void
   * @return void
   * @tested {@code Player.setToInit()}
   * @comment {@code Player.name} und {@code Player.colour} bleiben gleich und
   *          werden nicht zurück gesetzt / moeglicherweise auf
   *          {@code NullPointerException} achten bei {@code Player.takenPiece}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("setToInitSuccess")
  public void setToInitSuccessTest() {

  }

}
