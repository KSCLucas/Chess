package com.koerber.ausbildung.chess;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class StringToMap {
  // recieve actual String from field
  public static void main(String[] args) {
    // creats a string
    String eingabe = "r1wn1wb1wq1wk1wb2wn2wr2w/p1wp2wp3wp4wp5wp6wp7wp8w/########################/########################/########################/########################/p1bp2bp3bp4bp5bp6bp7bp8b/r1bn1bb1bq1bk1bb2bn2br2b/";
    // creats an empty treeMap
    Map<String, String> testCurrentGameState = new TreeMap<String, String>();
    // sets row to 0
    int row = 0;
    // splits the string each time a / appears
    for(String zeile : eingabe.split("/")) {
      // counts row +1
      row++;
      char column = 'a';
      // as long as zeile.length is not 0
      while(zeile.length() != 0) {
        // creats a fieldKey from column and row
        String fieldKey = Character.toString(column) + row;
        // sets string value to 3 digits of the string
        String value = zeile.substring(0, 3);
        // adds the values of key and value to the map
        testCurrentGameState.put(fieldKey, value);
        // sets temporary string zeile to the new shorter substring
        zeile = zeile.substring(3);
        // +1 column
        column++;
      }
    }
    for(Entry<String, String> entry : testCurrentGameState.entrySet()) {
      System.out.println(entry);
    }
    // System.out.println();
    // System.out.println(testCurrentGameState);

    // Map<String, String> terms = new TreeMap<>();
    // for (String term : a.split("/")) {
    // String[] pair = term.split("x\\^");
    // terms.merge(String.valueOf(pair[1]), Integer.valueOf(pair[0]),
    // Integer::sum);

    // }
  }
}
