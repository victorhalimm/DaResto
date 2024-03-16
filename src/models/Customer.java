package models;

import singletons.ActiveRestaurant;
import states.customer.CustomerEat;
import states.customer.CustomerState;
import states.customer.CustomerWaitChef;
import states.customer.CustomerWaitFood;
import states.customer.CustomerWaitOrder;
import states.customer.CustomerWaitServed;

public class Customer extends Person implements Runnable {
	
	public CustomerState state;
	public int patience = 15;
	
	private Restaurant restaurant;
	
	public Customer(String initial) {
		super(initial);
		// TODO Auto-generated constructor stub
		state = new CustomerWaitOrder(this);
		restaurant = ActiveRestaurant.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isOn) {
			if (!isPaused()) {			
				if (patience <= 0) {
					isOn = false;
					restaurant.customers.remove(this);
					break;
				}
				while (state instanceof CustomerWaitOrder) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (state instanceof CustomerWaitOrder) {
						this.patience--;
						if (patience <= 0) break;
					}
				}
				while (state instanceof CustomerWaitFood || state instanceof CustomerWaitChef || state instanceof CustomerWaitServed) {
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (state instanceof CustomerWaitFood || state instanceof CustomerWaitChef || state instanceof CustomerWaitServed) {
						patience--;
						if (patience <= 0) break;
					}
				}
				if (state instanceof CustomerEat) {
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					restaurant.addScoreAndMoney();
					restaurant.removeCustomer(this);
					isOn = false;
					break;
				}
			}
		}
	}

	

}
