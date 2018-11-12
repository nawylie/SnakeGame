package com.nawylie;

import com.nawylie.GameLoop;
import com.nawylie.Grid;
import com.nawylie.Snake;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * SnakeGame.java Where threads are dispached and event handling is done
 * 
 * @author Nick Wylie
 * @version 2018.11.10
 */
public class SnakeGame extends Application {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private GameLoop loop;
	private Grid grid;
	private GraphicsContext context;

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		context = canvas.getGraphicsContext2D();

		// Keypressed Events
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> {
			Snake snake = grid.getSnake();
			if (loop.isKeyPressed()) {
				return;
			}
			switch (e.getCode()) {
			case UP:
				snake.setUp();
				break;
			case DOWN:
				snake.setDown();
				break;
			case LEFT:
				snake.setLeft();
				break;
			case RIGHT:
				snake.setRight();
				break;
			case ESCAPE:
				if (loop.isPaused()) {
					reset();
					(new Thread(loop)).start();
				}
			default:
				break;
			}
		});

		reset();

		root.getChildren().add(canvas);

		Scene scene = new Scene(root);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Snake Game by Nick Wylie");
		primaryStage.setOnCloseRequest(e -> System.exit(0));
		primaryStage.setScene(scene);
		primaryStage.show();

		// Start the game thread
		(new Thread(loop)).start();
	}

	private void reset() {
		grid = new Grid(WIDTH, HEIGHT);
		loop = new GameLoop(grid, context);
		Painter.paint(grid, context);
	}

	// launch the args
	public static void main(String[] args) {
		Application.launch(args);
	}
}