package states.customer;

import models.Customer;
import models.Waiter;

public class CustomerWaitServed extends CustomerState {

	private Waiter waiter;
	
	public CustomerWaitServed(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "wait food";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.customer.state = new CustomerEat(this.customer);
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

}
