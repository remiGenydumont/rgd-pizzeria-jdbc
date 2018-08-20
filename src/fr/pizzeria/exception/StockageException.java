package fr.pizzeria.exception;

import java.sql.SQLException;

public class StockageException extends SQLException {
	public StockageException() {
		super();
	}
	public StockageException(String msg) {
		super(msg);
	}
}
