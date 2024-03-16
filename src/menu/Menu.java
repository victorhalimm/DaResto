package menu;

import java.util.Scanner;

import game.Game;
import singletons.ActiveRestaurant;
import singletons.HighScoreManager;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private HighScoreManager scoreManager;
	
	
	public Menu() {
		this.scoreManager = HighScoreManager.getInstance();
	}

	public void startMenu() {
		showAscii();
		showMenuList();
		navigate();
	}
	
	private void showAscii() {
		System.out.println(" ____      _____         _       ");
		System.out.println("|    \\ ___| __  |___ ___| |_ ___ ");
		System.out.println("|  |  | .'|    -| -_|_ -|  _| . |");
		System.out.println("|____/|__,|__|__|___|___|_| |___|");
		System.out.println("");
	}
	
	private void showMenuList() {
		System.out.println("1. Play New Restaurant");	
		System.out.println("2. High Score");	
		System.out.println("3. Exit");	
	}
	
	private void navigate() {
		int inp;
		do {
			String inpMenu;
			System.out.print(">> ");
			inpMenu = sc.nextLine();
			
			try {
				inp = Integer.parseInt(inpMenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				inp = -1;
			}
		} while (inp < 1 || inp > 3);
		
		if (inp == 1) initiateRestaurant();
		else if (inp == 2) scoreManager.printScoreTable();
		else System.exit(0);
	}
	
	private void cls() {
		System.out.print("\033[H\033[2J");  
	}
	
	private void initiateRestaurant() {
		cls();
		String name;
		do {
			System.out.print("Enter a name for your restaurant [Must be more than 4 letters]: ");
			name = sc.nextLine();
		} while (name.length() <= 4);
		ActiveRestaurant.getInstance(name);
		Thread game = new Thread(Game.getInstance());
		
		game.start();
	}
	
}
