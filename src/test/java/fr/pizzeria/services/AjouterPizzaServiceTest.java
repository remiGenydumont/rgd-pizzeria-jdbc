package fr.pizzeria.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

import org.junit.Rule;

public class AjouterPizzaServiceTest {
	
	public List<Pizza> providePizzaList(){
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L’orientale", 13.50));
		pizzas.add(new Pizza("IND", "L’indienne", 14.00));
		return pizzas;
	}
	
	@Rule 
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	@Test
	public void executeUCTest() throws SavePizzaException {
		List<Pizza> lp = providePizzaList();
		lp.add(new Pizza("VEG", "Végétarienne", 9.5));
		AjouterPizzaService addPizzaService = new AjouterPizzaService();
		PizzaMemDao dao = new PizzaMemDao();
		
		systemInMock.provideLines("VEG", "Végétarienne", "9,5");
		addPizzaService.executeUC(new Scanner(System.in), dao);
		
		List<Pizza> pizzaList = dao.findAllPizzas();
		assertArrayEquals(lp.toArray(), pizzaList.toArray());
	}
}
