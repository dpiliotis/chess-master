package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Knight extends AbstractPawn {

  private final List<BiFunction<Position, Integer, Position>> moves = new ArrayList<>();

  public Knight() {
    super(1);
    moves.add((p,r) -> createMove(p, 2, 1, r));
    moves.add((p,r) -> createMove(p, 2, -1, r));
    moves.add((p,r) -> createMove(p, -2, 1, r));
    moves.add((p,r) -> createMove(p, -2, -1, r));
    moves.add((p,r) -> createMove(p, 1, 2, r));
    moves.add((p,r) -> createMove(p, 1, -2, r));
    moves.add((p,r) -> createMove(p, -1, 2, r));
    moves.add((p,r) -> createMove(p, -1, -2, r));
  }

  @Override
  public List<BiFunction<Position, Integer, Position>> getMoves() {
    return moves;
  }

  @Override
  public String toString() {
    return "Knight";
  }
}
