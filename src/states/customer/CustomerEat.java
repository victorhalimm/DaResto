package states.customer;

import models.Customer;

public class CustomerEat extends CustomerState {

	public CustomerEat(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "eat";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		
	}

}
