package game;

import java.util.Random;

import generator.CustomerGenerator;
import models.Chef;
import models.Customer;
import models.Restaurant;
import models.Waiter;
import singletons.ActiveRestaurant;
import states.chef.ChefCook;
import states.chef.ChefDone;
import states.chef.ChefState;
import states.customer.CustomerOrder;
import states.customer.CustomerState;
import states.customer.CustomerWaitChef;
import states.customer.CustomerWaitFood;
import states.customer.CustomerWaitServed;
import states.waiter.WaiterBringOrder;
import states.waiter.WaiterServingFood;
import states.waiter.WaiterState;
import states.waiter.WaiterTakeOrder;


public class Game implements Runnable{
	Restaurant restaurant;
	
	private volatile boolean isPaused;
	private volatile boolean isOn;
	
	private static Game instance;
	
	private InputListener listener;
	
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game(ActiveRestaurant.getInstance());
		}
		
		return instance;
	}
	
	public Game(Restaurant restaurant) {
		this.restaurant = restaurant;
		setPaused(false);
		setOn(true);
		listener = InputListener.getInstance();
		Thread listenerThread = new Thread(listener);
		
		listenerThread.start();
		cls();
	}
	
	private void cls() {
		System.out.print("\033[H\033[2J");  
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		restaurant.initializeStaff();
		while (isOn) {
			if (!isPaused) {
				try {
					spawnCustomer();
					showStatus();
					printTable();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void showStatus() {
		cls();
		System.out.println("Restaurant ' " + restaurant.name + " ' is on Business!");
		System.out.println("Status");
		System.out.println("Money : Rp. " + restaurant.money);
		System.out.println("Score : " + restaurant.score + " Points");
		System.out.println("Size : " + restaurant.seatCount );
	}
	
	private void printTable() {
        String horizontalLine = "+--------------------------+--------------------------+--------------------------+";
        String format = "| %-24s | %-24s | %-24s |\n";

        System.out.println(horizontalLine);
        System.out.printf(format, "Customer", "Waiter", "Chef");
        System.out.println(horizontalLine);
        
        for (int i = 0; i < 10; i++) {
        	Customer customer;
			try {
				customer = restaurant.customers.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				customer = null;
			}
        	Waiter waiter;
			try {
				waiter = restaurant.waiters.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				waiter = null;
			}
			Chef chef;
        	try {
				chef = restaurant.chefs.get(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				chef = null;
			}
        	
            System.out.printf(format, customer != null ?  customer.name + " " + customer.patience + " " + getCustomerStatus(customer.state) : "", waiter != null ? waiter.name + " " + getWaiterStatus(waiter.state): "", chef != null ?  chef.name + " " + getChefStatus(chef.state) : "");
        }
        System.out.println(horizontalLine);
    }
	
	private void spawnCustomer() {
		Random rand = new Random();
		CustomerGenerator generator = new CustomerGenerator();
		if (rand.nextInt(4) == 0 && restaurant.customers.size() < restaurant.seatCount) {
			generator.update();
		}
	}
	
	private String getCustomerStatus(CustomerState state) {
		if (state instanceof CustomerWaitFood) {
			CustomerWaitFood downcasted = (CustomerWaitFood) state;
			return downcasted.stateName + " " + downcasted.getChef().name;
		}
		if (state instanceof CustomerOrder) {
			CustomerOrder downcasted = (CustomerOrder) state;
			return downcasted.stateName + " " + downcasted.getWaiter().name;
		}
		if (state instanceof CustomerWaitServed) {
			CustomerWaitServed downcasted = (CustomerWaitServed) state;
			return downcasted.stateName + " " + downcasted.getWaiter().name;
		}
		return state.stateName;
	}
	
	private String getWaiterStatus(WaiterState state) {
		if (state instanceof WaiterTakeOrder) {
			WaiterTakeOrder downcasted = (WaiterTakeOrder) state;
			return downcasted.stateName + downcasted.getCustomer().name;
		}
		if (state instanceof WaiterBringOrder) {
			WaiterBringOrder downcasted = (WaiterBringOrder) state;
			return downcasted.stateName + downcasted.getChef().name;
		}
		if (state instanceof WaiterServingFood) {
			WaiterServingFood downcasted = (WaiterServingFood) state;
			return downcasted.stateName + downcasted.getCustomer().name;
		}
		return state.stateName;
	}
	
	private String getChefStatus(ChefState state) {
		if (state instanceof ChefCook) {
			ChefCook downcasted = (ChefCook) state;
			return downcasted.stateName + downcasted.getCustomer().name;
		}
		if (state instanceof ChefDone) {
			ChefDone downcasted = (ChefDone) state;
			return downcasted.stateName + downcasted.getCustomer().name;
		}
		return state.stateName;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
}
