package com.koerber.ausbildung.chess.gui;

import java.awt.Component;
import java.awt.Point;
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
  private JPanel     p;

  public DropTargetListenerPanels(JPanel p) {
    this.p = p;
    dropTarget = new DropTarget(p, DnDConstants.ACTION_COPY, this, true, null);
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    try {
      DropTarget test = (DropTarget)event.getSource();
      Component ca = (Component)test.getComponent();
      Point dropPoint = ca.getMousePosition();
      Transferable tr = event.getTransferable();

      if(event.isDataFlavorSupported(DataFlavor.imageFlavor)) {

        JLabel label= (JLabel)tr.getTransferData(DataFlavor.imageFlavor);
        Icon ico = label.getIcon();
        
        if(ico != null) {
//          p.remove(0);
          ((JLabel) p.getComponent(0)).setIcon(ico);
          label.setIcon(null);
          ((JPanel)label.getParent()).updateUI();
//          p.add(new JLabel(ico));
          
//          p.revalidate();
//          p.repaint();
          event.dropComplete(true);
          p.updateUI();
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
