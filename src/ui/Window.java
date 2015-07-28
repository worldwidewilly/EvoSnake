package ui;

import java.awt.Graphics;
import javax.swing.JFrame;
import logic.SnakeActions;
import model.Snake;
import execute.Main;

public class Window extends JFrame implements Runnable {

	// Time for Thread.sleep()
	private final int SLEEPTIME = 20;
	// Current population counter
	private int popuationCounter;
	// Current generation counter
	private int generationCounter;

	public Window(String title, int width, int height) {
		super.setTitle(title);
		super.setSize(width, height);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
		this.popuationCounter = 0;
		this.generationCounter = 0;
		new Thread(this).start();
	}

	public void paint(Graphics gr) {
		super.paint(gr);
		// Draw apple
		Main.apple.paint(gr);
		// Draw snake
		Main.evolution.allSnakes[popuationCounter].paint(gr);
	}

	private void moveDirection(int index, Snake snake) throws Exception {
		switch (index) {
			case 1: {
				for (int i = 0; i < snake.getDown(); i++) {
					SnakeActions.moveDown(snake);
					super.repaint();
					Thread.sleep(SLEEPTIME);
				}
				break;
			}
			case 2: {
				for (int i = 0; i < snake.getUp(); i++) {
					SnakeActions.moveUp(snake);
					super.repaint();
					Thread.sleep(SLEEPTIME);
				}
				break;
			}
			case 3: {
				for (int i = 0; i < snake.getRight(); i++) {
					SnakeActions.moveRight(snake);
					super.repaint();
					Thread.sleep(SLEEPTIME);
				}
				break;
			}
			case 4: {
				for (int i = 0; i < snake.getLeft(); i++) {
					SnakeActions.moveLeft(snake);
					super.repaint();
					Thread.sleep(SLEEPTIME);
				}
				break;
			}
		}
	}

	private void move(Snake snake) {
		boolean bite = false;
		while (!bite) {
			for (int i = 0; i < snake.getOrder().size(); i++) {
				try {
					moveDirection(snake.getOrder().get(i).getDirectionCode(), snake);
				} catch (Exception ex) {
					System.out.println("Snake " + snake.getName() + " is dead\n" + ex.toString());
					System.out.println(snake.toString());
					return;
				}
			}
		}
	}

	@Override
	public void run() {
		while (Main.evolution.getGenerations() > generationCounter) {
			for (int i = 0; i < Main.evolution.allSnakes.length; i++) {
				popuationCounter = i;
				move(Main.evolution.allSnakes[i]);
				Main.apple.setRandomPosition();
			}
			generationCounter++;
			Main.evolution.getNewGeneration();
		}
		System.out.println(".ends");
	}
}
