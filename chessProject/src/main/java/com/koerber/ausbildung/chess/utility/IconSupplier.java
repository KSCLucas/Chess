package com.koerber.ausbildung.chess.utility;

import javax.swing.ImageIcon;

/**
 * Supplies {@code Pieces} with an {@code icon} given the {@code colour}.
 * 
 * @author PKamps
 */
public abstract class IconSupplier {
  
  public static ImageIcon getIcon(ChessColour colour, String pathWhiteIcon, String pathBlackIcon) {
    ImageIcon icon;
    try {
      if(colour.equals(ChessColour.WHITE)) {
        icon = new ImageIcon(pathWhiteIcon);
      }
      else {
        icon = new ImageIcon(pathBlackIcon);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      icon = null;
    }
    return icon;
  }
}
