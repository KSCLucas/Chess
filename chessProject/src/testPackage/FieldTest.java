package testPackage;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Testet die {@code Field}-Klasse
 * 
 * @comment Ersterstellung
 * @author Lucas Noack
 */
public class FieldTest {
  /**
   * Baut ein {@code Field}-Objekt mit einer {@code currentGameState}-Hashmap,
   * welche nicht der Startmap entspricht. Es wird erwartet, dass die
   * resultierende Hashmap gleich der Startpositionshashmap ist.
   * 
   * @param void
   * @return void
   * @tested {@code Field.initializeHashmap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("initializeHashmapSuccess")
  public void initializeHashmapSuccessTest() {

  }

  /**
   * Baut ein {@code Piece}-Objekt nit Testparametern und legt dieses in eine
   * Beispielhashmap. Mit Hilfe zweier Positionsparametern (Ursprungspos des
   * {@code Piece} und Zielpos des {@code Piece}. Vergleichen der beiden
   * Positionen nach Ausführung von {@code updateHashmap} und erwarten, dass in
   * Zielpos das {@code Piece} steht und in der Ursprungspos {@code "###"}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.updateHashmap()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("updateHashmapSuccess")
  public void updateHashmapSuccessTest() {

  }

  /**
   * Baut {@code Field}-Objekt mit {@code this.currentTurn != 1}. Erwartet, dass
   * {@code this.currentTurn = 1}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.resetCurrentTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("resetCurrentTurnSuccess")
  public void resetCurrentTurnSuccessTest() {

  }

  /**
   * Baut {@code Piece} mit {@code this.currentTurn = 5}. Erwartet, dass
   * {@code this.currentTurn = 6}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnSuccess")
  public void increaseCurrentTurnSuccessTest() {

  }

  /**
   * Baut {@code Piece}-Objekt mit {@code this.currentTurn = 0} und erwartet
   * {@code IllegalArgumentException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnBlockZeroTest")
  public void increaseCurrentTurnBlockZeroTest() {

  }

  /**
   * Baut {@code Field}-Objekt mit {@code this.currentTurn = -1} und erwartet
   * {@code IllegalArgumentException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnBlockNegative")
  public void increaseCurrentTurnBlockNegativeTest() {

  }

  /**
   * Baut {@code Field}-Objekt mit {@code this.currentTurn = null} und erwartet
   * eine {@code NullPointerException}
   * 
   * @param void
   * @return void
   * @tested {@code Player.increaseCurrenTurn()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("increaseCurrentTurnNull")
  public void increaseCurrentTurnNullTest() {

  }

  /**
   * Baut ein {@code King}-Objekt, das {@code this.isInCheckmate = true} und
   * {@code this.colour = 'w'} setzt. Erwartet, dass
   * {@code Field.whoWinner = 'b'}
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerSuccess")
  public void checkForWinnerSuccessTest() {

  }

  /**
   * Baut 2 {@code King}-Objekte, bei denen {@code this.isInCheckmate = false}
   * und {@code this.colour = 'w' & 'b'}. Erwartet,
   * dass{@code Field.whoWinner = 'n'}
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerNoCheckmate")
  public void checkForWinnerNoCheckmateTest() {

  }

  /**
   * Baut 2 {@code King}-Objekte, bei denen {@code this.isInCheckmate = true}
   * und {@code this.colour = 'w' & 'b'}. Erwartet eine
   * {@code OnlyOneWinnerException}.
   * 
   * @param void
   * @return void
   * @tested {@code Player.checkForWinner()}
   * @comment {@code OnlyOneWinnerException} muss noch erstellt werden.
   * @author Lucas Noack
   */
  @Test
  @DisplayName("checkForWinnerBothKingsCheckmate")
  public void checkForWinnerBothKingsCheckmateTest() {

  }
}
