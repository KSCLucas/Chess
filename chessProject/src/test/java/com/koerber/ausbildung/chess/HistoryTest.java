package com.koerber.ausbildung.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HistoryTest {

  private static final String FIRST_TURN = "StringBsp0";
  private static final String LAST_TURN  = "StringBsp7";

  private History setUpHistory() {
    History history = new History();

    history.addEntry(FIRST_TURN);
    history.addEntry("StringBsp1");
    history.addEntry("StringBsp2");
    history.addEntry("StringBsp3");
    history.addEntry("StringBsp4");
    history.addEntry("StringBsp5");
    history.addEntry("StringBsp6");
    history.addEntry(LAST_TURN);
    return history;
  }

  @Test
  @DisplayName("addFenEntry")
  public void addFenEntryTest() {
    History history = new History();
    List<String> fens = history.getFens();
    assertTrue(fens.isEmpty());
    history.addEntry(FIRST_TURN);
    assertEquals(1, fens.size());
    assertTrue(fens.contains(FIRST_TURN));
  }

  @Test
  @DisplayName("removeLastTurn")
  public void removeLastTurnTest() {
    History history = setUpHistory();
    List<String> fens = history.getFens();
    assertEquals(8, fens.size());
    history.removeLastTurn();
    assertEquals(7, fens.size());
    assertFalse(fens.contains(LAST_TURN));
  }

  @Test
  @DisplayName("STRING SAMPLES")
  public void shouldDisplayAStringBsp() {
    History history = setUpHistory();

    assertEquals(FIRST_TURN, history.getFenOfTurn(0));
  }

  @Test
  @DisplayName("MoreStringBsp")
  public void shouldAlsoDisplayAStringBsp() {
    History history = setUpHistory();

    assertEquals(LAST_TURN, history.getFenOfTurn(7));
  }
}
