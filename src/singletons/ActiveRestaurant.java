package singletons;

import facade.RestaurantFacade;
import game.Game;
import models.Chef;
import models.Customer;
import models.Restaurant;
import models.Waiter;

public class ActiveRestaurant extends Restaurant implements RestaurantFacade {
	
	private static ActiveRestaurant instance = null;
	
	private ActiveRestaurant(String name, int seatCount, int waiterCount, int chefCount, int money, int score) {
		super(name, seatCount, waiterCount, chefCount, money, score);
		// TODO Auto-generated constructor stub
	}
	
	public static ActiveRestaurant getInstance() {
		if (instance == null)
			instance = new ActiveRestaurant(null, 4, 2, 2, 1300, 0);
		
		return instance;
	}
	
	public static ActiveRestaurant getInstance(String username) {
		if (instance == null)
			instance = new ActiveRestaurant(username, 4, 2, 2, 1300, 0);
		
		return instance;
	}

	@Override
	public void pauseGame() {
		// TODO Auto-generated method stub
		for (Customer customer : customers) {
			customer.setPaused(true);
		}
		for (Waiter waiter : waiters) {
			waiter.setPaused(true);
		}
		for (Chef chef : chefs) {
			chef.setPaused(true);
		}
		
		Game.getInstance().setPaused(true);
	}


	@Override
	public void resumeGame() {
		// TODO Auto-generated method stub
		for (Customer customer : customers) {
			customer.setPaused(false);
		}
		for (Waiter waiter : waiters) {
			waiter.setPaused(false);
		}
		for (Chef chef : chefs) {
			chef.setPaused(false);
		}
		
		Game.getInstance().setPaused(false);
	}
	
}
