package com.koerber.ausbildung.chess.utility;

import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    // Self check
    if(this == o)
      return true;
    // Null check
    if(o == null)
      return false;
    // Type check and cast
    if(getClass() != o.getClass())
      return false;
    MoveVector moveVector = (MoveVector)o;
    // Field comparison
    return Objects.equals(x, moveVector.x) && Objects.equals(y, moveVector.y);
  }
}
