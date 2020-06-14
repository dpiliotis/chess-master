package com.mitsos.chess;

import com.mitsos.chess.pawn.Pawn;
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
    Position start = Position.from(1, 1);
    Position end = Position.from(8,8);
    Pawn pawn = mock(Pawn.class);

    when(terrain.isValidPosition(any(Position.class))).thenReturn(true);
    when(pawn.validDestination(start, end)).thenReturn(true);

    // when
    boolean validate = validator.validate(start, end, terrain, pawn);

    // then
    Assertions.assertTrue(validate);
  }

  @Test
  public void testInvalidPosition() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.from(1, 1);
    Position end = Position.from(9,8);
    Pawn pawn = mock(Pawn.class);
    when(terrain.isValidPosition(eq(start))).thenReturn(true);
    when(terrain.isValidPosition(eq(end))).thenReturn(false);
    when(pawn.validDestination(start, end)).thenReturn(true);

    // when
    boolean validate = validator.validate(start, end, terrain, pawn);

    // then
    Assertions.assertFalse(validate);
  }

  @Test
  public void testInvalidDestination() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.from(1, 1);
    Position end = Position.from(7,8);
    Pawn pawn = mock(Pawn.class);
    
    when(terrain.isValidPosition(any(Position.class))).thenReturn(true);
    when(pawn.validDestination(start, end)).thenReturn(false);

    // when
    boolean validate = validator.validate(start, end, terrain, pawn);

    // then
    Assertions.assertFalse(validate);
  }
}
