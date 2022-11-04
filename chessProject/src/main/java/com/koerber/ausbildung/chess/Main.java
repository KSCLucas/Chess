package com.koerber.ausbildung.chess;



public class Main {

  public static void main(String[] args) {

    System.out.println("Hello, Goodbye");
    System.out.println(" O haha reingeschaut");
    System.out.println(" schwanz");
    History myHistory = new History();
    myHistory.addEntry("fen");
    System.out.println(myHistory.getFens());
    System.out.println(myHistory.getFenOfTurn(5));
  }

}
