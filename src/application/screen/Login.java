package application.screen;

import java.io.File;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import application.exception.Alerta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			@SuppressWarnings("deprecation")
			URL url = new File("src/application/fxml/Login.fxml").toURL();
			Pane root = (Pane) FXMLLoader.load(url);
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(scene);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Pairly");
			@SuppressWarnings("unused")
			EntityManager em = emf.createEntityManager();
			primaryStage.show();
		}
		catch(Exception e) {
			Alerta a = new Alerta();
			a.alert(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
