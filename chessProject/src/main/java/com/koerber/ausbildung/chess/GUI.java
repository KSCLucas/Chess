package com.koerber.ausbildung.chess;

/**
 * GUI-Klasse stellt das gesamte GUI. Sie kommuniziert mit der Field- &
 * Player-Klasse.
 * 
 * @comment Ersterstellung
 * @author Lucas Noack
 */
public class GUI {

  private String posClickedPiece;

  /**
   * Konstruktor für GUI-Klasse
   * 
   * @param String
   * @return void
   * @comment
   * @author Lucas Noack
   */
  public GUI(String posClickedPiece) {
    this.posClickedPiece = posClickedPiece;
  }

  public String getPosClickedPiece() {

    return posClickedPiece;

  }

  public void setPosClickedPiece(String posClickedPiece) {
    this.posClickedPiece = posClickedPiece;
  }

  /**
   * Setzt alle Spielfiguren auf Anfangspos, loescht Historie, setzt
   * Spielerdaten zurück (Punkte, geschlagene Figuren, NICHT Farbe und Name)
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void startNewGame() {

  }

  /**
   * Stellt den Spielstand vor dem letzten Spielzug zurück
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void undoLastTurn() {

  }

  /**
   * Zeigt den in der Historie angewaehlten Spielstand an
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void jumpToSelctedFEN() {

  }

  /**
   * Geht aus der Historieanzeige zurück in das aktive Spiel
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void jumptToLiveGame() {

  }

  /**
   * Geht einen (1) Schritt/Zug in der Historie nach vorn
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void forwardInHistory() {

  }

  /**
   * Geht einen (1) Schritt/Zug in der Historie zurück
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void backwardInHistory() {

  }

  /**
   * Auf der Basis der {@code Field.currentGameState}-Hashmap werden Position
   * und Sprite der Spielfiguren ausgelesen und auf dem jeweiligen Feld
   * angezeigt
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void hashmapToGameState() {

  }

  /**
   * Gibt Koordinaten des angeklickten (Drag) Felds als String zurück
   * 
   * @param void
   * @return String
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public String getClickedFieldString() {
    return null;
  }

  /**
   * Gibt Koordinaten des losgelassenen Klick (Drop) Felds als String zurück
   * 
   * @param void
   * @return String
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public String getReleasedFieldString() {
    return null;
  }

  /**
   * Nimmt die Positionsdaten der gezogenen Figur und erstellt Historieneintrag
   * (Startposition -> Zielposition | Sprite geschlagener Figur)
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void createNewHistroyEntry() {

  }

  /**
   * Faerbt die Felder entsprechend der {@code Piece.legalMoveMap} gruen (darf
   * ziehen), rot (schlagen) oder garnicht (darf nicht ziehen)
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void highlightLegalMove() {

  }

  /**
   * Zeigt den Gewinner als Popup an und lockt das Game, nur noch Historie
   * anschaubar
   * 
   * @param void
   * @return void
   * @comment game lock = jeder Move illegal
   * @author Lucas Noack
   */
  public void showWinnerPopup() {

  }

  /**
   * Zeigt den Sprite der geschlagenen Spielfiguren im Spielerbereich an
   * 
   * @param void
   * @return void
   * @comment Ersterstellung
   * @author Lucas Noack
   */
  public void displayTakenPieces() {

  }

  /**
   * Fragt für Spielernamen der jeweiligen Seiten und belegt Player.name
   * 
   * @param void
   * @return void
   * @comment Default Namen: WHITE & BLACK
   * @author Lucas Noack
   */
  public void askForPlayerName() {

  }
}
