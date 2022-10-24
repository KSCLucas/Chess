package com.koerber.ausbildung.chess.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class GUIFrame {

  private JFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GUIFrame window = new GUIFrame();
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
  public GUIFrame() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setResizable(false);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{512, 896, 512, 0};
    gridBagLayout.rowHeights = new int[]{100, 100, 100, 100, 500, 0, 0};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    frame.getContentPane().setLayout(gridBagLayout);

    JPanel newGamePanel = new JPanel();
    GridBagConstraints gbc_panel = new GridBagConstraints();
    gbc_panel.insets = new Insets(0, 0, 5, 5);
    gbc_panel.fill = GridBagConstraints.BOTH;
    gbc_panel.gridx = 0;
    gbc_panel.gridy = 0;
    frame.getContentPane().add(newGamePanel, gbc_panel);
    newGamePanel.setLayout(new GridLayout(0, 2, 0, 0));

    JButton newGameButton = new JButton("New Game");
    newGameButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    newGamePanel.add(newGameButton);

    JButton backButton = new JButton("<--");
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    newGamePanel.add(backButton);

    JPanel gameBoardPanelBottomLayer = new JPanel();
    GridBagConstraints gbc_panel_3 = new GridBagConstraints();
    gbc_panel_3.gridheight = 4;
    gbc_panel_3.insets = new Insets(0, 0, 5, 5);
    gbc_panel_3.fill = GridBagConstraints.BOTH;
    gbc_panel_3.gridx = 1;
    gbc_panel_3.gridy = 1;
    frame.getContentPane().add(gameBoardPanelBottomLayer, gbc_panel_3);
    gameBoardPanelBottomLayer.setLayout(new GridLayout(8, 8, 0, 0));
    
    JLabel lblNewLabel = new JLabel("New label");
    gameBoardPanelBottomLayer.add(lblNewLabel);
    
    JLabel lblNewLabel_1 = new JLabel("New label");
    gameBoardPanelBottomLayer.add(lblNewLabel_1);
    
    JLabel lblNewLabel_2 = new JLabel("New label");
    gameBoardPanelBottomLayer.add(lblNewLabel_2);

    JLabel lblHistory = new JLabel("History");
    GridBagConstraints gbc_lblHistory = new GridBagConstraints();
    gbc_lblHistory.insets = new Insets(0, 0, 5, 5);
    gbc_lblHistory.gridx = 0;
    gbc_lblHistory.gridy = 1;
    frame.getContentPane().add(lblHistory, gbc_lblHistory);

    JPanel panel_1 = new JPanel();
    GridBagConstraints gbc_panel_1 = new GridBagConstraints();
    gbc_panel_1.insets = new Insets(0, 0, 5, 5);
    gbc_panel_1.fill = GridBagConstraints.BOTH;
    gbc_panel_1.gridx = 0;
    gbc_panel_1.gridy = 2;
    frame.getContentPane().add(panel_1, gbc_panel_1);
    panel_1.setLayout(new GridLayout(0, 2, 0, 0));

    JButton btnNewButton_2 = new JButton("<--");
    btnNewButton_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    panel_1.add(btnNewButton_2);

    JButton button = new JButton("-->");
    panel_1.add(button);

    JPanel panel_2 = new JPanel();
    GridBagConstraints gbc_panel_2 = new GridBagConstraints();
    gbc_panel_2.insets = new Insets(0, 0, 5, 5);
    gbc_panel_2.fill = GridBagConstraints.BOTH;
    gbc_panel_2.gridx = 0;
    gbc_panel_2.gridy = 3;
    frame.getContentPane().add(panel_2, gbc_panel_2);

    JButton btnNewButton_3 = new JButton(">>>>");
    btnNewButton_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });
    panel_2.setLayout(new GridLayout(0, 1, 0, 0));
    panel_2.add(btnNewButton_3);

    JScrollPane scrollPane = new JScrollPane();
    GridBagConstraints gbc_scrollPane = new GridBagConstraints();
    gbc_scrollPane.anchor = GridBagConstraints.BASELINE;
    gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
    gbc_scrollPane.fill = GridBagConstraints.HORIZONTAL;
    gbc_scrollPane.gridx = 0;
    gbc_scrollPane.gridy = 5;
    frame.getContentPane().add(scrollPane, gbc_scrollPane);
  }
}
