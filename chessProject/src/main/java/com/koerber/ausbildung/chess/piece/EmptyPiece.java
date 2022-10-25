package com.koerber.ausbildung.chess.piece;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EmptyPiece} class provides a standardised constructor for every
 * {@code EmptyPiece}.
 * 
 * @comment It is not intended to call setters, {@code createLegalMoveMap} and
 *          {@code movePiece} for {@code EmptyPiece}.
 * @author PKamps
 */
public class EmptyPiece extends Piece {

  public static final String                   ID                 = "###";
  public static final char                     COLOUR             = 'e';
  public static final int                      VALUE              = 0;
  public static final boolean                  IS_MOVE_REPEATABLE = false;
  public static final String                   POSITION           = "nd";
  public static final List<ArrayList<Integer>> MOVESET            = new ArrayList<>();
  public static final Image                    ICON               = null;

  /**
   * Calls parameterized constructor of {@code Piece} and passes values for
   * {@code EmptyPiece}.
   */
  public EmptyPiece() {
    super(ID, COLOUR, VALUE, IS_MOVE_REPEATABLE, POSITION, MOVESET, ICON);
  }
}
