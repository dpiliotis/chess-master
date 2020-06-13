package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Bishop;
import com.mitsos.chess.pawn.Knight;
import com.mitsos.chess.pawn.Pawn;
import com.mitsos.chess.pawn.Queen;
import com.mitsos.chess.pawn.Tower;

import java.util.List;

public class App {

  public static void main(String[] args) {

    Terrain terrain = new Terrain();
    Pawn knight = new Knight();
    Pawn queen = new Queen(terrain);
    Pawn tower = new Tower(terrain);
    Pawn bishop = new Bishop(terrain);

    Position start = Position.valueOf(1, 1);
    Position end = Position.valueOf(8,8);

    Validator validator = new Validator();
    if (!validator.validate(start, end, terrain)) {
      System.out.println("Invalid input...");
      return;
    }

    calculatePath(terrain, knight, start, end);
    calculatePath(terrain, queen, start, end);
    calculatePath(terrain, bishop, start, end);
    calculatePath(terrain, tower, start, end);
  }

  private static void calculatePath(Terrain terrain, Pawn pawn, Position start, Position end) {
    long init = System.currentTimeMillis();
    RouteProcessor processor = new RouteProcessor();

    List<Position> path = processor.createPath(start, end, pawn, terrain);

    System.out.println("Pawn: " + pawn);
    System.out.println("Route size: " + path.size());
    System.out.println("Got a route: " + path);
    System.out.println("Job done in " + (System.currentTimeMillis() - init) + "ms");
  }
}
