package com.mitsos.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Terrain {

  private static final int LENGTH = 8;

  private final List<Position> positions = new ArrayList<>();

  public Terrain() {

    for (int x = 1; x <= LENGTH; x++) {
      for (int y = 1; y <= LENGTH; y++) {
        positions.add(Position.valueOf(x, y));
      }
    }
  }

  public boolean isValidPosition(Position position) {
    return positions.contains(position);
  }

  public int getLength() {
    return LENGTH;
  }

}
