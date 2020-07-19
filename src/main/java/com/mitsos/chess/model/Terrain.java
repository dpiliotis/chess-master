package com.mitsos.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Terrain {

  private final int length;
  private final List<Position> positions = new ArrayList<>();

  public Terrain(int length) {

    this.length = length;

    for (int x = 1; x <= length; x++) {
      for (int y = 1; y <= length; y++) {
        positions.add(Position.from(x, y));
      }
    }
  }

  public boolean isValidPosition(Position position) {
    return positions.contains(position);
  }

  public int getLength() {
    return length;
  }

}
