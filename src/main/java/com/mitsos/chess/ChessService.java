package com.mitsos.chess;

import com.mitsos.chess.model.ChessFacade;
import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Pawn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

  private static final Logger logger = LoggerFactory.getLogger(ChessService.class);

  private final RouteProcessor processor;
  private final Validator validator;

  public ChessService(RouteProcessor processor, Validator validator) {
    this.processor = processor;
    this.validator = validator;
  }

  /**
   * Calculates a critical path between the two positions.
   *
   * @param start starting position
   * @param end final position
   * @param pawn the pawn that is going to move
   * @param terrain chess terrain
   * @return a critical path
   */
  public List<Position> calculatePath(Position start, Position end, Pawn pawn, Terrain terrain) {
    long init = System.currentTimeMillis();
    logger.info("Pawn: {}", pawn);

    if (!validator.validate(start, end, terrain, pawn)) {
      logger.error("Invalid input...");
      return Collections.emptyList();
    }

    List<Position> path = processor.createPath(start, end, pawn, terrain);

    logger.info("Route size: {}", (path.size() - 1));
    logger.info("Got a route: {}", formatPath(path));
    logger.info("Job done in {}ms", (System.currentTimeMillis() - init));

    return path;
  }

  private String formatPath(List<Position> path) {
    return path.stream().map(ChessFacade::prettyPrint).collect(Collectors.joining(" -> "));
  }
}
