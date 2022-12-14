package com.koerber.ausbildung.chess;

import java.util.ArrayList;
import java.util.List;

import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.utility.ChessColour;

/**
 * represents Player having a name, a colour, a score and the opponents taken
 * Pieces
 * 
 * @comment base-structure
 * @author Toni Gropper
 * @since 19.10.2022
 */
public class Player {
  private String      name;
  private ChessColour colour;
  private int         score;
  private List<Piece> takenPieces = new ArrayList<>();

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ChessColour getColour() {
    return colour;
  }

  public void setColour(ChessColour colour) {
    this.colour = colour;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public List<Piece> getTakenPieces() {
    return takenPieces;
  }

  /**
   * adds taken Pieces to this.takenPiece and opens increaseScore
   * 
   * @param Object Piece
   * @throws IllegalArgumentException if piece is not on map
   * @comment base-structure
   */
  public void addTakenPiece(Piece piece) {
    takenPieces.add(piece);
    // raise score
    this.score = this.score + piece.getValue();
  }

  /**
   * resets all attributes to initialization values
   * 
   * @param void
   * @comment base-structure
   */
  public void setToInit() {
    setScore(0);
    takenPieces.clear();
  }

}
