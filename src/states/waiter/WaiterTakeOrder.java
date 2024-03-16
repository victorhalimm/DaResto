package states.waiter;

import models.Customer;
import models.Waiter;

public class WaiterTakeOrder extends WaiterState {
	
	private Customer customer;
	
	public WaiterTakeOrder(Waiter waiter) {
		super(waiter);
		// TODO Auto-generated constructor stub
		stateName = "take order ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.waiter.state = new WaiterWaitCook(waiter);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
