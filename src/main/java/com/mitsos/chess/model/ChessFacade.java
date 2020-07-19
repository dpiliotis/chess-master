package com.mitsos.chess.model;

import java.util.regex.Pattern;

/**
 * Chess terrain 8x8
 */
public class ChessFacade {

  private static final Terrain TERRAIN = new Terrain(8);
  private static final Pattern POSITION_PATTERN = Pattern.compile("[a-hA-H][1-8]");

  private ChessFacade() { }

  public static Terrain getTable() {
    return TERRAIN;
  }

  public static Position positionFrom(String str) {
    String field = str.trim();
    if (!POSITION_PATTERN.matcher(field).matches()) {
      return null;
    }

    int x = Character.toUpperCase(field.charAt(0)) - 'A' + 1;
    int y = Character.getNumericValue(field.charAt(1));

    return Position.from(x, y);
  }

  public static String prettyPrint(Position position) {
    char letter = (char) ('A' + position.getAxisX() - 1);
    return String.valueOf(letter) + position.getAxisY();
  }
}
