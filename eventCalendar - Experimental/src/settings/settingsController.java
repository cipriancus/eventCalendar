package settings;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller pentru clasa view de setari, settings.fxml
 * @author Ciprian Cusmuliuc 
 *
 */
public class settingsController implements Initializable {

	@FXML
	private Button exit;
	@FXML
	private Button settings;
	@FXML
	private Button email;
	@FXML
	private ToggleButton back;
	@FXML
	private ComboBox<String> syncPeriod;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepareSlideMenuAnimation();
	}

	private void prepareSlideMenuAnimation() {

		syncPeriod.getItems().add("30s");
		syncPeriod.getItems().add("1min");
		syncPeriod.getItems().add("5min");
		syncPeriod.getItems().add("10min");
		syncPeriod.getItems().add("30min");
		syncPeriod.getItems().add("1hour");

		exit.setOnAction((ActionEvent evt) -> {
			System.exit(0);
		});

		email.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) syncPeriod.getScene().getWindow();
			Scene scene = syncPeriod.getScene();

			AnchorPane root;
			try {
				root = FXMLLoader.load(getClass().getResource("../email.fxml"));
				root.getStylesheets().add("MainView.css");
				scene.setRoot(root);
				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		back.setOnAction((ActionEvent evt) -> {
			Stage stage = (Stage) syncPeriod.getScene().getWindow();
			Scene scene = syncPeriod.getScene();

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
	}

}
