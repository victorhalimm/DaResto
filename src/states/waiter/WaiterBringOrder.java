package states.waiter;

import models.Chef;
import models.Customer;
import models.Waiter;

public class WaiterBringOrder extends WaiterState {
	
	private Chef chef;
	
	public WaiterBringOrder(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
		stateName = "bring order ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.waiter.state = new WaiterServingFood(waiter);
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	

}
