package factory;

import models.Chef;
import models.Person;

public class ChefFactory implements PersonFactory {

	@Override
	public Person createPerson(String username) {
		// TODO Auto-generated method stub
		return new Chef(username, 1, 1);
	}
	
	public Person createPerson(String username, int speed, int skillPoint) {
        return new Chef(username, speed, skillPoint);
    }

}
