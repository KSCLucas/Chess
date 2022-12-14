package com.koerber.ausbildung.chess.utility;

import javax.swing.ImageIcon;

/**
 * Supplies {@code Pieces} with an {@code icon} given the {@code colour}.
 * 
 * @author PKamps
 */
public abstract class IconSupplier {

  private static final String BASE_PATH  = "src/main/resources/Sprites_in_small/";
  private static final String FILE_TYPE     = ".png";
  private static final String WHITE_INFIX = "_w";

  public static ImageIcon getIcon(ChessColour colour, String iconFileName) {
    ImageIcon icon;
    try {
      if(colour == ChessColour.WHITE) {
        StringBuilder newFileName = new StringBuilder(iconFileName);
        newFileName = newFileName.insert(newFileName.indexOf("_"), WHITE_INFIX);
        icon = new ImageIcon(BASE_PATH + newFileName + FILE_TYPE);
      }
      else {
        icon = new ImageIcon(BASE_PATH + iconFileName + FILE_TYPE);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      icon = null;
    }
    return icon;
  }
}
