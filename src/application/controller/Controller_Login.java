package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import application.classes.Funcionario;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Cadastrar_Usuario;
import application.screen.Dashboard;
import application.screen.Esqueci_Senha;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Login implements Initializable{
	
    @FXML
    private Pane root;

    @FXML
    private JFXTextField txt_Login;

    @FXML
    private JFXPasswordField txt_Senha;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private JFXButton btn_Entrar;

    @FXML
    private JFXButton btn_Esqueci;
    
    @FXML
    private JFXSpinner spinner_load;
    
    @FXML
    private JFXButton btn_cadastro;

    private static Integer id_user;
    
    public static Integer getId_user() {
		return id_user;
	}

	public static void setId_user(Integer id_user) {
		Controller_Login.id_user = id_user;
	}

	@SuppressWarnings("static-access")
	@FXML
    void entrar(MouseEvent btn_Entrar) {  	
    	Alerta a = new Alerta();
    	Integer inter = 0;
    	Funcionario f = new Funcionario();
    	Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    	List<Funcionario> lista_funcionario = daoFunc.listar(f);
    	for(Funcionario x : lista_funcionario) {
    		if(x.getLogin_funcionario().equals(txt_Login.getText()) 
    				&& x.getSenha_funcionario().equals(txt_Senha.getText())) {
    			inter = 1;
    			Integer id = x.getId_funcionario();
    			Controller_Dashboard cont = new Controller_Dashboard();
    			cont.passagemParametro(id);
    			Controller_Login.setId_user(id);
    			Dashboard l = new Dashboard();
    			try {
    				final Node source = (Node) btn_Entrar.getSource();
    				final Stage stage = (Stage) source.getScene().getWindow();
    				stage.close();
					l.start(new Stage());
					spinner_load.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
					a.alert(e.getMessage());
					spinner_load.setVisible(false);
				}
    		}
    	}
    	if (inter == 0) {
    		a.alert("Login ou senha incorretos!");
    		spinner_load.setVisible(false);
    		txt_Login.setText("");
        	txt_Senha.setText("");
    	}
    	
    }
	
    @FXML
    void entrarKey(KeyEvent txt_Senha) {
    	final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
    	if(ENTER.match(txt_Senha)) {
    		Alerta a = new Alerta();
        	Integer inter = 0;
        	Funcionario f = new Funcionario();
        	Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
        	List<Funcionario> lista_funcionario = daoFunc.listar(f);
        	for(Funcionario x : lista_funcionario) {
        		if(x.getLogin_funcionario().equals(txt_Login.getText()) 
        				&& x.getSenha_funcionario().equals(this.txt_Senha.getText())) {
        			inter = 1;
        			Integer id = x.getId_funcionario();
        			Controller_Dashboard.passagemParametro(id);
        			Controller_Login.setId_user(id);
        			Dashboard l = new Dashboard();
        			try {
        				final Node source = (Node) txt_Senha.getSource();
        				final Stage stage = (Stage) source.getScene().getWindow();
        				stage.close();
    					l.start(new Stage());
    					spinner_load.setVisible(false);
    				} catch (Exception e) {
    					e.printStackTrace();
    					a.alert(e.getMessage());
    					spinner_load.setVisible(false);
    				}
        		}
        	}
        	if (inter == 0) {
        		a.alert("Login ou senha incorretos!");
        		spinner_load.setVisible(false);
        		txt_Login.setText("");
            	this.txt_Senha.setText("");
        	}
    	}
    }

    @FXML
    void sair(MouseEvent btn_Sair) {
    	try {
    		final Node source = (Node) btn_Sair.getSource();
    		final Stage stage = (Stage) source.getScene().getWindow();
    		stage.close();
    	}
    	catch(Exception e) {
    		Alerta a = new Alerta();
    		a.alert(e.getMessage());
    	}
    }
    
    @FXML
    void load(MouseEvent txt_Senha) {
    	this.spinner_load.setVisible(true);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_Login.setStyle("-fx-text-inner-color: white");
		txt_Senha.setStyle("-fx-text-inner-color: white");
		spinner_load.setVisible(false);
	}
	

    @FXML
    void screenCadastro(MouseEvent btn_cadastro) {
    	Cadastrar_Usuario c = new Cadastrar_Usuario();
    	try {
			c.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void esqueciMinhaSenha(MouseEvent btn_Esqueci) {
    	Esqueci_Senha s = new Esqueci_Senha();
    	try {
			s.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

		
}
