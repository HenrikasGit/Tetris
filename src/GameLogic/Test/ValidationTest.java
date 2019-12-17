package GameLogic.Test;

import GameLogic.*;
import GameLogic.Position;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    GameBoard gameBoard;
    Validation validation;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ShapeCache.loadCache();
        gameBoard = new GameBoard();
        validation = new Validation(gameBoard);
    }

    @org.junit.jupiter.api.Test
    void isFigureInLimitsFalse() {
        Shape shape = new Shape(3, 1);
        shape.setBlock(0, 0);
        shape.setBlock(1, 1);
        shape.setBlock(2, 2);
        Position position = new Position();
        position.setPosition(gameBoard.getColumnsSize()-2, 0);
        shape.setPosition(position);
        boolean actual = validation.isFigureInLimits(shape);
        assertFalse(actual);
    }
    @org.junit.jupiter.api.Test
    void isFigureInLimitsTrue() {
        Shape shape = new Shape(3, 1);
        shape.setBlock(0, 0);
        shape.setBlock(1, 1);
        Position position = new Position();
        position.setPosition(gameBoard.getColumnsSize()-2, 0);
        shape.setPosition(position);
        boolean actual = validation.isFigureInLimits(shape);
        assertTrue(actual);
    }

    @org.junit.jupiter.api.Test
    void doesFigureFitFalse() {
        Shape shape = new Shape(3, 1);
        shape.setBlock(0, 0);
        shape.setBlock(1, 1);
        Position position = new Position();
        position.setPosition(8, 0);
        shape.setPosition(position);
        gameBoard.setFigure(shape);
        Shape shape1 = new Shape(3, 1);
        shape1.setBlock(1, 1);
        Position position1 = new Position();
        position1.setPosition(8, 0);
        shape1.setPosition(position1);
        boolean actual = validation.canFigureBeThere(shape1);
        assertFalse(actual);
    }
    @org.junit.jupiter.api.Test
    void doesFigureFitTrue() {
        Shape shape = new Shape(3, 1);
        shape.setBlock(0, 0);
        shape.setBlock(1, 1);
        Position position = new Position();
        position.setPosition(8, 0);
        shape.setPosition(position);
        gameBoard.setFigure(shape);
        Shape shape1 = new Shape(3, 1);
        shape1.setBlock(1, 0);
        shape1.setBlock(0, 1);
        Position position1 = new Position();
        position1.setPosition(8, 0);
        shape1.setPosition(position1);
        boolean actual = validation.canFigureBeThere(shape1);
        assertTrue(actual);
    }

    @org.junit.jupiter.api.Test
    void isGameOverTrue() {
        Shape shape = new Shape(1, 1);
        shape.setBlock(0, 0);
        Position position = new Position();
        position.setPosition(0, 0);
        shape.setPosition(position);
        gameBoard.setFigure(shape);
        boolean actual = validation.isGameOver();
        assertTrue(actual);
    }
    @org.junit.jupiter.api.Test
    void isGameOverFalse() {
        Shape shape = new Shape(1, 1);
        shape.setBlock(0, 0);
        Position position = new Position();
        position.setPosition(0, 4);
        shape.setPosition(position);
        gameBoard.setFigure(shape);
        boolean actual = validation.isGameOver();
        gameBoard.print();
        assertFalse(actual);
    }
}