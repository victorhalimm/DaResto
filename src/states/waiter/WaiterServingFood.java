package states.waiter;

import models.Customer;
import models.Waiter;

public class WaiterServingFood extends WaiterState {
	
	private Customer customer;
	
	public WaiterServingFood(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
		stateName = "serving food ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.waiter.state = new WaiterIdle(waiter);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
