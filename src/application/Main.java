package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root); 
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//String css = this.getClass().getResource("application.css").toExternalForm();
			//scene.getStylesheets().add(css);
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
			
			stage.setOnCloseRequest(event -> {
				event.consume(); //Makes sure program only closes when pressing ok button
				exitProgram(stage);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exitProgram(Stage stage)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to exit!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK) 
		{
			stage.close();
		}
	}
	
	public static void main(String[] args) {
		launch(args); //Launches Arguments
	}
}
