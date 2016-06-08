package email;

import model.EmailAddress;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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

/**
 * Controller pentru clasa email, email.fxml
 * @author Ciprian Cusmuliuc 
 *
 */
public class emailController implements Initializable {
	private List<EmailAddress> allEmails = new ArrayList<EmailAddress>();

	@FXML
	private Button exit;
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
	@FXML
	private CheckBox allEmailsCheck;
	@FXML
	private CheckBox unreadCheck;
	@FXML
	private Button contactUs;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepareSlideMenuAnimation();
	}

	private void prepareSlideMenuAnimation() {
		
		EmailXMLParser emailParser = new EmailXMLParser();
		emailParser.deserialise();
		allEmails = emailParser.getEmails();
		
		for(EmailAddress iterator:allEmails){
			Text text=new Text();
			text.setText(iterator.toString());
			leftEmailList.getChildren().add(text);
		}
		

		exit.setOnAction((ActionEvent evt) -> {
			System.exit(0);
		});


		back.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) leftEmailList.getScene().getWindow();
			Scene scene = leftEmailList.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		contactUs.setOnAction((ActionEvent evt) -> {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/cipriancus/eventCalendar"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		saveButton.setOnAction((ActionEvent evt) -> {
			if(usernameEmail.getText()!=null && passwordEmail.getText()!=null){
				//adaugare confirmare a user pass
				EmailAddress emailAddress=new EmailAddress();
				
				emailAddress.setAddress(usernameEmail.getText());
				emailAddress.setPassword(passwordEmail.getText());
			
				if(allEmailsCheck.isSelected()==true){
					emailAddress.setRead(2);
					unreadCheck.setSelected(false);
					allEmailsCheck.setSelected(false);
				}else{
					emailAddress.setRead(1);//setez necitite ca default
					unreadCheck.setSelected(false);
					allEmailsCheck.setSelected(false);
				}
				allEmails.add(emailAddress);
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

}
