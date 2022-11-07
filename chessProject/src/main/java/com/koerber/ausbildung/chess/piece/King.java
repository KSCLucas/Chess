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

  /**
   * Checks, if {@code King} is in check.
   */
  public void checkForCheckAndCreateLegalMoveMap(Map<String, Piece> currentGameState) {
    // TODO add checkForCheckAndCreateLegalMoveMap implementation
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Create legalMoveMap with only Pieces of the same colour and store them
    List<Map<String, String>> opposingLegalMoveMaps = new ArrayList<>();
    Map<String, Piece> opposingPieces = currentGameState.entrySet().stream()
        .filter(x -> x.getValue().getColour() != getColour())
        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    for(Entry<String, Piece> entry : opposingPieces.entrySet()) {
      Piece currentPiece = entry.getValue();
      try {
        if(currentPiece instanceof Pawn) {
          for(int i = 0; i < getMoveSet().size(); i++) {
            // Set posLetterAsNumber and posNumber
            int posLetterAsNumber = currentPiece.getPosition().charAt(FIRST_CHAR_INDEX);
            int posNumber = Character.getNumericValue(currentPiece.getPosition().charAt(SECOND_CHAR_INDEX));
            MoveVector moveVector = currentPiece.getMoveSet().get(i);
            // Distinguish between move-only, take-only and check-only
            switch(i) {
            case 2, 5 -> {
              // Take
              if(currentPiece.getColour() == ChessColour.BLACK) {
                posLetterAsNumber += -1 * moveVector.getX();
                posNumber += -1 * moveVector.getY();
              }
              else {
                posLetterAsNumber += moveVector.getX();
                posNumber += moveVector.getY();
              }
              String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
              if(inFieldBounds(posLetterAsNumber, posNumber) && opposingPieces.get(fieldKey) == null) {
                currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
              }
            }
            }
          }
        }
        else {
          currentPiece.createLegalMoveMap(opposingPieces);
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
              getLegalMoveMap().put(fieldKey, getId());
            }
            // Check for Piece of Kings colour
            else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()) {
              getLegalMoveMap().put(fieldKey, HIT_STRING);
            }
            else {
            }
          }
          else {
            repeatLoop = false;
          }
        } while(isMoveRepeatable() && repeatLoop);
      }
      opposingLegalMoveMapsAllPieces.add(currentPiece.getLegalMoveMap());
    }
  }

  /**
   * Checks, if {@code King} is in checkmate.
   */
  public void checkForCheckmate() {
    // TODO add checkForCheckmate implementation
  }
}
