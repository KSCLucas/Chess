package com.koerber.ausbildung.chess.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.ObjectFactoryForTest;
import com.koerber.ausbildung.chess.piece.EmptyPiece;
import com.koerber.ausbildung.chess.piece.Pawn;
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Queen;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

public class GuiFrame {

  private JFrame              frame;
  private static final String XLABEL      = "ABCDEFGH";
  Color                       lightBrown  = new Color(205, 133, 63);
  Color                       lightGreen  = new Color(144, 238, 144, 127);
  Color                       lightRed    = new Color(255, 75, 75, 127);
  LineBorder                  blackBorder = new LineBorder(Color.BLACK);
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GuiFrame window = new GuiFrame();
          window.frame.setVisible(true);
        }
        catch(Exception e) {
          e.printStackTrace();
        }
      }
    });

  }

  /**
   * Create the application.
   */
  public GuiFrame() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    // frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{499, 32, 896, 499};
    gridBagLayout.rowHeights = new int[]{108, 108, 108, 108, 108, 108, 108, 108, 108, 32};
    gridBagLayout.columnWeights = new double[]{1.0, 1.0};
    gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    frame.getContentPane().setLayout(gridBagLayout);

    // Builds New Game Panel
    JPanel newGamePanel = new JPanel();
    GridBagConstraints gbcnewGamePanel = new GridBagConstraints();
    gbcnewGamePanel.insets = new Insets(0, 0, 5, 5);
    gbcnewGamePanel.fill = GridBagConstraints.BOTH;
    gbcnewGamePanel.gridx = 0;
    gbcnewGamePanel.gridy = 0;
    frame.getContentPane().add(newGamePanel, gbcnewGamePanel);
    newGamePanel.setLayout(new GridLayout(1, 2, 0, 0));

    JButton newGameButton = new JButton("NEW GAME");
    newGamePanel.add(newGameButton);

    JButton backButton = new JButton("BACK");
    newGamePanel.add(backButton);

    JLabel historyLabel = new JLabel("HISTORY");
    GridBagConstraints gbchistoryLabel = new GridBagConstraints();
    gbchistoryLabel.insets = new Insets(0, 0, 5, 5);
    gbchistoryLabel.gridx = 0;
    gbchistoryLabel.gridy = 1;
    frame.getContentPane().add(historyLabel, gbchistoryLabel);

    // Builds top part of history side
    JPanel historyPanelTop = new JPanel();
    GridBagConstraints gbchistoryPanelTop = new GridBagConstraints();
    gbchistoryPanelTop.insets = new Insets(0, 0, 5, 5);
    gbchistoryPanelTop.fill = GridBagConstraints.BOTH;
    gbchistoryPanelTop.gridx = 0;
    gbchistoryPanelTop.gridy = 2;
    frame.getContentPane().add(historyPanelTop, gbchistoryPanelTop);
    historyPanelTop.setLayout(new GridLayout(0, 2, 0, 0));

    JButton backwardsInHistoryButton = new JButton("BACK");
    historyPanelTop.add(backwardsInHistoryButton);

    JButton forwardsInHistoryButton = new JButton("FORWARD ");
    historyPanelTop.add(forwardsInHistoryButton);

    // Builds bottom part of history side
    JPanel historyPanelBot = new JPanel();
    GridBagConstraints gbchistoryPanelBot = new GridBagConstraints();
    gbchistoryPanelBot.insets = new Insets(0, 0, 5, 5);
    gbchistoryPanelBot.fill = GridBagConstraints.BOTH;
    gbchistoryPanelBot.gridx = 0;
    gbchistoryPanelBot.gridy = 3;

    frame.getContentPane().add(historyPanelBot, gbchistoryPanelBot);
    historyPanelBot.setLayout(new GridLayout(0, 1, 0, 0));

    JButton currentGameButton = new JButton("CURRENT GAME");
    historyPanelBot.add(currentGameButton);

    // Build Scroll pane for displaying history entries
    JScrollPane historyScrollPane = new JScrollPane();
    GridBagConstraints gbchistoryScrollPane = new GridBagConstraints();
    gbchistoryScrollPane.gridheight = 6;
    gbchistoryScrollPane.insets = new Insets(0, 0, 5, 5);
    gbchistoryScrollPane.fill = GridBagConstraints.BOTH;
    gbchistoryScrollPane.gridx = 0;
    gbchistoryScrollPane.gridy = 4;
    frame.getContentPane().add(historyScrollPane, gbchistoryScrollPane);

    // Labels x-axis of chess board (12345678)
    JPanel labelYPanel = new JPanel();
    GridBagConstraints gbclabelYPanel = new GridBagConstraints();
    gbclabelYPanel.anchor = GridBagConstraints.EAST;
    gbclabelYPanel.gridheight = 8;
    gbclabelYPanel.insets = new Insets(0, 0, 5, 5);
    gbclabelYPanel.fill = GridBagConstraints.VERTICAL;
    gbclabelYPanel.gridx = 1;
    gbclabelYPanel.gridy = 1;
    frame.getContentPane().add(labelYPanel, gbclabelYPanel);
    labelYPanel.setLayout(new GridLayout(8, 0, 0, 0));
    for(int i = 0; i < 8; i++) {
      labelYPanel.add(new JLabel("" + (i + 1)), SwingConstants.CENTER);
    }

    JPanel chessBoardBottomLayer = new JPanel();
    chessBoardBottomLayer.setBounds(0, 0, 896, 896);
    GridBagConstraints gbcchessBoardBottomLayer = new GridBagConstraints();
    gbcchessBoardBottomLayer.gridheight = 8;
    gbcchessBoardBottomLayer.insets = new Insets(0, 0, 5, 0);
    gbcchessBoardBottomLayer.fill = GridBagConstraints.BOTH;
    gbcchessBoardBottomLayer.gridx = 2;
    gbcchessBoardBottomLayer.gridy = 1;
    frame.getContentPane().add(chessBoardBottomLayer, gbcchessBoardBottomLayer);
    chessBoardBottomLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardBottomLayer.setBorder(new LineBorder(Color.BLACK));

    // Add Labels and colors them like a chess board
    JLabel[] labels = new JLabel[65];
    for(int i = 1; i < 65; i++) {
      labels[i] = new JLabel();
      // labels[i].setText("label" + i);
      labels[i].setOpaque(true);
      if((i > 0 && i <= 8) || (i > 16 && i <= 24) || (i > 32 && i <= 40) || (i > 48 && i <= 56)) {
        if(i % 2 == 0) {
          labels[i].setBackground(lightBrown);
        }
        else {
          labels[i].setBackground(Color.white);
        }
      }
      if((i > 8 && i <= 16) || (i > 24 && i <= 32) || (i > 40 && i <= 48) || (i > 56 && i <= 64)) {
        if(i % 2 == 0) {
          labels[i].setBackground(Color.white);
        }
        else {
          labels[i].setBackground(lightBrown);
        }
      }
      chessBoardBottomLayer.add(labels[i]);
    }

    JPanel chessBoardMiddleLayer = new JPanel();
    chessBoardMiddleLayer.setOpaque(false);
    chessBoardMiddleLayer.setBounds(0, 0, 896, 896);
    GridBagConstraints gbcchessBoardMiddleLayer = new GridBagConstraints();
    gbcchessBoardMiddleLayer.gridheight = 8;
    gbcchessBoardMiddleLayer.insets = new Insets(0, 0, 5, 0);
    gbcchessBoardMiddleLayer.fill = GridBagConstraints.BOTH;
    gbcchessBoardMiddleLayer.gridx = 2;
    gbcchessBoardMiddleLayer.gridy = 1;
    // frame.getContentPane().add(chessBoardLegalMoveMapLayer,
    // gbcchessBoardLegalMoveMapLayer);
    chessBoardMiddleLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardMiddleLayer.setBorder(new LineBorder(Color.BLACK));

    // Calls highlightLegalMove and adds the build labels to the middle Layer
    JLabel[] legalMoveLabels = highlightLegalMove();
    for(int i = 0; i < legalMoveLabels.length; i++) {
      chessBoardMiddleLayer.add(legalMoveLabels[i]);
    }

    JPanel chessBoardTopLayer = new JPanel();
    chessBoardTopLayer.setOpaque(false);
    chessBoardTopLayer.setBounds(0, 0, 896, 896);
    GridBagConstraints gbcchessBoardTopLayer = new GridBagConstraints();
    gbcchessBoardTopLayer.gridheight = 8;
    gbcchessBoardTopLayer.insets = new Insets(0, 0, 5, 0);
    gbcchessBoardTopLayer.fill = GridBagConstraints.BOTH;
    gbcchessBoardTopLayer.gridx = 2;
    gbcchessBoardTopLayer.gridy = 1;
    // frame.getContentPane().add(chessBoardLegalMoveMapLayer,
    // gbcchessBoardLegalMoveMapLayer);
    chessBoardTopLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardTopLayer.setBorder(new LineBorder(Color.BLACK));

    JLayeredPane layeredPane = new JLayeredPane();
    GridBagConstraints gbclayeredPane = new GridBagConstraints();
    layeredPane.setBounds(0, 0, 896, 896);
    gbclayeredPane.gridheight = 8;
    gbclayeredPane.insets = new Insets(0, 0, 5, 5);
    gbclayeredPane.fill = GridBagConstraints.BOTH;
    gbclayeredPane.gridx = 2;
    gbclayeredPane.gridy = 1;
    frame.getContentPane().add(layeredPane, gbclayeredPane);
    layeredPane.add(chessBoardBottomLayer, Integer.valueOf(0));
    layeredPane.add(chessBoardMiddleLayer, Integer.valueOf(1));
    layeredPane.add(chessBoardTopLayer, Integer.valueOf(2));

    // Labels x-axis of chess board (ABCDEFGH)
    JPanel labelXPanel = new JPanel();
    GridBagConstraints gbclabelXPanel = new GridBagConstraints();
    gbclabelXPanel.insets = new Insets(0, 0, 5, 0);
    gbclabelXPanel.fill = GridBagConstraints.BOTH;
    gbclabelXPanel.gridx = 2;
    gbclabelXPanel.gridy = 9;
    frame.getContentPane().add(labelXPanel, gbclabelXPanel);
    labelXPanel.setLayout(new GridLayout(0, 8, 0, 0));
    JLabel[] xLabels = new JLabel[8];
    for(int i = 0; i < 8; i++) {
      xLabels[i] = new JLabel(XLABEL.substring(i, i + 1));
      xLabels[i].setVerticalAlignment(SwingConstants.CENTER);
      xLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
      labelXPanel.add(xLabels[i]);
    }

    JLabel scoreLabel = new JLabel("SCORE");
    GridBagConstraints gbcscoreLabel = new GridBagConstraints();
    gbcscoreLabel.insets = new Insets(0, 0, 5, 0);
    gbcscoreLabel.gridx = 3;
    gbcscoreLabel.gridy = 1;
    frame.getContentPane().add(scoreLabel, gbcscoreLabel);

    // Label for Player 1 (top)
    JLabel player1Label = new JLabel("PLAYER 1");
    // TODO set Name (PLAYER 1 = DEFAULT NAME)
    player1Label.setOpaque(true);
    player1Label.setBackground(Color.white);
    GridBagConstraints gbcplayer1Label = new GridBagConstraints();
    gbcplayer1Label.insets = new Insets(0, 0, 5, 0);
    gbcplayer1Label.gridx = 3;
    gbcplayer1Label.gridy = 2;
    frame.getContentPane().add(player1Label, gbcplayer1Label);

    JPanel player1Panel = new JPanel();
    GridBagConstraints gbcplayer1Panel = new GridBagConstraints();
    gbcplayer1Panel.insets = new Insets(0, 0, 5, 5);
    gbcplayer1Panel.fill = GridBagConstraints.BOTH;
    gbcplayer1Panel.gridx = 3;
    gbcplayer1Panel.gridy = 3;
    frame.getContentPane().add(player1Panel, gbcplayer1Panel);
    player1Panel.setLayout(new GridLayout(2, 2, 0, 0));

    JLabel colorP1Label = new JLabel("COLOR");
    colorP1Label.setBorder(blackBorder);
    player1Panel.add(colorP1Label);
    JLabel colorP1Label2 = new JLabel("WHITE");
    colorP1Label2.setBorder(blackBorder);
    player1Panel.add(colorP1Label2);
    JLabel pointsP1Label = new JLabel("POINTS");
    pointsP1Label.setBorder(blackBorder);
    player1Panel.add(pointsP1Label);
    JLabel setPointsP1Label = new JLabel("FILLER");
    setPointsP1Label.setBorder(blackBorder);
    // TODO Display Points Player 1
    player1Panel.add(setPointsP1Label);

    JLabel piecesP1Label = new JLabel("PIECES");
    GridBagConstraints gbcpiecesP1Label = new GridBagConstraints();
    gbcpiecesP1Label.insets = new Insets(0, 0, 5, 0);
    gbcpiecesP1Label.gridx = 3;
    gbcpiecesP1Label.gridy = 4;
    frame.getContentPane().add(piecesP1Label, gbcpiecesP1Label);

    JPanel piecesP1Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcpiecesP1Panel = new GridBagConstraints();
    gbcpiecesP1Panel.insets = new Insets(0, 0, 5, 5);
    gbcpiecesP1Panel.fill = GridBagConstraints.BOTH;
    gbcpiecesP1Panel.gridx = 3;
    gbcpiecesP1Panel.gridy = 5;
    frame.getContentPane().add(piecesP1Panel, gbcpiecesP1Panel);
    piecesP1Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP1FillerLabel = new JLabel("Filler");
    piecesP1Panel.add(piecesP1FillerLabel);

    // Label for Player 2 (bottom)
    JLabel player2Label = new JLabel("PLAYER 2");
    // TODO set Name (PLAYER 2 = DEFAULT NAME)
    player2Label.setOpaque(true);
    player2Label.setBackground(Color.white);
    GridBagConstraints gbcplayer2Label = new GridBagConstraints();
    gbcplayer2Label.insets = new Insets(0, 0, 5, 0);
    gbcplayer2Label.gridx = 3;
    gbcplayer2Label.gridy = 6;
    frame.getContentPane().add(player2Label, gbcplayer2Label);

    JPanel player2Panel = new JPanel();
    GridBagConstraints gbcplayer2Panel = new GridBagConstraints();
    gbcplayer2Panel.insets = new Insets(0, 0, 5, 5);
    gbcplayer2Panel.fill = GridBagConstraints.BOTH;
    gbcplayer2Panel.gridx = 3;
    gbcplayer2Panel.gridy = 7;
    frame.getContentPane().add(player2Panel, gbcplayer2Panel);
    player2Panel.setLayout(new GridLayout(2, 2, 0, 0));

    JLabel colorP2Label = new JLabel("COLOR");
    colorP2Label.setBorder(blackBorder);
    player2Panel.add(colorP2Label);
    JLabel colorP2Label2 = new JLabel("BLACK");
    colorP2Label2.setBorder(blackBorder);
    player2Panel.add(colorP2Label2);
    JLabel pointsP2Label = new JLabel("POINTS");
    pointsP2Label.setBorder(blackBorder);
    player2Panel.add(pointsP2Label);
    JLabel setPointsP2Label = new JLabel("FILLER");
    setPointsP2Label.setBorder(blackBorder);
    // TODO Display Points Player 2
    player2Panel.add(setPointsP2Label);

    JLabel piecesP2Label = new JLabel("PIECES");
    GridBagConstraints gbcpiecesP2Label = new GridBagConstraints();
    gbcpiecesP2Label.insets = new Insets(0, 0, 5, 0);
    gbcpiecesP2Label.gridx = 3;
    gbcpiecesP2Label.gridy = 8;
    frame.getContentPane().add(piecesP2Label, gbcpiecesP2Label);

    // Panel to show taken Pieces
    JPanel piecesP2Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcpiecesP2Panel = new GridBagConstraints();
    gbcpiecesP2Panel.insets = new Insets(0, 0, 5, 5);
    gbcpiecesP2Panel.fill = GridBagConstraints.BOTH;
    gbcpiecesP2Panel.gridx = 3;
    gbcpiecesP2Panel.gridy = 9;
    frame.getContentPane().add(piecesP2Panel, gbcpiecesP2Panel);
    piecesP2Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP2FillerLabel = new JLabel("Filler");
    piecesP2Panel.add(piecesP2FillerLabel);
  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   */
  public JLabel[] highlightLegalMove(/* legalMoveMap */) {

    Queen tempQueen = ObjectFactoryForTest.getQueen();
    Pawn tempPawn = ObjectFactoryForTest.getPawn();
    tempQueen.setPosition("C6");
    tempQueen.setColour('w');
    tempPawn.setPosition("B7");
    tempPawn.setColour('b');

    Map<String, Piece> currentGameStateTemp = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        currentGameStateTemp.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    currentGameStateTemp.put("B7", tempPawn);
    currentGameStateTemp.put("C6", tempQueen);
    try {
      tempPawn.createLegalMoveMap(currentGameStateTemp);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    Map<String, String> legalMoveMapTemp = new TreeMap<>(tempQueen.getLegalMoveMap());
    System.out.println(legalMoveMapTemp);
    JLabel[] legalMoveLabels = new JLabel[64];
    for(int i = 0; i < 64; i++) {
      legalMoveLabels[i] = new JLabel();
      legalMoveLabels[i].setText("");
    }
    // get numeric Value "ttt" legal "hhh" enenmy

    for(Map.Entry<String, String> entry : legalMoveMapTemp.entrySet()) {
      if(entry.getValue().equals(Piece.TRUE_STRING)) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(lightGreen);
      }
      if(entry.getValue().equals(Piece.HIT_STRING)) {
        int columnAsNumber = entry.getKey().charAt(0) - 64;
        int rowAsNumber = entry.getKey().charAt(1) - 48;
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setOpaque(true);
        legalMoveLabels[Gui.getIndex(columnAsNumber, rowAsNumber)].setBackground(lightRed);
      }
    }
    System.out.println(currentGameStateTemp);
    return legalMoveLabels;
  }

}
