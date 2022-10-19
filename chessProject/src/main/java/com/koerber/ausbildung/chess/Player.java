package com.koerber.ausbildung.chess;

import java.util.ArrayList;

/**
 * represents Player having a name, a colour, a score and the opponents taken
 * Pieces
 * 
 * @comment base-structure
 * @author Toni Gropper
 * @since 19.10.2022
 */
public class Player {
  private String               name;
  private char                 colour;
  private int                  score;
  private ArrayList<Character> takenPiece;
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public char getColour() {
    return colour;
  }

  public void setColour(char colour) {
    this.colour = colour;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public ArrayList<Character> getTakenPiece() {
    return takenPiece;
  }

  public void setTakenPiece(ArrayList<Character> takenPiece) {
    this.takenPiece = takenPiece;
  }

  /**
   * adds taken Pieces to this.takenPiece and opens increaseScore
   * 
   * @param Object Piece
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void addTakenPiece(Object Piece) {
  }

  /**
   * this.score + int; int-Wert is equivalent to Piece-Wert
   * 
   * @param int
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void increaseScore(int score) {
  }

  /**
   * resets all attributes to initialization values
   * 
   * @param void
   * @return void
   * @throws
   * @comment base-structure
   * @author Toni Gropper
   */
  public void setToInit() {

  }

}
