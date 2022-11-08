package com.koerber.ausbildung.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.koerber.ausbildung.chess.utility.ChessColour;
import com.koerber.ausbildung.chess.utility.ChessPieceValue;
import com.koerber.ausbildung.chess.utility.IconSupplier;
import com.koerber.ausbildung.chess.utility.MoveSetSupplier;
import com.koerber.ausbildung.chess.utility.MoveVector;
import com.koerber.ausbildung.chess.utility.PieceOutOfBoundsException;

/**
 * The {@code King} class contains a constructor as well as methods for every
 * {@code King}.
 * 
 * @author PKamps
 * @see Piece
 */
public class King extends Piece {

  private boolean isInCheck      = false;
  private boolean isCheckmate    = false;
  private boolean canCastleShort = false;
  private boolean canCastleLong  = false;
  private boolean hasMoved       = false;

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code icon}, {@code isMoveRepeatable}, {@code moveSet}, {@code isInCheck},
   * {@code isCheckmate}, {@code canCastleShort}, {@code canCastleLong} and
   * {@code hasMoved}.
   * 
   * @param id
   * @param colour
   * @param position
   */
  public King(String id, ChessColour colour, String position) {
    super(id, colour, ChessPieceValue.KING.value, false, position, MoveSetSupplier.getKingMoveSet(),
        IconSupplier.getIcon(colour, "king_small"));
  }

  public boolean isInCheck() {
    return isInCheck;
  }

  public void setInCheck(boolean isInCheck) {
    this.isInCheck = isInCheck;
  }

  public boolean isCheckmate() {
    return isCheckmate;
  }

  public void setCheckmate(boolean isCheckmate) {
    this.isCheckmate = isCheckmate;
  }

  public boolean isCanCastleShort() {
    return canCastleShort;
  }

  public void setCanCastleShort(boolean canCastleShort) {
    this.canCastleShort = canCastleShort;
  }

  public boolean isCanCastleLong() {
    return canCastleLong;
  }

  public void setCanCastleLong(boolean canCastleLong) {
    this.canCastleLong = canCastleLong;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  @Override
  public void setPosition(String position) {
    this.position = position;
    setHasMoved(true);
  }

  /**
   * Checks, if {@code hasMoved} = {@code false} and calls {@code canCastle} of
   * all {@code Rooks} of the same colour. Sets {@code canCastleShort} and
   * {@code canCastleLong} to {@code true}, if castleing is possible.
   */
  public void checkForCastle() {
    // TODO add checkForCastle implementation
  }

  private void checkIfPieceIsMoveablePosLetterAsNumber(Map<String, Piece> currentGameState, Piece currentPiece,
      int posLetterAsNumberCurrentPiece, int posNumberCurrentPiece, int posNumberKing, String firstKey, int multiplier,
      boolean otherKeyFound, int modifier) {
    String fieldKey;
    do {
      posLetterAsNumberCurrentPiece = posLetterAsNumberCurrentPiece + multiplier * modifier;
      fieldKey = Character.toString(posLetterAsNumberCurrentPiece) + posNumberKing;
      if(currentPiece.getLegalMoveMap().get(fieldKey).equals(HIT_STRING) && firstKey.isEmpty()) {
        firstKey = fieldKey;
      }
      else if(currentPiece.getLegalMoveMap().get(fieldKey).equals(HIT_STRING) && !firstKey.isEmpty()) {
        otherKeyFound = true;
      }
      multiplier++;
    } while(posNumberCurrentPiece > posNumberKing);
    if(!firstKey.isEmpty() && !otherKeyFound) {
      currentGameState.get(firstKey).setMoveable(false);
    }
  }

  private void checkIfPieceIsMoveablePosNumber(Map<String, Piece> currentGameState, Piece currentPiece,
      int posNumberCurrentPiece, int posLetterAsNumberKing, int posNumberKing, String firstKey, int multiplier,
      boolean otherKeyFound, int modifier) {
    String fieldKey;
    do {
      posNumberCurrentPiece = posNumberCurrentPiece + multiplier * modifier;
      fieldKey = Character.toString(posLetterAsNumberKing) + posNumberCurrentPiece;
      if(currentPiece.getLegalMoveMap().get(fieldKey).equals(HIT_STRING) && firstKey.isEmpty()) {
        firstKey = fieldKey;
      }
      else if(currentPiece.getLegalMoveMap().get(fieldKey).equals(HIT_STRING) && !firstKey.isEmpty()) {
        otherKeyFound = true;
      }
      multiplier++;
    } while(posNumberCurrentPiece > posNumberKing);
    if(!firstKey.isEmpty() && !otherKeyFound) {
      currentGameState.get(firstKey).setMoveable(false);
    }
  }

  /**
   * Checks, if {@code King} is in check and creates {@code legalMoveMap}.
   * Resets {@code moveable}.
   * 
   * @param currentGameState
   */
  public void checkForCheckAndCreateLegalMoveMap(Map<String, Piece> currentGameState) {
    // TODO add checkForCheckAndCreateLegalMoveMap implementation
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Reset moveable
    currentGameState.entrySet().stream().filter(x -> x.getValue().getColour() == getColour())
        .forEach(x -> x.getValue().setMoveable(true));
    // Create legalMoveMap with only Pieces of the same colour and store them
    List<Map<String, String>> opposingLegalMoveMaps = new ArrayList<>();
    Map<String, Piece> opposingPieces = currentGameState.entrySet().stream()
        .filter(x -> x.getValue().getColour() != getColour())
        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    for(Entry<String, Piece> entry : opposingPieces.entrySet()) {
      try {
        if(entry.getValue() instanceof Pawn currentPawn) {
          // Clear legalMoveMap
          currentPawn.getLegalMoveMap().clear();
          // Add take MoveVectors as "ttt" to legalMoveMap
          for(int i = 0; i < getMoveSet().size(); i++) {
            int posLetterAsNumber = currentPawn.getPosition().charAt(FIRST_CHAR_INDEX);
            int posNumber = Character.getNumericValue(currentPawn.getPosition().charAt(SECOND_CHAR_INDEX));
            MoveVector moveVector = currentPawn.getMoveSet().get(i);
            switch(i) {
            case 2, 5 -> {
              int modifier = currentPawn.getColour() == ChessColour.BLACK ? -1 : 1;
              posLetterAsNumber += modifier * moveVector.getX();
              posNumber += modifier * moveVector.getY();
              String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
              if(inFieldBounds(posLetterAsNumber, posNumber) && opposingPieces.get(fieldKey) == null) {
                currentPawn.getLegalMoveMap().put(fieldKey, TRUE_STRING);
              }
            }
            }
            opposingLegalMoveMaps.add(currentPawn.getLegalMoveMap());
          }
        }
        else {
          entry.getValue().createLegalMoveMap(opposingPieces);
          opposingLegalMoveMaps.add(entry.getValue().getLegalMoveMap());
        }
      }
      catch(PieceOutOfBoundsException e) {
        e.printStackTrace();
      }
    }
    // Create legalMoveMap with all Pieces for every opposing Piece and store
    // them
    List<Map<String, String>> opposingLegalMoveMapsAllPieces = new ArrayList<>();
    for(Entry<String, Piece> entry : opposingPieces.entrySet()) {
      Piece currentPiece = entry.getValue();
      // Clear legalMoveMap
      currentPiece.getLegalMoveMap().clear();
      // Loop over every move vector in moveSet
      for(MoveVector moveVector : currentPiece.getMoveSet()) {
        int posLetterAsNumber = currentPiece.getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumber = Character.getNumericValue(currentPiece.getPosition().charAt(SECOND_CHAR_INDEX));
        // Change content of legalMoveMap based on move vector i and
        // currentGameState
        boolean repeatLoop = true;
        do {
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          // Check for fieldKey still on Field
          if(inFieldBounds(posLetterAsNumber, posNumber)) {
            // Check for EmptyPiece
            if(currentGameState.get(fieldKey) == null) {
              currentPiece.getLegalMoveMap().put(fieldKey, TRUE_STRING);
            }
            // Check for this King
            else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()
                && currentGameState.get(fieldKey) instanceof King) {
              currentPiece.getLegalMoveMap().put(fieldKey, getId());
            }
            // Check for Piece of Kings colour
            else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()) {
              currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
            }
          }
          else {
            repeatLoop = false;
          }
        } while(currentPiece.isMoveRepeatable() && repeatLoop);
      }
      opposingLegalMoveMapsAllPieces.add(currentPiece.getLegalMoveMap());
    }
    // Check for direct check
    for(Map<String, String> currentMap : opposingLegalMoveMapsAllPieces) {
      if(currentMap.containsKey(getPosition())) {
        setInCheck(true);
        break;
      }
      else {
        setInCheck(false);
      }
    }
    // Filter out Rook, Bishop and Queen
    Map<String, Piece> roBiQuesMap = opposingPieces.entrySet().stream()
        .filter(x -> x.getValue() instanceof Queen || x.getValue() instanceof Rook || x.getValue() instanceof Bishop)
        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    // Check, which allied Pieces are allowed to move
    for(Entry<String, Piece> entry : roBiQuesMap.entrySet()) {
      Piece currentPiece = entry.getValue();
      currentPiece.getLegalMoveMap().clear();
      // Loop over every move vector in moveSet
      for(MoveVector moveVector : currentPiece.getMoveSet()) {
        int posLetterAsNumber = currentPiece.getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumber = Character.getNumericValue(currentPiece.getPosition().charAt(SECOND_CHAR_INDEX));
        // Change content of legalMoveMap based on move vector i and
        // currentGameState
        boolean repeatLoop = true;
        do {
          posLetterAsNumber += moveVector.getX();
          posNumber += moveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
          // Check for fieldKey still on Field
          if(inFieldBounds(posLetterAsNumber, posNumber)) {
            // Check for EmptyPiece
            if(currentGameState.get(fieldKey) == null) {
              currentPiece.getLegalMoveMap().put(fieldKey, TRUE_STRING);
            }
            // Check for this King
            else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()
                && currentGameState.get(fieldKey) instanceof King) {
              currentPiece.getLegalMoveMap().put(fieldKey, getId());
            }
            // Check for Piece of Kings colour
            else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()) {
              currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
            }
          }
          else {
            repeatLoop = false;
          }
        } while(currentPiece.isMoveRepeatable() && repeatLoop);
      }
      if(currentPiece.getLegalMoveMap().containsKey(getPosition())) {
        int posLetterAsNumberCurrentPiece = currentPiece.getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumberCurrentPiece = Character.getNumericValue(currentPiece.getPosition().charAt(SECOND_CHAR_INDEX));
        int posLetterAsNumberKing = getPosition().charAt(FIRST_CHAR_INDEX);
        int posNumberKing = Character.getNumericValue(getPosition().charAt(SECOND_CHAR_INDEX));
        String fieldKey;
        String firstKey = "";
        int multiplier = 1;
        boolean otherKeyFound = false;
        if(posLetterAsNumberCurrentPiece == posLetterAsNumberKing && posNumberCurrentPiece != posNumberKing) {
          int modifier;
          if(posNumberCurrentPiece > posNumberKing) {
            modifier = -1;
            checkIfPieceIsMoveablePosNumber(currentGameState, currentPiece, posNumberCurrentPiece,
                posLetterAsNumberKing, posNumberKing, firstKey, multiplier, otherKeyFound, modifier);
          }
          else {
            modifier = 1;
            checkIfPieceIsMoveablePosNumber(currentGameState, currentPiece, posNumberCurrentPiece,
                posLetterAsNumberKing, posNumberKing, firstKey, multiplier, otherKeyFound, modifier);
          }
        }
        else if(posNumberCurrentPiece == posNumberKing && posLetterAsNumberCurrentPiece != posLetterAsNumberKing) {
          int modifier;
          if(posLetterAsNumberCurrentPiece > posLetterAsNumberKing) {
            modifier = -1;
            checkIfPieceIsMoveablePosLetterAsNumber(currentGameState, currentPiece, posLetterAsNumberCurrentPiece,
                posNumberCurrentPiece, posNumberKing, firstKey, multiplier, otherKeyFound, modifier);
          }
          else {
            modifier = 1;
            checkIfPieceIsMoveablePosLetterAsNumber(currentGameState, currentPiece, posLetterAsNumberCurrentPiece,
                posNumberCurrentPiece, posNumberKing, firstKey, multiplier, otherKeyFound, modifier);
          }
        }
        else { // TODO add diagonal logic
          int letterModifier;
          int numberModifier;
          if(posNumberCurrentPiece > posNumberKing && posLetterAsNumberCurrentPiece > posLetterAsNumberKing) {
            letterModifier = -1;
            numberModifier = -1;
          }
          else if(posNumberCurrentPiece < posNumberKing && posLetterAsNumberCurrentPiece > posLetterAsNumberKing) {
            letterModifier = -1;
            numberModifier = 1;
          }
          else if(posNumberCurrentPiece > posNumberKing && posLetterAsNumberCurrentPiece < posLetterAsNumberKing) {
            letterModifier = 1;
            numberModifier = -1;
          }
          else {
            letterModifier = 1;
            numberModifier = 1;
          }
        }
      }
    }
  }

  /**
   * Checks, if {@code King} is in checkmate.
   */
  public void checkForCheckmate() {
    // TODO add checkForCheckmate implementation
  }
}
