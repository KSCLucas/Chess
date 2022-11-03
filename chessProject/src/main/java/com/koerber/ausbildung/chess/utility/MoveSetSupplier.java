package com.koerber.ausbildung.chess.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MoveSetSupplier} class contains static methods, which provide the
 * {@code MoveSet} for each type of {@code Piece}.
 * 
 * @author PKamps
 */
public abstract class MoveSetSupplier {

  /**
   * Provides {@code pawnMoveSet}.
   * 
   * @return pawnMoveSet
   */
  public static List<MoveVector> getPawnMoveSet() {
    MoveVector vector1 = new MoveVector(0, 1);
    MoveVector vector2 = new MoveVector(0, 2);
    MoveVector vector3 = new MoveVector(1, 1);
    MoveVector vector4 = new MoveVector(1, 0);
    MoveVector vector5 = new MoveVector(-1, 0);
    MoveVector vector6 = new MoveVector(-1, 1);

    List<MoveVector> pawnMoveSet = new ArrayList<>();
    pawnMoveSet.add(vector1);
    pawnMoveSet.add(vector2);
    pawnMoveSet.add(vector3);
    pawnMoveSet.add(vector4);
    pawnMoveSet.add(vector5);
    pawnMoveSet.add(vector6);

    return pawnMoveSet;
  }

  /**
   * Provides {@code kingMoveSet}.
   * 
   * @return kingMoveSet
   */
  public static List<MoveVector> getKingMoveSet() {
    MoveVector vector1 = new MoveVector(0, 1);
    MoveVector vector2 = new MoveVector(1, 1);
    MoveVector vector3 = new MoveVector(1, 0);
    MoveVector vector4 = new MoveVector(1, -1);
    MoveVector vector5 = new MoveVector(0, -1);
    MoveVector vector6 = new MoveVector(-1, -1);
    MoveVector vector7 = new MoveVector(-1, 0);
    MoveVector vector8 = new MoveVector(-1, 1);

    List<MoveVector> kingMoveSet = new ArrayList<>();
    kingMoveSet.add(vector1);
    kingMoveSet.add(vector2);
    kingMoveSet.add(vector3);
    kingMoveSet.add(vector4);
    kingMoveSet.add(vector5);
    kingMoveSet.add(vector6);
    kingMoveSet.add(vector7);
    kingMoveSet.add(vector8);

    return kingMoveSet;
  }

  /**
   * Provides {@code rookMoveSet}.
   * 
   * @return rookMoveSet
   */
  public static List<MoveVector> getRookMoveSet() {
    MoveVector vector1 = new MoveVector(0, 1);
    MoveVector vector2 = new MoveVector(1, 0);
    MoveVector vector3 = new MoveVector(0, -1);
    MoveVector vector4 = new MoveVector(-1, 0);

    List<MoveVector> rookMoveSet = new ArrayList<>();
    rookMoveSet.add(vector1);
    rookMoveSet.add(vector2);
    rookMoveSet.add(vector3);
    rookMoveSet.add(vector4);

    return rookMoveSet;
  }

  /**
   * Provides {@code knightMoveSet}.
   * 
   * @return knightMoveSet
   */
  public static List<MoveVector> getKnightMoveSet() {
    MoveVector vector1 = new MoveVector(-1, 2);
    MoveVector vector2 = new MoveVector(1, 2);
    MoveVector vector3 = new MoveVector(2, 1);
    MoveVector vector4 = new MoveVector(2, -1);
    MoveVector vector5 = new MoveVector(1, -2);
    MoveVector vector6 = new MoveVector(-1, -2);
    MoveVector vector7 = new MoveVector(-2, -1);
    MoveVector vector8 = new MoveVector(-2, 1);

    List<MoveVector> knightMoveSet = new ArrayList<>();
    knightMoveSet.add(vector1);
    knightMoveSet.add(vector2);
    knightMoveSet.add(vector3);
    knightMoveSet.add(vector4);
    knightMoveSet.add(vector5);
    knightMoveSet.add(vector6);
    knightMoveSet.add(vector7);
    knightMoveSet.add(vector8);

    return knightMoveSet;
  }

  /**
   * Provides {@code bishopMoveSet}.
   * 
   * @return bishopMoveSet
   */
  public static List<MoveVector> getBishopMoveSet() {
    MoveVector vector1 = new MoveVector(1, 1);
    MoveVector vector2 = new MoveVector(1, -1);
    MoveVector vector3 = new MoveVector(-1, -1);
    MoveVector vector4 = new MoveVector(-1, 1);

    List<MoveVector> bishopMoveSet = new ArrayList<>();
    bishopMoveSet.add(vector1);
    bishopMoveSet.add(vector2);
    bishopMoveSet.add(vector3);
    bishopMoveSet.add(vector4);

    return bishopMoveSet;
  }

  /**
   * Provides {@code queenMoveSet}.
   * 
   * @return queenMoveSet
   */
  public static List<MoveVector> getQueenMoveSet() {
    List<MoveVector> queenMoveSet = getKingMoveSet();

    return queenMoveSet;
  }
}
