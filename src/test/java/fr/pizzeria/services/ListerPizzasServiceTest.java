package fr.pizzeria.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasServiceTest {

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
	public SystemOutRule systemInMock=new SystemOutRule() ;
	
	@Test
	public void executeUCTest(){
		List<Pizza> lp = providePizzaList();
		ListerPizzasService listPizzaService = new ListerPizzasService();
		PizzaMemDao dao = new PizzaMemDao();
		systemInMock.enableLog();

		listPizzaService.executeUC(new Scanner(System.in), dao);
		String consoleLogs = systemInMock.getLog();
		
		boolean contains = consoleLogs.contains("Lister les pizzas");
		for (Pizza pizza : lp) {
			contains = contains && consoleLogs.contains(pizza.toString());
		}
		assertTrue(contains);	
	}
}
