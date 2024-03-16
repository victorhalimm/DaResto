package game;

import java.util.Scanner;

import models.Restaurant;
import singletons.ActiveRestaurant;

public class InputListener implements Runnable{
	
	private static InputListener instance;
	
	Scanner sc = new Scanner(System.in);
	
	private boolean isOn;
	private boolean isPaused;

	
	private ActiveRestaurant restaurant;
	
	private InputListener() {
		isOn = true;
		isPaused = false;
		restaurant = ActiveRestaurant.getInstance();
	}
	
	public static InputListener getInstance() {
		if (instance == null) {
			instance = new InputListener();
		}
		
		return instance;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isOn) {
			if (!isPaused) {
				sc.nextLine();
				restaurant.pauseGame();
				isPaused = true;
			}
			else {
				sc.nextLine();
				restaurant.resumeGame();
				isPaused = false;
			}
		}
	}
}
