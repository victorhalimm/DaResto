package states.chef;

import models.Chef;

public class ChefIdle extends ChefState {

	public ChefIdle(Chef chef) {
		super(chef);
		// TODO Auto-generated constructor stub
		stateName = "";
	}

	@Override
	public void changeState() {
		// TODO Auto-generated method stub
		chef.state = new ChefCook(chef);
	}

}
