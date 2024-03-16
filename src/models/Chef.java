package models;

import mediator.RestaurantMediator;
import singletons.ActiveRestaurant;
import states.chef.ChefCook;
import states.chef.ChefIdle;
import states.chef.ChefState;

public class Chef extends Person implements Runnable{

	public ChefState state;
	public int speed;
	public int skillPoint;
	
	private RestaurantMediator mediator;
	
	public Chef(String initial, int speed, int skillPoint) {
		super(initial);
		// TODO Auto-generated constructor stub
		state = new ChefIdle(this);
		this.speed = speed;
		this.skillPoint = skillPoint;
		mediator = RestaurantMediator.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isOn) {
			if (!isPaused()) {
				if (state instanceof ChefCook) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mediator.chefDoneCook(this);
				}
			}
		}
	}

}
