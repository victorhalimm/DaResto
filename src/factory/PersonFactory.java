package factory;

import models.Person;

public interface PersonFactory {
	Person createPerson(String username);
}
