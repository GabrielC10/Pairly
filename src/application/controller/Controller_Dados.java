package application.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import application.classes.DadosFuncionario;
import application.classes.Funcionario;
import application.classes.Linguagem;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Inserir_Avatar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Dados implements Initializable {

	private static Integer passagemId;
	
    @FXML
    private Pane root;

    @FXML
    private JFXTextField txt_Nome;

    @FXML
    private JFXTextField txt_Sobrenome;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXTextField txt_Email;

    @FXML
    private JFXCheckBox ck_Programador;

    @FXML
    private JFXTextField txt_Telefone;

    @FXML
    private ImageView img_Avatar;

    @FXML
    private JFXButton btn_selecionar;

    @FXML
    private JFXButton btn_visualizar;    

    @FXML
    private JFXCheckBox ck_java;

    @FXML
    private JFXCheckBox ck_php;

    @FXML
    private JFXCheckBox ck_cs;

    @FXML
    private JFXCheckBox ck_c;

    
	public static Integer getPassagemId() {
		return passagemId;
	}

	public static void setPassagemId(Integer passagemId) {
		Controller_Dados.passagemId = passagemId;
	}
	
    @FXML
    void ativarCombobox(MouseEvent ck_Programador) {
    	if(this.ck_Programador.isSelected()) {
    		ck_java.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_php.setDisable(true);
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    		ck_php.setDisable(false);
    	}
    }

    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Funcionario f = new Funcionario();
    	Linguagem l = new Linguagem();
    	DadosFuncionario d = new DadosFuncionario();
    	Dao<Linguagem> daoLing = new DaoGeneric<Linguagem>();
    	Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    	Dao<DadosFuncionario> daoDados = new DaoGeneric<DadosFuncionario>();
    	List<Linguagem> lista_linguagem = daoLing.listar(l);
    	List<Funcionario> lista_funcionario = daoFunc.listar(f);
    	for(Funcionario x : lista_funcionario) {
    		if(x.getId_funcionario() == Controller_Dados.passagemId) {
    			f.setChave_recuperacao(x.getChave_recuperacao());
    			f.setId_funcionario(x.getId_funcionario());
    			f.setLogin_funcionario(x.getLogin_funcionario());
    			f.setPergunta(x.getPergunta());
    			f.setPlano(x.getPlano());
    			f.setProjetos(x.getProjetos());
    			f.setSenha_funcionario(x.getSenha_funcionario());
    		}
    	}
    	if(ck_Programador.isSelected()) {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(0).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(0).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(0).getNome_linguagem());
    		d.setLinguagem(linguagem);
    	}
    	else if(ck_java.isSelected()) {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(1).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(1).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(1).getNome_linguagem());
    		d.setLinguagem(linguagem);
    	}
    	else if(ck_php.isSelected()) {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(2).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(2).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(2).getNome_linguagem());
    		d.setLinguagem(linguagem);
    	}
    	else if(ck_cs.isSelected()) {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(3).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(3).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(3).getNome_linguagem());
    		d.setLinguagem(linguagem);
    	}
    	else if(ck_c.isSelected()) {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(4).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(4).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(4).getNome_linguagem());
    		d.setLinguagem(linguagem);
    	}
    	else {
    		Linguagem linguagem = new Linguagem();
    		linguagem.setCarga_horaria(lista_linguagem.get(0).getCarga_horaria());
    		linguagem.setId_linguagem(lista_linguagem.get(0).getId_linguagem());
    		linguagem.setNome_linguagem(lista_linguagem.get(0).getNome_linguagem());
    		d.setLinguagem(linguagem);	
    	}
    	d.setId_dado(null);
    	d.setCaminho_avatar(null);
    	d.setEmail(txt_Email.getText().toLowerCase());
    	d.setNome_funcionario(txt_Nome.getText());
    	d.setSobrenome_funcionario(txt_Sobrenome.getText());
    	d.setTelefone_principal(txt_Telefone.getText());
    	d.setCaminho_avatar(Controller_Avatar.getCaminho_original());
    	Alerta a = new Alerta();
    	Integer salvar = 0;
    	if(txt_Email.getText().equals(null) || txt_Nome.getText().equals(null) 
    			|| txt_Sobrenome.getText().equals(null) || txt_Telefone.getText().equals(null)) {
    		a.alertInformation("Preencha todos os campos!");
    		salvar = 1;
    	}
    	else if(salvar == 0) {
    		daoDados.inserir(d);
    		f.setDados(d);
    		daoFunc.alterar(f);
    		final Node source = (Node) btn_Salvar.getSource();
    		final Stage scene = (Stage) source.getScene().getWindow();
    		scene.close();
    		a.alertInformation("Seus dados foram salvos com sucesso!");
    	}
    	else {
    		a.alertInformation("Verifique com mais atenção às informações!");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_Nome.setStyle("-fx-text-inner-color:white");
		txt_Sobrenome.setStyle("-fx-text-inner-color:white");
		txt_Email.setStyle("-fx-text-inner-color:white");
		txt_Telefone.setStyle("-fx-text-inner-color:white");
		img_Avatar.setImage(new Image("file:///"+Controller_Avatar.getCaminho_original()));
		ck_java.setDisable(false);
		ck_php.setDisable(false);
		ck_cs.setDisable(false);
		ck_c.setDisable(false);
		ck_Programador.setDisable(false);
	}
    

    @FXML
    void selecionarImagem(MouseEvent btn_selecionar) {
    	Inserir_Avatar a = new Inserir_Avatar();
    	try {
			a.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void visualizar(MouseEvent btn_visualizar) {
    	img_Avatar.setImage(new Image("file:///"+Controller_Avatar.getCaminho_original()));
    }
    
    @FXML
    void ativarC(MouseEvent ck_c) {
    	if(this.ck_c.isSelected()) {
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_Programador.setDisable(true);
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }

    @FXML
    void ativarCS(MouseEvent ck_cs) {
    	if(this.ck_cs.isSelected()) {
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_c.setDisable(true);
    		ck_Programador.setDisable(true);
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_c.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }
    
    @FXML
    void ativarJava(MouseEvent ck_java) {
    	if(this.ck_java.isSelected()) {
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_Programador.setDisable(true);
    	}
    	else {
    		ck_php.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }

    @FXML
    void ativarPHP(MouseEvent ck_php) {
    	if(this.ck_php.isSelected()) {
    		ck_java.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_Programador.setDisable(true);
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }

}
