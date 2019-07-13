package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.classes.Funcionario;
import application.classes.Plano;
import application.classes.Projeto;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Cadastrar_Dados;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_CadastrarUsuario implements Initializable {

    @FXML
    private Pane root;

    @FXML
    private JFXTextField txt_Login;

    @FXML
    private JFXPasswordField txt_Senha;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private JFXPasswordField txt_repetirsenha;

     @FXML
    private JFXButton btn_cadastrar;

    @FXML
    private JFXPasswordField txt_pergunta;

    @FXML
    private JFXTextField txt_recuperacao;
    
    private static String chave_recuperacao;

    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage scene = (Stage) source.getScene().getWindow();
    	scene.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_pergunta.setStyle("-fx-text-inner-color:white");
		txt_recuperacao.setStyle("-fx-text-inner-color:white");
		txt_repetirsenha.setStyle("-fx-text-inner-color:white");
		txt_Login.setStyle("-fx-text-inner-color:white");
		txt_Senha.setStyle("-fx-text-inner-color:white");
		chave_recuperacao = chaveRecuperacao();
		txt_recuperacao.setText(chave_recuperacao);
	}
	
	public String chaveRecuperacao() {
		Random random = new Random();
		Integer numero0 = random.nextInt(9);
		Integer numero1 = random.nextInt(9);
		Integer numero2 = random.nextInt(9);
		Integer numero3 = random.nextInt(9);
		Integer numero4 = random.nextInt(9);
		Integer numero5 = random.nextInt(9);
		Integer numero6 = random.nextInt(9);
		Integer numero7 = random.nextInt(9);
		Integer numero8 = random.nextInt(9);
		Integer numero9 = random.nextInt(9);
		String chave = numero0.toString().concat(numero1.toString().concat(numero2.toString().concat(numero3.toString()
				.concat(numero4.toString().concat(numero5.toString().concat(numero6.toString()
						.concat(numero7.toString().concat(numero8.toString().concat(numero9.toString())))))))));
		return chave;
	}

    @FXML
    void screenDados(MouseEvent btn_cadastrar) throws SQLException {
    	Alerta a = new Alerta();
		if(txt_Login.getText().equals(null) || txt_Senha.getText().equals(null) 
				|| txt_repetirsenha.getText().equals(null) || txt_pergunta.getText().equals(null)) {
			a.alert("Preencha todos os campos corretamente!");
		}
		else if(txt_Login.getText().length() < 5 || txt_Senha.getText().length() < 5 
				|| txt_repetirsenha.getText().length() < 5 || txt_pergunta.getText().length() < 1) {
			a.alertInformation("Número de caracteres inválidos!");
		}
		else if(txt_Senha.getText().equals(txt_repetirsenha.getText())) {
			Funcionario f = new Funcionario();
			Plano p = new Plano();
			Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
			List<Funcionario> lista_funcionario = daoFunc.listar(f);
			Dao<Plano> daoPlano = new DaoGeneric<Plano>();
			List<Plano> lista_plano = daoPlano.listar(p);
			p.setId_plano(lista_plano.get(0).getId_plano());
			p.setNome_plano(lista_plano.get(0).getNome_plano());
			p.setPreco(lista_plano.get(0).getPreco());
			for(Funcionario r : lista_funcionario) {
				if(r.getChave_recuperacao() == chave_recuperacao) {
					while(r.getChave_recuperacao() == chave_recuperacao) {
						chave_recuperacao = chaveRecuperacao();
					}
				}
			}
			f.setChave_recuperacao(chave_recuperacao);
			f.setId_funcionario(null);
			f.setLogin_funcionario(txt_Login.getText().toLowerCase());
			f.setSenha_funcionario(txt_Senha.getText());
			f.setPergunta(txt_pergunta.getText());
			f.setProjetos(new ArrayList<Projeto>());
			f.setPlano(p);
			f.setDados(null);
			Integer login = 0;
			for(Funcionario u : lista_funcionario) {
				if(u.getLogin_funcionario().equals(txt_Login.getText())) {
					login = 1;
				}
			}
			if(login == 0) {
				daoFunc.inserir(f);
				Map<String,Object> mapa = new HashMap<String,Object>();
				mapa.put("login_funcionario", txt_Login.getText());
				String where = " where t.login_funcionario = :login_funcionario";
				List<Funcionario> lista_func = daoFunc.listarWhere(f, where, mapa);
				Cadastrar_Dados c = new Cadastrar_Dados();
				try {
					for(Funcionario q : lista_func) {
						if(lista_func.isEmpty()) {
							a.alert("Contate o suporte");
						}
						else {
							Controller_Dados.setPassagemId(q.getId_funcionario());
						}
					}
					final Node source = (Node) btn_cadastrar.getSource();
					final Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
					c.start(new Stage());
					a.alertInformation("Usuário cadastrado com sucesso, insira seus dados na próxima etapa!");
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
			else {
				a.alert("Usuário já cadastrado!");
			}
		}
		else {
			a.alert("As senhas não coincidem!");
			txt_Senha.setText(null);
			txt_repetirsenha.setText(null);
		}
    }
}
