package mediator;

import java.util.ArrayList;

import models.Chef;
import models.Customer;
import models.Restaurant;
import models.Waiter;
import singletons.ActiveRestaurant;
import states.chef.ChefCook;
import states.chef.ChefDone;
import states.chef.ChefIdle;
import states.customer.CustomerOrder;
import states.customer.CustomerWaitChef;
import states.customer.CustomerWaitFood;
import states.customer.CustomerWaitOrder;
import states.customer.CustomerWaitServed;
import states.waiter.WaiterBringOrder;
import states.waiter.WaiterServingFood;
import states.waiter.WaiterTakeOrder;
import states.waiter.WaiterWaitCook;

public class RestaurantMediator {
	private Restaurant restaurant;
	private static RestaurantMediator instance;
	
	private RestaurantMediator() {
		restaurant = ActiveRestaurant.getInstance();
	}
	
	public static RestaurantMediator getInstance() {
		if (instance == null) {
			instance = new RestaurantMediator();
		}
		
		return instance;
	}
	
	public synchronized void takeCustomerOrder(Waiter waiter) {
		Customer customer = getCustomerWaitOrder();
		if (customer == null) return;
		if (customer.state instanceof CustomerOrder) {
			CustomerOrder orderState = (CustomerOrder) customer.state;
			orderState.setWaiter(waiter);
			waiter.state.changeState();
			WaiterTakeOrder downcastedState = (WaiterTakeOrder) waiter.state;
			downcastedState.setCustomer(customer);
		}
	}
	
	public synchronized void waitProcessOrder(Waiter waiter, Customer customer) {
		Chef availableChef = getIdleOrDoneChef();
		if (waiter.state instanceof WaiterTakeOrder) {
			waiter.state.changeState();
		}
		
		if (customer.state instanceof CustomerOrder) {
			customer.state.changeState();
		}
		
		while (availableChef == null) {
			availableChef = getIdleOrDoneChef();
		}
		
		if (availableChef.state instanceof ChefIdle) {
			if (waiter.state instanceof WaiterWaitCook) {
				WaiterWaitCook downcasted = (WaiterWaitCook) waiter.state;
				downcasted.changeBackToIdle();
			}
			availableChef.state.changeState();
			if (availableChef.state instanceof ChefCook) {
				ChefCook downcasted = (ChefCook) availableChef.state;
				downcasted.setCustomer(customer);
			}
		}
		
		else if (availableChef.state instanceof ChefDone) {
			ChefDone chefDowncasted = (ChefDone) availableChef.state;
			waiter.state.changeState();
			if (waiter.state instanceof WaiterBringOrder) {
				WaiterBringOrder downcasted = (WaiterBringOrder) waiter.state;
				downcasted.setChef(availableChef);
			}
			chefDowncasted.changeToCook();
			if (availableChef.state instanceof ChefCook) {
				ChefCook downcasted = (ChefCook) availableChef.state;
				downcasted.setCustomer(customer);
			}
		}
		
		if (customer.state instanceof CustomerWaitChef) {
			customer.state.changeState();
			CustomerWaitFood downcastedCustomerState = (CustomerWaitFood) customer.state;
			downcastedCustomerState.setChef(availableChef);
		}
	}
	
	public synchronized void serveOrder(Waiter waiter, Chef chef) {
		Customer customer;
		if (chef.state instanceof ChefDone) {
			ChefDone downcastedState = (ChefDone) chef.state;
			waiter.state.changeState();
			WaiterServingFood waiterState = (WaiterServingFood) waiter.state;
			waiterState.setCustomer(downcastedState.getCustomer());
			customer = waiterState.getCustomer();
			if (customer.state instanceof CustomerWaitFood) {
				customer.state.changeState();
				CustomerWaitServed downcastedCustomer = (CustomerWaitServed) customer.state;
				downcastedCustomer.setWaiter(waiter);
			}
		}
		
	}
	
	public synchronized void doneServeOrder(Waiter waiter, Customer customer) {
		if (waiter.state instanceof WaiterServingFood) {
			waiter.state.changeState();
		}
		if (customer.state instanceof CustomerWaitServed) {
			customer.state.changeState();
		}
	}
	
	public synchronized void chefDoneCook(Chef chef) {
		if (chef.state instanceof ChefCook) {
			ChefCook downcastedPrevState = (ChefCook) chef.state;
			Customer customer = downcastedPrevState.getCustomer();
			chef.state.changeState();
			ChefDone downcastedState = (ChefDone) chef.state;
			downcastedState.setCustomer(customer);
		}
	}
	
	
	private Chef getIdleOrDoneChef() {
		for (Chef chef : restaurant.chefs) {
			if (chef.state instanceof ChefIdle || chef.state instanceof ChefDone) {
				return chef;
			}
		}
		return null;
	}
	
	private Customer getCustomerWaitOrder() {
		for (Customer customer : restaurant.customers) {
			if (customer != null) {
				if (customer.state instanceof CustomerWaitOrder) {
					customer.state.changeState();
					return customer;
				}
			}
		}
		return null;
	}
}
