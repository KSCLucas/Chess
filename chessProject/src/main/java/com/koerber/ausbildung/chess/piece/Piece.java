package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * Die {@code Piece}-Klasse stellt einen Constructor und wichtige Methoden für
 * jede Spielfigur.
 * 
 * @comment Die Hashmap wird parallel zum Constructor aufgebaut.
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
   * Parametisierter Constructor für ein {@code Piece}.
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
   * Bewegt Spielfigur zur {@code targetPosition}, wenn diese legal ist. Ruft
   * dafür {@code createLegalMoveMap} auf. Überprüft dafür den Wert für den Key
   * {@code targetPosition}. Befindet sich eine gegnerische Figur auf dem
   * legalen Feld, wird die {@code position} dieser Figur auf "xy" gesetzt.
   * 
   * @param targetPosition
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
   */
  public void movePiece(String targetPosition) throws PieceOutOfBoundsException {

  }

  /**
   * Erstellt auf Basis von {@code this.position} eine HashMap mit den Werten
   * "fff" für nicht legal, "ttt" für legal und "hhh" für das mögliche Schlagen
   * einer Spielfigur.
   * 
   * @return void
   * @throws PieceOutOfBoundsException
   * @author PKamps
   */
  public void createLegalMoveMap() throws PieceOutOfBoundsException {

  }

}
