package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Pawn;

import java.util.Objects;
import java.util.stream.Stream;

public class Validator {

  /**
   * Validates if the input data are valid and the path could be build.
   *
   * @param start starting position
   * @param end final position
   * @param pawn the pawn that is going to move
   * @param terrain chess terrain
   * @return true if all parameters are valid, false otherwise.
   */
  public boolean validate(Position start, Position end, Terrain terrain, Pawn pawn) {

    if (Stream.of(terrain, start, end, pawn).anyMatch(Objects::isNull)) {
      return false;
    }

    if (start.equals(end)) {
      return false;
    }

    if (!Stream.of(start, end).allMatch(terrain::isValidPosition)) {
      return false;
    }

    return pawn.validDestination(start, end);
  }
}
