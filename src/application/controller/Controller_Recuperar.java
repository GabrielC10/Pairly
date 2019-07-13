package application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.classes.Funcionario;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller_Recuperar {

    @FXML
    private JFXTextField txt_Login;

    @FXML
    private JFXTextField txt_Resposta;

    @FXML
    private JFXTextField txt_Chave;

    @FXML
    private JFXTextField txt_Senha;

    @FXML
    private JFXTextField txt_RepetirSenha;

    @FXML
    private JFXButton btn_Atualizar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    void atualizar(MouseEvent btn_Atualizar) {
    	Alerta alerta = new Alerta();
    	Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    	Funcionario f = new Funcionario();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	if(txt_Login.getText().equals(null)) {
    		alerta.alertInformation("Insira o login!");
    	}
    	else {
    		argumentos.put("login_funcionario", txt_Login.getText());	
    		String where = " where t.login_funcionario = :login_funcionario";
        	List<Funcionario> lista_funcionario = daoFunc.listarWhere(f, where, argumentos);
        	if(lista_funcionario.isEmpty()) {
        		alerta.alert("Login inválido!");
        	}
        	for(Funcionario x : lista_funcionario) {
        		if(x.getLogin_funcionario().equals(txt_Login.getText())) {
        			f.setChave_recuperacao(x.getChave_recuperacao());
        			f.setDados(x.getDados());
        			f.setId_funcionario(x.getId_funcionario());
        			f.setLogin_funcionario(x.getLogin_funcionario());
        			f.setPergunta(x.getPergunta());
        			f.setPlano(x.getPlano());
        			f.setProjetos(x.getProjetos());
        			f.setSenha_funcionario(x.getSenha_funcionario());
        		}
        	}
        	if(txt_Login.getText().equals(null) || txt_Resposta.getText().equals(null) 
        			|| txt_Chave.getText().equals(null) || txt_Senha.getText().equals(null)
        													|| txt_RepetirSenha.getText().equals(null)) {
        		alerta.alertInformation("Preencha todos os campos corretamente!");
        	}
        	else if (txt_Login.getText().length() < 5 || txt_Resposta.getText().length() < 2 
        			|| txt_Chave.getText().length() < 9 || txt_Senha.getText().length() < 5 
        													|| txt_RepetirSenha.getText().length() < 5) {
        		alerta.alertInformation("Preenche todos os campos corretamente!");
        	}
        	else if(f.getChave_recuperacao().equals(txt_Chave.getText()) 
        			& f.getPergunta().equals(txt_Resposta.getText()) 
        				& txt_Senha.getText().equals(txt_RepetirSenha.getText())) {
        		f.setSenha_funcionario(txt_Senha.getText());
        		daoFunc.alterar(f);
        		final Node source = (Node) btn_Atualizar.getSource();
        		final Stage stage = (Stage) source.getScene().getWindow();
        		stage.close();
        		alerta.alertInformation("Senha alterada com sucesso!");
        	}
        	else {
        		alerta.alert("Verifique a 'Chave de Recuperação', 'Reposta da Pergunta' e às 'Novas Senhas''");
        	}
    	}	
    }

    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

}
