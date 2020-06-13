package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Tower extends Pawn {

  private final List<BiFunction<Position, Integer, Position>> moves = new ArrayList<>();

  public Tower(Terrain terrain) {
    super(terrain.getLength());
    moves.add((p,r) -> createMove(p, 1, 0, r));
    moves.add((p,r) -> createMove(p, 0, 1, r));
    moves.add((p,r) -> createMove(p, -1, 0, r));
    moves.add((p,r) -> createMove(p, 0, -1, r));
  }

  @Override
  protected List<BiFunction<Position, Integer, Position>> getMoves() {
    return moves;
  }

  @Override
  public String toString() {
    return "Tower";
  }
}