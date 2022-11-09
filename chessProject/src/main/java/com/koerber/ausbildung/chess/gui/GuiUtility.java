package com.koerber.ausbildung.chess.gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GuiUtility {

  /**
   * GridBagContraints Builder
   * 
   * @param rightInset
   * @param fill
   * @param xPos
   * @param yPos
   * @param height
   * @return
   */
  public static GridBagConstraints setGridBag(boolean rightInset, boolean fill, int xPos, int yPos, int height) {
    GridBagConstraints tempGridBag = new GridBagConstraints();
    int rightInsetInt = 0;
    if(rightInset) {
      rightInsetInt = 5;
    }
    tempGridBag.insets = new Insets(0, 0, 5, rightInsetInt);
    if(fill) {
      tempGridBag.fill = GridBagConstraints.BOTH;
    }
    tempGridBag.gridx = xPos;
    tempGridBag.gridy = yPos;
    tempGridBag.gridheight = height;
    return tempGridBag;
  }

}

