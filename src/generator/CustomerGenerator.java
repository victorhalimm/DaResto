package generator;

import java.util.concurrent.Flow.Subscriber;

import factory.CustomerFactory;
import models.Customer;
import models.Restaurant;
import singletons.ActiveRestaurant;

public class CustomerGenerator implements observer.Subscriber {
	CustomerFactory factory = new CustomerFactory();
	
	private Restaurant restaurant;
	
	public CustomerGenerator() {
		restaurant = ActiveRestaurant.getInstance();
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Customer newCustomer = (Customer) factory.createPerson(InitialGenerator.generate());
		restaurant.customers.add(newCustomer);
		Thread thread = new Thread(newCustomer);
		thread.start();
	}
}
