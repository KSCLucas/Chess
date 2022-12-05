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

  private List<String> fens             = new ArrayList<>();
  private List<String> historyEntryList = new ArrayList<>();
  public List<String> getFens() {
    return fens;
  }

  public List<String> getHistoryEntryList() {
    return historyEntryList;
  }

  public void setHistoryEntryList(List<String> historyEntryList) {
    this.historyEntryList = historyEntryList;
  }

  public void setFens(List<String> fens) {
    this.fens = fens;
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

  public void addEntry(String fen) {
    fens.add(fen);
  }

  public void removeLastTurn() {
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
  public String getFenOfTurn(int posEntry) {
    if(fens.size() - 1 < posEntry) {
      throw new IndexOutOfBoundsException(posEntry);
    }
    return fens.get(posEntry);
  }
}
