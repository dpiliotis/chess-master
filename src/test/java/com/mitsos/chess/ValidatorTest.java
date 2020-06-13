package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatorTest {

  Validator validator = new Validator();

  @Test
  public void test() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.valueOf(1, 1);
    Position end = Position.valueOf(8,8);
    when(terrain.isValidPosition(any(Position.class))).thenReturn(true);

    // when
    boolean validate = validator.validate(start, end, terrain);

    // then
    Assertions.assertTrue(validate);
  }

  @Test
  public void testInvalidPosition() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.valueOf(1, 1);
    Position end = Position.valueOf(9,8);
    when(terrain.isValidPosition(eq(start))).thenReturn(true);
    when(terrain.isValidPosition(eq(end))).thenReturn(false);

    // when
    boolean validate = validator.validate(start, end, terrain);

    // then
    Assertions.assertFalse(validate);
  }
}
