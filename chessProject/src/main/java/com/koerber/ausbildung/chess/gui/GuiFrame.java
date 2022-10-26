package com.koerber.ausbildung.chess.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import com.koerber.ausbildung.chess.piece.Piece;
import com.koerber.ausbildung.chess.piece.Rook;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

public class GuiFrame {

  private JFrame              frame;
  private static final String X_LABEL     = "ABCDEFGH";
  Color                       lightBrown  = new Color(205, 133, 63);
  Color                       lightGreen  = new Color(144, 238, 144);
  Color                       lightRed    = new Color(255, 114, 118);
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
    System.out.println(Gui.getIndex(6, 8));
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
    GridBagConstraints gbc_newGamePanel = new GridBagConstraints();
    gbc_newGamePanel.insets = new Insets(0, 0, 5, 5);
    gbc_newGamePanel.fill = GridBagConstraints.BOTH;
    gbc_newGamePanel.gridx = 0;
    gbc_newGamePanel.gridy = 0;
    frame.getContentPane().add(newGamePanel, gbc_newGamePanel);
    newGamePanel.setLayout(new GridLayout(1, 2, 0, 0));

    JButton newGameButton = new JButton("NEW GAME");
    newGamePanel.add(newGameButton);

    JButton backButton = new JButton("BACK");
    newGamePanel.add(backButton);

    JLabel historyLabel = new JLabel("HISTORY");
    GridBagConstraints gbc_historyLabel = new GridBagConstraints();
    gbc_historyLabel.insets = new Insets(0, 0, 5, 5);
    gbc_historyLabel.gridx = 0;
    gbc_historyLabel.gridy = 1;
    frame.getContentPane().add(historyLabel, gbc_historyLabel);

    // Builds top part of history side
    JPanel historyPanelTop = new JPanel();
    GridBagConstraints gbc_historyPanelTop = new GridBagConstraints();
    gbc_historyPanelTop.insets = new Insets(0, 0, 5, 5);
    gbc_historyPanelTop.fill = GridBagConstraints.BOTH;
    gbc_historyPanelTop.gridx = 0;
    gbc_historyPanelTop.gridy = 2;
    frame.getContentPane().add(historyPanelTop, gbc_historyPanelTop);
    historyPanelTop.setLayout(new GridLayout(0, 2, 0, 0));

    JButton backwardsInHistoryButton = new JButton("BACK");
    historyPanelTop.add(backwardsInHistoryButton);

    JButton forwardsInHistoryButton = new JButton("FORWARD ");
    historyPanelTop.add(forwardsInHistoryButton);

    // Builds bottom part of history side
    JPanel historyPanelBot = new JPanel();
    GridBagConstraints gbc_historyPanelBot = new GridBagConstraints();
    gbc_historyPanelBot.insets = new Insets(0, 0, 5, 5);
    gbc_historyPanelBot.fill = GridBagConstraints.BOTH;
    gbc_historyPanelBot.gridx = 0;
    gbc_historyPanelBot.gridy = 3;

    frame.getContentPane().add(historyPanelBot, gbc_historyPanelBot);
    historyPanelBot.setLayout(new GridLayout(0, 1, 0, 0));

    JButton currentGameButton = new JButton("CURRENT GAME");
    historyPanelBot.add(currentGameButton);

    // Build Scroll pane for displaying history entries
    JScrollPane historyScrollPane = new JScrollPane();
    GridBagConstraints gbc_historyScrollPane = new GridBagConstraints();
    gbc_historyScrollPane.gridheight = 6;
    gbc_historyScrollPane.insets = new Insets(0, 0, 5, 5);
    gbc_historyScrollPane.fill = GridBagConstraints.BOTH;
    gbc_historyScrollPane.gridx = 0;
    gbc_historyScrollPane.gridy = 4;
    frame.getContentPane().add(historyScrollPane, gbc_historyScrollPane);

    // Labels x-axis of chess board (12345678)
    JPanel labelYPanel = new JPanel();
    GridBagConstraints gbc_labelYPanel = new GridBagConstraints();
    gbc_labelYPanel.anchor = GridBagConstraints.EAST;
    gbc_labelYPanel.gridheight = 8;
    gbc_labelYPanel.insets = new Insets(0, 0, 5, 5);
    gbc_labelYPanel.fill = GridBagConstraints.VERTICAL;
    gbc_labelYPanel.gridx = 1;
    gbc_labelYPanel.gridy = 1;
    frame.getContentPane().add(labelYPanel, gbc_labelYPanel);
    labelYPanel.setLayout(new GridLayout(8, 0, 0, 0));
    for(int i = 0; i < 8; i++) {
      labelYPanel.add(new JLabel("" + (i + 1)), SwingConstants.CENTER);
    }

    JPanel chessBoardBottomLayer = new JPanel();
    chessBoardBottomLayer.setBounds(0, 0, 896, 896);
    GridBagConstraints gbc_chessBoardBottomLayer = new GridBagConstraints();
    gbc_chessBoardBottomLayer.gridheight = 8;
    gbc_chessBoardBottomLayer.insets = new Insets(0, 0, 5, 0);
    gbc_chessBoardBottomLayer.fill = GridBagConstraints.BOTH;
    gbc_chessBoardBottomLayer.gridx = 2;
    gbc_chessBoardBottomLayer.gridy = 1;
    frame.getContentPane().add(chessBoardBottomLayer, gbc_chessBoardBottomLayer);
    chessBoardBottomLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardBottomLayer.setBorder(new LineBorder(Color.BLACK));

    // Add Labels and colors them like a chess board
    JLabel[] labels = new JLabel[65];
    for(int i = 1; i < 65; i++) {
      labels[i] = new JLabel();
      labels[i].setText("label" + i);
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
    GridBagConstraints gbc_chessBoardMiddleLayer = new GridBagConstraints();
    gbc_chessBoardMiddleLayer.gridheight = 8;
    gbc_chessBoardMiddleLayer.insets = new Insets(0, 0, 5, 0);
    gbc_chessBoardMiddleLayer.fill = GridBagConstraints.BOTH;
    gbc_chessBoardMiddleLayer.gridx = 2;
    gbc_chessBoardMiddleLayer.gridy = 1;
    // frame.getContentPane().add(chessBoardLegalMoveMapLayer,
    // gbc_chessBoardLegalMoveMapLayer);
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
    GridBagConstraints gbc_chessBoardTopLayer = new GridBagConstraints();
    gbc_chessBoardTopLayer.gridheight = 8;
    gbc_chessBoardTopLayer.insets = new Insets(0, 0, 5, 0);
    gbc_chessBoardTopLayer.fill = GridBagConstraints.BOTH;
    gbc_chessBoardTopLayer.gridx = 2;
    gbc_chessBoardTopLayer.gridy = 1;
    // frame.getContentPane().add(chessBoardLegalMoveMapLayer,
    // gbc_chessBoardLegalMoveMapLayer);
    chessBoardTopLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardTopLayer.setBorder(new LineBorder(Color.BLACK));

    JLayeredPane layeredPane = new JLayeredPane();
    GridBagConstraints gbc_layeredPane = new GridBagConstraints();
    layeredPane.setBounds(0, 0, 896, 896);
    gbc_layeredPane.gridheight = 8;
    gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
    gbc_layeredPane.fill = GridBagConstraints.BOTH;
    gbc_layeredPane.gridx = 2;
    gbc_layeredPane.gridy = 1;
    frame.getContentPane().add(layeredPane, gbc_layeredPane);
    layeredPane.add(chessBoardBottomLayer, Integer.valueOf(0));
    layeredPane.add(chessBoardMiddleLayer, Integer.valueOf(1));
    layeredPane.add(chessBoardTopLayer, Integer.valueOf(2));

    // Labels x-axis of chess board (ABCDEFGH)
    JPanel labelXPanel = new JPanel();
    GridBagConstraints gbc_labelXPanel = new GridBagConstraints();
    gbc_labelXPanel.insets = new Insets(0, 0, 5, 0);
    gbc_labelXPanel.fill = GridBagConstraints.BOTH;
    gbc_labelXPanel.gridx = 2;
    gbc_labelXPanel.gridy = 9;
    frame.getContentPane().add(labelXPanel, gbc_labelXPanel);
    labelXPanel.setLayout(new GridLayout(0, 8, 0, 0));
    JLabel[] xLabels = new JLabel[8];
    for(int i = 0; i < 8; i++) {
      xLabels[i] = new JLabel(X_LABEL.substring(i, i + 1));
      xLabels[i].setVerticalAlignment(SwingConstants.CENTER);
      xLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
      labelXPanel.add(xLabels[i]);
    }

    JLabel scoreLabel = new JLabel("SCORE");
    GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
    gbc_scoreLabel.insets = new Insets(0, 0, 5, 0);
    gbc_scoreLabel.gridx = 3;
    gbc_scoreLabel.gridy = 1;
    frame.getContentPane().add(scoreLabel, gbc_scoreLabel);

    // Label for Player 1 (top)
    JLabel player1Label = new JLabel("PLAYER 1");
    // TODO set Name (PLAYER 1 = DEFAULT NAME)
    player1Label.setOpaque(true);
    player1Label.setBackground(Color.white);
    GridBagConstraints gbc_player1Label = new GridBagConstraints();
    gbc_player1Label.insets = new Insets(0, 0, 5, 0);
    gbc_player1Label.gridx = 3;
    gbc_player1Label.gridy = 2;
    frame.getContentPane().add(player1Label, gbc_player1Label);

    JPanel player1Panel = new JPanel();
    GridBagConstraints gbc_player1Panel = new GridBagConstraints();
    gbc_player1Panel.insets = new Insets(0, 0, 5, 5);
    gbc_player1Panel.fill = GridBagConstraints.BOTH;
    gbc_player1Panel.gridx = 3;
    gbc_player1Panel.gridy = 3;
    frame.getContentPane().add(player1Panel, gbc_player1Panel);
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
    GridBagConstraints gbc_piecesP1Label = new GridBagConstraints();
    gbc_piecesP1Label.insets = new Insets(0, 0, 5, 0);
    gbc_piecesP1Label.gridx = 3;
    gbc_piecesP1Label.gridy = 4;
    frame.getContentPane().add(piecesP1Label, gbc_piecesP1Label);

    JPanel piecesP1Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbc_piecesP1Panel = new GridBagConstraints();
    gbc_piecesP1Panel.insets = new Insets(0, 0, 5, 5);
    gbc_piecesP1Panel.fill = GridBagConstraints.BOTH;
    gbc_piecesP1Panel.gridx = 3;
    gbc_piecesP1Panel.gridy = 5;
    frame.getContentPane().add(piecesP1Panel, gbc_piecesP1Panel);
    piecesP1Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP1FillerLabel = new JLabel("Filler");
    piecesP1Panel.add(piecesP1FillerLabel);

    // Label for Player 2 (bottom)
    JLabel player2Label = new JLabel("PLAYER 2");
    // TODO set Name (PLAYER 2 = DEFAULT NAME)
    player2Label.setOpaque(true);
    player2Label.setBackground(Color.white);
    GridBagConstraints gbc_player2Label = new GridBagConstraints();
    gbc_player2Label.insets = new Insets(0, 0, 5, 0);
    gbc_player2Label.gridx = 3;
    gbc_player2Label.gridy = 6;
    frame.getContentPane().add(player2Label, gbc_player2Label);

    JPanel player2Panel = new JPanel();
    GridBagConstraints gbc_player2Panel = new GridBagConstraints();
    gbc_player2Panel.insets = new Insets(0, 0, 5, 5);
    gbc_player2Panel.fill = GridBagConstraints.BOTH;
    gbc_player2Panel.gridx = 3;
    gbc_player2Panel.gridy = 7;
    frame.getContentPane().add(player2Panel, gbc_player2Panel);
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
    GridBagConstraints gbc_piecesP2Label = new GridBagConstraints();
    gbc_piecesP2Label.insets = new Insets(0, 0, 5, 0);
    gbc_piecesP2Label.gridx = 3;
    gbc_piecesP2Label.gridy = 8;
    frame.getContentPane().add(piecesP2Label, gbc_piecesP2Label);

    // Panel to show taken Pieces
    JPanel piecesP2Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbc_piecesP2Panel = new GridBagConstraints();
    gbc_piecesP2Panel.insets = new Insets(0, 0, 5, 5);
    gbc_piecesP2Panel.fill = GridBagConstraints.BOTH;
    gbc_piecesP2Panel.gridx = 3;
    gbc_piecesP2Panel.gridy = 9;
    frame.getContentPane().add(piecesP2Panel, gbc_piecesP2Panel);
    piecesP2Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP2FillerLabel = new JLabel("Filler");
    piecesP2Panel.add(piecesP2FillerLabel);
  }

  /**
   * Colors the fields according to the {@code Piece.legalMoveMap} green (may
   * move), red (hit) or not at all (may not move).
   */
  public JLabel[] highlightLegalMove() {
    // JLabel[] topLabels = new JLabel[64];
    //// for(int i = 0; i < 64; i++) {
    //// topLabels[i] = new JLabel("");
    //// if(i == 63) {
    //// topLabels[i] = new JLabel();
    //// topLabels[i].setOpaque(true);
    //// topLabels[i].setBackground(lightGreen);
    //// topLabels[i].setText("Test");
    //// }
    //// }
    //// return topLabels;
    Rook tempRook = ObjectFactoryForTest.getRook();
    Map<String, Piece> currentGameStateTemp = new TreeMap<String, Piece>();
    for(int i = Field.LEFT_BOUND; i <= Field.RIGHT_BOUND; i++) {
      for(int j = Field.LOWER_BOUND; j <= Field.UPPER_BOUND; j++) {
        currentGameStateTemp.put(Character.toString(i) + String.valueOf(j), new EmptyPiece());
      }
    }
    currentGameStateTemp.put("A1", tempRook);
    try {
      tempRook.createLegalMoveMap(currentGameStateTemp);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    Map<String, String> legalMoveMapTemp = new TreeMap<>(tempRook.getLegalMoveMap());
    System.out.println(legalMoveMapTemp);
    JLabel[] legalMoveLabels = new JLabel[legalMoveMapTemp.size()];
    // get numeric Value
    int i = 0;
    for(Map.Entry<String, String> entry : legalMoveMapTemp.entrySet()) {
      legalMoveLabels[i] = new JLabel("");
      if(i == 13) {
        legalMoveLabels[i] = new JLabel();
        legalMoveLabels[i].setOpaque(true);
        legalMoveLabels[i].setBackground(lightGreen);
        legalMoveLabels[i].setText("Test");
      }
      i++;
    }
    return legalMoveLabels;
  }

}
