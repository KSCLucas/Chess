package com.koerber.ausbildung.chess.utility;

/**
 * A class for creating {@code MoveVectors} for the {@code Pieces}.
 * 
 * @author PKamps
 */
public class MoveVector {

  private int x;
  private int y;

  public MoveVector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "[" + getX() + ", " + getY() + "]";
  }
}
