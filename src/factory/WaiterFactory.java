package factory;

import models.Person;
import models.Waiter;

public class WaiterFactory implements PersonFactory {

	@Override
	public Person createPerson(String username) {
		// TODO Auto-generated method stub
		return new Waiter(username, 1);
	}
	
	public Person createPerson(String username, int speed) {
		// TODO Auto-generated method stub
		return new Waiter(username, speed);
	}
}
