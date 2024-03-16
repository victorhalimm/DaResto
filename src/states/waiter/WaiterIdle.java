package states.waiter;

import models.Waiter;

public class WaiterIdle extends WaiterState {

	public WaiterIdle(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
		stateName = "";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.waiter.state = new WaiterTakeOrder(waiter);
	}

}
