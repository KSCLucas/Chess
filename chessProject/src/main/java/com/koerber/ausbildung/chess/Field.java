package com.koerber.ausbildung.chess;

import java.util.TreeMap;

import com.koerber.ausbildung.chess.piece.Bishop;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Knight;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.piece.Rook;
import com.koerber.ausbildung.chess.utility.ChessColour;

/**
 * Serves as a gameclass that manages everything related to chess.
 * 
 * @author Toni Gropper, PKamps
 * @since 19.10.2022
 */
public class Field {

  public static final int         UPPER_BOUND      = 8;
  public static final int         LOWER_BOUND      = 1;
  public static final char        LEFT_BOUND       = 'A';
  public static final char        RIGHT_BOUND      = 'H';
  private TreeMap<String, Object> currentGameState = new TreeMap<>();
  private int                     currentTurn      = 1;
  private char                    whoWinner;

  public TreeMap<String, Object> getCurrentGameState() {
    return currentGameState;
  }

  public void setCurrentGameState(TreeMap<String, Object> currentGameState) {
    this.currentGameState = currentGameState;
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public void setCurrentTurn(int currentTurn) {
    this.currentTurn = currentTurn;
  }

  public char getWhoWinner() {
    return whoWinner;
  }

  public void setWhoWinner(char whoWinner) {
    this.whoWinner = whoWinner;
  }

  /**
   * Sets game pieces in {@code currentGameState} to the starting positions of
   * {@code initialGameState}.
   */
  public void initializeMap() {
    // Clear currentGameState
    getCurrentGameState().clear();
    // Create white pawns
    for(int i = 1; i < 9; i++) {
      Pawn pawn = new Pawn("p" + i + "w", ChessColour.WHITE, Character.toString(64 + i) + "2");
      getCurrentGameState().put(pawn.getPosition(), pawn);
    }
    // Create black pawns
    for(int i = 1; i < 9; i++) {
      Pawn pawn = new Pawn("p" + i + "b", ChessColour.BLACK, Character.toString(64 + i) + "7");
      getCurrentGameState().put(pawn.getPosition(), pawn);
    }
    // Create other white pieces
    Rook rookW1 = new Rook("r1w", ChessColour.WHITE, "A1", Rook.CASTLE_SIDE_LONG);
    Rook rookW2 = new Rook("r2w", ChessColour.WHITE, "H1", Rook.CASTLE_SIDE_SHORT);
    Knight knightW1 = new Knight("n1w", ChessColour.WHITE, "B1");
    Knight knightW2 = new Knight("n2w", ChessColour.WHITE, "G1");
    Bishop bishopW1 = new Bishop("b1w", ChessColour.WHITE, "C1");
    Bishop bishopW2 = new Bishop("b2w", ChessColour.WHITE, "F1");
    Queen queenW = new Queen("q1w", ChessColour.WHITE, "D1");
    King kingW = new King("k1w", ChessColour.WHITE, "E1");
    getCurrentGameState().put(rookW1.getPosition(), rookW1);
    getCurrentGameState().put(rookW2.getPosition(), rookW2);
    getCurrentGameState().put(knightW1.getPosition(), knightW1);
    getCurrentGameState().put(knightW2.getPosition(), knightW2);
    getCurrentGameState().put(bishopW1.getPosition(), bishopW1);
    getCurrentGameState().put(bishopW2.getPosition(), bishopW2);
    getCurrentGameState().put(queenW.getPosition(), queenW);
    getCurrentGameState().put(kingW.getPosition(), kingW);
    // Create other black pieces
    Rook rookB1 = new Rook("r1b", ChessColour.BLACK, "A8", Rook.CASTLE_SIDE_LONG);
    Rook rookB2 = new Rook("r2b", ChessColour.BLACK, "H8", Rook.CASTLE_SIDE_SHORT);
    Knight knightB1 = new Knight("n1b", ChessColour.BLACK, "B8");
    Knight knightB2 = new Knight("n2b", ChessColour.BLACK, "G8");
    Bishop bishopB1 = new Bishop("b1b", ChessColour.BLACK, "C8");
    Bishop bishopB2 = new Bishop("b2b", ChessColour.BLACK, "F8");
    Queen queenB = new Queen("q1b", ChessColour.BLACK, "D8");
    King kingB = new King("k1b", ChessColour.BLACK, "E8");
    getCurrentGameState().put(rookB1.getPosition(), rookB1);
    getCurrentGameState().put(rookB2.getPosition(), rookB2);
    getCurrentGameState().put(knightB1.getPosition(), knightB1);
    getCurrentGameState().put(knightB2.getPosition(), knightB2);
    getCurrentGameState().put(bishopB1.getPosition(), bishopB1);
    getCurrentGameState().put(bishopB2.getPosition(), bishopB2);
    getCurrentGameState().put(queenB.getPosition(), queenB);
    getCurrentGameState().put(kingB.getPosition(), kingB);
  }

  /**
   * Updates {@code currentGameState} with the completed move.
   */
  public void updateMap() {

  }

  /**
   * Sets {@code currentTurn} to initial value (=1).
   */
  public void resetCurrentTurn() {

  }

  /**
   * Increases {@code currentTurn} by 1.
   */
  public void increaseCurrentTurn() {

  }

  // TODO game loop needed???
  /**
   * Regulates the course of the chess game and calls all methods that are
   * required for the game.
   */
  public void gameLoop() {

  }

  /**
   * Checks, if a player has won and sets {@code whoWinner} to
   * {@code ChessColour.WHITE} or {@code ChessColour.BLACK}, calls the GUI
   * method {@code showWinnerPopup}. If none of the Kings are in checkmate then
   * {@code whoWinner = ChessColour.NONE} is set.
   */
  public void checkForWinner() {

  }
}
