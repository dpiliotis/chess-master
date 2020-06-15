package com.mitsos.chess.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Position {

  private static final Map<String, Position> CACHE = new HashMap<>();
  private static final Pattern POSITION_PATTERN = Pattern.compile("[a-hA-H][1-8]");

  private final int axisX;
  private final int axisY;

  public static Position from(int axisX, int axisY) {
    String key = axisX + "," + axisY;
    return CACHE.computeIfAbsent(key, k -> new Position(axisX, axisY));
  }

  public static Position from(String str) {
    String field = str.trim();
    if (!POSITION_PATTERN.matcher(field).matches()) {
      return null;
    }

    int x = Character.toUpperCase(field.charAt(0)) - 'A' + 1;
    int y = Character.getNumericValue(field.charAt(1));

    return Position.from(x, y);
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

  public String prettyPrint() {
    char letter = (char) ('A' + getAxisX() - 1);
    return String.valueOf(letter) + getAxisY();
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
