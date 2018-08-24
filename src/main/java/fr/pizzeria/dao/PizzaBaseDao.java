package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaBaseDao implements IPizzaDao {

	private String driver;
	private String protocole;
	private String url;
	private String user;
	private String password;

	public PizzaBaseDao() {
			try {
			Properties properties = new Properties();
			FileInputStream input = new FileInputStream("ressources/jdbc.properties");
			properties.load(input);
			input.close();
			driver = properties.getProperty("driver");
			protocole = properties.getProperty("protocole");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Pizza> findAllPizzas() {
		List<Pizza> pizzaList = new ArrayList<>();
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			PreparedStatement statment = null;
			statment = myConnection.prepareStatement("Select * From PIZZA");
			ResultSet resultat = statment.executeQuery();

			while (resultat.next()) {
				pizzaList.add(new Pizza(resultat.getInt(1), resultat.getString(2), resultat.getString(3), resultat.getDouble(4)));
			}
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pizzaList;
	}

	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			PreparedStatement statment = null;

			statment = myConnection.prepareStatement("INSERT INTO PIZZA(id,code,libelle,prix) VALUES (?,?,?,?)");
			statment.setInt(1, pizza.getId());
			statment.setString(2, pizza.getCode());
			statment.setString(3, pizza.getLibelle());
			statment.setDouble(4, pizza.getPrix());
			statment.execute();
			
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SavePizzaException("Error during SQL request INSERT !");
		}
	}

	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			PreparedStatement statment = null;

			statment = myConnection.prepareStatement("UPDATE PIZZA SET ID=?,CODE=?,LIBELLE=?,PRIX=? WHERE CODE=?");
			statment.setInt(1, pizza.getId());
			statment.setString(2, pizza.getCode());
			statment.setString(3, pizza.getLibelle());
			statment.setDouble(4, pizza.getPrix());
			statment.setString(5, codePizza);
			statment.executeUpdate();
			
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UpdatePizzaException("Error during SQL request UPDATE !");
		}

	}

	public void deletePizza(String codePizza) throws DeletePizzaException {
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			myConnection.setAutoCommit(false);
			PreparedStatement statment = null;

			statment = myConnection.prepareStatement("DELETE FROM PIZZA WHERE CODE=?");
			statment.setString(1, codePizza);
			statment.executeUpdate();
			myConnection.commit();
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DeletePizzaException("Error during SQL request DELETE !");
		}
	}
	
	public void deleteAllPizza() throws DeletePizzaException {
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			PreparedStatement statment = null;

			statment = myConnection.prepareStatement(" TRUNCATE TABLE PIZZA");
			statment.executeUpdate();
			
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DeletePizzaException("Error during SQL request DELETE !");
		}
	}
	
	public void initListPizzaFromFile(String path)  {
		 
		String line;
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(
					this.getDriver() + ":" + this.getProtocole() + ":" + this.getUrl(), this.getUser(),
					this.getPassword());
			PreparedStatement statment = null;
			
			this.deleteAllPizza();
			
			BufferedReader br = new BufferedReader(new FileReader(path)) ;
			 while ((line = br.readLine()) != null) {
	                String[] pizzaSlice = line.split(",");

	                statment = myConnection.prepareStatement("INSERT INTO PIZZA(id,code,libelle,prix) VALUES (?,?,?,?)");
	    			statment.setInt(1, Integer.parseInt(pizzaSlice[0]) );
	    			statment.setString(2, pizzaSlice[1]);
	    			statment.setString(3, pizzaSlice[2]);
	    			statment.setDouble(4, Double.parseDouble(pizzaSlice[3]));
	    			statment.execute();
	            }
			br.close();
			myConnection.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDriver() {
		return driver;
	}

	public String getProtocole() {
		return protocole;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
