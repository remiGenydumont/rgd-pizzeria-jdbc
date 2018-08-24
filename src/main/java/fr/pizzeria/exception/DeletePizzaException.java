package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2149448715820606009L;
	public DeletePizzaException() {
		super();
	}
	public DeletePizzaException(String msg) {
		super(msg);
	}
}
