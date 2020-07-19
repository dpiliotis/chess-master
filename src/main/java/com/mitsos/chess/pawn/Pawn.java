package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;

import java.util.Set;

public interface Pawn {

  Set<Position> moves(Position position, Terrain terrain);
  boolean validDestination(Position start, Position end);
}
