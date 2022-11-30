package com.koerber.ausbildung.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
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

  private boolean      isInCheck         = false;
  private boolean      isCheckmate       = false;
  private boolean      canCastleShort    = false;
  private boolean      canCastleLong     = false;
  private boolean      hasMoved          = false;
  private List<String> attackKeys        = new ArrayList<>();
  private List<Piece>  potentialSaviours = new ArrayList<>();

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

  public List<Piece> getPotentialSaviours() {
    return potentialSaviours;
  }

  public void setPotentialSaviours(List<Piece> potentialSaviours) {
    this.potentialSaviours = potentialSaviours;
  }

  public List<String> getAttackKeys() {
    return attackKeys;
  }

  public void setAttackKeys(List<String> attackFields) {
    this.attackKeys = attackFields;
  }

  /**
   * Adds a {@code potentialSaviour} to internal list {@code potentialSaviours}.
   * 
   * @param potentialSaviour
   */
  public void addPotentialSaviour(Piece potentialSaviour) {
    getPotentialSaviours().add(potentialSaviour);
  }

  /**
   * Emptys the list {@code potentialSaviours}.
   */
  public void clearPotentialSaviours() {
    getPotentialSaviours().clear();
  }

  /**
   * Adds a {@code attackFields} to internal list {@code attackFields}.
   * 
   * @param key
   */
  public void addAttackKeys(String key) {
    getAttackKeys().add(key);
  }

  /**
   * Emptys the map {@code attackFields}.
   */
  public void clearAttackKeys() {
    getAttackKeys().clear();
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
   * Builds and returned a special List of {@ MoveVectors}
   * 
   * @param inverseMoveVector
   * @param pawnColour
   * @return availableMoveVectors
   */
  private List<MoveVector> getAvailableMoveVectorsForPawn(MoveVector inverseMoveVector, ChessColour pawnColour) {
    List<MoveVector> tempMoves = pawnColour == ChessColour.WHITE ? MoveSetSupplier.getPawnWhiteMoveSet()
        : MoveSetSupplier.getPawnBlackMoveSet();
    List<MoveVector> availableMoveVectors = new ArrayList<>();
    for(int i = 0; i < 6; i++) {
      if(!tempMoves.get(i).equals(inverseMoveVector)) {
        availableMoveVectors.add(new MoveVector(0, 0));
      }
      else {
        availableMoveVectors.add(inverseMoveVector);
      }
    }
    return availableMoveVectors;
  }

  /**
   * Creates List of {@code legalMoveMaps} of {@code Pieces} of the opposing
   * {@code Colour} and returns it.
   * 
   * @param currentGameState
   * @return opposingMoveMaps
   */
  private List<Map<String, String>> getOpposingMoveMaps(Map<String, Piece> currentGameState) {
    // Reset moveVectors of all Pieces of the same colour
    currentGameState.entrySet().stream().filter(x -> x.getValue().getColour() == getColour())
        .forEach(x -> x.getValue().setAvailableMoveVectorsToMoveSet());
    // Reset attackKey list
    clearAttackKeys();
    // Filter for every opposing piece
    Map<String, Piece> opposingPieces = currentGameState.entrySet().stream()
        .filter(x -> x.getValue().getColour() != getColour())
        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
    List<Map<String, String>> opposingMoveMaps = new ArrayList<>();
    for(Entry<String, Piece> entry : opposingPieces.entrySet()) {
      Piece currentPiece = entry.getValue();
      // Clear legalMoveMap
      currentPiece.getLegalMoveMap().clear();
      // Distinguish between Pawn and other Pieces
      if(currentPiece instanceof Pawn) {
        for(int i = 2; i <= 5; i += 3) {
          int posLetterAsNumber = currentPiece.getPosition().charAt(FIRST_CHAR_INDEX);
          int posNumber = Character.getNumericValue(currentPiece.getPosition().charAt(SECOND_CHAR_INDEX));
          MoveVector currentMoveVector = currentPiece.getMoveSet().get(i);
          posLetterAsNumber += currentMoveVector.getX();
          posNumber += currentMoveVector.getY();
          String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
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
        }
      }
      else {
        // Loop over every move vector in moveSet
        for(MoveVector moveVector : currentPiece.getMoveSet()) {
          // Track Pieceposition and trail
          List<String> fields = new ArrayList<>();
          fields.add(currentPiece.getPosition());
          // Reset moveability criteria
          int opposingPieceCount = 0;
          boolean allyPieceDetected = false;
          String encounterKey = NOT_ON_FIELD;
          boolean kingInLineVector = false;
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
                if(opposingPieceCount < 1 && !allyPieceDetected) {
                  currentPiece.getLegalMoveMap().put(fieldKey, TRUE_STRING);
                  // Add key to trail
                  fields.add(fieldKey);
                }
              }
              // Check for this King
              else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()
                  && currentGameState.get(fieldKey) instanceof King) {
                currentPiece.getLegalMoveMap().put(fieldKey, getId());
                kingInLineVector = true;
              }
              // Check for Piece of Kings colour
              else if(currentGameState.get(fieldKey).getColour() != currentPiece.getColour()) {
                if(opposingPieceCount < 1 && !allyPieceDetected) {
                  currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
                }
                opposingPieceCount++;
                if(encounterKey.equals(NOT_ON_FIELD)) {
                  encounterKey = fieldKey;
                }
              }
              else {
                allyPieceDetected = true;
              }
            }
            else {
              repeatLoop = false;
              // Set moveability and availableMoveVectors
              // Check for kingInLine to set moveability or availableMoveVectors
              if(opposingPieceCount == 1 && kingInLineVector) {
                // Empty availableMoveVectors
                currentGameState.get(encounterKey).emptyAvailableMoveVectors();
                // Get inverse vector
                MoveVector inverseMoveVector = new MoveVector(-1 * moveVector.getX(), -1 * moveVector.getY());
                boolean moveVectorFound = false;
                for(MoveVector vector : currentGameState.get(encounterKey).getMoveSet()) {
                  if(vector.equals(inverseMoveVector)) {
                    moveVectorFound = true;
                  }
                }
                if(moveVectorFound) {
                  // Look for instance of Pawn
                  if(currentGameState.get(encounterKey) instanceof Pawn pawn) {
                    // Give Pawn a special availableMoveSet
                    pawn.setAvailableMoveVectors(getAvailableMoveVectorsForPawn(inverseMoveVector, pawn.getColour()));
                  }
                  else {
                    // Set availableMoveVectors to inverseMoveVector
                    currentGameState.get(encounterKey).getAvailableMoveVectors().add(inverseMoveVector);
                  }
                }
                else {
                  // Set moveable false
                  currentGameState.get(encounterKey).setMoveable(false);
                }
              }
              else {
                // Check for check
                if(opposingPieceCount == 0 && kingInLineVector) {
                  setInCheck(true);
                  setAttackKeys(fields);
                  System.out.println(getAttackKeys());
                }
              }
            }
          } while(currentPiece.isMoveRepeatable() && repeatLoop);
        }
      }
      opposingMoveMaps.add(currentPiece.getLegalMoveMap());
    }
    System.out.println(getAttackKeys());
    return opposingMoveMaps;
  }

  /**
   * Combines a List of {@code moveMaps} into one Map and returns it.
   * 
   * @param opposingMoveMaps
   * @return mergedMoveMap
   */
  private Map<String, String> mergeOpposingMoveMaps(List<Map<String, String>> opposingMoveMaps) {
    Map<String, String> mergedMoveMap = new TreeMap<>();
    // Loop over every Map
    for(Map<String, String> map : opposingMoveMaps) {
      // Loop over every Map Entry
      for(Entry<String, String> entry : map.entrySet()) {
        mergedMoveMap.putIfAbsent(entry.getKey(), entry.getValue());
      }
    }
    return mergedMoveMap;
  }

  /**
   * Checks, if {@code King} is in check and creates {@code legalMoveMap}.
   * Resets {@code moveable}.
   * 
   * @param currentGameState
   */
  public void checkForCheckAndCreateLegalMoveMap(Map<String, Piece> currentGameState) {
    // Clear legalMoveMap
    getLegalMoveMap().clear();
    // Reset moveable
    currentGameState.entrySet().stream().filter(x -> x.getValue().getColour() == getColour())
        .forEach(x -> x.getValue().setMoveable(true));
    // Create List<Map<String, String>> of Pieces of the opposing colour
    List<Map<String, String>> opposingMoveMaps = getOpposingMoveMaps(currentGameState);
    // Merge all opposingMoveMaps into one Map
    Map<String, String> mergedMoveMap = mergeOpposingMoveMaps(opposingMoveMaps);
    // Get King legalMoveMap without mergedMoveMap
    try {
      createLegalMoveMap(currentGameState);
    }
    catch(PieceOutOfBoundsException e) {
      e.printStackTrace();
    }
    // Merge King legalMoveMap with mergedMoveMap
    List<String> keys = new ArrayList<>();
    for(Entry<String, String> entry : getLegalMoveMap().entrySet()) {
      if(mergedMoveMap.containsKey(entry.getKey())) {
        keys.add(entry.getKey());
      }
    }
    for(String key : keys) {
      getLegalMoveMap().remove(key);
    }
  }

  /**
   * Checks, if {@code King} is in checkmate.
   */
  public void checkForCheckmate() {
    // TODO add checkForCheckmate implementation
  }
}
