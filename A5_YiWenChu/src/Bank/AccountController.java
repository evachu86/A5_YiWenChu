/* 
 * Name: Yi-Wen Chu    991624614
 * Assignment: Assignment 5 
 * Program: Computer Systems Technology -
 * 	Software Development and Network Engineering
 * File: AccountController.java
 * Other Files in this Project:
 * Account.java 
 * FXMLAccount.fxml
 * Main class: Main.java
 * 
 * Date: Aug 4, 2021
 * 
 * Description: The controller for the Bank application.
 */
package Bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;

/**
 * The Class AccountController.
 *
 * @author Yi-Wen Chu
 * Computer Systems Technology
 * Software Development and Network Engineering
 */
public class AccountController implements Initializable {
	
	/**
	 * The Enum ServiceType.
	 *
	 * @author Yi-Wen Chu
	 * Computer Systems Technology
	 * Software Development and Network Engineering
	 */
	public enum ServiceType {
		
		/** The deposit. */
		DEPOSIT("Deposit"), 
		/** The withdraw. */
		WITHDRAW("Withdraw");
		
		private String displayName;

		// private constructor to set display name
		private ServiceType(String displayName) {
			this.displayName = displayName;
		}
		
		/**
		 * To string.
		 * called by JavaFx to display customized name. 
		 *
		 * @return the string of display name.
		 */
		@Override
		public String toString() {
			return displayName;
		}
	}
	
	@FXML
	private TextField txtAmount, txtBalance, txtRate;
	
	@FXML
	private Label lblAccount, lblServiceType;
	
	@FXML
	private Button btnUpdate, btn, btnExit , btnSave;
	
	@FXML
	private ComboBox<Account> cmbAccount;
	
	@FXML
	private ListView<ServiceType> lstServiceType;
	
	
	
	// method for update the deposit / withdraw result.
	@FXML
	private void update(ActionEvent event) {
		
		// read input 
		SingleSelectionModel<Account> pickedAccount = 
				cmbAccount.getSelectionModel();
		Account pickedAcct = pickedAccount.getSelectedItem();
		ServiceType serviceType = lstServiceType
				.getSelectionModel().getSelectedItem();
		double balance = pickedAcct.getBalance(),
				amount = Double.parseDouble(txtAmount.getText());
		// calculate balance according to service type.
		switch(serviceType) {
			case DEPOSIT: balance += amount;
				break;
			case WITHDRAW: balance -= amount;
				break;
			default:
				System.err.println("Invalid service type: "
						+serviceType.name());
		}
		// set result back to the ComboBox.
		pickedAcct.setBalance(balance);
		cmbAccount.getItems().set(
				pickedAccount.getSelectedIndex(), pickedAcct);
		// set result back to the TextField.
		setAccountInfo(pickedAcct);
	}
	
	// method for output update/deposit result to the text file.
	@FXML
	private void save(ActionEvent event) {
		
		File file = new File("Assignment5.dat");
		
		try (	FileWriter fileWriter = new FileWriter(file,true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter printWriterOut = new PrintWriter(bufferedWriter)) {
			
			if(!file.exists()) {
				file.createNewFile();
			} else {
				Account pickedAcct = cmbAccount
						.getSelectionModel().getSelectedItem();
				ServiceType pickedServiceType = lstServiceType
						.getSelectionModel().getSelectedItem();
				printWriterOut.append(pickedAcct+","+pickedServiceType+"\n");
			}
			
			
		} catch(Exception e) {
			System.err.println();
		}
	}
	
	/**
	 * Initialize following control objects: 
	 * Function button btnUpdate, btnSave, btnExit.
	 * ComboBox Account.  
	 * ListView Service Type.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// initialize Account ComboBox
		setCmbAccount();
		// set handler while change the option.
		SingleSelectionModel<Account> pickedAccount = 
				cmbAccount.getSelectionModel();
		pickedAccount.selectedItemProperty().addListener(
			(obs, newValue, oldValue) -> {
			Account pickedAcct = cmbAccount.getSelectionModel()
									.getSelectedItem();
			setAccountInfo(pickedAcct);
		});
		
		// initialize Service Type ListView 
		setLstServiceType();
		// set handler while change the option.
		MultipleSelectionModel<ServiceType> pickedService = 
				lstServiceType.getSelectionModel();
		pickedService.selectedItemProperty().addListener( obs -> {
			lblServiceType.setText(
					"["+pickedService.getSelectedItem().toString()+"]");
		});
		
		// Function for the button of "Exit"
		btnExit.setOnAction(event -> {
			Platform.exit();
		});
	}

	// set list for Account ComboBox
	private void setCmbAccount() {
		// items for Account ComboBox 
		Account acct1 = new Account();
		acct1.setId("c-500");
		acct1.setBalance(1000);
		acct1.setInterestRate(2.5);
		Account acct2 = new Account();
		acct2.setId("c-700");
		acct2.setBalance(2000);
		acct2.setInterestRate(3.5);
		Account acct3 = new Account();
		acct3.setId("c-900");
		acct3.setBalance(3000);
		acct3.setInterestRate(4.5);
		// initialize ObservableList for Account ComboBox
		ObservableList<Account> obsAccountList = 
				FXCollections.observableArrayList();
		obsAccountList.add(acct1);
		obsAccountList.add(acct2);
		obsAccountList.add(acct3);
		// set ObservableList to Account ComboBox
		cmbAccount.setItems(obsAccountList);
	}
	
	// set list for Service Type ListView 
	private void setLstServiceType() {
		// initialize ObservableList for Service Type ListView
		ObservableList<ServiceType> obsServiceType = 
				FXCollections.observableArrayList(ServiceType.values());
		// set ObservableList to Service Type ListView 
		lstServiceType.setItems(obsServiceType);
	}
	
	// set Account information to text fields.
	private void setAccountInfo(Account acct) {
		txtAmount.setText("0.0");
		txtBalance.setText(fmtDouble(acct.getBalance()));
		txtRate.setText(fmtDouble(acct.getInterestRate()));
		lblAccount.setText(acct.toString());
	}
	
	// set string format for double.   
	private String fmtDouble(double input) {
		return String.format("%.2f", input);
	}
}
