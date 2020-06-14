package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChessServiceTest {

  private RouteProcessor processor;
  private Validator validator;
  private ChessService service;

  @BeforeEach
  public void setup() {
    processor = mock(RouteProcessor.class);
    validator = mock(Validator.class);
    service = new ChessService(processor, validator);
  }

  @Test
  public void test() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.from(1, 1);
    Position end = Position.from(8,8);
    Pawn pawn = mock(Pawn.class);

    List<Position> path = Arrays.asList(start, end);
    when(validator.validate(start, end, terrain, pawn)).thenReturn(true);
    when(processor.createPath(start, end, pawn, terrain)).thenReturn(path);

    // when
    List<Position> result = service.calculatePath(start, end, pawn, terrain);

    // then
    Assertions.assertEquals(path, result);
  }
}
