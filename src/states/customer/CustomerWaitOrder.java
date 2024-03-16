package states.customer;

import models.Customer;

public class CustomerWaitOrder extends CustomerState {
	
	
	public CustomerWaitOrder(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "order";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.customer.state = new CustomerOrder(this.customer);
	}

}
