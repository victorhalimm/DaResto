package states.customer;

import models.Chef;
import models.Customer;

public class CustomerWaitFood extends CustomerState {
	
	private Chef chef;
	
	public CustomerWaitFood(Customer customer) {
		super(customer);
		// TODO Auto-generated constructor stub
		stateName = "wait food";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		this.customer.state = new CustomerWaitServed(this.customer);
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

}
