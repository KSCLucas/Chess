package com.koerber.ausbildung.chess.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests the {@code Converter} class.
 * 
 * @author Lucas Noack
 */
class ConverterTest {

  /**
   * Specify a hashmap and expect the correct FEN string.
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
   * Provide a hashmap with {@code null} and expect a
   * {@code NullPointerException}.
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
   * Provide a hashmap of the wrong size (includes empty hashmap). A
   * {@code WrongSizeException} is expected.
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
    // TODO implement WrongSizeException
  }

  /**
   * Provide a FEN string and expect the correct hashmap.
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
   * Provide a {@code null} string and expect a {@code NullPointerException}.
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
   * Provide a string of the wrong size (includes empty string) . A
   * {@code WrongSizeException} is expected.
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
   * Specify a FEN string and expect a proper history string.
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
   * Provide a {@code null} string and expect a {@code NullPointerException}.
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
   * Provide a string of the wrong size (includes empty string). A
   * {@code WrongSizeException} is expected.
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
