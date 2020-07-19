package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Pawn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteProcessor {

  /**
   * Returns a correct path with the minimum number of moves.
   *
   * @param start starting position
   * @param end final position
   * @param pawn the pawn that is going to move
   * @param terrain chess terrain
   * @return critical path
   */
  public List<Position> createPath(Position start, Position end, Pawn pawn, Terrain terrain) {

    List<Position> correctPath;
    List<Deque<Position>> paths = new ArrayList<>();
    Set<Position> previousPositions = new HashSet<>();

    paths.add(new LinkedList<>(Collections.singleton(start))); // set first path

    do {
      correctPath = processPaths(pawn, terrain, end, paths, previousPositions);
    } while (correctPath.isEmpty());

    return correctPath;
  }

  private List<Position> processPaths(Pawn pawn,
                                      Terrain terrain,
                                      Position end,
                                      List<Deque<Position>> previousPaths,
                                      Set<Position> previousPositions) {

    List<Deque<Position>> newPaths = new ArrayList<>();

    for (Deque<Position> previous : previousPaths) {

      Set<Position> nextMoves = pawn.moves(previous.getLast(), terrain);

      if (nextMoves.contains(end)) {
        return new ArrayList<>(buildPath(previous, end));
      }

      nextMoves.stream()
               .filter(position -> !previousPositions.contains(position))
               .forEach(position -> {
                 newPaths.add(buildPath(previous, position));
                 previousPositions.add(position);
               });
    }

    // mutate original paths
    previousPaths.clear();
    previousPaths.addAll(newPaths);

    return Collections.emptyList();
  }

  private Deque<Position> buildPath(Deque<Position> previous, Position next) {
    return Stream.concat(previous.stream(), Stream.of(next))
                 .collect(Collectors.toCollection(LinkedList::new));
  }
}
