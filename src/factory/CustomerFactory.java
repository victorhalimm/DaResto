package factory;

import models.Customer;
import models.Person;

public class CustomerFactory implements PersonFactory  {

	@Override
	public Person createPerson(String username) {
		// TODO Auto-generated method stub
		return new Customer(username);
	}
	
	
	
}
