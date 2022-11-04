package com.koerber.ausbildung.chess.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GuiFrame {

  private JFrame                 frame;
  private static final String    X_LABEL      = "ABCDEFGH";
  public static final Color      LIGHT_BROWN  = new Color(205, 133, 63);
  public static final Color      LIGHT_GREEN  = new Color(144, 238, 144, 127);
  public static final Color      LIGHT_RED    = new Color(255, 75, 75, 127);
  public static final LineBorder BLACK_BORDER = new LineBorder(Color.BLACK);
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
    Container contentPane = frame.getContentPane();

    frame.setBounds(0, 0, 1920, 1080);
    // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{499, 32, 896, 499};
    gridBagLayout.rowHeights = new int[]{108, 108, 108, 108, 108, 108, 108, 108, 108, 32};
    gridBagLayout.columnWeights = new double[]{1.0, 1.0};
    gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    contentPane.setLayout(gridBagLayout);

    // Builds New Game Panel and Buttons
    JPanel newGamePanel = new JPanel();
    GridBagConstraints gbcNewGamePanel = GuiUtility.setGridBag(true, true, 0, 0, 1);
    contentPane.add(newGamePanel, gbcNewGamePanel);
    newGamePanel.setLayout(new GridLayout(1, 2, 0, 0));

    JButton newGameButton = new JButton("NEW GAME");
    newGamePanel.add(newGameButton);

    JButton backButton = new JButton("BACK");
    newGamePanel.add(backButton);
    JLabel historyLabel = new JLabel("HISTORY");
    GridBagConstraints gbcHistoryPanel = GuiUtility.setGridBag(true, false, 0, 1, 1);
    contentPane.add(historyLabel, gbcHistoryPanel);

    // Builds top part of history side
    JPanel historyPanelTop = new JPanel();
    GridBagConstraints gbcHistoryPanelTop = GuiUtility.setGridBag(true, true, 0, 2, 1);
    contentPane.add(historyPanelTop, gbcHistoryPanelTop);
    historyPanelTop.setLayout(new GridLayout(0, 2, 0, 0));

    JButton backwardsInHistoryButton = new JButton("BACK");
    historyPanelTop.add(backwardsInHistoryButton);

    JButton forwardsInHistoryButton = new JButton("FORWARD ");
    historyPanelTop.add(forwardsInHistoryButton);

    // Builds bottom part of history side
    JPanel historyPanelBot = new JPanel();
    GridBagConstraints gbcHistoryPanelBot = GuiUtility.setGridBag(true, true, 0, 3, 1);
    contentPane.add(historyPanelBot, gbcHistoryPanelBot);
    historyPanelBot.setLayout(new GridLayout(0, 1, 0, 0));

    JButton currentGameButton = new JButton("CURRENT GAME");
    historyPanelBot.add(currentGameButton);

    // Build Scroll pane for displaying history entries
    JScrollPane historyScrollPane = new JScrollPane();
    GridBagConstraints gbcHistoryScrollPane = GuiUtility.setGridBag(true, true, 0, 4, 6);
    contentPane.add(historyScrollPane, gbcHistoryScrollPane);

    // Labels x-axis of chess board (12345678)
    JPanel labelYPanel = new JPanel();
    GridBagConstraints gbcLabelYPanel = GuiUtility.setGridBag(true, false, 1, 1, 8);
    gbcLabelYPanel.anchor = GridBagConstraints.EAST;
    gbcLabelYPanel.fill = GridBagConstraints.VERTICAL;
    contentPane.add(labelYPanel, gbcLabelYPanel);
    labelYPanel.setLayout(new GridLayout(8, 0, 0, 0));
    for(int i = 0; i < 8; i++) {
      labelYPanel.add(new JLabel("" + (i + 1)), SwingConstants.CENTER);
    }

    /**
     * Initializes bottom layer. Bottom layer only displays chess tiles.
     */
    JPanel chessBoardBottomLayer = new JPanel();
    chessBoardBottomLayer.setBounds(0, 0, 896, 896);
    chessBoardBottomLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardBottomLayer.setBorder(new LineBorder(Color.BLACK));

    // Add Labels and colors them like a chess board
    JLabel[] labels = new JLabel[65];
    for(int i = 1; i < 65; i++) {
      labels[i] = new JLabel();
      labels[i].setOpaque(true);
      if((i > 0 && i <= 8) || (i > 16 && i <= 24) || (i > 32 && i <= 40) || (i > 48 && i <= 56)) {
        if(i % 2 == 0) {
          labels[i].setBackground(LIGHT_BROWN);
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
          labels[i].setBackground(LIGHT_BROWN);
        }
      }
      chessBoardBottomLayer.add(labels[i]);
    }

    /**
     * Initializes middle layer. Middle layer displays legalMoveMap of the
     * selected piece.
     */
    JPanel chessBoardMiddleLayer = new JPanel();
    chessBoardMiddleLayer.setOpaque(false);
    chessBoardMiddleLayer.setBounds(0, 0, 896, 896);
    chessBoardMiddleLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardMiddleLayer.setBorder(new LineBorder(Color.BLACK));

    // Calls highlightLegalMove and adds build labels to middle Layer
    JLabel[] legalMoveLabels = Gui.highlightLegalMove();
    for(int i = 0; i < legalMoveLabels.length; i++) {
      chessBoardMiddleLayer.add(legalMoveLabels[i]);
    }

    /**
     * Initializes top layer. Top layer displays currentGameState (images) and
     * acts as interactive part of the chessboard for players.
     */
    JPanel chessBoardTopLayer = new JPanel();
    chessBoardTopLayer.setOpaque(false);
    chessBoardTopLayer.setBounds(0, 0, 896, 896);
    chessBoardTopLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardTopLayer.setBorder(new LineBorder(Color.BLACK));

    JLabel[] currentGameStateLabels = Gui.showCurrentGameState();
    for(int i = 0; i < currentGameStateLabels.length; i++) {
      chessBoardTopLayer.add(currentGameStateLabels[i]);
    }

    /**
     * Initializes layeredPan. Used for layering the chessboard.
     */
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setBounds(0, 0, 896, 896);
    GridBagConstraints gbcLayeredPane = GuiUtility.setGridBag(true, true, 2, 1, 8);
    contentPane.add(layeredPane, gbcLayeredPane);
    layeredPane.add(chessBoardBottomLayer, Integer.valueOf(0));
    layeredPane.add(chessBoardMiddleLayer, Integer.valueOf(1));
    layeredPane.add(chessBoardTopLayer, Integer.valueOf(2));

    // Labels x-axis of chess board (ABCDEFGH)
    JPanel labelXPanel = new JPanel();
    GridBagConstraints gbcLabelXPanel = GuiUtility.setGridBag(false, true, 2, 9, 1);
    contentPane.add(labelXPanel, gbcLabelXPanel);
    labelXPanel.setLayout(new GridLayout(0, 8, 0, 0));
    JLabel[] xLabels = new JLabel[8];
    for(int i = 0; i < 8; i++) {
      xLabels[i] = new JLabel(X_LABEL.substring(i, i + 1));
      xLabels[i].setVerticalAlignment(SwingConstants.CENTER);
      xLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
      labelXPanel.add(xLabels[i]);
    }

    // SCORE Label
    JLabel scoreLabel = new JLabel("SCORE");
    GridBagConstraints gbcScoreLabel = GuiUtility.setGridBag(false, false, 3, 1, 1);
    contentPane.add(scoreLabel, gbcScoreLabel);

    // Player 1 label (top)
    JLabel player1Label = new JLabel("PLAYER 1");
    // TODO set Name (PLAYER 1 = DEFAULT NAME)
    player1Label.setOpaque(true);
    player1Label.setBackground(Color.white);
    GridBagConstraints gbcPlayer1Label = GuiUtility.setGridBag(false, false, 3, 2, 1);
    contentPane.add(player1Label, gbcPlayer1Label);

    // Player 1 panel
    JPanel player1Panel = new JPanel();
    GridBagConstraints gbcPlayer1Panel = GuiUtility.setGridBag(true, true, 3, 3, 1);
    contentPane.add(player1Panel, gbcPlayer1Panel);
    player1Panel.setLayout(new GridLayout(2, 2, 0, 0));

    // Player 1 table
    JLabel colorP1Label = new JLabel("COLOR");
    colorP1Label.setBorder(BLACK_BORDER);
    player1Panel.add(colorP1Label);
    JLabel colorP1Label2 = new JLabel("WHITE");
    colorP1Label2.setBorder(BLACK_BORDER);
    player1Panel.add(colorP1Label2);
    JLabel pointsP1Label = new JLabel("POINTS");
    pointsP1Label.setBorder(BLACK_BORDER);
    player1Panel.add(pointsP1Label);
    JLabel setPointsP1Label = new JLabel("FILLER");
    setPointsP1Label.setBorder(BLACK_BORDER);
    // TODO Display Points Player 1
    player1Panel.add(setPointsP1Label);

    // Player 1 taken pieces label
    JLabel piecesP1Label = new JLabel("PIECES");
    GridBagConstraints gbcPiecesP1Label = GuiUtility.setGridBag(false, false, 3, 4, 1);
    contentPane.add(piecesP1Label, gbcPiecesP1Label);

    // Player 1 taken pieces panel
    JPanel piecesP1Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcPiecesP1Panel = GuiUtility.setGridBag(true, true, 3, 5, 1);
    contentPane.add(piecesP1Panel, gbcPiecesP1Panel);
    piecesP1Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP1FillerLabel = new JLabel("Filler");
    piecesP1Panel.add(piecesP1FillerLabel);

    // Player 2 label (bottom)
    JLabel player2Label = new JLabel("PLAYER 2");
    // TODO set Name (PLAYER 2 = DEFAULT NAME)
    player2Label.setOpaque(true);
    player2Label.setBackground(Color.white);
    GridBagConstraints gbcPlayer2Label = GuiUtility.setGridBag(false, false, 3, 6, 1);
    contentPane.add(player2Label, gbcPlayer2Label);

    // Player 2 panel
    JPanel player2Panel = new JPanel();
    GridBagConstraints gbcPlayer2Panel = GuiUtility.setGridBag(true, true, 3, 7, 1);
    contentPane.add(player2Panel, gbcPlayer2Panel);
    player2Panel.setLayout(new GridLayout(2, 2, 0, 0));

    // Player 2 table
    JLabel colorP2Label = new JLabel("COLOR");
    colorP2Label.setBorder(BLACK_BORDER);
    player2Panel.add(colorP2Label);
    JLabel colorP2Label2 = new JLabel("BLACK");
    colorP2Label2.setBorder(BLACK_BORDER);
    player2Panel.add(colorP2Label2);
    JLabel pointsP2Label = new JLabel("POINTS");
    pointsP2Label.setBorder(BLACK_BORDER);
    player2Panel.add(pointsP2Label);
    JLabel setPointsP2Label = new JLabel("FILLER");
    setPointsP2Label.setBorder(BLACK_BORDER);
    // TODO Display Points Player 2
    player2Panel.add(setPointsP2Label);

    // Player 2 taken pieces label
    JLabel piecesP2Label = new JLabel("PIECES");
    GridBagConstraints gbcPiecesP2Label = GuiUtility.setGridBag(false, false, 3, 8, 1);
    contentPane.add(piecesP2Label, gbcPiecesP2Label);

    // Player 2 taken pieces panel
    JPanel piecesP2Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcPiecesP2Panel = GuiUtility.setGridBag(true, true, 3, 9, 1);
    contentPane.add(piecesP2Panel, gbcPiecesP2Panel);
    piecesP2Panel.setLayout(new GridLayout(2, 2, 0, 0));
    JLabel piecesP2FillerLabel = new JLabel("Filler");
    piecesP2Panel.add(piecesP2FillerLabel);
  }

}
