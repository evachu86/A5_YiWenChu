/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 5 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: Main.java
 * Other Files in this Project: 
 * Account.java
 * AccountController.java
 * FXMLAccount.fxml
 * 
 * Date: Aug 4, 2021
 * 
 * Description: Main class for the Bank application.
 */
package Bank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class MainCalculator.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
public class Main extends Application{

	/**
	 * Start inherit from Application class.
	 *
	 * @param stage the stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(
				getClass().getResource("FXMLAccount.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("ABC Bank System");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
