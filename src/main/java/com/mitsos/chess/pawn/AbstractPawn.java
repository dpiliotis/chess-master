package com.mitsos.chess.pawn;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractPawn implements Pawn {

  private final int range;

  public AbstractPawn(int range) {
    this.range = range;
  }

  protected int getRange() {
    return range;
  }

  protected abstract List<BiFunction<Position, Integer, Position>> getMoves();

  @Override
  public boolean validDestination(Position start, Position end) {
    return true;
  }

  public Set<Position> moves(Position position, Terrain terrain) {

    return IntStream
        .rangeClosed(1, getRange())
        .boxed()
        .map(r -> getMoves().stream().map(f -> f.apply(position, r)).collect(Collectors.toList()))
        .flatMap(Collection::stream)
        .filter(terrain::isValidPosition)
        .collect(Collectors.toSet());
  }

  protected Position createMove(Position position, int newX, int newY, int range) {
    int x = position.getAxisX() + (newX * range);
    int y = position.getAxisY() + (newY * range);
    return Position.from(x, y);
  }
}
