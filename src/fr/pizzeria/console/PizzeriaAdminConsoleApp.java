package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaBaseDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {
	
	public static void main(String[] args) {
		
		
		/* Initialisation du TDB de pizzas */
		IPizzaDao dao = new PizzaBaseDao();
		
		/* Initialisation du scanner */
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		/* Structure de répétition de la boucle principale */
		do {
			println("***** Pizzeria Administration *****");
			println("0. Initialiser la liste de pizza ");
			println("1. Lister les pizzas ");
			println("2. Ajouter une nouvelle pizza ");
			println("3. Mettre à jour une pizza ");
			println("4. Supprimer une pizza ");
			println("99. Sortir ");
	
			System.out.print("Veuillez choisir une option:");
			choix = scanner.nextInt();
			
			if (choix != 99) {
				MenuService service = MenuServiceFactory.getInstance(choix);
				try {
					service.executeUC(scanner, dao);
				} catch (StockageException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}	
			}
		} while(choix!=99);
		
		scanner.close();
		
	}
	
	

	private static void println(String msg){
		System.out.println(msg);
	}
}
