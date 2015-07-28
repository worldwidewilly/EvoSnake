package logic;

import model.Snake;
import execute.Main;

public class Evolution {

	// Array with all snakes
	public static Snake[] allSnakes;
	// Number of top snakes
	private final int TOPSNAKES;
	// Number of generations
	private final int GENERATIONS;
	// Current generation
	private int currentGeneration;
	// Number of population
	private int population;
	// Number to choose random steps by direction
	private final int MAXSTEPS = 25;
	// Number to choose random direction
	private final int CHOOSEDIRECTION = 5;

	public Evolution(int generations, int population) {
		this.GENERATIONS = generations;
		this.currentGeneration = 1;
		this.population = population;
		this.allSnakes = new Snake[population];
		this.TOPSNAKES = population / 2;
		init();
	}

	private void init() {
		for (int i = 0; i < population; i++)
			allSnakes[i] = createSnake(i);
	}

	private Snake createSnake(int index) {
		int down = Main.random.nextInt(MAXSTEPS);
		int up = Main.random.nextInt(MAXSTEPS);
		int right = Main.random.nextInt(MAXSTEPS);
		int left = Main.random.nextInt(MAXSTEPS);
		return new Snake(down, up, right, left, currentGeneration, index, Main.window);
	}

	public int getGenerations() {
		return GENERATIONS;
	}

	public int getPopulation() {
		return population;
	}

	// Sort all snakes by length of elements form long to short
	public void sortSnake() {
		boolean unsorted = true;
		while (unsorted) {
			unsorted = false;
			for (int i = 0; i < allSnakes.length - 1; i++) {
				if (allSnakes[i].getElements().size() < allSnakes[i + 1].getElements().size()) {
					Snake temp = allSnakes[i];
					allSnakes[i] = allSnakes[i + 1];
					allSnakes[i + 1] = temp;
					unsorted = true;
				}
			}
		}
	}

	// Generate a new generation of snakes
	public void getNewGeneration() {
		sortSnake();
		resetTop();
		currentGeneration++;
		int index = 0;
		for (int i = TOPSNAKES; i < allSnakes.length; i++) {
			Snake snake =
					new Snake(allSnakes[index].getDown(), allSnakes[index].getUp(),
							allSnakes[index].getRight(), allSnakes[index].getLeft(),
							currentGeneration, i, Main.window);
			snake.setOrder(allSnakes[index].getOrder());
			final int DIRECTION = Main.random.nextInt(CHOOSEDIRECTION);
			final int NEWVALUE = Main.random.nextInt(MAXSTEPS);
			changeOption(snake, DIRECTION, NEWVALUE);
			allSnakes[i] = snake;
			index++;
		}
		System.out
				.println("############################ NEXT GENERATION IT IS YOUR TURN ############################");
	}

	// Print and reset top snakes
	private void resetTop() {
		System.out.println("### LIST OF TOP SNAKES ###");
		for (int i = 0; i < TOPSNAKES; i++) {
			System.out.println(allSnakes[i].getName() + " [ " + allSnakes[i].getElements().size()
					+ " ]");
			allSnakes[i].resetSnake();
		}
		System.out.println("### ###");
	}

	// Change the value of a direction
	private void changeOption(Snake snake, int direction, int newValue) {
		switch (direction) {
			case 1: {
				snake.setDown(newValue);
				break;
			}
			case 2: {
				snake.setUp(newValue);
				break;
			}
			case 3: {
				snake.setRight(newValue);
				break;
			}
			case 4: {
				snake.setLeft(newValue);
				break;
			}
		}
	}

}
