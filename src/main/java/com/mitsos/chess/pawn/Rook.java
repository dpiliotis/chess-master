package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Rook extends AbstractPawn {

  private final List<BiFunction<Position, Integer, Position>> moves = new ArrayList<>();

  public Rook(int range) {
    super(range);
    moves.add((p,r) -> createMove(p, 1, 0, r));
    moves.add((p,r) -> createMove(p, 0, 1, r));
    moves.add((p,r) -> createMove(p, -1, 0, r));
    moves.add((p,r) -> createMove(p, 0, -1, r));
  }

  @Override
  public List<BiFunction<Position, Integer, Position>> getMoves() {
    return moves;
  }

  @Override
  public String toString() {
    return "Tower";
  }
}