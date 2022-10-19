package com.koerber.ausbildung.chess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testet die {@code Converter}-Klasse
 * 
 * @author Lucas Noack
 */
class ConverterTest {

  /**
   * Gebe eine Hashmap vor und erwarte den richtigen FEN-String.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertHashToFEN()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertHashToFENSuccess")
  void convertHashToFENSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Geben eine Hashmap mit {@code null} vor und erwarten eine
   * {@code NullPointerException}
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertHashToFEN()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertHashToFENNull")
  void convertHashToFENNullTest() {
    fail("Not yet implemented");
  }

  /**
   * Geben eine Hashmap in der falschen Groeﬂe vor (schlieﬂt leere Hashmap ein).
   * Es wird eine {@code WrongSizeException} erwartet.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertHashToFEN()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertHashToFENMapWrongSize")
  void convertHashToFENMapWrongSizeTest() {
    fail("Not yet implemented");
    // TODO WrongSizeException implementieren
  }

  /**
   * Geben einen FEN-String vor und erwarte die richtige Hashmap.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHash()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHashSuccess")
  void convertFENToHashSuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Geben einen {@code null}-String vor und erwarten eine
   * {@code NullPointerException}.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHash()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHashNull")
  void convertFENToHashNullTest() {
    fail("Not yet implemented");
  }

  /**
   * Geben einen String der falschen Groeﬂe und (schlieﬂ leeren String ein) vor.
   * Erwartet wird eine {@code WrongSizeException}.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHash()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHashStringWrongSize")
  void convertFENToHashStringWrongSizeTest() {
    fail("Not yet implemented");
  }

  /**
   * Gebe einen FEN-String vor und erwarte einen richtigen History-String.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHistory()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHistorySuccess")
  void convertFENToHistorySuccessTest() {
    fail("Not yet implemented");
  }

  /**
   * Geben einen {@code null}-String vor und erwarte eine
   * {@code NullPointerException}.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHistory()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHistoryNull")
  void convertFENToHistoryNullTest() {
    fail("Not yet implemented");
  }

  /**
   * Gebe einen String der falschen Groeﬂe (schlieﬂt leeren String ein) vor.
   * Erwartet wird eine {@code WrongSizeException}.
   * 
   * @param void
   * @return void
   * @tested {@code Converter.convertFENToHistory()}
   * @author Lucas Noack
   */
  @Test
  @DisplayName("convertFENToHistoryWrongSize")
  void convertFENToHistoryWrongSizeTest() {
    fail("Not yet implemented");
  }
}
