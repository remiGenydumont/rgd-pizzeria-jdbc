package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;

public class InitListPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SavePizzaException {
		System.out.println("Entrez le nom du fichier de Configuration (path) :");
		String path = scanner.next();
		dao.initListPizzaFromFile(path);
	}

}
