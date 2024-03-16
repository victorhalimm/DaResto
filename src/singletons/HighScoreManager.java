package singletons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import models.Restaurant;

public class HighScoreManager {
	
	private static HighScoreManager instance;
	
	private List<Restaurant> restaurantList;
	
	public static HighScoreManager getInstance() {
		if (instance == null)
			instance = new HighScoreManager();
		
		return instance;
	}
	
	
	
	private HighScoreManager() {
		restaurantList = new ArrayList<Restaurant>(); 
		loadScore();
		Collections.sort(restaurantList);
	}
	
	public void printScoreTable() {
		System.out.println(String.format("%-5s %-20s %s", "Rank", "Restaurant Name", "Scores"));
        System.out.println("-------------------------------------------------");
        int rank = 1;
        for (Restaurant restaurant : restaurantList) {
            System.out.println(String.format("%-5d %-20s %d", rank, restaurant.name, restaurant.score));
            rank++;
        }
	}
	
	private void loadScore()  {
		File highscore = new File("highscore.txt");
		
		try {
			highscore.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (Scanner sc = new Scanner(highscore)) {
			while (sc.hasNextLine()) {
				String formmattedScore = sc.nextLine();
				String parts[] = formmattedScore.split("#");
				
				String username = parts[0];
				int score = Integer.parseInt(parts[1]);
				
				restaurantList.add(new Restaurant(username, 4, 2, 2, 1300, score));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
