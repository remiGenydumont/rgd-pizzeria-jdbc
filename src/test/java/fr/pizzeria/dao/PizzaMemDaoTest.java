package fr.pizzeria.dao;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {
	List<Pizza> pizzas;
	
	public PizzaMemDaoTest() {
		pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
	}
	

	@Test
	public void findAllPizzasTest() {
		PizzaMemDao dao = new PizzaMemDao();
		List<Pizza> pizzaList = dao.findAllPizzas();
		
		assertArrayEquals(pizzas.toArray(), pizzaList.toArray());
	}
	
	@Test
	public void saveNewPizzaTest(){
		pizzas.add(new Pizza("VEG", "Végétarienne", 12.50));
		Pizza p = new Pizza("VEG", "Végétarienne", 12.50);
		
		PizzaMemDao dao = new PizzaMemDao();
		dao.saveNewPizza(p);
		List<Pizza> pizzaList = dao.findAllPizzas();
		assertArrayEquals(pizzas.toArray(), pizzaList.toArray());
		
	}

	@Test
	public void updatePizzaTest(){
		Pizza p1 = new Pizza("PEP", "Pépéronininini", 8.5);
		pizzas.set(0, p1);
		
		PizzaMemDao dao = new PizzaMemDao();
		dao.updatePizza("PEP", p1);
		List<Pizza> pizzaList = dao.findAllPizzas();
		assertArrayEquals(pizzas.toArray(), pizzaList.toArray());
	}
	
	@Test
	public void deletePizzaTest(){
		pizzas.remove(4);
		PizzaMemDao dao = new PizzaMemDao();
		dao.deletePizza("CAN");
		List<Pizza> pizzaList = dao.findAllPizzas();
		assertArrayEquals(pizzas.toArray(), pizzaList.toArray());
	}
}
