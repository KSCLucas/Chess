package gamePackage;

import java.util.ArrayList;

/**
 * speichert alle vergangenen Züge als eigene FEN
 * 
 * @comment Grundgerüst
 * @author Toni Gropper
 * @since 18.10.2022
 */
public class History {

  private ArrayList<String> FENs;

  public ArrayList<String> getFENs() {
    return FENs;
  }

  public void setFENs(ArrayList<String> FENs) {
    this.FENs = FENs;
  }

  /**
   * fügt einen FEN-String mit FENs.add() der Liste hinzu
   * 
   * @param String
   * @return void
   * @throws
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public void addEntry(String FEN) {
  FENs.add(FEN);
  
  }
  

  /**
   * returned FEN an Index int aus FENs. Falls es den Index nicht gibt, wird
   * IndexOutOfBoundsException geworfen
   * 
   * @param int
   * @return String
   * @throws IndexOutOfBoundsException
   * @comment Grundgerüst
   * @author Toni Gropper
   */
  public String getEntryAtIndex() {
    return null;
  }
}
