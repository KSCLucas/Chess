package com.koerber.ausbildung.chess;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * saves all turns as a separate FEN.
 * 
 * @comment base-structure
 * @author Toni Gropper
 * @since 19.10.2022
 */
public class History {

  private ArrayList<String> FENs = new ArrayList<>(Arrays.asList("john", "paul", "lucas"));

  public ArrayList<String> getFENs() {
    return FENs;
  }

  public void setFENs(ArrayList<String> FENs) {
    this.FENs = FENs;
  }

  /**
   * adds new FEN-String to the ArrayList with FENs.add() .
   * 
   * @param String
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */

  public void addEntry(String FEN) {

    FENs.add(FEN);

  }

  /**
   * returns FEN to Index from FENs. If Index does not exist return an IndexOutOfBoundsException.

   * 
   * @param int
   * @return String
   * @throws IndexOutOfBoundsException
   * @comment base-structure
   * @author Toni Gropper
   */
  public String getEntryAtIndex(int posEntry) {

    return null;

  }
}
