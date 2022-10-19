package com.koerber.ausbildung.chess.utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Die {@code MoveSetSupplier}-Klasse bietet statische Methoden, die das
 * jeweilige {@code MoveSet} der Spielfigur in dem Methodennamen.
 * 
 * @author PKamps
 */
public abstract class MoveSetSupplier {

  /**
   * Baut {@code pawnMoveSet}.
   * 
   * @return pawnMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyPawnMoveSet() {
    ArrayList<Integer> entry1 = new ArrayList<Integer>(Arrays.asList(0, 1));
    ArrayList<Integer> entry2 = new ArrayList<Integer>(Arrays.asList(0, 2));
    ArrayList<Integer> entry3 = new ArrayList<Integer>(Arrays.asList(1, 1));
    ArrayList<Integer> entry4 = new ArrayList<Integer>(Arrays.asList(1, 0));
    ArrayList<Integer> entry5 = new ArrayList<Integer>(Arrays.asList(-1, 0));
    ArrayList<Integer> entry6 = new ArrayList<Integer>(Arrays.asList(-1, 1));

    ArrayList<ArrayList<Integer>> pawnMoveSet = new ArrayList<ArrayList<Integer>>();
    pawnMoveSet.add(entry1);
    pawnMoveSet.add(entry2);
    pawnMoveSet.add(entry3);
    pawnMoveSet.add(entry4);
    pawnMoveSet.add(entry5);
    pawnMoveSet.add(entry6);

    return pawnMoveSet;
  }

  /**
   * Baut {@code kingMoveSet}.
   * 
   * @return kingMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyKingMoveSet() {
    ArrayList<Integer> entry1 = new ArrayList<Integer>(Arrays.asList(0, 1));
    ArrayList<Integer> entry2 = new ArrayList<Integer>(Arrays.asList(1, 1));
    ArrayList<Integer> entry3 = new ArrayList<Integer>(Arrays.asList(0, 1));
    ArrayList<Integer> entry4 = new ArrayList<Integer>(Arrays.asList(1, -1));
    ArrayList<Integer> entry5 = new ArrayList<Integer>(Arrays.asList(0, -1));
    ArrayList<Integer> entry6 = new ArrayList<Integer>(Arrays.asList(-1, -1));
    ArrayList<Integer> entry7 = new ArrayList<Integer>(Arrays.asList(-1, 0));
    ArrayList<Integer> entry8 = new ArrayList<Integer>(Arrays.asList(-1, 1));

    ArrayList<ArrayList<Integer>> kingMoveSet = new ArrayList<ArrayList<Integer>>();
    kingMoveSet.add(entry1);
    kingMoveSet.add(entry2);
    kingMoveSet.add(entry3);
    kingMoveSet.add(entry4);
    kingMoveSet.add(entry5);
    kingMoveSet.add(entry6);
    kingMoveSet.add(entry7);
    kingMoveSet.add(entry8);

    return kingMoveSet;
  }

  /**
   * Baut {@code rookMoveSet}.
   * 
   * @return rookMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyRookMoveSet() {
    ArrayList<Integer> entry1 = new ArrayList<Integer>(Arrays.asList(0, 1));
    ArrayList<Integer> entry2 = new ArrayList<Integer>(Arrays.asList(1, 0));
    ArrayList<Integer> entry3 = new ArrayList<Integer>(Arrays.asList(0, -1));
    ArrayList<Integer> entry4 = new ArrayList<Integer>(Arrays.asList(-1, 0));

    ArrayList<ArrayList<Integer>> rookMoveSet = new ArrayList<ArrayList<Integer>>();
    rookMoveSet.add(entry1);
    rookMoveSet.add(entry2);
    rookMoveSet.add(entry3);
    rookMoveSet.add(entry4);

    return rookMoveSet;
  }

  /**
   * Baut {@code knightMoveSet}.
   * 
   * @return knightMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyKnightMoveSet() {
    ArrayList<Integer> entry1 = new ArrayList<Integer>(Arrays.asList(-1, 2));
    ArrayList<Integer> entry2 = new ArrayList<Integer>(Arrays.asList(1, 2));
    ArrayList<Integer> entry3 = new ArrayList<Integer>(Arrays.asList(2, 1));
    ArrayList<Integer> entry4 = new ArrayList<Integer>(Arrays.asList(2, -1));
    ArrayList<Integer> entry5 = new ArrayList<Integer>(Arrays.asList(1, -2));
    ArrayList<Integer> entry6 = new ArrayList<Integer>(Arrays.asList(-1, -2));
    ArrayList<Integer> entry7 = new ArrayList<Integer>(Arrays.asList(-2, -1));
    ArrayList<Integer> entry8 = new ArrayList<Integer>(Arrays.asList(-2, 1));

    ArrayList<ArrayList<Integer>> knightMoveSet = new ArrayList<ArrayList<Integer>>();
    knightMoveSet.add(entry1);
    knightMoveSet.add(entry2);
    knightMoveSet.add(entry3);
    knightMoveSet.add(entry4);
    knightMoveSet.add(entry5);
    knightMoveSet.add(entry6);
    knightMoveSet.add(entry7);
    knightMoveSet.add(entry8);

    return knightMoveSet;
  }

  /**
   * Baut {@code bishopMoveSet}.
   * 
   * @return bishopMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyBishopMoveSet() {
    ArrayList<Integer> entry1 = new ArrayList<Integer>(Arrays.asList(1, 1));
    ArrayList<Integer> entry2 = new ArrayList<Integer>(Arrays.asList(1, -1));
    ArrayList<Integer> entry3 = new ArrayList<Integer>(Arrays.asList(-1, -1));
    ArrayList<Integer> entry4 = new ArrayList<Integer>(Arrays.asList(-1, 1));

    ArrayList<ArrayList<Integer>> bishopMoveSet = new ArrayList<ArrayList<Integer>>();
    bishopMoveSet.add(entry1);
    bishopMoveSet.add(entry2);
    bishopMoveSet.add(entry3);
    bishopMoveSet.add(entry4);

    return bishopMoveSet;
  }

  /**
   * Baut {@code queenMoveSet}.
   * 
   * @return queenMoveSet
   */
  public static ArrayList<ArrayList<Integer>> supplyQueenMoveSet() {
    ArrayList<ArrayList<Integer>> queenMoveSet = supplyKingMoveSet();

    return queenMoveSet;
  }
}
