package execute;

import java.util.Random;
import logic.Evolution;
import model.Apple;
import ui.Window;

public class Main {

	// Window title
	public final static String TITLE = "Evolutionary Algorithm";
	// Window width
	public final static int WIDTH = 700;
	// Window height
	public final static int HEIGHT = 800;
	// Size of snake element of food
	public final static int SIZE = 16;
	// Multiplier of position x
	public final static int MULTIX = WIDTH / SIZE;
	// Multiplier of position y
	public final static int MULTIY = HEIGHT / SIZE;
	public static Window window;
	public static Random random;
	public static Apple apple;
	public static Evolution evolution;

	public static void main(String[] args) {
		System.out.println("### " + TITLE + " ###");
		System.out.println(".starts");
		random = new Random();
		evolution = new Evolution(10, 10);
		window = new Window(TITLE, WIDTH, HEIGHT);
		apple = new Apple(100, 100, window);
	}

}
