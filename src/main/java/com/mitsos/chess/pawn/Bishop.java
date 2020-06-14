package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Bishop extends AbstractPawn {

  private final List<BiFunction<Position, Integer, Position>> moves = new ArrayList<>();

  public Bishop(int range) {
    super(range);
    moves.add((p,r) -> createMove(p, 1, 1, r));
    moves.add((p,r) -> createMove(p, 1, -1, r));
    moves.add((p,r) -> createMove(p, -1, 1, r));
    moves.add((p,r) -> createMove(p, -1, -1, r));
  }

  @Override
  public List<BiFunction<Position, Integer, Position>> getMoves() {
    return moves;
  }

  @Override
  public boolean validDestination(Position start, Position end) {

    int x = Integer.sum(end.getAxisX(), start.getAxisX()) % 2;
    int y = Integer.sum(end.getAxisY(), start.getAxisY()) % 2;

    return x == y;
  }

  @Override
  public String toString() {
    return "Bishop";
  }
}