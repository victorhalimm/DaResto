package models;

public abstract class Person {
	
	public String name;
	
	protected volatile boolean isOn = true;
	private volatile boolean isPaused;
	
	
	public Person(String name) {
		super();
		this.name = name;
		setPaused(false);
	}


	public boolean isPaused() {
		return isPaused;
	}


	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
}
