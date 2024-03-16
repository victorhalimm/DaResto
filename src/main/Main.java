package main;

import generator.InitialGenerator;
import menu.Menu;
import singletons.HighScoreManager;

public class Main {
	
	public Main() {
		Menu menu = new Menu();
		HighScoreManager scoreManager = HighScoreManager.getInstance();
		menu.startMenu();
	}
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		new Main();
	}
	
}
