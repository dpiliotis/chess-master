package com.mitsos.chess.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {

  private static final Map<String, Position> CACHE = new HashMap<>();

  private final int axisX;
  private final int axisY;

  public static Position from(int axisX, int axisY) {
    String key = axisX + "," + axisY;
    return CACHE.computeIfAbsent(key, k -> new Position(axisX, axisY));
  }

  private Position(int axisX, int axisY) {
    this.axisX = axisX;
    this.axisY = axisY;
  }

  public int getAxisX() {
    return axisX;
  }

  public int getAxisY() {
    return axisY;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return axisX == position.axisX &&
           axisY == position.axisY;
  }

  @Override
  public int hashCode() {
    return Objects.hash(axisX, axisY);
  }

  @Override
  public String toString() {
    return "Position{" +
           "axisX=" + axisX +
           ", axisY=" + axisY +
           '}';
  }
}
