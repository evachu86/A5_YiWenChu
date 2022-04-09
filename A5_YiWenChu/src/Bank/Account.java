/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 5 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: Account.java
 * Other Files in this Project:
 * AccountController.java 
 * FXMLAccount.fxml
 * Main class: Main.java
 * 
 * Date: Aug 4, 2021
 * 
 * Description: Model class of Account for the bank application. 
 */
package Bank;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The Class Account.
 *
 * Modified by
 * @author Yi-Wen Chu 
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
public class Account {

	private String id = "c-100";
	private final DoubleProperty balance = new SimpleDoubleProperty(this, "balance");
	private final DoubleProperty interestRate = 
			new SimpleDoubleProperty(this, "interestRate", 2.3);

	/**
	 * Instantiates a new account object.
	 */
	public Account() {
	}

	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public final void setBalance(double balance) {
		if (balance >= 0)
			this.balance.set(balance);
		else
			throw new IllegalArgumentException("Error: balance must be 0 or more.");
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public final double getBalance() {
		return balance.get();
	}

	/**
	 * Balance property.
	 *
	 * @return the double property
	 */
	public final DoubleProperty balanceProperty() {
		return balance;
	}

	/**
	 * Sets the interest rate.
	 *
	 * @param rate the new interest rate
	 */
	public final void setInterestRate(double rate) {
		if (rate >= 0)
			interestRate.set(rate);
		else
			throw new IllegalArgumentException("Error: interest rate must be greater than 0.");
	}

	/**
	 * Gets the interest rate.
	 *
	 * @return the interest rate
	 */
	public final double getInterestRate() {
		return interestRate.get();
	}

	/**
	 * Interest rate property.
	 *
	 * @return the double property
	 */
	public final DoubleProperty interestRateProperty() {
		return interestRate;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		if (id != null && !id.trim().isEmpty()) {
			this.id = id;
		} else {
			throw new IllegalArgumentException("Error - ID can't be empty.");
		}
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return String.format("%s Balance: $%.2f", id, balance.get());
	}

}
