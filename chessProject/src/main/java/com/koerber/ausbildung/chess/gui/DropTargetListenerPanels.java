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
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.Converter;

public class DropTargetListenerPanels extends DropTargetAdapter {

  private DropTarget     dropTarget;
  private JPanel         dropPanel;
  private final Field    field;
  private final History  history;
  private final GuiFrame window;

  public DropTargetListenerPanels(JPanel p, Field field, History history, GuiFrame window) {
    this.dropPanel = p;
    this.field = field;
    this.history = history;
    this.window = window;
    setDropTarget(new DropTarget(p, DnDConstants.ACTION_COPY, this, true, null));
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    try {
      Transferable tr = event.getTransferable();
      if(event.isDataFlavorSupported(DataFlavor.imageFlavor)) {
        JLabel dragLabel = (JLabel)tr.getTransferData(DataFlavor.imageFlavor);
        String startPosition = dragLabel.getName();
        JLabel dropLabel = ((JLabel)dropPanel.getComponent(0));
        String targetPosition = dropLabel.getName();
        Converter.setTargetPosition(targetPosition);
        boolean moveSuccessful = false;
        if(field.getCurrentGameState().get(startPosition) != null) {
          moveSuccessful = field.getCurrentGameState().get(startPosition).movePiece(field.getCurrentGameState(),
              targetPosition, field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE);
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
            window.clearLegalMoveMap(window.getLegalMoveLabels());
            Gui.showCurrentGameState(field.getCurrentGameState(), window.getCurrentGameStateLabels());
            dropPanel.updateUI();
            field.getCurrentGameState().entrySet().stream().filter(x -> x.getValue() instanceof Pawn).forEach(x -> {
              Pawn pawn = (Pawn)x.getValue();
              pawn.resetEnPassant(field.getCurrentGameState(),
                  field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE);
            });
            field.increaseCurrentTurn();
            window.highlightActivePlayer(field, window.getPlayer1Label(), window.getPlayer2Label());
            field.turnLock();
            history.addEntry(Converter.convertMapToFEN(field.getCurrentGameState()));
            Gui.createNewHistroyEntry(field, history, window.getHistoryJList());
            window.getHistoryJList().setSelectedIndex(field.getCurrentTurn() - 2);
            field.getCurrentGameState().entrySet().stream()
                .filter(x -> x.getValue() instanceof King && x.getValue()
                    .getColour() == (field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE))
                .forEach(x -> {
                  King king = (King)x.getValue();
                  king.checkForCheckAndCreateLegalMoveMap(field.getCurrentGameState());
                  king.checkForCheckmate();
                });
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
