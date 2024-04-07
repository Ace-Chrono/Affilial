package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController implements Initializable{
	
    @FXML
    private Button AccountIcon;

    @FXML
    private AnchorPane accountInfo;

    @FXML
    private Button accountInfoButton;

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<partnerData, String> contacterEmailColumn;

    @FXML
    private TextField contacterEmailField;

    @FXML
    private TableColumn<partnerData, String> contacterPhoneNumberColumn;

    @FXML
    private TextField contacterPhoneNumberField;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button dashboardIcon;

    @FXML
    private AnchorPane dataTable;

    @FXML
    private Button dataTableButton;

    @FXML
    private Button dataTableIcon;

    @FXML
    private TableColumn<partnerData, Date> dateJoinedColumn;

    @FXML
    private DatePicker dateJoinedField;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<partnerData, String> employeesAvailableColumn;

    @FXML
    private TextField employeesAvailableField;
    
    @FXML
    private Text employeesAvailableText;

    @FXML
    private TableColumn<partnerData, String> fundsAvailableColumn;

    @FXML
    private TextField fundsAvailableField;
    
    @FXML
    private Text fundsAvailableText;

    @FXML
    private Button logoutButton;

    @FXML
    private TableColumn<partnerData, String> mainContacterColumn;

    @FXML
    private TextField mainContacterField;

    @FXML
    private TextField organizationTypeField;
    
    @FXML
    private PieChart organizationTypePieChart;

    @FXML
    private TableColumn<partnerData, String> orginazationTypeColumn;
    
    @FXML
    private Text partnerAmountText;
    
    @FXML
    private TableView<partnerData> partnerDataTable;

    @FXML
    private TableColumn<partnerData, String> partnerNameColumn;

    @FXML
    private TextField partnerNameField;
    
    @FXML
    private AnchorPane helpPage;
    
    @FXML
    private Button HelpIcon;
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField searchButton;

    @FXML
    private AnchorPane sidebar1;

    @FXML
    private AnchorPane sidebar2;

    @FXML
    private Button sidebarButton;
    
    @FXML
    private LineChart<String, Number> totalPartnersChart;

    @FXML
    private Button updateButton;
    
    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLabel;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement; 
    private ResultSet result;
    
    private String username;
    
    public ObservableList<partnerData> addPartnersListData()
    {
    	ObservableList<partnerData> listPartners = FXCollections.observableArrayList();
    	
    	String sql = "SELECT * FROM partners";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		partnerData partnerD;
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while(result.next())
    		{
    			partnerD = new partnerData(result.getInt("partnerId")
    					, result.getString("partnerName")
    					, result.getString("organizationType")
    					, result.getBigDecimal("fundsAvailable")
    					, result.getInt("employeesAvailable")
    					, result.getString("mainContacter")
    					, result.getString("contacterPhoneNumber")
    					, result.getString("contacterEmail")
    					, result.getDate("dateJoined"));
    			
    			listPartners.add(partnerD);
    		}
    		
    	}catch(Exception e) {e.printStackTrace();}
    	return listPartners;
    }
    
    private ObservableList<partnerData> addPartnersListD;
    public void addPartnersShowListData()
    {
    	addPartnersListD = addPartnersListData();
    	
    	partnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
    	orginazationTypeColumn.setCellValueFactory(new PropertyValueFactory<>("organizationType"));
    	fundsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("fundsAvailable"));
    	employeesAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("employeesAvailable"));
    	mainContacterColumn.setCellValueFactory(new PropertyValueFactory<>("mainContacter"));
    	contacterPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("contacterPhoneNumber"));
    	contacterEmailColumn.setCellValueFactory(new PropertyValueFactory<>("contacterEmail"));
    	dateJoinedColumn.setCellValueFactory(new PropertyValueFactory<>("dateJoined"));
    	
    	partnerDataTable.setItems(addPartnersListD); //Error here
    	addPartnersSearch();
    }
    
    public void addPartnersSelect()
    {
    	partnerData partnerD = partnerDataTable.getSelectionModel().getSelectedItem();
    	int num = partnerDataTable.getSelectionModel().getSelectedIndex();
    	
    	if ((num - 1) < -1) {return;}
    	
    	partnerNameField.setText(String.valueOf(partnerD.getPartnerName()));
    	organizationTypeField.setText(String.valueOf(partnerD.getOrganizationType()));
    	fundsAvailableField.setText(String.valueOf(partnerD.getFundsAvailable()));
    	employeesAvailableField.setText(String.valueOf(partnerD.getEmployeesAvailable()));
    	mainContacterField.setText(String.valueOf(partnerD.getMainContacter()));
    	contacterPhoneNumberField.setText(String.valueOf(partnerD.getContacterPhoneNumber()));
    	contacterEmailField.setText(String.valueOf(partnerD.getContacterEmail()));
    	dateJoinedField.setValue(partnerD.getDateJoined().toLocalDate());
    }
    
    public void addPartnersAdd()
    {
    	String insertData = "INSERT INTO partners "
    			+ "(partnerId, partnerName, organizationType, fundsAvailable, employeesAvailable, mainContacter, contacterPhoneNumber, contacterEmail, dateJoined) "
    			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		Alert alert;
    		if (partnerNameField.getText().isEmpty() || organizationTypeField.getText().isEmpty() ||
    				fundsAvailableField.getText().isEmpty() || employeesAvailableField.getText().isEmpty() ||
    				mainContacterField.getText().isEmpty() || contacterPhoneNumberField.getText().isEmpty() ||
    				contacterEmailField.getText().isEmpty() || dateJoinedField.getValue() == null)
    		{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill in all blank fields");
    			alert.showAndWait();
    		}
    		else
    		{
    			String checkData = "SELECT partnerName FROM partners WHERE partnerName = '"+ partnerNameField.getText() + "'";
    			
    			statement = connect.createStatement();
    			result = statement.executeQuery(checkData);
    			
    			if(result.next())
    			{
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Partner already exists");
        			alert.showAndWait();
    			}
    			else
    			{
    				String fundsAvailableText = fundsAvailableField.getText();
    				String employeesAvailableText = employeesAvailableField.getText();
    				
    				prepare = connect.prepareStatement(insertData);
        			prepare.setInt(1, partnerDataTable.getItems().size() + 1);
        			prepare.setString(2, partnerNameField.getText());
        			prepare.setString(3, organizationTypeField.getText());
        			prepare.setBigDecimal(4, new BigDecimal(fundsAvailableText));
        			prepare.setInt(5, Integer.parseInt(employeesAvailableText));
        			prepare.setString(6, mainContacterField.getText());
        			prepare.setString(7, contacterPhoneNumberField.getText());
        			prepare.setString(8, contacterEmailField.getText());
        			prepare.setString(9, String.valueOf(dateJoinedField.getValue()));
        			prepare.executeUpdate();
        			
        			String sortData = "SELECT * FROM partners ORDER BY TIMESTAMP(dateJoined)";
                    result = statement.executeQuery(sortData);
                    int newPartnerId = 1;
                    while (result.next()) {
                        int partnerId = result.getInt("partnerId");
                        
                        // Check if the newPartnerId already exists, if so, find the next available unique id
                        while (isPartnerIdExists(newPartnerId, connect)) {
                            newPartnerId++;
                        }

                        String updateData = "UPDATE partners SET partnerId = ? WHERE partnerId = ?";
                        prepare = connect.prepareStatement(updateData);
                        prepare.setInt(1, newPartnerId++);
                        prepare.setInt(2, partnerId);
                        prepare.executeUpdate();
                    }

                    result = statement.executeQuery(sortData);
                    newPartnerId = 1;
                    boolean fixBug = false;
                    boolean bugChecker = true;
                    while (result.next()) {
                        int partnerId = result.getInt("partnerId");
                        if (partnerId != 1 && bugChecker)
                        {
                        	fixBug = true;
                        	bugChecker = false;
                        }
                        if (fixBug)
                        {
                        	String updateData = "UPDATE partners SET partnerId = ? WHERE partnerId = ?";
                            prepare = connect.prepareStatement(updateData);
                            prepare.setInt(1, newPartnerId++);
                            prepare.setInt(2, partnerId);
                            prepare.executeUpdate();
                        }
                    }
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully added!");
        			alert.showAndWait();
        			
        			addPartnersShowListData();
        			addPartnersClear();
    			}
    		}
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    }
    
    private boolean isPartnerIdExists(int partnerId, Connection connection) throws SQLException {
        String checkData = "SELECT COUNT(*) FROM partners WHERE partnerId = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkData)) {
            checkStatement.setInt(1, partnerId);
            try (ResultSet checkResult = checkStatement.executeQuery()) {
                if (checkResult.next()) {
                    int count = checkResult.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

	public void addPartnersClear()
    {
    	partnerNameField.setText("");
    	organizationTypeField.setText("");
    	fundsAvailableField.setText("");
    	employeesAvailableField.setText("");
    	mainContacterField.setText("");
    	contacterPhoneNumberField.setText("");
    	contacterEmailField.setText("");
    	dateJoinedField.setValue(null);
    }
    
    public void addPartnersUpdate()
    {
    	String updateData = "UPDATE partners SET "
    			+ "partnerName = '" + partnerNameField.getText()
    			+ "', organizationType = '" + organizationTypeField.getText()
    			+ "', fundsAvailable = '" + fundsAvailableField.getText()
    			+ "', employeesAvailable = '" + employeesAvailableField.getText()
    			+ "', mainContacter = '" + mainContacterField.getText()
    			+ "', contacterPhoneNumber = '" + contacterPhoneNumberField.getText()
    			+ "', contacterEmail = '" + contacterEmailField.getText()
    			+ "', dateJoined = '" + dateJoinedField.getValue() + "' WHERE partnerName = '" 
    			+ partnerDataTable.getSelectionModel().getSelectedItem().getPartnerName() + "'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (partnerNameField.getText().isEmpty() || organizationTypeField.getText().isEmpty() ||
    				fundsAvailableField.getText().isEmpty() || employeesAvailableField.getText().isEmpty() ||
    				mainContacterField.getText().isEmpty() || contacterPhoneNumberField.getText().isEmpty() ||
    				contacterEmailField.getText().isEmpty() || dateJoinedField.getValue() == null)
    		{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill in all blank fields");
    			alert.showAndWait();
    		}
    		else
    		{
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure you want to update the information?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK))
    			{
    				statement = connect.createStatement();
        			statement.executeUpdate(updateData);
        			
        			String sortData = "SELECT * FROM partners ORDER BY TIMESTAMP(dateJoined)";
                    result = statement.executeQuery(sortData);
                    int newPartnerId = 1;
                    while (result.next()) {
                        int partnerId = result.getInt("partnerId");
                        
                        // Check if the newPartnerId already exists, if so, find the next available unique id
                        while (isPartnerIdExists(newPartnerId, connect)) {
                            newPartnerId++;
                        }

                        String reSort = "UPDATE partners SET partnerId = ? WHERE partnerId = ?";
                        prepare = connect.prepareStatement(reSort);
                        prepare.setInt(1, newPartnerId++);
                        prepare.setInt(2, partnerId);
                        prepare.executeUpdate();
                    }

                    result = statement.executeQuery(sortData);
                    newPartnerId = 1;
                    boolean fixBug = false;
                    boolean bugChecker = true;
                    while (result.next()) {
                        int partnerId = result.getInt("partnerId");
                        if (partnerId != 1 && bugChecker)
                        {
                        	fixBug = true;
                        	bugChecker = false;
                        }
                        if (fixBug)
                        {
                        	String reSort = "UPDATE partners SET partnerId = ? WHERE partnerId = ?";
                            prepare = connect.prepareStatement(reSort);
                            prepare.setInt(1, newPartnerId++);
                            prepare.setInt(2, partnerId);
                            prepare.executeUpdate();
                        }
                    }
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully updated!");
        			alert.showAndWait();
        			
        			addPartnersShowListData();
        			addPartnersClear();
    			}
    			else
    			{
    				return;
    			}
    		}
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    }
    
    public void addPartnersDelete()
    {
    	String deleteData = "DELETE FROM partners WHERE partnerName = '"
    			+ partnerDataTable.getSelectionModel().getSelectedItem().getPartnerName() + "'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (partnerNameField.getText().isEmpty() || organizationTypeField.getText().isEmpty() ||
    				fundsAvailableField.getText().isEmpty() || employeesAvailableField.getText().isEmpty() ||
    				mainContacterField.getText().isEmpty() || contacterPhoneNumberField.getText().isEmpty() ||
    				contacterEmailField.getText().isEmpty() || dateJoinedField.getValue() == null)
    		{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill in all blank fields");
    			alert.showAndWait();
    		}
    		else
    		{
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure you want to delete the information?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK))
    			{
    				int deletedPartnerId = partnerDataTable.getSelectionModel().getSelectedItem().getPartnerId();

                    // Execute the DELETE statement
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    // Update the remaining rows with partnerId greater than the deletedPartnerId
                    String updateData = "UPDATE partners SET partnerId = partnerId - 1 WHERE partnerId > " + deletedPartnerId;
                    statement.executeUpdate(updateData);
    				
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully deleted!");
        			alert.showAndWait();
        			
        			addPartnersShowListData();
        			addPartnersClear();
    			}
    			else
    			{
    				return;
    			}
    		}
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void addPartnersSearch()
    {
    	FilteredList<partnerData> filter = new FilteredList<>(addPartnersListD, e-> true);
    	
    	searchButton.textProperty().addListener((Observable, oldValue, newValue) -> {
    		filter.setPredicate(predicatePartnerData ->{
    			
    			if(newValue == null || newValue.isEmpty())
    				return true; 
    			
    			String searchKey = newValue.toLowerCase();
    			
    			if(predicatePartnerData.getPartnerName().toLowerCase().contains(searchKey))
    				return true; 
    			else if (predicatePartnerData.getOrganizationType().toLowerCase().contains(searchKey))
    				return true; 
    			else if (predicatePartnerData.getMainContacter().toLowerCase().contains(searchKey))
    				return true; 
    			else if (predicatePartnerData.getContacterPhoneNumber().contains(searchKey))
    				return true; 
    			else if (predicatePartnerData.getContacterEmail().toLowerCase().contains(searchKey))
    				return true; 
    			else if (predicatePartnerData.getDateJoined().toString().contains(searchKey))
    				return true; 
    			else
    				return false; 
    		});
    		
    	});
    	
    	SortedList<partnerData> sortList = new SortedList<>(filter);
    	
    	sortList.comparatorProperty().bind(partnerDataTable.comparatorProperty());
    	partnerDataTable.setItems(sortList);

    }
    
    public void updateAccount()
    {
    	String updateData = "UPDATE accounts SET "
    			+ "Username = '" + usernameField.getText()
    			+ "', Password = '" + passwordField.getText()
    			+ "' WHERE Username = '" 
    			+ username + "'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty())
    		{
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill in all blank fields");
    			alert.showAndWait();
    		}
    		else
    		{
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confirmation Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure you want to update the information?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK))
    			{
    				statement = connect.createStatement();
        			statement.executeUpdate(updateData);
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully updated!");
        			alert.showAndWait();
        			
        			usernameField.setText("");
        			passwordField.setText("");
    			}
    			else
    			{
    				return;
    			}
    		}
    		
    	}catch(Exception e) {e.printStackTrace();}
    	
    }
    
    public void generateReport() 
    {
    	String downloadData = "SELECT partnerId, partnerName, organizationType, fundsAvailable, employeesAvailable, mainContacter, contacterPhoneNumber, contacterEmail, dateJoined FROM partners";
    	
    	connect = Database.connectDb();
    	try {
    		statement = connect.createStatement();
			result = statement.executeQuery(downloadData);
			
			StringBuilder reportData = new StringBuilder();
			
	        // Process the result set and write the report to the file
	        while (result.next()) 
	        {
	            // Retrieve data from the result set
	            int column1Value = result.getInt("partnerId");
	            String column2Value = result.getString("partnerName");
	            String column3Value = result.getString("organizationType");
	            BigDecimal column4Value = result.getBigDecimal("fundsAvailable");
	            int column5Value = result.getInt("employeesAvailable");
	            String column6Value = result.getString("mainContacter");
	            String column7Value = result.getString("contacterPhoneNumber");
	            String column8Value = result.getString("contacterEmail");
	            String column9Value = result.getString("dateJoined");

	            reportData.append("Partner ID: ").append(column1Value).append("\n");
                reportData.append("Partner Name: ").append(column2Value).append("\n");
                reportData.append("Organization Type: ").append(column3Value).append("\n");
                reportData.append("Funds Available: ").append(column4Value).append("\n");
                reportData.append("Employees Available: ").append(column5Value).append("\n");
                reportData.append("Main Contacter: ").append(column6Value).append("\n");
                reportData.append("Contacter Phone Number: ").append(column7Value).append("\n");
                reportData.append("Contacter Email: ").append(column8Value).append("\n");
                reportData.append("Date Joined: ").append(column9Value).append("\n");
                reportData.append("-----------------------------------\n");
	        }
	            
	        // Prompt the user to choose the location to save the file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Report");
            fileChooser.setInitialFileName("report.txt");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File fileToSave = fileChooser.showSaveDialog(null);

            if (fileToSave != null) {
                // Save the report data to the selected file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                    writer.write(reportData.toString());
                    System.out.println("Report saved to: " + fileToSave.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            try {
                Runtime.getRuntime().exec("notepad.exe " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
                
	        System.out.println("Report generated and saved to 'report.txt'");
	            
	    }catch(Exception e) {e.printStackTrace();}
    	       
    }
    
    public void homeDisplayTotalPartnersChart()
    {
    	totalPartnersChart.getData().clear();
    	
    	String sql = "SELECT dateJoined, partnerId FROM partners ORDER BY TIMESTAMP(dateJoined)";
    	
    	//"SELECT MAX(partnerId) AS partnerId, dateJoined FROM partners GROUP BY dateJoined ORDER BY TIMESTAMP(dateJoined)"
    			
    	//"SELECT dateJoined, partnerId FROM (SELECT dateJoined, partnerId FROM partners ORDER BY TIMESTAMP(dateJoined) DESC LIMIT 5) AS subquery ORDER BY TIMESTAMP(dateJoined) ASC";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		XYChart.Series<String, Number> chart = new XYChart.Series<>();
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		
    		LocalDate startDate = null;
            LocalDate endDate = null;
    		boolean firstDataPoint = true;
    		int daysDifference = 0;
    		
    		Set<String> addedDates = new HashSet<>();
    		
    		while(result.next())
    		{
    			LocalDate date = result.getDate("dateJoined").toLocalDate();
    			int partnerId = result.getInt("partnerId");
    			String xValue = date.format(formatter);
    			
    			if (firstDataPoint) {
                    // Set the start date for date range
                    startDate = date;
                    endDate = date;
                    firstDataPoint = false;
                } else {
                    endDate = date;
                    while (!startDate.isEqual(endDate)) {
                        String inBetweenDate = startDate.format(formatter);
                        chart.getData().add(new XYChart.Data<>(inBetweenDate, partnerId-1));
                        startDate = startDate.plusDays(1);
                        daysDifference++;
                    }
                }
    			
    			if (!addedDates.contains(xValue)) {
                    chart.getData().add(new XYChart.Data<>(xValue, partnerId));
                    addedDates.add(xValue);
                }
    			else
    			{
    				chart.getData().remove(chart.getData().size()-1);
    				chart.getData().add(new XYChart.Data<>(xValue, partnerId));
    			}
    		}
    		for (XYChart.Data<String, Number> data : chart.getData()) {
                Node node = data.getNode();
                if (node != null) {
                    node.setStyle("-fx-background-color: blue;");
                }
            }

            if (daysDifference >= 730) {
            	Map<String, Integer> yearCounts = new HashMap<>();
            	
            	try
            	{
            		prepare = connect.prepareStatement(sql);
            		result = prepare.executeQuery();
            		while (result.next())
            		{
            			Date date = result.getDate("dateJoined");
                        Year year = Year.from(date.toLocalDate());
                        String yearString = year.toString();
                        yearCounts.put(yearString, yearCounts.getOrDefault(yearString, 0) + 1);
            		}
            	} catch (SQLException e) {
                    e.printStackTrace();
                }
        		
                chart.getData().clear();

                for (Map.Entry<String, Integer> entry : yearCounts.entrySet()) {
                    chart.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                }
                
                chart.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
                List<XYChart.Data<String, Number>> dataList = new ArrayList<>(chart.getData());
            	int previousCount = 0; 
            	String previousX = "";
            	int index = 0;
            	for (XYChart.Data<String, Number> data : chart.getData()) {
            	    String xValue = data.getXValue();
            	    int yValue = data.getYValue().intValue();
            	    data.setYValue(yValue + previousCount);
            	    
            	    if (previousX != null && !previousX.isEmpty()) {
            	        int yearDifference = Integer.parseInt(xValue.substring(0, 4)) - Integer.parseInt(previousX.substring(0, 4));
            	        for (int i = 1; i < yearDifference; i++) {
            	            int year = Integer.parseInt(previousX.substring(0, 4)) + i;
            	            String newDateString = String.valueOf(year);
            	            XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(newDateString, previousCount);
            	            dataList.add(index, newDataPoint);
            	            index++;
            	        }
            	    }
            	    previousCount += yValue;
            	    previousX = xValue;
            	}
            	dataList.sort(Comparator.comparing(XYChart.Data::getXValue));

            	chart.getData().clear();
            	chart.getData().addAll(dataList);
            } 
            else if (daysDifference >= 60) {
            	Map<String, Integer> monthCounts = new HashMap<>();
            	
            	try
            	{
            		prepare = connect.prepareStatement(sql);
            		result = prepare.executeQuery();
            		while (result.next())
            		{
            			Date date = result.getDate("dateJoined");
            			String monthString = new SimpleDateFormat("yyyy-MM").format(date);
            			monthCounts.put(monthString, monthCounts.getOrDefault(monthString, 0) + 1);
            		}
	
            	} catch (SQLException e) {
                    e.printStackTrace();
                }
        		
                chart.getData().clear();

                for (Map.Entry<String, Integer> entry : monthCounts.entrySet()) {

                    chart.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                }
                
                chart.getData().sort(Comparator.comparing(XYChart.Data::getXValue));
                List<XYChart.Data<String, Number>> dataList = new ArrayList<>(chart.getData());
            	int previousCount = 0; 
            	String previousX = "";
            	int index = 0;
            	for (XYChart.Data<String, Number> data : chart.getData()) {
            	    String xValue = data.getXValue();
            	    int yValue = data.getYValue().intValue();
            	    data.setYValue(yValue + previousCount);

            	    if (previousX != null && !previousX.isEmpty()) {
            	        int yearDifference = Integer.parseInt(xValue.substring(0, 4)) - Integer.parseInt(previousX.substring(0, 4));
            	        int monthDifference = Integer.parseInt(xValue.substring(5, 7)) - Integer.parseInt(previousX.substring(5, 7));

            	        for (int i = 1; i < yearDifference; i++) {
            	            int year = Integer.parseInt(previousX.substring(0, 4)) + i;
            	            for (int j = 1; j <= 12; j++) {
            	                String newDateString = String.format("%04d-%02d", year, j);
            	                XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(newDateString, previousCount);
            	                dataList.add(index, newDataPoint);
            	                index++;
            	            }
            	        }

            	        for (int i = 1; i < monthDifference; i++) {
            	            int month = Integer.parseInt(previousX.substring(5, 7)) + i;
            	            if (month > 12) {
            	                month -= 12;
            	                int year = Integer.parseInt(previousX.substring(0, 4)) + 1;
            	                String newDateString = String.format("%04d-%02d", year, month);
            	                XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(newDateString, previousCount);
            	                dataList.add(index, newDataPoint);
            	                index++;
            	            } else {
            	                String newDateString = String.format("%04d-%02d", Integer.parseInt(previousX.substring(0, 4)), month);
            	                XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(newDateString, previousCount);
            	                dataList.add(index, newDataPoint);
            	                index++;
            	            }
            	        }
            	    }

            	    previousCount += yValue;
            	    previousX = xValue;
            	}
            	dataList.sort(Comparator.comparing(XYChart.Data::getXValue));

            	chart.getData().clear();
            	chart.getData().addAll(dataList);
            } 

            totalPartnersChart.setAnimated(false);
            totalPartnersChart.setLegendVisible(false);
    		totalPartnersChart.getData().add(chart);
    		
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void homeDisplayOrganizationTypeChart()
    {
    	organizationTypePieChart.getData().clear();

    	
    	String sql = "SELECT organizationType, COUNT(*) AS partnerId FROM partners GROUP BY organizationType";
    	
    	connect = Database.connectDb();
    	
    	try {
    		List<PieChart.Data> dataList = new ArrayList<>();
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while (result.next())
    		{
    			dataList.add(new PieChart.Data(result.getString("organizationType"), result.getInt("partnerId")));
    		}
        	//organizationTypePieChart.setAnimated(false);
        	organizationTypePieChart.getData().addAll(dataList);
        	
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void homeDisplayPartnerAmount()
    {
    	String sql = "SELECT partnerId FROM partners";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		int partnerCount = 0;
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while(result.next())
    		{
    			partnerCount = result.getInt("partnerId");
    		}
    		
    		partnerAmountText.setText(String.valueOf(partnerCount));
    		
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void homeDisplayFundsAvailable()
    {
    	String sql = "SELECT fundsAvailable FROM partners";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		BigDecimal fundsAvailable = new BigDecimal (0);
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while(result.next())
    		{
    			fundsAvailable = fundsAvailable.add(result.getBigDecimal("fundsAvailable"));
    		}
    		
    		fundsAvailableText.setText(String.valueOf(fundsAvailable));
    		
    	}catch(Exception e) {e.printStackTrace();}
    }
    
    public void homeDisplayEmployeesAvailable()
    {
    	String sql = "SELECT employeesAvailable FROM partners";
    	
    	connect = Database.connectDb();
    	
    	try {
    		
    		int employeesAvailable = 0;
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while(result.next())
    		{
    			employeesAvailable += result.getInt("employeesAvailable");
    		}
    		
    		employeesAvailableText.setText(String.valueOf(employeesAvailable));
    		
    	}catch(Exception e) {e.printStackTrace();}
    }
	
	private Stage stage; 
	private Scene scene;
	private Parent root;
	
	private boolean sidebarOpen = false; 
	private boolean inAnimation = false; 
	
	public void displayName(String username)
	{
		this.username = username;
		usernameLabel.setText("Hello: " + username);
	}
	
	public void logout(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK) 
		{
			try {
				root = FXMLLoader.load(getClass().getResource("Login.fxml"));
				//FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
				//root = loader.load();
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sidebarExpandOrShrink(ActionEvent event)
	{
		if (sidebarOpen && !inAnimation)
		{
			inAnimation = true; 
			
			TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.25),sidebar2);
			translateTransition1.setByX(-300);
			translateTransition1.play();
			
			translateTransition1.setOnFinished(event1 ->{
				sidebar2.setVisible(false);
				sidebarOpen = false; 
				inAnimation = false; 
			});
		}
		else if (!inAnimation)
		{
			inAnimation = true; 
			
			sidebar2.setVisible(true);
			
			TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.25),sidebar2);
			translateTransition1.setByX(+300);
			translateTransition1.play();
			
			translateTransition1.setOnFinished(event1 ->{
				sidebarOpen = true; 
				inAnimation = false; 
			});
		}
	}
	
	public void openDashboard(ActionEvent event)
	{
		dashboard.setVisible(true);
		accountInfo.setVisible(false);
		dataTable.setVisible(false);
		dashboard.setDisable(false);
		accountInfo.setDisable(true);
		dataTable.setDisable(true);
		homeDisplayTotalPartnersChart();
		homeDisplayOrganizationTypeChart();
		homeDisplayPartnerAmount();
		homeDisplayFundsAvailable();
		homeDisplayEmployeesAvailable();
	}
	
	public void openDataTable(ActionEvent event)
	{
		addPartnersShowListData();
		dashboard.setVisible(false);
		accountInfo.setVisible(false);
		dataTable.setVisible(true);
		helpPage.setVisible(false);
		dashboard.setDisable(true);
		accountInfo.setDisable(true);
		dataTable.setDisable(false);
		helpPage.setDisable(true);
	}
	
	public void openAccountInfo(ActionEvent event)
	{
		dashboard.setVisible(false);
		accountInfo.setVisible(true);
		dataTable.setVisible(false);
		helpPage.setVisible(false);
		dashboard.setDisable(true);
		accountInfo.setDisable(false);
		dataTable.setDisable(true);
		helpPage.setDisable(true);
	}
	
	public void openHelpPage(ActionEvent event)
	{
		dashboard.setVisible(false);
		accountInfo.setVisible(false);
		dataTable.setVisible(false);
		helpPage.setVisible(true);
		dashboard.setDisable(true);
		accountInfo.setDisable(true);
		dataTable.setDisable(true);
		helpPage.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dashboard.setVisible(true);
		accountInfo.setVisible(false);
		dataTable.setVisible(false);
		helpPage.setVisible(false);
		dashboard.setDisable(false);
		accountInfo.setDisable(true);
		dataTable.setDisable(true);
		helpPage.setDisable(true);
		addPartnersShowListData();
		homeDisplayTotalPartnersChart();
		homeDisplayOrganizationTypeChart();
		homeDisplayPartnerAmount();
		homeDisplayFundsAvailable();
		homeDisplayEmployeesAvailable();
	}
}
