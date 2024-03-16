package states.customer;

import models.Customer;

public class CustomerWaitChef extends CustomerState {

	public CustomerWaitChef(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "wait food";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.customer.state = new CustomerWaitFood(this.customer);
	}

}
