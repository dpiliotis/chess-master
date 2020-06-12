package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Pawn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteProcessor {

  /**
   * Returns a correct path with the minimum number of moves.
   *
   * @param start
   * @param end
   * @param pawn
   * @param terrain
   * @return critical path
   */
  public List<Position> createPath(Position start, Position end, Pawn pawn, Terrain terrain) {

    List<Position> correctPath;
    List<Deque<Position>> paths = new ArrayList<>();

    paths.add(new LinkedList<>(Collections.singleton(start))); // set first path

    do {
      correctPath = processPaths(pawn, terrain, paths, end);
    }
    while (correctPath.isEmpty());

    return correctPath;
  }

  private List<Position> processPaths(Pawn pawn, Terrain terrain, List<Deque<Position>> previousPaths, Position end) {

    List<Deque<Position>> newPaths = new ArrayList<>();

    for (Deque<Position> previous : previousPaths) {

      List<Position> next = pawn.moves(previous.getLast(), terrain);

      if (next.contains(end)) {
        return new ArrayList<>(getPath(previous, end));
      }

      next.stream()
          .filter(position -> notExistsInOtherPaths(previousPaths, position))
          .map(position -> getPath(previous, position))
          .forEach(newPaths::add);
    }

    // mutate original paths
    previousPaths.clear();
    previousPaths.addAll(newPaths);

    return Collections.emptyList();
  }

  private boolean notExistsInOtherPaths(List<Deque<Position>> previousPaths, Position position) {

    return previousPaths.stream() // if the terrain is too big consider parallel stream here
                        .noneMatch(otherPaths -> otherPaths.contains(position));
  }

  private Deque<Position> getPath(Deque<Position> previous, Position next) {
    return Stream.concat(previous.stream(), Stream.of(next))
                 .collect(Collectors.toCollection(LinkedList::new));
  }
}
