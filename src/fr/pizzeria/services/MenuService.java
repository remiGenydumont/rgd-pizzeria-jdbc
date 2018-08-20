package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;

public abstract class MenuService {

	public abstract void executeUC(Scanner scanner, IPizzaDao dao) throws SavePizzaException, UpdatePizzaException, DeletePizzaException;
}
