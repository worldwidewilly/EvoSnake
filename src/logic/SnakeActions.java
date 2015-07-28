package logic;

import java.awt.Rectangle;
import model.Position;
import model.Snake;
import exception.SnakeBiteHimselfsException;
import exception.SnakeOutOfEnergyException;
import execute.Main;


public class SnakeActions {

	public final static int TOP = 35;
	public final static int BORDER = 2;
	private final static int FOODENERGY = 500;

	public static void moveDown(Snake snake) throws SnakeBiteHimselfsException,
			SnakeOutOfEnergyException {
		Position old =
				new Position(snake.getElements().get(0).getX(), snake.getElements().get(0).getY());
		if (snake.getHead().getY() + snake.getSize() > Main.HEIGHT - Main.SIZE) {
			snake.getHead().setY(Main.SIZE);
		} else {
			snake.getElements().get(0).setY(old.getY() + snake.getSize());
		}
		if (!checkBite(snake)) {
			foundApple(snake);
			for (int i = 1; i < snake.getElements().size(); i++) {
				old = updateTail(snake, i, old);
			}
			lostEnergy(snake);
			if (snake.getSteps() <= 0)
				throw new SnakeOutOfEnergyException("Snake lost all of the energy");
		} else
			throw new SnakeBiteHimselfsException("Snake bite himself [down]");
	}

	public static void moveUp(Snake snake) throws SnakeBiteHimselfsException,
			SnakeOutOfEnergyException {
		Position old =
				new Position(snake.getElements().get(0).getX(), snake.getElements().get(0).getY());
		if (snake.getHead().getY() - snake.getSize() < Main.SIZE) {
			snake.getHead().setY(Main.HEIGHT - Main.SIZE);
		} else {
			snake.getElements().get(0).setY(old.getY() - snake.getSize());
		}
		if (!checkBite(snake)) {
			foundApple(snake);
			for (int i = 1; i < snake.getElements().size(); i++) {
				old = updateTail(snake, i, old);
			}
			lostEnergy(snake);
			if (snake.getSteps() <= 0)
				throw new SnakeOutOfEnergyException("Snake lost all of the energy");
		} else
			throw new SnakeBiteHimselfsException("Snake bite himself [up]");
	}

	public static void moveLeft(Snake snake) throws SnakeOutOfEnergyException,
			SnakeBiteHimselfsException {
		Position old =
				new Position(snake.getElements().get(0).getX(), snake.getElements().get(0).getY());
		if (snake.getHead().getX() - snake.getSize() < Main.SIZE) {
			snake.getHead().setX(Main.WIDTH - Main.SIZE);
		} else {
			snake.getElements().get(0).setX(old.getX() - snake.getSize());
		}
		if (!checkBite(snake)) {
			foundApple(snake);
			for (int i = 1; i < snake.getElements().size(); i++) {
				old = updateTail(snake, i, old);
			}
			lostEnergy(snake);
			if (snake.getSteps() <= 0)
				throw new SnakeOutOfEnergyException("Snake lost all of the energy");
		} else
		throw new SnakeBiteHimselfsException("Snake bite himself [left]");
	}

	public static void moveRight(Snake snake) throws SnakeOutOfEnergyException,
			SnakeBiteHimselfsException {
		Position old =
				new Position(snake.getElements().get(0).getX(), snake.getElements().get(0).getY());
		if (snake.getHead().getX() + snake.getSize() > Main.WIDTH - Main.SIZE) {
			snake.getHead().setX(Main.SIZE);
		} else {
			snake.getElements().get(0).setX(old.getX() + snake.getSize());
		}
		if (!checkBite(snake)) {
			foundApple(snake);
			for (int i = 1; i < snake.getElements().size(); i++) {
				old = updateTail(snake, i, old);
			}
			lostEnergy(snake);
			if (snake.getSteps() <= 0)
				throw new SnakeOutOfEnergyException("Snake lost all of the energy");
		} else
			throw new SnakeBiteHimselfsException("Snake bite himself [right]");
	}

	private static boolean checkBite(Snake snake) {
		final Rectangle head =
				new Rectangle(snake.getHead().getX(), snake.getHead().getY(), snake.getSize(),
						snake.getSize());
		for (int i = 1; i < snake.getElements().size(); i++) {
			Rectangle tailElement =
					new Rectangle(snake.getElements().get(i).getX(), snake.getElements().get(i)
							.getY(), snake.getSize(), snake.getSize());
			if (head.intersects(tailElement)) return true;
		}
		return false;
	}

	private static Position updateTail(Snake snake, int index, Position position) {
		Position returnPosition = snake.getElements().get(index);
		snake.getElements().set(index, position);
		return returnPosition;
	}

	private static void foundApple(Snake snake) {
		final Rectangle head =
				new Rectangle(snake.getHead().getX(), snake.getHead().getY(), snake.getSize(),
						snake.getSize());
		final Rectangle food =
				new Rectangle(Main.apple.getX(), Main.apple.getY(), Main.SIZE, Main.SIZE);
		if (head.intersects(food)) {
			snake.addElement();
			eatFood(snake);
			Main.apple.setRandomPosition();
		}
	}

	private static void lostEnergy(Snake snake) {
		snake.setSteps(snake.getSteps() - 1);
	}

	private static void eatFood(Snake snake) {
		snake.setSteps(snake.getSteps() + FOODENERGY);
	}
}
