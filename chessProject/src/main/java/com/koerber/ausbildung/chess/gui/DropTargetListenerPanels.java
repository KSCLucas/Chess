package com.koerber.ausbildung.chess.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DropTargetListenerPanels extends DropTargetAdapter {

  private DropTarget dropTarget;
  private JPanel     dropPanel;

  public DropTargetListenerPanels(JPanel p) {
    this.dropPanel = p;
    dropTarget = new DropTarget(p, DnDConstants.ACTION_COPY, this, true, null);
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    try {
      Transferable tr = event.getTransferable();

      if(event.isDataFlavorSupported(DataFlavor.imageFlavor)) {

        JLabel dragLabel = (JLabel)tr.getTransferData(DataFlavor.imageFlavor);
        Icon ico = dragLabel.getIcon();
        dragLabel.getName();
        if(ico != null) {
          ((JLabel)dropPanel.getComponent(0)).setIcon(ico);
          dragLabel.setIcon(null);
          ((JPanel)dragLabel.getParent()).updateUI();

          event.dropComplete(true);
          dropPanel.updateUI();
        }
      }
      else {
        event.rejectDrop();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      event.rejectDrop();
    }
  }

}
