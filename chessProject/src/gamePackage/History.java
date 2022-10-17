package gamePackage;

import java.util.ArrayList;

/**
 * speichert alle vergangenen Z�ge als eigene FEN
 * 
 * @comment Grundger�st
 * @author Toni Gropper
 * @since 17.10.2022
 */
public class History {

  private ArrayList<String> FENs;

  public ArrayList<String> getFENs() {
    return FENs;
  }

  public void setFENs(ArrayList<String> fENs) {
    FENs = fENs;
  }

}
