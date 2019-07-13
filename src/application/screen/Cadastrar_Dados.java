package application.screen;

import java.io.File;
import java.net.URL;

import application.exception.Alerta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Cadastrar_Dados extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			@SuppressWarnings("deprecation")
			URL url = new File("src/application/fxml/Cadastrar_Dados.fxml").toURL();
			Pane root = (Pane) FXMLLoader.load(url);
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) {
			Alerta a = new Alerta();
			a.alert(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
