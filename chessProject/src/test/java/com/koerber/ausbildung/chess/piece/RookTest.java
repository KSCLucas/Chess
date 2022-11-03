package com.koerber.ausbildung.chess.piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.ObjectFactoryForTest;

/**
 * Tests the {@code Rook} class.
 * 
 * @author Lucas Noack, PKamps
 */
class RookTest {

  /**
   * Builds a {@code Rook} object with initial test values. Then set
   * {@code Rook.position} to another legal value and expect
   * {@code Rook.hasMoved = true}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveSuccess")
  void checkForFirstMoveSuccessTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    testRook.setPosition("A3");
    assertEquals("A3", testRook.getPosition());
    assertEquals(true, testRook.isHasMoved());
  }

  /**
   * Builds a {@code Rook} object with initial test values. Don't
   * change{@code Rook.position} and expect{@code Rook.hasMoved = false}.
   * 
   * @tests {@code setPosition}
   */
  @Test
  @DisplayName("checkForFirstMoveFailure")
  void checkForFirstMoveFailureTest() {
    Rook testRook = ObjectFactoryForTest.getRook();
    assertEquals(false, testRook.isHasMoved());

  }

  /**
   * Builds {@code Rook} object with initial test values. All fields between Rook
   * and King are an {@code EmptyPiece}. It is expected that
   * {@code Rook.canCastle = true}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleSuccess")
  void checkForCastleSuccessTest() {
    King king = ObjectFactoryForTest.getKing();
    king.setPosition("E1");
    king.setHasMoved(false);
    Rook testRookShortSide = ObjectFactoryForTest.getRook();
    testRookShortSide.setPosition("H1");
    testRookShortSide.setHasMoved(false);
    Rook testRookLongSide = ObjectFactoryForTest.getRook();
    testRookLongSide.setId("r2w");
    testRookLongSide.setCastleSide('l');
    
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("E1", king);
    testCurrentGameState.put("H1", testRookShortSide);
    testCurrentGameState.put("A1", testRookLongSide);
    
    testRookShortSide.checkForCastle(testCurrentGameState);
    testRookLongSide.checkForCastle(testCurrentGameState);
    
    assertEquals(true, testRookShortSide.isCanCastle());
    assertEquals(true, testRookLongSide.isCanCastle());
  }

  /**
   * Builds a {@code Rook} object with initial test values. Not all fields are
   * empty or Rook has already moved. It is expected that
   * {@code Rook.canCastle = false}.
   * 
   * @tests {@code checkForCastle()}
   */
  @Test
  @DisplayName("checkForCastleFailure")
  void checkForCastleFailureTest() {
    King king = ObjectFactoryForTest.getKing();
    king.setPosition("E1");
    king.setHasMoved(false);
    Rook testRookShortSide = ObjectFactoryForTest.getRook();
    testRookShortSide.setPosition("H1");
    Rook testRookLongSide = ObjectFactoryForTest.getRook();
    testRookLongSide.setId("r2w");
    testRookLongSide.setCastleSide('l');
    Queen queen = ObjectFactoryForTest.getQueen();
    queen.setPosition("D1");
    
    Map<String, Piece> testCurrentGameState = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        testCurrentGameState.put(Character.toString(i) + String.valueOf(j), null);
      }
    }
    testCurrentGameState.put("E1", king);
    testCurrentGameState.put("H1", testRookShortSide);
    testCurrentGameState.put("A1", testRookLongSide);
    testCurrentGameState.put("D1", queen);
    
    testRookShortSide.checkForCastle(testCurrentGameState);
    testRookLongSide.checkForCastle(testCurrentGameState);
    
    assertEquals(false, testRookShortSide.isCanCastle());
    assertEquals(false, testRookLongSide.isCanCastle());
  }
}
