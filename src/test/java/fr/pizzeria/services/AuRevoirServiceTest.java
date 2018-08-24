package fr.pizzeria.services;

import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import fr.pizzeria.dao.PizzaMemDao;

public class AuRevoirServiceTest {
	
	@Rule 
	public SystemOutRule systemInMock=new SystemOutRule() ;

	@Test
	public void executeUCTest(){
		AuRevoirService auRevoirService = new AuRevoirService();
		PizzaMemDao dao = new PizzaMemDao();
		systemInMock.enableLog();

		auRevoirService.executeUC(new Scanner(System.in), dao);
		String consoleLogs = systemInMock.getLog();
		
		assertTrue(consoleLogs.contains("Au revoir"));
	}
}
