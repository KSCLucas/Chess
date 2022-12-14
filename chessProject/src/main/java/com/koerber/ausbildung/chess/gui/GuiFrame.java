package com.koerber.ausbildung.chess.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.koerber.ausbildung.chess.Field;
import com.koerber.ausbildung.chess.History;
import com.koerber.ausbildung.chess.Player;
import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.Converter;
import com.koerber.ausbildung.chess.utility.IconSupplier;

public class GuiFrame {

  public static final String     X_LABEL                = "ABCDEFGH";
  public static final Color      LIGHT_BROWN            = new Color(205, 133, 63);
  public static final Color      LIGHT_GREEN            = new Color(144, 238, 144, 127);
  public static final Color      LIGHT_RED              = new Color(255, 75, 75, 127);
  public static final LineBorder BLACK_BORDER           = new LineBorder(Color.BLACK);
  private JFrame                 frame;
  private JLabel[]               currentGameStateLabels = new JLabel[64];
  private JLabel[]               legalMoveLabels        = new JLabel[64];
  private JList<String>          historyJList           = new JList<String>();
  private JLabel                 player1Label;
  private JLabel                 player2Label;

  private JLabel                 player1ScoreLabel;

  private JLabel                 player2ScoreLabel;

  private JLabel[]               player1TakenPiecesLabels;
  private JLabel[]               player2TakenPiecesLabels;

  public GuiFrame(Field field, History history, Player player1, Player player2) {
    initialize(field, history, player1, player2);
  }

  public JLabel[] getCurrentGameStateLabels() {
    return currentGameStateLabels;
  }

  public void setCurrentGameStateLabels(JLabel[] currentGameStateLabels) {
    this.currentGameStateLabels = currentGameStateLabels;
  }

  public JLabel[] getLegalMoveLabels() {
    return legalMoveLabels;
  }

  public void setLegalMoveLabels(JLabel[] legalMoveLabels) {
    this.legalMoveLabels = legalMoveLabels;
  }

  public JList<String> getHistoryJList() {
    return historyJList;
  }

  public void setHistoryJList(JList<String> historyJList) {
    this.historyJList = historyJList;
  }

  public JLabel getPlayer1Label() {
    return player1Label;
  }

  public void setPlayer1Label(JLabel player1Label) {
    this.player1Label = player1Label;
  }

  public JLabel getPlayer2Label() {
    return player2Label;
  }

  public void setPlayer2Label(JLabel player2Label) {
    this.player2Label = player2Label;
  }

  public JLabel getPlayer1ScoreLabel() {
    return player1ScoreLabel;
  }

  public void setPlayer1ScoreLabel(JLabel player1ScoreLabel) {
    this.player1ScoreLabel = player1ScoreLabel;
  }

  public JLabel getPlayer2ScoreLabel() {
    return player2ScoreLabel;
  }

  public void setPlayer2ScoreLabel(JLabel player2ScoreLabel) {
    this.player2ScoreLabel = player2ScoreLabel;
  }

  public JLabel[] getPlayer1TakenPiecesLabels() {
    return player1TakenPiecesLabels;
  }

  public void setPlayer1TakenPiecesLabels(JLabel[] player1TakenPiecesLabels) {
    this.player1TakenPiecesLabels = player1TakenPiecesLabels;
  }

  public JLabel[] getPlayer2TakenPiecesLabels() {
    return player2TakenPiecesLabels;
  }

  public void setPlayer2TakenPiecesLabels(JLabel[] player2TakenPiecesLabels) {
    this.player2TakenPiecesLabels = player2TakenPiecesLabels;
  }

  public JFrame getFrame() {
    return frame;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }

  /**
   * Initialize the contents of the frame
   * 
   * @param field
   * @param history
   */
  private void initialize(Field field, History history, Player player1, Player player2) {

    // builds frame and sets basic information, sets gridbaglayout
    frame = new JFrame();
    Container contentPane = frame.getContentPane();
    frame.setBounds(0, 0, 1920, 1080);
    frame.setTitle("CHESS");
    frame.setIconImage(IconSupplier.getIcon(null, "chess_logo").getImage());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{499, 32, 908, 450};
    gridBagLayout.rowHeights = new int[]{50, 112, 112, 112, 112, 112, 112, 112, 112, 32};
    gridBagLayout.columnWeights = new double[]{1.0, 1.0};
    gridBagLayout.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
    contentPane.setLayout(gridBagLayout);

    // builds new game panel
    JPanel newGamePanel = new JPanel();
    GridBagConstraints gbcNewGamePanel = setGridBag(true, true, 0, 0, 1);
    contentPane.add(newGamePanel, gbcNewGamePanel);
    newGamePanel.setLayout(new GridLayout(1, 2, 0, 0));

    createNewGameButtonWithActionListener(field, history, newGamePanel, player1, player2);

    // createBackButtonWithActionListener(field, history, newGamePanel);

    JLabel historyLabel = new JLabel("HISTORY");
    GridBagConstraints gbcHistoryPanel = setGridBag(true, false, 0, 1, 1);
    contentPane.add(historyLabel, gbcHistoryPanel);

    // Builds top part of history side
    JPanel historyPanelTop = new JPanel();
    GridBagConstraints gbcHistoryPanelTop = setGridBag(true, true, 0, 2, 1);
    contentPane.add(historyPanelTop, gbcHistoryPanelTop);
    historyPanelTop.setLayout(new GridLayout(0, 2, 0, 0));

    createBackInHistoryButtonWithActionListener(history, historyPanelTop);

    createForwardsInHistoryButtonWithActionListener(field, history, historyPanelTop);

    // builds bottom part of history side
    JPanel historyPanelBot = new JPanel();
    GridBagConstraints gbcHistoryPanelBot = setGridBag(true, true, 0, 3, 1);
    contentPane.add(historyPanelBot, gbcHistoryPanelBot);
    historyPanelBot.setLayout(new GridLayout(0, 1, 0, 0));

    createCurrentGameButtonWithActionListener(field, historyPanelBot);

    // Build Scroll pane for displaying history entries with list selection
    // listener
    JScrollPane historyScrollPane = new JScrollPane();
    GridBagConstraints gbcHistoryScrollPane = setGridBag(true, true, 0, 4, 6);
    createListSelectionListenerForHistory(history);
    historyScrollPane.getViewport().setView(historyJList);
    historyScrollPane.setOpaque(true);
    contentPane.add(historyScrollPane, gbcHistoryScrollPane);
    contentPane.validate();
    contentPane.repaint();

    // Labels y-axis of chess board (12345678)
    JPanel labelYPanel = new JPanel();
    GridBagConstraints gbcLabelYPanel = setGridBag(true, false, 1, 1, 8);
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
    chessBoardBottomLayer.setBounds(0, 0, 898, 934);
    chessBoardBottomLayer.setLayout(new GridLayout(8, 8, 0, 0));
    createChessboardLabels(chessBoardBottomLayer);

    /**
     * Initializes middle layer. Middle layer displays legalMoveMap of the
     * selected piece.
     */
    JPanel chessBoardMiddleLayer = new JPanel();
    chessBoardMiddleLayer.setOpaque(false);
    chessBoardMiddleLayer.setBounds(0, 0, 898, 934);
    chessBoardMiddleLayer.setLayout(new GridLayout(8, 8, 0, 0));
    // initialize legalMoveLabel array with empty labels
    for(int i = 0; i < 64; i++) {
      legalMoveLabels[i] = new JLabel();
      legalMoveLabels[i].setText("");
      chessBoardMiddleLayer.add(legalMoveLabels[i]);
    }

    /**
     * Initializes top layer. Top layer displays currentGameState (images) and
     * acts as interactive part of the chessboard for players.
     */
    JPanel chessBoardTopLayer = new JPanel();
    chessBoardTopLayer.setOpaque(false);
    chessBoardTopLayer.setBounds(0, 0, 898, 934);
    chessBoardTopLayer.setLayout(new GridLayout(8, 8, 0, 0));
    chessBoardTopLayer.setBorder(BLACK_BORDER);
    MouseListener musMusculus = createMouseListenerForTopLayer(field, history);
    // initializes currentGameStateLabels array (filled with labels named A1 to
    // H8) and adds mouselistener to it
    createDragAndDropLabels(musMusculus);
    GuiUtility.showCurrentGameState(field.getCurrentGameState(), getCurrentGameStateLabels());
    JPanel[] topLayerPanels = createDragAndDropPanels(chessBoardTopLayer);
    addDragAndDropLogicToPanels(field, history, topLayerPanels, player1, player2);

    /**
     * Initializes layeredPan. Used for layering the chessboard.
     */
    JLayeredPane layeredPane = new JLayeredPane();
    layeredPane.setBounds(0, 0, 908, 908);
    GridBagConstraints gbcLayeredPane = setGridBag(true, true, 2, 1, 8);
    contentPane.add(layeredPane, gbcLayeredPane);
    layeredPane.add(chessBoardBottomLayer, Integer.valueOf(0));
    layeredPane.add(chessBoardMiddleLayer, Integer.valueOf(1));
    layeredPane.add(chessBoardTopLayer, Integer.valueOf(2));

    // Labels x-axis of chess board (ABCDEFGH)
    JPanel labelXPanel = new JPanel();
    GridBagConstraints gbcLabelXPanel = setGridBag(false, true, 2, 9, 1);
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
    JLabel scoreLabel = new JLabel("");
    scoreLabel.setFont(new Font(null, Font.BOLD, 20));
    GridBagConstraints gbcScoreLabel = setGridBag(false, false, 3, 0, 1);
    contentPane.add(scoreLabel, gbcScoreLabel);

    // Player 2 label (top)
    int playerNameFontSize = 20;
    player2Label = new JLabel(player2.getName(), JLabel.CENTER);
    player2Label.setOpaque(true);
    player2Label.setForeground(Color.white);
    player2Label.setBackground(Color.black);
    player2Label.setFont(new Font(null, Font.PLAIN, playerNameFontSize));
    GridBagConstraints gbcPlayer2Label = setGridBag(false, true, 3, 1, 1);
    contentPane.add(player2Label, gbcPlayer2Label);

    // Player 2 panel
    JPanel player2Panel = new JPanel();
    GridBagConstraints gbcPlayer2Panel = setGridBag(true, true, 3, 2, 1);
    contentPane.add(player2Panel, gbcPlayer2Panel);
    player2Panel.setLayout(new GridLayout(2, 2, 0, 0));

    // Player 2 table
    JLabel colorP2Label = new JLabel("COLOR", JLabel.CENTER);
    colorP2Label.setBorder(BLACK_BORDER);
    player2Panel.add(colorP2Label);
    JLabel colorP2Label2 = new JLabel("BLACK", JLabel.CENTER);
    colorP2Label2.setBorder(BLACK_BORDER);
    player2Panel.add(colorP2Label2);
    JLabel pointsP2Label = new JLabel("POINTS", JLabel.CENTER);
    pointsP2Label.setBorder(BLACK_BORDER);
    player2Panel.add(pointsP2Label);
    player2ScoreLabel = new JLabel("0", JLabel.CENTER);
    player2ScoreLabel.setBorder(BLACK_BORDER);
    player2Panel.add(player2ScoreLabel);

    // Player 2 taken pieces label
    JLabel piecesP2Label = new JLabel("PIECES", JLabel.CENTER);
    GridBagConstraints gbcPiecesP2Label = setGridBag(false, false, 3, 3, 1);
    contentPane.add(piecesP2Label, gbcPiecesP2Label);

    // Player 2 taken pieces panel
    JPanel piecesP2Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcPiecesP2Panel = setGridBag(true, true, 3, 4, 1);
    contentPane.add(piecesP2Panel, gbcPiecesP2Panel);
    piecesP2Panel.setLayout(new GridLayout(2, 2, 0, 0));
    piecesP2Panel.setLayout(new GridLayout(2, 8));
    player2TakenPiecesLabels = new JLabel[16];
    for(int i = 0; i < 16; i++) {
      player2TakenPiecesLabels[i] = new JLabel();
      piecesP2Panel.add(player2TakenPiecesLabels[i]);
    }

    // Player 1 label (bottom)
    player1Label = new JLabel(player1.getName(), JLabel.CENTER);
    player1Label.setOpaque(true);
    player1Label.setBackground(Color.white);
    player1Label.setFont(new Font(null, Font.PLAIN, playerNameFontSize));
    GridBagConstraints gbcPlayer1Label = setGridBag(false, true, 3, 5, 1);
    contentPane.add(player1Label, gbcPlayer1Label);

    // Player 1 panel
    JPanel player1Panel = new JPanel();
    GridBagConstraints gbcPlayer1Panel = setGridBag(true, true, 3, 6, 1);
    contentPane.add(player1Panel, gbcPlayer1Panel);
    player1Panel.setLayout(new GridLayout(2, 2, 0, 0));

    // Player 1 table
    JLabel colorP1Label = new JLabel("COLOR", JLabel.CENTER);
    colorP1Label.setBorder(BLACK_BORDER);
    player1Panel.add(colorP1Label);
    JLabel colorP1Label2 = new JLabel("WHITE", JLabel.CENTER);
    colorP1Label2.setBorder(BLACK_BORDER);
    player1Panel.add(colorP1Label2);
    JLabel pointsP1Label = new JLabel("POINTS", JLabel.CENTER);
    pointsP1Label.setBorder(BLACK_BORDER);
    player1Panel.add(pointsP1Label);
    player1ScoreLabel = new JLabel("0", JLabel.CENTER);
    player1ScoreLabel.setBorder(BLACK_BORDER);
    player1Panel.add(player1ScoreLabel);

    // Player 1 taken pieces label
    JLabel piecesP1Label = new JLabel("PIECES");
    GridBagConstraints gbcPiecesP1Label = setGridBag(false, false, 3, 7, 1);
    contentPane.add(piecesP1Label, gbcPiecesP1Label);

    JPanel piecesP1Panel = new JPanel();
    // TODO add piece sprites
    GridBagConstraints gbcPiecesP1Panel = setGridBag(true, true, 3, 8, 1);
    contentPane.add(piecesP1Panel, gbcPiecesP1Panel);
    piecesP1Panel.setLayout(new GridLayout(2, 2, 0, 0));
    piecesP1Panel.setLayout(new GridLayout(2, 8));
    player1TakenPiecesLabels = new JLabel[16];
    for(int i = 0; i < 16; i++) {
      player1TakenPiecesLabels[i] = new JLabel();
      piecesP1Panel.add(player1TakenPiecesLabels[i]);
    }
  }

  /**
   * build new game button and action listener
   * 
   * @param field
   * @param history
   * @param newGamePanel
   */
  private void createNewGameButtonWithActionListener(Field field, History history, JPanel newGamePanel, Player player1,
      Player player2) {
    JButton newGameButton = new JButton("NEW GAME");
    ActionListener newGame = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int whiteName = JOptionPane.showOptionDialog(frame, "RESTART GAME?", "Start New Game",
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
            IconSupplier.getIcon(ChessColour.BLACK, "knight_crying"), null, null);
        if(whiteName == JOptionPane.YES_OPTION) {
          // resets map and turn counter, clears history list and jlist, clears
          // winner
          field.initializeMap();
          field.resetCurrentTurn();
          field.turnLock();
          GuiUtility.clearHistory(history, getHistoryJList());
          highlightActivePlayer(field, getPlayer1Label(), getPlayer2Label());
          GuiUtility.showCurrentGameState(field.getCurrentGameState(), getCurrentGameStateLabels());
          field.setWinner(null);
          player1.setToInit();
          player2.setToInit();
          GuiUtility.showScore(player1ScoreLabel, player1TakenPiecesLabels, player1);
          GuiUtility.showScore(player2ScoreLabel, player2TakenPiecesLabels, player2);
          for(JLabel label : player1TakenPiecesLabels) {
            label.setIcon(null);
          }
          for(JLabel label : player2TakenPiecesLabels) {
            label.setIcon(null);
          }
        }
      }
    };
    newGameButton.addActionListener(newGame);
    newGamePanel.add(newGameButton);
  }

  // /**
  // * builds back button with action listener
  // *
  // * @param field
  // * @param history
  // * @param newGamePanel
  // */
  // private void createBackButtonWithActionListener(Field field, History
  // history, JPanel newGamePanel) {
  // JButton backButton = new JButton("BACK");
  // ActionListener backListener = new ActionListener() {
  // @Override
  // public void actionPerformed(ActionEvent e) {
  // GuiUtility.undoLastTurn(field, history);
  // }
  // };
  // backButton.addActionListener(backListener);
  // newGamePanel.add(backButton);
  // }

  /**
   * builds backwards in history button with action listener
   * 
   * @param history
   * @param historyPanelTop
   */
  private void createBackInHistoryButtonWithActionListener(History history, JPanel historyPanelTop) {
    JButton backwardsInHistoryButton = new JButton("BACK");
    ActionListener backInHistory = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GuiUtility.backwardInHistory(history, GuiFrame.this);
      }
    };
    backwardsInHistoryButton.addActionListener(backInHistory);
    historyPanelTop.add(backwardsInHistoryButton);
  }

  /**
   * builds forwars in history button with action listener
   * 
   * @param field
   * @param history
   * @param historyPanelTop
   */
  private void createForwardsInHistoryButtonWithActionListener(Field field, History history, JPanel historyPanelTop) {
    JButton forwardsInHistoryButton = new JButton("FORWARD ");
    ActionListener forwardInHistory = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GuiUtility.forwardInHistory(field, history, GuiFrame.this);
      }
    };
    forwardsInHistoryButton.addActionListener(forwardInHistory);
    historyPanelTop.add(forwardsInHistoryButton);
  }

  /**
   * builds current game button with action listener
   * 
   * @param field
   * @param historyPanelBot
   */
  private void createCurrentGameButtonWithActionListener(Field field, JPanel historyPanelBot) {
    JButton currentGameButton = new JButton("CURRENT GAME");
    ActionListener jumpToLiveGame = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        GuiUtility.jumptToLiveGame(field.getCurrentGameState(), field, GuiFrame.this);
      }
    };
    currentGameButton.addActionListener(jumpToLiveGame);
    historyPanelBot.add(currentGameButton);
  }

  /**
   * creates list selection listener for history jlist
   * 
   * @param history
   */
  private void createListSelectionListenerForHistory(History history) {
    ListSelectionListener lsl = new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        // shows game state of selected history jlist entry
        GuiUtility.jumpToSelectedFEN(historyJList.getSelectedValue(), history, currentGameStateLabels);
      }
    };
    historyJList.addListSelectionListener(lsl);
  }

  /**
   * Add Labels and colors them like a chess board
   * 
   * @param chessBoardBottomLayer
   */
  private void createChessboardLabels(JPanel chessBoardBottomLayer) {
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
  }

  /**
   * mouse listener to show legal move map of selected piece
   * 
   * @param field
   * @param history
   * @return
   */
  private MouseListener createMouseListenerForTopLayer(Field field, History history) {
    MouseListener musMusculus = new MouseListener() {
      @Override
      // clears legal move labels after mouse release
      public void mouseReleased(MouseEvent e) {
        for(int l = 0; l < 64; l++) {
          legalMoveLabels[l].setBackground(null);
          legalMoveLabels[l].setOpaque(false);
        }
      }

      @Override
      // calculates and shows legal move map of selected piece
      public void mousePressed(MouseEvent e) {
        if((history.getHistoryEntryList().size() - 1) == historyJList.getSelectedIndex()) {
          String position = e.getComponent().getName();
          if(field.getCurrentGameState().get(position) != null) {
            clearLegalMoveMap(getLegalMoveLabels());
            Converter.setStartPosition(position);
            GuiUtility.highlightLegalMove(legalMoveLabels, field.getCurrentGameState().get(position),
                field.getCurrentTurn() % 2 == 0 ? ChessColour.BLACK : ChessColour.WHITE, field);
          }
        }
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseClicked(MouseEvent e) {
      }
    };
    return musMusculus;
  }

  /**
   * creates drag and drop labels for the top layer and add mouse listener to
   * them
   * 
   * @param musMusculus
   */
  private void createDragAndDropLabels(MouseListener musMusculus) {
    for(int i = 0; i < 64; i++) {
      currentGameStateLabels[i] = new JLabel();
      currentGameStateLabels[i].addMouseListener(musMusculus);
      if(i >= 0 && i < 8) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i, i + 1) + 8);
      }
      if(i >= 8 && i < 16) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 8, i - 7) + 7);
      }
      if(i >= 16 && i < 24) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 16, i - 15) + 6);
      }
      if(i >= 24 && i < 32) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 24, i - 23) + 5);
      }
      if(i >= 32 && i < 40) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 32, i - 31) + 4);
      }
      if(i >= 40 && i < 48) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 40, i - 39) + 3);
      }
      if(i >= 48 && i < 56) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 48, i - 47) + 2);
      }
      if(i >= 56 && i < 64) {
        currentGameStateLabels[i].setName(GuiFrame.X_LABEL.substring(i - 56, i - 55) + 1);
      }
    }
  }

  /**
   * builds jpanels to add currentGameStateLabels
   * 
   * @param chessBoardTopLayer
   * @return
   */
  private JPanel[] createDragAndDropPanels(JPanel chessBoardTopLayer) {
    JPanel[] topLayerPanels = new JPanel[64];
    for(int i = 0; i < 64; i++) {
      topLayerPanels[i] = new JPanel();
      topLayerPanels[i].setOpaque(false);
      topLayerPanels[i].setLayout(new GridLayout(1, 1));
      topLayerPanels[i].add(currentGameStateLabels[i], SwingConstants.CENTER);
      if(i >= 0 && i < 8) {
        topLayerPanels[i].setName(X_LABEL.substring(i, i + 1) + 8);
      }
      if(i >= 8 && i < 16) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 8, i - 7) + 7);
      }
      if(i >= 16 && i < 24) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 16, i - 15) + 6);
      }
      if(i >= 24 && i < 32) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 24, i - 23) + 5);
      }
      if(i >= 32 && i < 40) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 32, i - 31) + 4);
      }
      if(i >= 40 && i < 48) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 40, i - 39) + 3);
      }
      if(i >= 48 && i < 56) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 48, i - 47) + 2);
      }
      if(i >= 56 && i < 64) {
        topLayerPanels[i].setName(X_LABEL.substring(i - 56, i - 55) + 1);
      }
      chessBoardTopLayer.add(topLayerPanels[i]);
    }
    return topLayerPanels;
  }

  /**
   * add drag and drop components to jpanels
   * 
   * @param field
   * @param history
   * @param topLayerPanels
   */
  private void addDragAndDropLogicToPanels(Field field, History history, JPanel[] topLayerPanels, Player player1,
      Player player2) {
    for(int j = 0; j < 64; j++) {
      DragGestureListenerPanels dragListenerPanels = new DragGestureListenerPanels();
      DragSource dragSourcePanels = new DragSource();
      dragSourcePanels.createDefaultDragGestureRecognizer(currentGameStateLabels[j], DnDConstants.ACTION_COPY,
          dragListenerPanels);
      new DropTargetListenerPanels(topLayerPanels[j], field, history, this, player1, player2);
      TransferHandler dnd = new TransferHandler() {
        private static final long serialVersionUID = 1L;
        @Override
        public boolean canImport(TransferSupport support) {
          if(!support.isDrop()) {
            return false;
          }
          if(!support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            return false;
          }
          return true;
        }

        @Override
        public boolean importData(TransferSupport support) {
          if(!canImport(support)) {
            return false;
          }
          Transferable transferable = support.getTransferable();
          try {
            transferable.getTransferData(DataFlavor.imageFlavor);
          }
          catch(Exception e) {
            e.printStackTrace();
            return false;
          }
          return true;
        }
      };
      topLayerPanels[j].setTransferHandler(dnd);
    }
  }

  /**
   * GridBagContraints Builder
   * 
   * @param rightInset
   * @param fill
   * @param xPos
   * @param yPos
   * @param height
   * @return
   */
  private GridBagConstraints setGridBag(boolean rightInset, boolean fill, int xPos, int yPos, int height) {
    GridBagConstraints tempGridBag = new GridBagConstraints();
    int rightInsetInt = 0;
    if(rightInset) {
      rightInsetInt = 5;
    }
    tempGridBag.insets = new Insets(5, 5, 5, rightInsetInt);
    if(fill) {
      tempGridBag.fill = GridBagConstraints.BOTH;
    }
    tempGridBag.gridx = xPos;
    tempGridBag.gridy = yPos;
    tempGridBag.gridheight = height;
    return tempGridBag;
  }

  /**
   * clears legal move map labels
   * 
   * @param legalMoveLabels
   */
  public void clearLegalMoveMap(JLabel[] legalMoveLabels) {
    for(JLabel label : legalMoveLabels) {
      label.setBackground(null);
      label.setOpaque(false);
    }
  }

  /**
   * highlights currently active player based on turn
   * 
   * @param field
   * @param player1Label
   * @param player2Label
   */
  public void highlightActivePlayer(Field field, JLabel player1Label, JLabel player2Label) {
    if(field.getCurrentTurn() % 2 == 0) {
      player2Label.setBorder(new LineBorder(Color.green, 5));
      player1Label.setBorder(null);
    }
    else {
      player1Label.setBorder(new LineBorder(Color.green, 5));
      player2Label.setBorder(null);
    }
  }

}
