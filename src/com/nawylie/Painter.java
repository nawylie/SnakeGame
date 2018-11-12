package com.nawylie;

import com.nawylie.Food;
import com.nawylie.Grid;
import com.nawylie.Point;
import com.nawylie.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.nawylie.Grid.SIZE;

/**
 * A Painter class for painting different elements of the game based on events
 * 
 * @author Nick Wylie
 * @version 2018.11.10
 */
public class Painter {

	/**
	 * 
	 * 
	 * @param grid The grid the objects to paint reside in
	 * @param gc   
	 */
	public static void paint(Grid grid, GraphicsContext gc) {
		// Grid
		gc.setFill(Grid.COLOR);
		gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

		// Food
		gc.setFill(Food.COLOR);
		paintPoint(grid.getFood().getPoint(), gc);

		// Snake
		Snake snake = grid.getSnake();
		gc.setFill(Snake.COLOR);
		snake.getPoints().forEach(point -> paintPoint(point, gc));
		if (!snake.isSafe()) {
			gc.setFill(Snake.DEAD);
			paintPoint(snake.getHead(), gc);
		}

		// Score
		gc.setFill(Color.WHITE);
		gc.fillText("Score: " + 1 * snake.getPoints().size(), 10, 20);
	}

	/**
	 * Method to paint a specified point on the grid
	 * 
	 * @param point The point to paint a color
	 * @param gc
	 */
	private static void paintPoint(Point point, GraphicsContext gc) {
		gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
	}

	/**
	 * Paint and display the "restart" message
	 * 
	 * @param gc
	 */
	public static void paintResetMessage(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillText("Press ESC to restart.", 200, 230);
	}
}