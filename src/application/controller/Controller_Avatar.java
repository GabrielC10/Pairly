package application.controller;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Avatar {

	private String caminho1 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\boy-1.png";
	private String caminho2 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\man-1.png";
	private String caminho3 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\man.png";
	private String caminho4 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\girl-1.png";
	private String caminho5 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\man-4.png";
	private String caminho6 = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\girl.png";
	private static String caminho_original = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\avatares\\generic.png";

	public static String getCaminho_original() {
		return caminho_original;
	}

	public static void setCaminho_original(String caminho_original) {
		Controller_Avatar.caminho_original = caminho_original;
	}

	public String getCaminho1() {
		return caminho1;
	}

	public String getCaminho2() {
		return caminho2;
	}

	public String getCaminho3() {
		return caminho3;
	}

	public String getCaminho4() {
		return caminho4;
	}

	public String getCaminho5() {
		return caminho5;
	}

	public String getCaminho6() {
		return caminho6;
	}

	public Pane getRoot() {
		return root;
	}

	public ImageView getImg_View1() {
		return img_View1;
	}

	public ImageView getImg_View2() {
		return img_View2;
	}

	public ImageView getImg_View3() {
		return img_View3;
	}

	public ImageView getImg_View4() {
		return img_View4;
	}

	public ImageView getImg_View5() {
		return img_View5;
	}

	public ImageView getImg_View6() {
		return img_View6;
	}

	public JFXButton getBtn_Selecionar() {
		return btn_Selecionar;
	}

	public JFXButton getBtn_Sair() {
		return btn_Sair;
	}

	@FXML
    private Pane root;

    @FXML
    private ImageView img_View1;

    @FXML
    private ImageView img_View2;

    @FXML
    private ImageView img_View3;

    @FXML
    private ImageView img_View4;

    @FXML
    private ImageView img_View5;

    @FXML
    private ImageView img_View6;

    @FXML
    private JFXButton btn_Selecionar;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage scene = (Stage) source.getScene().getWindow();
    	scene.close();
    }

    @FXML
    void selecionar(MouseEvent btn_Selecionar) {
    	final Node source = (Node) btn_Selecionar.getSource();
    	final Stage scene = (Stage) source.getScene().getWindow();
    	scene.close();
    }

    @FXML
    void selecionarImg1(MouseEvent img_View1) {
    	Controller_Avatar.setCaminho_original(caminho1);
    }

    @FXML
    void selecionarImg2(MouseEvent img_View2) {
    	Controller_Avatar.setCaminho_original(caminho2);
    }

    @FXML
    void selecionarImg3(MouseEvent img_View3) {
    	Controller_Avatar.setCaminho_original(caminho3);
    }

    @FXML
    void selecionarImg4(MouseEvent img_View4) {
    	Controller_Avatar.setCaminho_original(caminho4);
    }

    @FXML
    void selecionarImg5(MouseEvent img_View5) {
    	Controller_Avatar.setCaminho_original(caminho5);
    }

    @FXML
    void selecionarImg6(MouseEvent img_View6) {
    	Controller_Avatar.setCaminho_original(caminho6);
    }

}
