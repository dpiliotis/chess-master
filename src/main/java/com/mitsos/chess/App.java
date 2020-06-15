package com.mitsos.chess;

import com.mitsos.chess.model.Position;
import com.mitsos.chess.model.Terrain;
import com.mitsos.chess.pawn.Bishop;
import com.mitsos.chess.pawn.Knight;
import com.mitsos.chess.pawn.Pawn;
import com.mitsos.chess.pawn.Queen;
import com.mitsos.chess.pawn.Rook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {

    Terrain terrain = Terrain.getInstance();
    Pawn knight = new Knight();
    Pawn queen = new Queen(terrain.getLength());
    Pawn rook = new Rook(terrain.getLength());
    Pawn bishop = new Bishop(terrain.getLength());

    ChessService service = new ChessService(new RouteProcessor(), new Validator());

    Pawn test;
    firstScreen();

    try (Scanner in = new Scanner(System.in)) {
      while (true) {
        menu();

        String input = in.nextLine();
        switch (input) {
          case "1":
            test = knight;
            break;
          case "2":
            test = queen;
            break;
          case "3":
            test = bishop;
            break;
          case "4":
            test = rook;
            break;
          case "5": return;
          default:
            logger.warn("Invalid choose");
            continue;
        }

        logger.info("Set starting point (A1 to H8):");
        Position start = Position.from(in.nextLine());
        logger.info("Set destination point (A1 to H8):");
        Position end = Position.from(in.nextLine());

        service.calculatePath(start, end, test, terrain);
      }
    } catch (RuntimeException e) {
      logger.error("Something went wrong...", e);
    }
  }

  private static void firstScreen() {
    logger.info("================================");
    logger.info("=== Critical Path Calculator ===");
    logger.info("================================");
  }

  private static void menu() {
    logger.info("================================");
    logger.info("** Choose pawn **");
    logger.info("1. Knight");
    logger.info("2. Queen");
    logger.info("3. Bishop");
    logger.info("4. Rook");
    logger.info("5. Exit");
  }
}
