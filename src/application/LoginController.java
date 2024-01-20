package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Connection connect; 
	private PreparedStatement prepare;
	private ResultSet result;
	
	@FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
	
	public void loginAdmin(ActionEvent event) {
		String sql = "SELECT * FROM accounts WHERE username = ? and password = ?";
		
		connect = Database.connectDb();
		
		try {
			Alert alert; 
			
			prepare = connect.prepareStatement(sql);
			prepare.setString(1, usernameField.getText()); //Sets username = ?
			prepare.setString(2, passwordField.getText()); //Sets password = ?
			
			result = prepare.executeQuery(); //Returns a ResultSet containing retrieved data, position before first row of the result set
			
			if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error Message");
				alert.setHeaderText(null);
				alert.setContentText("Please fill all blank fields");
				alert.showAndWait();
			}else {
				if (result.next()) { //Looks at the first row to see if there is an existing value, or if there is a username or password that fits
					
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Message");
					alert.setHeaderText(null);
					alert.setContentText("Successfully Logged In!");
					alert.showAndWait();
					
					loginButton.getScene().getWindow().hide();
					
					FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
					root = loader.load();
					
					String username = usernameField.getText();
					MenuController menuController = loader.getController();
					menuController.displayName(username);
					
					stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					
				}else {
					alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Message");
					alert.setHeaderText(null);
					alert.setContentText("Wrong Username/Password");
					alert.showAndWait();
				}
			}	
		}catch(Exception e) {e.printStackTrace();}
	}
}
