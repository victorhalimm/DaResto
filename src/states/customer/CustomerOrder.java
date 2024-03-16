package states.customer;

import models.Customer;
import models.Waiter;

public class CustomerOrder extends CustomerState {
	
	private Waiter waiter;
	
	public CustomerOrder(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "order ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.customer.state = new CustomerWaitChef(this.customer);
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	

}
