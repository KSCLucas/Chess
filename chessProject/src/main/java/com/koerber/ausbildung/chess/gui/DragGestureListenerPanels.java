package com.koerber.ausbildung.chess.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.io.IOException;

import javax.swing.JLabel;

public class DragGestureListenerPanels implements DragGestureListener {

  @Override
  public void dragGestureRecognized(DragGestureEvent event) {
    JLabel label = (JLabel)event.getComponent();

    Transferable transferable = new Transferable() {

      @Override
      public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
      }

      @Override
      public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return label;
      }

      @Override
      public boolean isDataFlavorSupported(DataFlavor flavor) {
        if(!isDataFlavorSupported(flavor)) {
          return false;
        }
        return true;
      }
    };
    event.startDrag(null, transferable);

  }

}
