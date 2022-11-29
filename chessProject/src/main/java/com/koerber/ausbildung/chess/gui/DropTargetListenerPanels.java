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

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.Converter;

public class DropTargetListenerPanels extends DropTargetAdapter {

  private DropTarget dropTarget;
  private JPanel     dropPanel;

  public DropTargetListenerPanels(JPanel p) {
    this.dropPanel = p;
    setDropTarget(new DropTarget(p, DnDConstants.ACTION_COPY, this, true, null));
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    // try {
    // Transferable tr = event.getTransferable();
    //
    // if(event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
    //
    // JLabel dragLabel = (JLabel)tr.getTransferData(DataFlavor.imageFlavor);
    // Icon ico = dragLabel.getIcon();
    // System.out.println(dragLabel.getName());
    // dragLabel.getName();
    // if(ico != null) {
    // ((JLabel)dropPanel.getComponent(0)).setIcon(ico);
    // dragLabel.setIcon(null);
    // ((JPanel)dragLabel.getParent()).updateUI();
    //
    // event.dropComplete(true);
    // dropPanel.updateUI();
    // }
    // }
    // else {
    // event.rejectDrop();
    // }
    // }
    // catch(Exception e) {
    // e.printStackTrace();
    // event.rejectDrop();
    // }
    try {
      Transferable tr = event.getTransferable();
      if(event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
        JLabel dragLabel = (JLabel)tr.getTransferData(DataFlavor.imageFlavor);
        String startPosition = dragLabel.getName();
        JLabel dropLabel = ((JLabel)dropPanel.getComponent(0));
        String targetPosition = dropLabel.getName();
        Converter.setTargetPosition(targetPosition);
        boolean moveSuccessful = false;
        if(Field.getCurrentGameState().get(startPosition) != null) {
          moveSuccessful = Field.getCurrentGameState().get(startPosition).movePiece(Field.getCurrentGameState(),
              targetPosition);
        }
        else {
          event.rejectDrop();
        }
        if(!moveSuccessful) {
          event.rejectDrop();
        }
        else {
          Icon ico = dragLabel.getIcon();
          if(ico != null) {
            ((JLabel)dropPanel.getComponent(0)).setIcon(ico);
            dragLabel.setIcon(null);
            ((JPanel)dragLabel.getParent()).updateUI();
            
            event.dropComplete(true);
            GuiFrame.clearLegalMoveMap();
            Gui.showCurrentGameState(GuiFrame.currentGameStateLabels);
            dropPanel.updateUI();
            Pawn.resetEnPassant(Field.getCurrentGameState(),
                Field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE);
            Field.increaseCurrentTurn();
            GuiFrame.highlightActivePlayer();
            Field.turnLock();
            Gui.createNewHistroyEntry();
            
          }
          else {
            event.rejectDrop();
          }
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

  public DropTarget getDropTarget() {
    return dropTarget;
  }

  public void setDropTarget(DropTarget dropTarget) {
    this.dropTarget = dropTarget;
  }
}
