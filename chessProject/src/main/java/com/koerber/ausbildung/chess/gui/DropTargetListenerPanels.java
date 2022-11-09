package com.koerber.ausbildung.chess.gui;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JPanel;

public class DropTargetListenerPanels extends DropTargetAdapter {

  private DropTarget dropTarget;
  private JPanel     p;

  public DropTargetListenerPanels(JPanel p) {
    this.p = p;
    dropTarget = new DropTarget(p, DnDConstants.ACTION_COPY, this, true, null);
  }

  @Override
  public void drop(DropTargetDropEvent dtde) {
    // TODO Auto-generated method stub
 
  }

}
