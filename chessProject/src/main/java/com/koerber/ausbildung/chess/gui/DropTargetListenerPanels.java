package com.koerber.ausbildung.chess.gui;

import java.awt.Component;
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
          if(field.getCurrentGameState().get(targetPosition) instanceof King king) {
            try {
              king.castle(field.getCurrentGameState());
            }
            catch(Exception e) {
            }
          }
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
            // everything below happens after a successful drop
            // clear middle layer / legal move layer
            window.clearLegalMoveMap(window.getLegalMoveLabels());
            // update top layer / piece sprite layer
            Gui.showCurrentGameState(field.getCurrentGameState(), window.getCurrentGameStateLabels());
            dropPanel.updateUI();
            // sets en passant to false for all opposing pawns & checks for
            // possible promotion
            field.getCurrentGameState().entrySet().stream().filter(x -> x.getValue() instanceof Pawn).forEach(x -> {
              Pawn pawn = (Pawn)x.getValue();
              pawn.resetEnPassant(field.getCurrentGameState(),
                  field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE);
              pawn.checkForPromotion();
              if(pawn.isPromotable()) {
                while(field.getCurrentGameState().get(pawn.getPosition()) instanceof Pawn) {
                  Gui.showPromotionSelection(window.getFrame(), field.getCurrentGameState(), pawn);
                }
              }
            });
            // increase turn counter
            field.increaseCurrentTurn();
            // highlights active player based on turn
            window.highlightActivePlayer(field, window.getPlayer1Label(), window.getPlayer2Label());
            // sets movable to false for all own pieces
            field.turnLock();
            // add entry to fen-list
            history.addEntry(Converter.convertMapToFEN(field.getCurrentGameState()));
            // add entry to jlist
            Gui.createNewHistroyEntry(field, history, window.getHistoryJList());
            // highlights current turn in history jlist
            window.getHistoryJList().setSelectedIndex(field.getCurrentTurn() - 2);
            // check for checkmate
            field.getCurrentGameState().entrySet().stream()
                .filter(x -> x.getValue() instanceof King && x.getValue()
                    .getColour() == (field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE))
                .forEach(x -> {
                  King king = (King)x.getValue();
                  king.checkForCheckAndCreateLegalMoveMap(field.getCurrentGameState());
                  king.checkForCheckmate();
                });
            // show winner popup if there is a winner
            Gui.showWinnerPopup(field);
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
