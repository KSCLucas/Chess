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
  private int          historyCounter;
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

  public int getHistoryCounter() {
    return historyCounter;
  }

  public void setHistoryCounter(int historyCounter) {
    this.historyCounter = historyCounter;
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
   */
  public String getFenOfTurn(int posEntry) {
    if(fens.size() - 1 < posEntry) {
      throw new IndexOutOfBoundsException(posEntry);
    }
    return fens.get(posEntry);
  }
}
