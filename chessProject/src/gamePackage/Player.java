package gamePackage;

import java.util.ArrayList;

/**
 * Representiert den Spieler mit seinem Namen, Farbe, Punktestand und den
 * geschlagenen gegnerischen Figuren
 * 
 * @comment Grundgerüst, statt public Object sollte public Piece stehen
 * @author Toni Gropper
 * @since 17.10.2022
 */
public class Player {
  private String               name;
  private char                 colour;
  private int                  score;
  private ArrayList<Character> takenPiece;
  /**
   * fügt this.takenPiece das geschlagene Piece hinzu und ruft increaseScore auf
   * 
   * @param Object Piece
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void addTakenPiece(Object Piece) {
  }

  /**
   * this.score + int; der int-Wert entspricht dem Piece-Wert
   * 
   * @param int
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void increaseScore(int score) {
  }

  /**
   * setzt alle Attribute auf Initialisierungswerte zurück
   * 
   * @param void
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void setToInit() {

  }

}
