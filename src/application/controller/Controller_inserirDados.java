package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class Controller_inserirDados implements Initializable {

	static String caminho_imagem;
	
	static Integer identificador;
	
    @FXML
    private Pane root;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private JFXTextField txt_Nome;

    @FXML
    private JFXTextField txt_Sobrenome;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private JFXTextField txt_Email;

    @FXML
    private JFXTextField txt_Telefone;

    @FXML
    private JFXButton btn_SelecionarImg;
    
    @FXML
    private JFXButton btn_Visualizar;
    
    @FXML
    private JFXCheckBox ck_Programador;

    @FXML
    private JFXCheckBox ck_java;

    @FXML
    private JFXCheckBox ck_php;

    @FXML
    private JFXCheckBox ck_cs;

    @FXML
    private JFXCheckBox ck_c;
    
    @FXML
    private ImageView img_Avatar;
    
    private Integer id = Controller_Dashboard.getIndentificador();
    
    public Integer getId() {
		return id;
	}

	@FXML
    void ativarC(MouseEvent ck_c) {
    	if(this.ck_c.isSelected()) {
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_Programador.setDisable(true);
    		ck_java.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_php.setSelected(false);
    		ck_Programador.setSelected(false);
    		identificador = 4;
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
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		ck_Programador.setSelected(false);
    		identificador = 3;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_c.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }

    @FXML
    void ativarCombobox(MouseEvent ck_Programador) {
    	if(this.ck_Programador.isSelected()) {
    		ck_java.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_php.setDisable(true);
    		ck_java.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_c.setSelected(false);
    		ck_php.setSelected(false);
    		identificador = 0;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    		ck_php.setDisable(false);
    	}
    }

    @FXML
    void ativarJava(MouseEvent ck_java) {
    	if(this.ck_java.isSelected()) {
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_Programador.setDisable(true);
    		identificador = 1;
    		ck_php.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_c.setSelected(false);
    		ck_Programador.setSelected(false);
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
    		ck_java.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_c.setSelected(false);
    		ck_Programador.setSelected(false);
    		identificador = 2;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    		ck_Programador.setDisable(false);
    	}
    }

    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Alerta alerta = new Alerta();
    	Funcionario f = new Funcionario();
    	DadosFuncionario dados = new DadosFuncionario();
    	Linguagem l = new Linguagem();
    	Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    	Dao<DadosFuncionario> daoDados = new DaoGeneric<DadosFuncionario>();
    	Dao<Linguagem> daoLing = new DaoGeneric<Linguagem>();
    	List<Linguagem> lista_ling = daoLing.listar(l);
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	argumentos.put("id_funcionario", getId());
    	String where = " where t.id_funcionario = :id_funcionario";
    	List<Funcionario> lista_func = daoFunc.listarWhere(f, where, argumentos);
    	for(Funcionario x : lista_func) {
    		if(x.getId_funcionario() == getId()) {
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
    	if(txt_Nome.getText().equals(null) || txt_Sobrenome.getText().equals(null) 
    			|| txt_Email.getText().equals(null) || txt_Telefone.getText().equals(null)) {
    		alerta.alertInformation("Preencha todos os campos corretamente!");
    	}
    	else if(txt_Nome.getText().length() < 1 || txt_Sobrenome.getText().length() < 1 
    			|| txt_Email.getText().length() < 5 || txt_Telefone.getText().length() < 8) {
    		alerta.alertInformation("Númereo de caracteres inválido!");
    	}
    	else {
    		dados.setCaminho_avatar(Controller_Avatar.getCaminho_original());
    		dados.setEmail(txt_Email.getText());
    		dados.setId_dado(f.getDados().getId_dado());
    		dados.setNome_funcionario(txt_Nome.getText());
    		dados.setSobrenome_funcionario(txt_Sobrenome.getText());
    		dados.setTelefone_principal(txt_Telefone.getText());
    		if(ck_Programador.isSelected()) {
    			l.setCarga_horaria(lista_ling.get(0).getCarga_horaria());
        		l.setId_linguagem(lista_ling.get(0).getId_linguagem());
        		l.setNome_linguagem(lista_ling.get(0).getNome_linguagem());
        		dados.setLinguagem(l);	
    		}
    		else if(ck_java.isSelected()) {
    			l.setCarga_horaria(lista_ling.get(1).getCarga_horaria());
        		l.setId_linguagem(lista_ling.get(1).getId_linguagem());
        		l.setNome_linguagem(lista_ling.get(1).getNome_linguagem());
        		dados.setLinguagem(l);
    		}
    		else if(ck_php.isSelected()) {
    			l.setCarga_horaria(lista_ling.get(2).getCarga_horaria());
        		l.setId_linguagem(lista_ling.get(2).getId_linguagem());
        		l.setNome_linguagem(lista_ling.get(2).getNome_linguagem());
        		dados.setLinguagem(l);
    		}
    		else if(ck_c.isSelected()) {
    			l.setCarga_horaria(lista_ling.get(3).getCarga_horaria());
        		l.setId_linguagem(lista_ling.get(3).getId_linguagem());
        		l.setNome_linguagem(lista_ling.get(3).getNome_linguagem());
        		dados.setLinguagem(l);
    		}
    		else if(ck_cs.isSelected()) {
    			l.setCarga_horaria(lista_ling.get(4).getCarga_horaria());
        		l.setId_linguagem(lista_ling.get(4).getId_linguagem());
        		l.setNome_linguagem(lista_ling.get(4).getNome_linguagem());
        		dados.setLinguagem(l);
    		}
    		daoDados.alterar(dados);
    		alerta.alertInformation("Dados atualizados com sucesso!");
    	}
    }

    @FXML
    void selecionarImg(MouseEvent btn_SelecionarImg) {
    	Inserir_Avatar i = new Inserir_Avatar();
    	try {
			i.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_Nome.setStyle("-fx-text-inner-color:white");
		txt_Sobrenome.setStyle("-fx-text-inner-color:white");
		txt_Email.setStyle("-fx-text-inner-color:white");
		txt_Telefone.setStyle("-fx-text-inner-color:white");
		ck_Programador.setDisable(true);
		ck_java.setDisable(true);
		ck_c.setDisable(true);
		ck_php.setDisable(true);
		ck_cs.setDisable(true);
		Alerta alerta = new Alerta();
		Funcionario f = new Funcionario();
		DadosFuncionario dados = new DadosFuncionario();
		Linguagem l = new Linguagem();
		Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_funcionario", getId());
		String where = " where t.id_funcionario = :id_funcionario";
		List<Funcionario> lista_func = daoFunc.listarWhere(f, where, argumentos);
		for(Funcionario x : lista_func) {
			if(x.getId_funcionario() == getId()) {
				dados.setCaminho_avatar(x.getDados().getCaminho_avatar());
				dados.setEmail(x.getDados().getEmail());
				dados.setId_dado(x.getDados().getId_dado());
				dados.setLinguagem(x.getDados().getLinguagem());
				dados.setNome_funcionario(x.getDados().getNome_funcionario());
				dados.setSobrenome_funcionario(x.getDados().getSobrenome_funcionario());
				dados.setTelefone_principal(x.getDados().getTelefone_principal());
			}
			else {
				alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor entrar em contato com o suporte!");
			}
			txt_Nome.setText(dados.getNome_funcionario());
			txt_Sobrenome.setText(dados.getSobrenome_funcionario());
			txt_Email.setText(dados.getEmail());
			txt_Telefone.setText(dados.getTelefone_principal());
			caminho_imagem = dados.getCaminho_avatar();
			img_Avatar.setImage(new Image("file:///"+caminho_imagem));
			l.setCarga_horaria(dados.getLinguagem().getCarga_horaria());
			l.setId_linguagem(dados.getLinguagem().getId_linguagem());
			l.setNome_linguagem(dados.getLinguagem().getNome_linguagem());
			System.out.println(l.getId_linguagem());
			if(l.getId_linguagem() == 1) {
				ck_Programador.setSelected(true);
			}
			else if(l.getId_linguagem() == 2) {
				ck_java.setSelected(true);
				ck_java.setDisable(false);
			}
			else if(l.getId_linguagem() == 3) {
				ck_php.setSelected(true);
				ck_php.setDisable(false);
			}
			else if(l.getId_linguagem() == 4) {
				ck_cs.setSelected(true);
				ck_cs.setDisable(false);
			}
			else if(l.getId_linguagem() == 5) {
				ck_c.setSelected(true);
				ck_c.setDisable(false);
			}
		}
	}
	
	@FXML
	void visualizar(MouseEvent btn_Visualizar) {
		img_Avatar.setImage(new Image("file:///"+Controller_Avatar.getCaminho_original()));
	}

}
