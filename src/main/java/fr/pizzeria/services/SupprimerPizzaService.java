package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws DeletePizzaException {
		System.out.println("Supprimer une pizza");
		
		System.out.println("Code de la pizza � supprimer:");
		String codePizzaSupp = scanner.next();
		
		dao.deletePizza(codePizzaSupp);

	}

}
