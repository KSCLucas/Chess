package com.koerber.ausbildung.chess.utility;

import javax.swing.ImageIcon;

/**
 * Supplies {@code Pieces} with an {@code icon} given the {@code colour}.
 * 
 * @author PKamps
 */
public abstract class IconSupplier {

  private static final String BASE_PATH  = "src/main/resources/Sprites_in_small/";
  private static final String SUFFIX     = ".png";
  private static final String INSERTABLE = "_w";

  public static ImageIcon getIcon(ChessColour colour, String iconFileName) {
    ImageIcon icon;
    try {
      if(colour == ChessColour.WHITE) {
        StringBuilder newFileName = new StringBuilder(iconFileName);
        newFileName = newFileName.insert(newFileName.indexOf("_"), INSERTABLE);
        icon = new ImageIcon(BASE_PATH + newFileName + SUFFIX);
      }
      else {
        icon = new ImageIcon(BASE_PATH + iconFileName + SUFFIX);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      icon = null;
    }
    return icon;
  }
}
