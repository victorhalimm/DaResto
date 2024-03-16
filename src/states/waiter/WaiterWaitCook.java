package states.waiter;

import models.Waiter;

public class WaiterWaitCook extends WaiterState {

	public WaiterWaitCook(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
		stateName = "wait cook ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.waiter.state = new WaiterBringOrder(waiter);
	}
	
	public void changeBackToIdle() {
		this.waiter.state = new WaiterIdle(waiter);
	}

}
