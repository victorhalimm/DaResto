package models;

import java.util.ArrayList;
import java.util.Queue;

import factory.CustomerFactory;
import generator.CustomerGenerator;
import generator.InitialGenerator;

public class Restaurant implements Comparable<Restaurant> {
	
	public String name;
	public int seatCount;
	public int waiterCount;
	public int chefCount;
	public int money;
	public int score;
	
	public int waiterSpeed;
	public int chefSpeed;
	public int chefSkill;
	
	public ArrayList<Customer> customers;
	public ArrayList<Waiter> waiters;
	public ArrayList<Chef> chefs;
	
	public Restaurant(String name, int seatCount, int waiterCount, int chefCount, int money, int score) {
		super();
		this.name = name;
		this.seatCount = seatCount;
		this.waiterCount = waiterCount;
		this.chefCount = chefCount;
		this.money = money;
		this.score = score;
		
		waiterSpeed = 1;
		chefSpeed = 1;
		chefSkill = 1;
		
		this.chefs = new ArrayList<Chef>();
		this.waiters = new ArrayList<Waiter>();
		this.customers = new ArrayList<Customer>();
		
	}
	
	@Override
	public int compareTo(Restaurant o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.score, this.score);
	}
	
	public void initializeStaff() {

		for (int i = 0 ; i < chefCount; i ++) {
			chefs.add(new Chef(InitialGenerator.generate(), chefSpeed, chefSkill));
			Thread thread = new Thread(chefs.get(i));
			thread.start();
		}
		
		for (int i = 0 ; i < waiterCount; i ++) {
			waiters.add(new Waiter(InitialGenerator.generate(), waiterSpeed));
			Thread thread = new Thread(waiters.get(i));
			thread.start();
		}
	}
	
	public void removeCustomer(Customer customer) {
		customers.remove(customer);
	}
	
	public void addScoreAndMoney() {
		money += chefSkill * 30;
		score += chefSkill * 30;
	}
	
}
