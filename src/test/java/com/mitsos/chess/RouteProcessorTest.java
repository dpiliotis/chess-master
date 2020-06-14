package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.AbstractPawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouteProcessorTest {

  private RouteProcessor processor = new RouteProcessor();

  @Test
  public void test() {
    // given
    Terrain terrain = mock(Terrain.class);
    Position start = Position.from(1,1);
    Position intermediate = Position.from(4, 4);
    Position end = Position.from(8,8);
    AbstractPawn pawn = mock(AbstractPawn.class);

    when(pawn.moves(start, terrain)).thenReturn(Collections.singletonList(intermediate));
    when(pawn.moves(intermediate, terrain)).thenReturn(Collections.singletonList(end));

    // when
    List<Position> path = processor.createPath(start, end, pawn, terrain);

    // then
    Assertions.assertEquals(3, path.size());
    Assertions.assertTrue(path.contains(start));
    Assertions.assertTrue(path.contains(intermediate));
    Assertions.assertTrue(path.contains(end));
  }
}
