package com.koerber.ausbildung.chess;

import java.util.ArrayList;
import java.util.List;

/**
 * saves all turns as a separate FEN.
 * 
 * @comment functional addEntry Method, but just samples. need converter-class
 *          to get the correct input.
 * @author Toni Gropper
 * @since 2.11.2022
 */
public class History {

  private static List<String> fens             = new ArrayList<>();

  public static List<String>  historyEntryList = new ArrayList<>();

  public List<String> getFens() {
    return fens;
  }

  /**
   * adds new FEN-String to the ArrayList with fens.add() .
   * 
   * @param String
   * @return void
   * @throws
   * @comment fens.add have examples, need to be conected with converter. gets
   *          from converter fen-Strings. maybe create a converter-Object
   */

  public static void addEntry(String fen) {
    fens.add(fen);
  }

  public static void removeLastTurn() {
    fens.remove(fens.size() - 1);
  }

  /**
   * returns FEN to Index from FENs. If Index does not exist return an
   * IndexOutOfBoundsException.
   * 
   * @param int
   * @return String
   * @throws IndexOutOfBoundsException
   * @comment base-structure
   * @author Toni Gropper
   */
  public static String getFenOfTurn(int posEntry) {
    if(fens.size() - 1 < posEntry) {
      throw new IndexOutOfBoundsException(posEntry);
    }
    return fens.get(posEntry);
  }
}

// main method
// History myHistory = new History();
// myHistory.addEntry("fen");
// System.out.println(myHistory.getFens());
// System.out.println(myHistory.getEntryAtIndex(4));
