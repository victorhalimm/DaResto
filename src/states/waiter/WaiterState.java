package states.waiter;

import models.Waiter;

public abstract class WaiterState {
	public Waiter waiter;
	public String stateName;

	public WaiterState(Waiter waiter) {
		super();
		this.waiter = waiter;
	}
	
	public abstract void changeState();
	
}
