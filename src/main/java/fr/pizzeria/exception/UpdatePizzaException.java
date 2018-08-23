package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {
	public UpdatePizzaException() {
		super();
	}
	public UpdatePizzaException(String msg) {
		super(msg);
	}
}
