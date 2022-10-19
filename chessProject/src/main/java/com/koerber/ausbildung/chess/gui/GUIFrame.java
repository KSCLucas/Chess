package com.koerber.ausbildung.chess.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

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
//    frame.setUndecorated(true);
  
//    frame.setBounds(0, 0, 1920, 1080);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
