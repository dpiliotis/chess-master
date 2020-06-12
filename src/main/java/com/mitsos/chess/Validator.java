package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;

import java.util.Objects;
import java.util.stream.Stream;

public class Validator {

  public boolean validate(Position start, Position stop, Terrain terrain) {

    Objects.requireNonNull(terrain);
    Objects.requireNonNull(start);
    Objects.requireNonNull(stop);

    if (start.equals(stop)) {
      return false;
    }

    return Stream.of(start, stop).allMatch(terrain::isValidPosition);
  }
}
