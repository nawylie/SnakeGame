package com.nawylie;

import javafx.scene.paint.Color;

/**
 * A simple point class to represent the food position.
 * 
 * The food of choice is a Green Apple ;)
 * 
 * @author Nick Wylie
 * @version 2018.11.10
 */
public class Food {
	public static final Color COLOR = Color.LIGHTGREEN;

	private Point point;

	Food(Point point) {
		this.point = point;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
}