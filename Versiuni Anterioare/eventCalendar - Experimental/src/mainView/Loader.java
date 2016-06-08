package mainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Clasa pentru a porni mainView.fxml,
 * se apeleaza static Loader.view, din clasa main
 * @author Ciprian Cusmuliuc 
 *
 */
public class Loader extends Application {

	public static void view(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane root = FXMLLoader.load(getClass().getResource("../mainView.fxml"));
		root.getStylesheets().add("MainView.css");

		Scene scene = new Scene(root, 1700	, 900);
        stage.setScene(scene);
        stage.show();	
	}

}
