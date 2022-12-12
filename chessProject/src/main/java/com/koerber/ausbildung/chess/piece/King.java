package com.koerber.ausbildung.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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

  private boolean      inCheck       = false;
  private boolean      checkmate     = false;
  private String       castleKeyShort;
  private String       castleKeyLong;
  private boolean      hasMoved      = false;
  private List<String> attackKeys    = new ArrayList<>();
  private List<Piece>  saviourPieces = new ArrayList<>();

  /**
   * Calls parameterized constructor of {@code Piece} and sets {@code value},
   * {@code icon}, {@code isMoveRepeatable}, {@code moveSet}, {@code isInCheck},
   * {@code isCheckmate}, {@code canCastleShort}, {@code canCastleLong} and
   * {@code hasMoved}.
   * 
   * @param idNum
   * @param colour
   * @param position
   */
  public King(int idNum, ChessColour colour, String position) {
    super(idNum, colour, ChessPieceValue.KING.value, false, position, MoveSetSupplier.getKingMoveSet(),
        IconSupplier.getIcon(colour, "king_small"));
  }

  public boolean isInCheck() {
    return inCheck;
  }

  public void setInCheck(boolean isInCheck) {
    this.inCheck = isInCheck;
  }

  public boolean isCheckmate() {
    return checkmate;
  }

  public void setCheckmate(boolean isCheckmate) {
    this.checkmate = isCheckmate;
  }

  public String getCastleKeyShort() {
    return castleKeyShort;
  }

  public void setCastleKeyShort(String castleKeyShort) {
    this.castleKeyShort = castleKeyShort;
  }

  public String getCastleKeyLong() {
    return castleKeyLong;
  }

  public void setCastleKeyLong(String castleKeyLong) {
    this.castleKeyLong = castleKeyLong;
  }

  public boolean isHasMoved() {
    return hasMoved;
  }

  public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
  }

  public List<String> getAttackKeys() {
    return attackKeys;
  }

  public void setAttackKeys(List<String> attackFields) {
    this.attackKeys = attackFields;
  }

  public List<Piece> getSaviourPieces() {
    return saviourPieces;
  }

  public void setSaviourPieces(List<Piece> saviourPieces) {
    this.saviourPieces = saviourPieces;
  }

  @Override
  public void setPosition(String position) {
    this.position = position;
    setHasMoved(true);
  }

  /**
   * If {@code castleKeys != null}, executes rochade on the according
   * {@code castleSide}.
   * 
   * @param currentGameState
   */
  public void castle(Map<String, Piece> currentGameState) {
    if(getPosition().equals(getCastleKeyShort())) {
      int posLetterAsNumber = getPosLetterAsNumber(getCastleKeyShort());
      int posNumber = getPosNumber(getCastleKeyShort());
      currentGameState.entrySet().stream().filter(x -> (x.getValue().getColour() == getColour())
          && x.getValue() instanceof Rook rook && rook.getCastleSide() == Rook.CASTLE_SIDE_SHORT).forEach(x -> {
            Rook rook = (Rook)x.getValue();
            currentGameState.remove(rook.getPosition());
            rook.setPosition(getFieldKey(posLetterAsNumber - 1, posNumber));
            currentGameState.put(rook.getPosition(), rook);
          });
    }
    if(getPosition().equals(getCastleKeyLong())) {
      int posLetterAsNumber = getPosLetterAsNumber(getCastleKeyLong());
      int posNumber = getPosNumber(getCastleKeyLong());
      currentGameState.entrySet().stream().filter(x -> (x.getValue().getColour() == getColour())
          && x.getValue() instanceof Rook rook && rook.getCastleSide() == Rook.CASTLE_SIDE_LONG).forEach(x -> {
            Rook rook = (Rook)x.getValue();
            currentGameState.remove(rook.getPosition());
            rook.setPosition(getFieldKey(posLetterAsNumber + 1, posNumber));
            currentGameState.put(rook.getPosition(), rook);
          });
    }
  }

  /**
   * Builds and returned a special list of {@ MoveVectors}
   * 
   * @param inverseMoveVector
   * @param pawnColour
   * @return availableMoveVectors
   */
  private List<MoveVector> getAvailableMoveVectorsForPawn(MoveVector inverseMoveVector, ChessColour pawnColour) {
    List<MoveVector> tempMoves = pawnColour == ChessColour.WHITE ? MoveSetSupplier.getPawnWhiteMoveSet()
        : MoveSetSupplier.getPawnBlackMoveSet();
    List<MoveVector> availableMoveVectors = new ArrayList<>();
    int firstEqualIndex = -1;
    for(int i = 0; i < 6; i++) {
      if(firstEqualIndex == 0) {
        availableMoveVectors.add(new MoveVector(tempMoves.get(i).getX(), tempMoves.get(i).getY()));
        firstEqualIndex = -2;
      }
      if(!tempMoves.get(i).equals(inverseMoveVector)) {
        availableMoveVectors.add(new MoveVector(0, 0));
      }
      else {
        firstEqualIndex = i;
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
    getAttackKeys().clear();
    // Reset inCheck
    setInCheck(false);
    // Filter for every opposing piece
    List<Piece> opposingPieces = currentGameState.entrySet().stream()
        .filter(x -> x.getValue().getColour() != getColour()).map(x -> x.getValue()).toList();
    List<Map<String, String>> opposingMoveMaps = new ArrayList<>();
    for(Piece currentPiece : opposingPieces) {
      // Clear legalMoveMap
      currentPiece.getLegalMoveMap().clear();
      // Distinguish between Pawn and other Pieces
      if(currentPiece instanceof Pawn) {
        // Get map with only attack vectors
        createMapWithMoveSetIndex(currentGameState, currentPiece, 2);
        createMapWithMoveSetIndex(currentGameState, currentPiece, 5);
      }
      else {
        // Loop over every move vector in moveSet
        createMapWithMoveSet(currentGameState, currentPiece);
      }
      opposingMoveMaps.add(currentPiece.getLegalMoveMap());
    }
    godSaveTheKing(currentGameState);
    return opposingMoveMaps;
  }

  /**
   * Creates special {@code moveMap} for every {@code Piece} but {@code Pawn}.
   * 
   * @param currentGameState
   * @param currentPiece
   */
  private void createMapWithMoveSet(Map<String, Piece> currentGameState, Piece currentPiece) {
    for(MoveVector moveVector : currentPiece.getMoveSet()) {
      // Track Pieceposition and trail
      List<String> trail = new ArrayList<>();
      trail.add(currentPiece.getPosition());
      // Reset moveability criteria
      int opposingPieceCount = 0;
      boolean allyPieceDetected = false;
      boolean allyPieceInfrontKingDetected = false;
      String encounterKey = NOT_ON_FIELD;
      boolean kingInLine = false;
      int posLetterAsNumber = getPosLetterAsNumber(currentPiece.getPosition());
      int posNumber = getPosNumber(currentPiece.getPosition());
      boolean loop = true;
      // Change content of legalMoveMap based on move vector i and
      // currentGameState
      while((currentPiece.isMoveRepeatable() || loop == true) && inFieldBounds(posLetterAsNumber, posNumber)) {
        posLetterAsNumber += moveVector.getX();
        posNumber += moveVector.getY();
        String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
        Piece detectedPiece = currentGameState.get(fieldKey);
        // Check for empty field
        if(detectedPiece == null && opposingPieceCount < 1 && !allyPieceDetected) {
          currentPiece.getLegalMoveMap().put(fieldKey, MOVE_STRING);
          // Add key to trail
          if(!kingInLine) {
            trail.add(fieldKey);
          }
        }
        // Check for this King
        else if(detectedPiece.getColour() != currentPiece.getColour() && detectedPiece instanceof King) {
          currentPiece.getLegalMoveMap().put(fieldKey, getId());
          kingInLine = true;
        }
        // Check for Piece of Kings colour
        else if(detectedPiece.getColour() != currentPiece.getColour()) {
          if(opposingPieceCount < 1 && !allyPieceDetected) {
            currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
          }
          if(!kingInLine) {
            opposingPieceCount++;
          }
          if(encounterKey.equals(NOT_ON_FIELD)) {
            encounterKey = fieldKey;
          }
        }
        else {
          if(!allyPieceDetected && opposingPieceCount < 1) {
            currentPiece.getLegalMoveMap().put(fieldKey, MOVE_STRING);
          }
          allyPieceDetected = true;
          if(!kingInLine) {
            allyPieceInfrontKingDetected = true;
          }
        }
        loop = false;
      }
      enableBlock(currentGameState, moveVector, opposingPieceCount, encounterKey, kingInLine,
          allyPieceInfrontKingDetected);
      checkForCheck(trail, opposingPieceCount, allyPieceInfrontKingDetected, kingInLine);
    }
  }

  /**
   * Creates special {@code moveMap} for every {@code Pawn} at the given
   * {@code moveSet} index.
   * 
   * @param currentGameState
   * @param currentPiece
   * @param moveSetIndex
   */
  private void createMapWithMoveSetIndex(Map<String, Piece> currentGameState, Piece currentPiece, int moveSetIndex) {
    int posLetterAsNumber = getPosLetterAsNumber(currentPiece.getPosition());
    int posNumber = getPosNumber(currentPiece.getPosition());
    if(!inFieldBounds(posLetterAsNumber, posNumber)) {
      return;
    }
    MoveVector currentMoveVector = currentPiece.getMoveSet().get(moveSetIndex);
    posLetterAsNumber += currentMoveVector.getX();
    posNumber += currentMoveVector.getY();
    String fieldKey = Character.toString(posLetterAsNumber) + posNumber;
    Piece detectedPiece = currentGameState.get(fieldKey);
    // Check for empty field
    if(detectedPiece == null || detectedPiece.getColour() == currentPiece.getColour()) {
      currentPiece.getLegalMoveMap().put(fieldKey, MOVE_STRING);
    }
    // Check for Piece of Kings colour
    else if(detectedPiece.getColour() != currentPiece.getColour()) {
      if(detectedPiece instanceof King) {
        getAttackKeys().add(currentPiece.getPosition());
      }
      else {
        currentPiece.getLegalMoveMap().put(fieldKey, HIT_STRING);
      }
    }
  }

  /**
   * Sets {@code moveable = false} of the {@code Piece} with {@code position} of
   * {@ encounterKey}, if it is the only {@code Piece} in between the
   * {@code currentPiece} and {@code King}.
   * 
   * @param currentGameState
   * @param moveVector
   * @param opposingPieceCount
   * @param encounterKey
   * @param kingInLine
   */
  private void enableBlock(Map<String, Piece> currentGameState, MoveVector moveVector, int opposingPieceCount,
      String encounterKey, boolean kingInLine, boolean allyPieceInfrontKingDetected) {
    // Check for kingInLine to set moveability or availableMoveVectors
    if(opposingPieceCount != 1 || !kingInLine || allyPieceInfrontKingDetected) {
      return;
    }
    Piece currentPiece = currentGameState.get(encounterKey);
    // Empty availableMoveVectors and legalMoveMap
    currentPiece.getAvailableMoveVectors().clear();
    currentPiece.getLegalMoveMap().clear();
    // Get inverse vector
    MoveVector inverseMoveVector = new MoveVector(-1 * moveVector.getX(), -1 * moveVector.getY());
    boolean moveVectorFound = false;
    for(MoveVector vector : currentPiece.getMoveSet()) {
      if(vector.equals(inverseMoveVector)) {
        moveVectorFound = true;
      }
    }
    if(moveVectorFound) {
      // Look for instance of Pawn
      if(currentPiece instanceof Pawn pawn) {
        // Give Pawn a special availableMoveSet
        pawn.setAvailableMoveVectors(getAvailableMoveVectorsForPawn(inverseMoveVector, pawn.getColour()));
      }
      else {
        // Set availableMoveVectors to inverseMoveVector
        currentPiece.getAvailableMoveVectors().add(inverseMoveVector);
      }
    }
    else {
      // Set moveable false
      currentPiece.setMoveable(false);
    }
  }

  /**
   * Checks, whether or not the {@code King} is in check and sets the value
   * accordingly. Adds trail to {@code attackKeys}.
   * 
   * @param trail
   * @param opposingPieceCount
   * @param allyPieceInfrontKingDetected
   * @param kingInLine
   */
  private void checkForCheck(List<String> trail, int opposingPieceCount, boolean allyPieceInfrontKingDetected,
      boolean kingInLine) {
    if(opposingPieceCount == 0 && kingInLine && !allyPieceInfrontKingDetected) {
      setInCheck(true);
      getAttackKeys().addAll(trail);
    }
  }

  /**
   * Limits the {@code legalMoveMap} of all {@code Pieces} of the same colour to
   * save the {@code King}.
   * 
   * @param currentGameState
   */
  private void godSaveTheKing(Map<String, Piece> currentGameState) {
    if(getAttackKeys().isEmpty()) {
      return;
    }
    // Empty saviourPieces
    getSaviourPieces().clear();
    // Get all Pieces of the same colour except the King
    List<Piece> piecesOfSameColour = currentGameState.entrySet().stream()
        .filter(x -> x.getValue().getColour() == getColour() && !(x.getValue() instanceof King)).map(x -> x.getValue())
        .toList();
    // Check whether or not at least one key is present
    for(Piece currentPiece : piecesOfSameColour) {
      Map<String, String> safeMap = new TreeMap<>();
      try {
        currentPiece.createLegalMoveMap(currentGameState);
      }
      catch(PieceOutOfBoundsException e) {
        e.printStackTrace();
      }
      boolean safeKeyFound = createSaveMap(currentPiece, safeMap);
      if(safeKeyFound) {
        currentPiece.setLegalMoveMap(safeMap);
        getSaviourPieces().add(currentPiece);
      }
      else {
        currentPiece.getLegalMoveMap().clear();
      }
      currentPiece.setMoveable(false);
    }
  }

  /**
   * Checks, whether or not a safeKey can be found and returns {@code true} or
   * {@code false}. Puts found safeKeys into the {@code safeMap}.
   * 
   * @param currentPiece
   * @param safeMap
   * @return {@code true}, if {@code safeKey} was found
   */
  private boolean createSaveMap(Piece currentPiece, Map<String, String> safeMap) {
    boolean safeKeyFound = false;
    for(Entry<String, String> moveMapEntry : currentPiece.getLegalMoveMap().entrySet()) {
      String currentEntryKey = moveMapEntry.getKey();
      for(String attackKey : getAttackKeys()) {
        if(attackKey.equals(currentEntryKey)) {
          safeMap.put(currentEntryKey, moveMapEntry.getValue());
          safeKeyFound = true;
        }
      }
    }
    return safeKeyFound;
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
      currentGameState.entrySet().stream()
          .filter(x -> (x.getValue().getColour() == getColour()) && x.getValue() instanceof Rook).forEach(x -> {
            Rook rook = (Rook)x.getValue();
            rook.checkForCastle(currentGameState);
            // Check for castle and set castleKeys
            if(!isHasMoved() && !isInCheck() && rook.isCanCastle()) {
              addCastleKeys(rook);
            }
          });
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
    // Remove illegal castleKeys
    removeIllegalCastleKeys();
  }

  /**
   * Adds {@code castleKeys} to the {@code Kings} {@code legalMoveMap}.
   * 
   * @param rook
   */
  private void addCastleKeys(Rook rook) {
    int posLetterAsNumber = getPosLetterAsNumber(getPosition());
    int posNumber = getPosNumber(getPosition());
    if(rook.getCastleSide() == Rook.CASTLE_SIDE_SHORT) {
      getLegalMoveMap().put(getFieldKey(posLetterAsNumber + 2, posNumber), MOVE_STRING);
      setCastleKeyShort(getFieldKey(posLetterAsNumber + 2, posNumber));
    }
    if(rook.getCastleSide() == Rook.CASTLE_SIDE_LONG) {
      getLegalMoveMap().put(getFieldKey(posLetterAsNumber - 2, posNumber), MOVE_STRING);
      setCastleKeyLong(getFieldKey(posLetterAsNumber - 2, posNumber));
    }
  }

  /**
   * Removes all impossible {@code castleKeys} of {@code King}.
   */
  private void removeIllegalCastleKeys() {
    if(getCastleKeyShort() != null) {
      int posLetterAsNumber = getPosLetterAsNumber(getCastleKeyShort());
      int posNumber = getPosNumber(getCastleKeyShort());
      if(getLegalMoveMap().get(getFieldKey(posLetterAsNumber - 1, posNumber)) == null) {
        getLegalMoveMap().remove(getCastleKeyShort());
        setCastleKeyShort(null);
      }
    }
    if(getCastleKeyLong() != null) {
      int posLetterAsNumber = getPosLetterAsNumber(getCastleKeyLong());
      int posNumber = getPosNumber(getCastleKeyLong());
      if(getLegalMoveMap().get(getFieldKey(posLetterAsNumber + 1, posNumber)) == null) {
        getLegalMoveMap().remove(getCastleKeyLong());
        setCastleKeyLong(null);
      }
    }
  }

  /**
   * Checks, if {@code King} is in checkmate.
   */
  public void checkForCheckmate() {
    if(inCheck && getLegalMoveMap().isEmpty() && getSaviourPieces().isEmpty()) {
      setCheckmate(true);
    }
  }
}
