package fr.pizzeria.exception;

public class SavePizzaException extends StockageException {
	public SavePizzaException() {
		super();
	}
	public SavePizzaException(String msg) {
		super(msg);
	}

}
