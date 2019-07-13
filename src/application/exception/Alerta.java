package application.exception;

import javafx.scene.control.Alert;

public class Alerta {

	public void alert(String mensagem) {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle("Error");
		alert.setContentText(mensagem);
		alert.show();
		
	}
	
	public void alertInformation(String mensagem) {
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Information");
		alert.setContentText(mensagem);
		alert.show();
		
	}
	
	public void alertConfirmation(String mensagem) {
		
		Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setHeaderText(null);
		alert.setTitle("Confirmation");
		alert.setContentText(mensagem);
		alert.show();
		
	}
	
}
