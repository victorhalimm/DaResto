package states.chef;

import models.Chef;
import models.Customer;

public class ChefCook extends ChefState {
	
	private Customer customer;
	
	public ChefCook(Chef chef) {
		super(chef);
		// TODO Auto-generated constructor stub
		stateName = "cook ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		chef.state = new ChefDone(chef);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
