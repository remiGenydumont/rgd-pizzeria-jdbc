package fr.pizzeria.services;

import java.io.FileInputStream;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

public class InitListPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SavePizzaException {
		System.out.println("Entrez le nom du fichier de Configuration (path) :");
		String path = scanner.next();
		dao.initListPizzaFromFile(path);
	}

}
