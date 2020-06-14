package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Bishop;
import com.mitsos.chess.pawn.Knight;
import com.mitsos.chess.pawn.Pawn;
import com.mitsos.chess.pawn.Queen;
import com.mitsos.chess.pawn.Tower;

public class App {

  public static void main(String[] args) {

    Terrain terrain = Terrain.getInstance();
    Position start = Position.from(1, 1);
    Position end = Position.from(8,8);

    Pawn knight = new Knight();
    Pawn queen = new Queen(terrain.getLength());
    Pawn tower = new Tower(terrain.getLength());
    Pawn bishop = new Bishop(terrain.getLength());

    ChessService service = new ChessService(new RouteProcessor(), new Validator());

    service.calculatePath(start, end, knight, terrain);
    service.calculatePath(start, end, queen, terrain);
    service.calculatePath(start, end, bishop, terrain);
    service.calculatePath(start, end, tower, terrain);
  }
}
