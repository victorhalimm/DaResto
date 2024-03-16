package states.chef;

import models.Chef;

public abstract class ChefState {
	public Chef chef;
	public String stateName;

	public ChefState(Chef chef) {
		super();
		this.chef = chef;
	}
	
	public abstract void changeState();
	
}
