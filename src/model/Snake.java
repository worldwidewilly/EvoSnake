package model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import execute.Main;

public class Snake {

	private List<Position> elements;
	public final int ENERGY = 1000;
	private int down;
	private int up;
	private int right;
	private int left;
	private int steps;
	private String name;
	private List<Direction> order;
	private Component comp;
	private Color color;

	public Snake(int down, int up, int right, int left, int generation, int type, Component comp) {
		this.elements = new ArrayList<Position>();
		this.elements.add(new Position(Main.SIZE, Main.SIZE));
		this.down = down;
		this.up = up;
		this.right = right;
		this.left = left;
		this.steps = ENERGY;
		this.name = "Ringo " + generation + "." + type;
		if (generation == 1) {
			initOrder();
		}
		this.comp = comp;
	}

	public void setOrder(List<Direction> order) {
		this.order = order;
	}

	private void initOrder() {
		List<Direction> directions = new ArrayList<Direction>();
		order = new ArrayList<Direction>();
		directions.add(Direction.DOWN);
		directions.add(Direction.UP);
		directions.add(Direction.RIGHT);
		directions.add(Direction.LEFT);
		while (directions.size() > 1) {
			int item = Main.random.nextInt(directions.size());
			order.add(directions.get(item));
			directions.remove(item);
		}
		order.add(directions.get(0));
		directions.remove(0);
	}

	public void addElement() {
		Position element =
				new Position(elements.get(elements.size() - 1).getX(), elements.get(
						elements.size() - 1).getY()
						- Main.SIZE);
		this.elements.add(element);
	}

	public Position getHead() {
		return this.elements.get(0);
	}

	public int getDown() {
		return down;
	}

	public int getUp() {
		return up;
	}

	public int getRight() {
		return right;
	}

	public int getLeft() {
		return left;
	}

	public int getSize() {
		return Main.SIZE;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	private int getByDirection(int index) {
		switch (index) {
			case 1: {
				return this.down;
			}
			case 2: {
				return this.up;
			}
			case 3: {
				return this.right;
			}
			case 4: {
				return this.left;
			}
		}
		return 0;
	}

	public List<Direction> getOrder() {
		return this.order;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public void resetSnake() {
		this.steps = ENERGY;
		this.elements.clear();
		this.elements.add(new Position(0, 0));
	}

	public String getName() {
		return name;
	}

	public List<Position> getElements() {
		return this.elements;
	}
	
	public void clearElements() {
		this.elements.clear();
	}

	public void paint(Graphics gr) {
		for (int i = 0; i < elements.size(); i++) {
			gr.setColor(Color.RED);
			gr.fillRect(elements.get(i).getX(), elements.get(i).getY(), Main.SIZE, Main.SIZE);
		}
	}

	@Override
	public String toString() {
		String result = "Snake " + name + " had " + elements.size();
		for (Direction d : order) {
			result += "\n" + d.toString() + ": " + getByDirection(d.getDirectionCode());
		}
		return result;
	}
}
