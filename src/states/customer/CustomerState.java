package states.customer;

import models.Customer;

public abstract class CustomerState {
	public Customer customer;
	public String stateName;
	
	public abstract void changeState();
	
	public CustomerState(Customer customer) {
		this.customer = customer;
	}
}
