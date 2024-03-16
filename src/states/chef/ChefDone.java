package states.chef;

import models.Chef;
import models.Customer;

public class ChefDone extends ChefState {
	
	private Customer customer;
	
	public ChefDone(Chef chef) {
		super(chef);
		// TODO Auto-generated constructor stub
		stateName = "done ";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.chef.state = new ChefIdle(chef);
	}
	
	public void changeToCook() {
		this.chef.state = new ChefCook(chef);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
