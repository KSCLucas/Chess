package gamePackage;

import java.util.HashMap;

/**
 * dient als Game-Klasse, die alles was mit Schach zu tun hat managed
 * 
 * @comment Grundgerüst, final muss erst noch initialisiert werden
 * @author Toni Gropper
 * @since 17.10.2022
 */
public class Field {
  private HashMap             currentGameState;
  private /* final */ HashMap initialGameState;// final HashMap muss //
                                               // initialisiert werden
  private int                 currentTurn;
  private char                whoWinner;

  /**
   * setzt Spielfiguren in this.currentGameState auf die Startpositionen von
   * this.initialGameState
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void initializeHashmap() {

  }

  /**
   * aktualisiert this.currentGameState um den vollzogenen Zug
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void updateHashMap() {

  }

  /**
   * setzt this.currentTurn auf Anfangswert (=1)
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void resetCurrentTurn() {

  }

  /**
   * setzt this.currentTurn um 1 nach oben
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void increaseCurrentTurn() {

  }

  /**
   * regelt den Ablauf des Schachspiels und ruft sämtliche Methoden auf, die für
   * das Spiel benötigt werden
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void gameLoop() {

  }

  /**
   * prüft, ob einer der beiden Spieler gewonnen hat und setzt this.whoWinner
   * auf 'w' oder 'b'; ruft die GUI-Methode showWinnerPopup auf. Wenn keiner der
   * Könige im Schachmatt steht wird this.whoWinner = 'n' (n=none) gesetzt.'
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void checkForWinner() {

  }
}
