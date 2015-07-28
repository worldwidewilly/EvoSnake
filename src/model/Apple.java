package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import logic.SnakeActions;
import execute.Main;

public class Apple extends Position {

	private Component comp;
	private final Color COLOR = Color.GREEN;

	public Apple(int x, int y, Component comp) {
		super(x, y);
		this.comp = comp;
	}

	public void paint(Graphics gr) {
		gr.setColor(COLOR);
		gr.fillRect(super.getX(), super.getY(), Main.SIZE, Main.SIZE);
	}

	public void setRandomPosition() {
		int x = Main.random.nextInt(Main.SIZE) * Main.MULTIX;
		int y = Main.random.nextInt(Main.SIZE) * Main.MULTIY;
		while (x < Main.SIZE + SnakeActions.BORDER || x > Main.WIDTH)
			x = Main.random.nextInt(Main.SIZE) * Main.MULTIX;
		while (y < Main.SIZE + SnakeActions.TOP || y > Main.HEIGHT)
			y = Main.random.nextInt(Main.SIZE) * Main.MULTIY;
		super.setX(x);
		super.setY(y);
	}

}
