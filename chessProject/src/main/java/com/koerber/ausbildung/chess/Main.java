package com.koerber.ausbildung.chess;

import java.awt.EventQueue;

import com.koerber.ausbildung.chess.gui.GuiFrame;
import com.koerber.ausbildung.chess.piece.King;
import com.koerber.ausbildung.chess.utility.ChessColour;

public class Main {

  public static void main(String[] args) {
    Field field = new Field();
    History history = new History();
    field.initializeMap();
    field.turnLock();
    field.getCurrentGameState().entrySet().stream()
        .filter(x -> x.getValue() instanceof King && x.getValue().getColour() == ChessColour.WHITE).forEach(x -> {
          King king = (King)x.getValue();
          king.checkForCheckAndCreateLegalMoveMap(field.getCurrentGameState());
        });
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GuiFrame window = new GuiFrame(field, history);
          window.getFrame().setVisible(true);
          window.highlightActivePlayer(field, window.getPlayer1Label(), window.getPlayer2Label());
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
