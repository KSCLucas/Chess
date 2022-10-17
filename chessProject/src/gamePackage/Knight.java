package gamePackage;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Die Knight-Klasse stellt einen Constructor für jeden Knight.
 * 
 * @author PKamps
 */
public class Knight extends Piece {
  
  // TODO Spezifisches {@code moveSet} mit MoveSetBuilder (noch zu bauen)
  // produzieren.
  /**
   * Ruft parametisierten Constructor von Piece auf.
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
  public Knight(char name, char colour, int value, boolean isMoveRepeatable, String position,
      ArrayList<ArrayList<Integer>> moveSet, Image skin) {
    super(name, colour, value, isMoveRepeatable, position, moveSet, skin);
  }

}
