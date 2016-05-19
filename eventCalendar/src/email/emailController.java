package email;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import parseXML.EmailXMLParser;

public class emailController implements Initializable {
	private Map<String, String> allEmails = new HashMap<>();

	@FXML
	private ToggleButton menu;
	@FXML
	private AnchorPane navList;
	@FXML
	private TextArea currentDate;
	@FXML
	private Button exit;
	@FXML
	private Button settings;
	@FXML
	private Button email;
	@FXML
	private ToggleButton back;
	@FXML
	private VBox leftEmailList;
	@FXML
	private TextField usernameEmail;
	@FXML
	private PasswordField passwordEmail;
	@FXML
	private Button saveButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepareSlideMenuAnimation();
	}

	private void prepareSlideMenuAnimation() {
		TranslateTransition openNav = new TranslateTransition(new Duration(350), navList);
		openNav.setToX(0);
		TranslateTransition closeNav = new TranslateTransition(new Duration(350), navList);

		currentDate.setText(getDate());

		EmailXMLParser emailParser = new EmailXMLParser();
		emailParser.deserialise();
		allEmails = emailParser.getEmails();
		
		for(String iterator:allEmails.keySet()){
			Text text=new Text();
			text.setText(iterator);
			leftEmailList.getChildren().add(text);
		}
		
		menu.setOnAction((ActionEvent evt) -> {
			if (navList.getTranslateX() != 0) {
				openNav.play();
			} else {
				closeNav.setToX(-(navList.getWidth()));
				closeNav.play();
			}
		});

		exit.setOnAction((ActionEvent evt) -> {
			System.exit(0);
		});

		settings.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) navList.getScene().getWindow();
			Scene scene = navList.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("../settings.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		back.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) navList.getScene().getWindow();
			Scene scene = navList.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("../mainView.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		saveButton.setOnAction((ActionEvent evt) -> {
			if(usernameEmail.getText()!=null && passwordEmail.getText()!=null){
				//adaugare confirmare a user pass
				
				allEmails.put(usernameEmail.getText(), passwordEmail.getText());
				EmailXMLParser parser = new EmailXMLParser();
				parser.serialise(allEmails);
				
				Text text=new Text();
				text.setText(usernameEmail.getText());
				leftEmailList.getChildren().add(text);
				
				usernameEmail.clear();
				passwordEmail.clear();
			}
		});
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd   MM   yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
