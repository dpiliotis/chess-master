package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Knight;
import com.mitsos.chess.pawn.Pawn;

import java.util.List;

public class App {

  public static void main(String[] args) {

    long init = System.currentTimeMillis();

    Terrain terrain = new Terrain();
    Pawn knight = new Knight();

    Position start = Position.valueOf(1, 1);
    Position end = Position.valueOf(8,8);

    Validator validator = new Validator();
    if (!validator.validate(start, end, terrain)) {
      System.out.println("Invalid input...");
      return;
    }

    RouteProcessor processor = new RouteProcessor();

    List<Position> path = processor.createPath(start, end, knight, terrain);

    System.out.println("Route size: " + path.size());
    System.out.println("Got a route: " + path);
    System.out.println("Job done in " + (System.currentTimeMillis() - init) + "ms");
  }
}
