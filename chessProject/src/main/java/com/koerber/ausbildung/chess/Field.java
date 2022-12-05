package com.koerber.ausbildung.chess;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.koerber.ausbildung.chess.piece.Bishop;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Knight;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.piece.Rook;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.OnlyOneWinnerException;

/**
 * Serves as a gameclass that manages everything related to chess.
 * 
 * @author Toni Gropper, PKamps
 * @since 19.10.2022
 */
public class Field {

  public static final int    UPPER_BOUND      = 8;
  public static final int    LOWER_BOUND      = 1;
  public static final char   LEFT_BOUND       = 'A';
  public static final char   RIGHT_BOUND      = 'H';
  private Map<String, Piece> currentGameState = new TreeMap<>();
  private int                currentTurn      = 1;
  private ChessColour        winner           = null;
  public Map<String, Piece> getCurrentGameState() {
    return currentGameState;
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public void setCurrentTurn(int currentTurn) {
    this.currentTurn = currentTurn;
  }

  public ChessColour getWinner() {
    return winner;
  }

  public void setWinner(ChessColour whoWinner) {
    this.winner = whoWinner;
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
   * Sets {@code currentTurn} to initial value (=1).
   */
  public void resetCurrentTurn() {
    setCurrentTurn(1);
  }

  /**
   * Increases {@code currentTurn} by 1.
   */
  public void increaseCurrentTurn() {
    setCurrentTurn(getCurrentTurn() + 1);
  }

  /**
   * Decreases {@code currentTurn} by 1.
   */
  public void decreaseCurrentTurn() {
    if(getCurrentTurn() > 1) {
      setCurrentTurn(getCurrentTurn() - 1);
    }
  }

  /**
   * Checks, if a player has won and sets {@code winner} to
   * {@code ChessColour.WHITE} or {@code ChessColour.BLACK}, calls the GUI
   * method {@code showWinnerPopup}. If none of the Kings are in checkmate then
   * {@code whoWinner = ChessColour.NONE} is set.
   */
  public void checkForWinner() throws OnlyOneWinnerException {
    List<King> kings = getCurrentGameState().entrySet().stream().map(Entry::getValue).filter(King.class::isInstance)
        .map(King.class::cast).toList();
    for(King king : kings) {
      if(king.isCheckmate() && getWinner() == null) {
        setWinner(king.getColour() == ChessColour.WHITE ? ChessColour.BLACK : ChessColour.WHITE);
      }
      else if(king.isCheckmate() && getWinner() != null) {
        throw new OnlyOneWinnerException();
      }
    }
  }

  /**
   * Sets {@code moveable = false} for every {@code Piece} depending on the
   * current {@code turn}.
   */
  public void turnLock() {
    ChessColour toLock = getCurrentTurn() % 2 == 0 ? ChessColour.WHITE : ChessColour.BLACK;
    for(Entry<String, Piece> entry : getCurrentGameState().entrySet()) {
      if(entry.getValue().getColour() == toLock) {
        entry.getValue().setMoveable(false);
      }
      else {
        entry.getValue().setMoveable(true);
      }
    }
  }
}
