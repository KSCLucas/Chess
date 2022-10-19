package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code Piece} class contains a constructor as well as methods for every
 * {@code Piece}.
 * 
 * @comment The {@code legalMoveMap} is built parallel to the constructor.
 * @author PKamps
 */
public abstract class Piece {

  private String                        name;
  private char                          colour;
  private int                           value;
  private boolean                       isMoveRepeatable;
  private String                        position;
  private ArrayList<ArrayList<Integer>> moveSet;
  private Image                         skin;
  private HashMap<String, String>       legalMoveMap;

  /**
   * Parameterized constructor for a {@code Piece}.
   * 
   * @param name
   * @param colour
   * @param value
   * @param isMoveRepeatable
   * @param position
   * @param moveSet
   * @param skin
   * @author PKamps
   */
  public Piece(String name, char colour, int value, boolean isMoveRepeatable, String position,
      ArrayList<ArrayList<Integer>> moveSet, Image skin) {
    this.name = name;
    this.colour = colour;
    this.value = value;
    this.isMoveRepeatable = isMoveRepeatable;
    this.position = position;
    this.moveSet = moveSet;
    this.skin = skin;
    this.legalMoveMap = new HashMap<String, String>();
  }

  {

  }

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

  public int getValue() {
    return value;
  }

  public boolean isMoveRepeatable() {
    return isMoveRepeatable;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public ArrayList<ArrayList<Integer>> getMoveSet() {
    return moveSet;
  }

  public Image getSkin() {
    return skin;
  }

  public void setSkin(Image skin) {
    this.skin = skin;
  }

  public HashMap<String, String> getLegalMoveMap() {
    return legalMoveMap;
  }

  public void setLegalMoveMap(HashMap<String, String> legalMoveMap) {
    this.legalMoveMap = legalMoveMap;
  }

  /**
   * Calls the {@code createLegalMoveMap} method. Moves {@code Piece} to
   * {@code targetPosition}, if {@code targetPosition} is legal. Checks legality
   * by checking the value for the key {@code targetPosition}. If there is an
   * opposing {@code Piece} on {@code targetPosition}, it sets {@code position}
   * of that {@code Piece} to "xy".
   * 
   * @param targetPosition
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
   */
  public void movePiece(String targetPosition) throws PieceOutOfBoundsException {

  }

  /**
   * Provides a HashMap based on {@code position} with the following values:
   * "fff" for not legal, "ttt" for legal and "hhh" for an opposing takeable
   * {@code Piece}.
   * 
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
   */
  public void createLegalMoveMap() throws PieceOutOfBoundsException {

  }

}
