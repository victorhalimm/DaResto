package models;

import mediator.RestaurantMediator;
import states.waiter.WaiterBringOrder;
import states.waiter.WaiterIdle;
import states.waiter.WaiterServingFood;
import states.waiter.WaiterState;
import states.waiter.WaiterTakeOrder;

public class Waiter extends Person implements Runnable{
	
	public WaiterState state;
	public int speed;
	
	private RestaurantMediator mediator;
	
	public Waiter(String initial, int speed) {
		super(initial);
		// TODO Auto-generated constructor stub
		state = new WaiterIdle(this);
		this.speed = speed;
		mediator = RestaurantMediator.getInstance();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isOn) {
			if (!isPaused()) {
				if (state instanceof WaiterIdle) {
					mediator.takeCustomerOrder(this);
				}
				if (state instanceof WaiterTakeOrder) {
					try {
						Thread.sleep(6000 - speed  * 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WaiterTakeOrder downcastedState = (WaiterTakeOrder) state;
					mediator.waitProcessOrder(this, downcastedState.getCustomer());
				}
				if (state instanceof WaiterBringOrder) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WaiterBringOrder downcastedState = (WaiterBringOrder) state;
					mediator.serveOrder(this, downcastedState.getChef());
				}
				if (state instanceof WaiterServingFood) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					WaiterServingFood downcastedState = (WaiterServingFood) state;
					mediator.doneServeOrder(this, downcastedState.getCustomer());
				}
			}
			
		}
	}
	
}
