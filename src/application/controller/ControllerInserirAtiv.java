package application.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Funcionario;
import application.classes.Projeto;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerInserirAtiv implements Initializable {

    @FXML
    private JFXButton btnSair;

    @FXML
    private TableView<Funcionario> tbResponsavel;

    @FXML
    private TableColumn<Funcionario, String> clnResponsavel;

    @FXML
    private JFXButton btnSelecionar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXDatePicker dtCreate;

    @FXML
    private JFXCheckBox ckDesenv;

    @FXML
    private JFXDatePicker dtPrazo;

    @FXML
    private JFXCheckBox ckAlta;

    @FXML
    private JFXCheckBox ckMedia;

    @FXML
    private JFXCheckBox ckBaixa;

    @FXML
    private JFXCheckBox ckPronto;
    
    @FXML
    private JFXCheckBox ckAtrasado;
    
    @FXML
    private JFXCheckBox ckEspera;
    
    @FXML
    private JFXTextField txtDias;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    static Integer codigo_funcionario;
    
    static Integer identificador_status;
    
    static Integer identificador_prioridade;
    
    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void sair(MouseEvent btnSair) {
    	final Node source = (Node) btnSair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Alerta alerta = new Alerta();
    	if(codigo_funcionario == null) {
    		alerta.alertInformation("Selecione um usuário responsável.");
    	}
    	else if(txtNome.getText().equals(null) || txtDesc.getText().equals(null) 
    			||  dtCreate.getValue().toString().equals(null) 
    													|| dtPrazo.getValue().toString().equals(null)) {
    		alerta.alertInformation("Preencha todos os campos corretamente!");
    	}
    	else {
    		LocalDate data1;
    		LocalDate data2;
    		data1 = dtCreate.getValue();
    		data2 = dtPrazo.getValue();
    		if(data2.isBefore(data1)) {
    			alerta.alertInformation("A data do prazo não pode ser menor que a data de criação!");
    		}
    		else {
    			Dao<Fase> daoFase = new DaoGeneric<Fase>();
    			List<Fase> listaFase = daoFase.listar(new Fase());
    			Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    			Map<String,Object> argumentos = new HashMap<String,Object>();
    			Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    			argumentos.put("id_funcionario", codigo_funcionario);
    			String where = " where t.id_funcionario = :id_funcionario";
    			List<Funcionario> listaFunc = daoFunc.listarWhere(new Funcionario(), where, argumentos);
    			Map<String,Object> argumentos2 = new HashMap<String,Object>();
    			argumentos2.put("id_projeto", Controller_Dashboard.codigo_projeto);
    			String where2 = " where t.id_projeto = :id_projeto";
    			List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where2, argumentos2);
    			Atividade atividade = new Atividade();
        		Date dt1 = Date.valueOf(data1);
        		Date dt2 = Date.valueOf(data2);
        		atividade.setData_fim(dt2);
        		atividade.setData_inicio(dt1);
        		atividade.setDescricao(txtDesc.getText());
        		atividade.setFase(listaFase.get(1));
        		atividade.setFuncionario(listaFunc.get(0));
        		atividade.setNome_projeto(txtNome.getText());
        		if(identificador_prioridade == 0) {
        			atividade.setPrioridade('B');
        		}
        		else if(identificador_prioridade == 1) {
        			atividade.setPrioridade('M');
        		}
        		else if(identificador_prioridade == 2) {
        			atividade.setPrioridade('A');
        		}
        		else {
        			atividade.setPrioridade('B');
        		}
        		atividade.setProjeto(listaProjeto.get(0));
        		Projeto projeto = new Projeto();
        		projeto.setAtividades(listaProjeto.get(0).getAtividades());
        		projeto.setCor(listaProjeto.get(0).getCor());
        		projeto.setCusto(listaProjeto.get(0).getCusto());
        		projeto.setData_fim(listaProjeto.get(0).getData_fim());
        		projeto.setData_inicio(listaProjeto.get(0).getData_inicio());
        		projeto.setDescricao(listaProjeto.get(0).getDescricao());
        		projeto.setFase(listaProjeto.get(0).getFase());
        		projeto.setFuncionarios(listaProjeto.get(0).getFuncionarios());
        		projeto.setId_projeto(listaProjeto.get(0).getId_projeto());
        		projeto.setLinguagem(listaProjeto.get(0).getLinguagem());
        		projeto.setMeses(listaProjeto.get(0).getMeses());
        		projeto.setNome_projeto(listaProjeto.get(0).getNome_projeto());
        		projeto.setPrioridade(listaProjeto.get(0).getPrioridade());
        		projeto.setProduct_owner(listaProjeto.get(0).getProduct_owner());
        		projeto.setScrum_master(listaProjeto.get(0).getScrum_master());
        		projeto.setTimebox(listaProjeto.get(0).getTimebox());
        		projeto.setAtivo(listaProjeto.get(0).getAtivo());
        		if(identificador_status == 1) {
        			atividade.setStatus('P');
        		}
        		else if(identificador_status == 2) {
        			atividade.setStatus('A');
        		}
        		else if(identificador_status == 3) {
        			atividade.setStatus('E');
        		}
        		else {
        			atividade.setStatus('E');
        		}
        		atividade.setTotal_dias_fazendo(1);
        		try {
        			Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
        			projeto.getAtividades().add(atividade);
        			daoAtividade.inserir(atividade);
        			daoProjeto.alterar(projeto);
        			alerta.alertInformation("Atividade inserida com sucesso!");
        			final Node source = (Node) btn_Salvar.getSource();
        			final Stage stage = (Stage) source.getScene().getWindow();
        			stage.close();
        		}
        		catch(Exception e) {
        			alerta.alert("Erro 9000 - Impossível inserir a atividade.");
        		}
    		}
    	}
    	
    }

    @FXML
    void selecionar(MouseEvent btnSelecionar) {
    	try {
    		codigo_funcionario = tbResponsavel.getSelectionModel().getSelectedItem().getId_funcionario();
    		Alerta alerta = new Alerta();
    		alerta.alertInformation("Usuário selecionado com sucesso!");
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alertInformation("Selecione um usuário responsável.");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Funcionario> obsFuncionario = FXCollections.observableArrayList();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		listaFuncionario.addAll(listaProjeto.get(0).getFuncionarios());
		obsFuncionario.addAll(listaFuncionario);
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("login_funcionario"));
		tbResponsavel.setItems(obsFuncionario);
		txtNome.setStyle("-fx-text-inner-color: white");
		txtDesc.setStyle("-fx-text-inner-color: white");
		dtCreate.setStyle("-fx-text-inner-color: white");
		dtPrazo.setStyle("-fx-text-inner-color: white");
		txtDias.setStyle("-fx-text-inner-color: white");
		txtDias.setDisable(true);
		txtDias.setText("1");
	}
	
	@FXML
	void pronto(MouseEvent ckPronto) {
		if(this.ckPronto.isSelected()) {
    		ckAtrasado.setDisable(true);
    		ckEspera.setDisable(true);
    		ckAtrasado.setSelected(false);
    		ckEspera.setSelected(false);
    		identificador_status = 1;
    	}
    	else {
    		ckAtrasado.setDisable(false);
    		ckEspera.setDisable(false);
    	}
	}
	
	@FXML
	void atrasado(MouseEvent ckAtrasado) {
		if(this.ckAtrasado.isSelected()) {
    		ckPronto.setDisable(true);
    		ckEspera.setDisable(true);
    		ckPronto.setSelected(false);
    		ckEspera.setSelected(false);
    		identificador_status = 2;
    	}
    	else {
    		ckPronto.setDisable(false);
    		ckEspera.setDisable(false);
    	}
	}
	
	@FXML
	void espera(MouseEvent ckEspera) {
		if(this.ckEspera.isSelected()) {
    		ckAtrasado.setDisable(true);
    		ckPronto.setDisable(true);
    		ckAtrasado.setSelected(false);
    		ckPronto.setSelected(false);
    		identificador_status = 3;
    	}
    	else {
    		ckAtrasado.setDisable(false);
    		ckPronto.setDisable(false);
    	}
	}
	
	@FXML
    void alta(MouseEvent ckAlta) {
    	if(this.ckAlta.isSelected()) {
    		ckMedia.setDisable(true);
    		ckBaixa.setDisable(true);
    		ckMedia.setSelected(false);
    		ckBaixa.setSelected(false);
    		identificador_prioridade = 2;
    	}
    	else {
    		ckMedia.setDisable(false);
    		ckBaixa.setDisable(false);
    	}
    }
	
	@FXML
    void baixa(MouseEvent ckBaixa) {
    	if(this.ckBaixa.isSelected()) {
    		ckAlta.setDisable(true);
    		ckMedia.setDisable(true);
    		ckAlta.setSelected(false);
    		ckMedia.setSelected(false);
    		identificador_prioridade = 0;
    	}
    	else {
    		ckAlta.setDisable(false);
    		ckMedia.setDisable(false);
    	}
    }
	
	@FXML
    void media(MouseEvent ckMedia) {
    	if(this.ckMedia.isSelected()) {
    		ckAlta.setDisable(true);
    		ckBaixa.setDisable(true);
    		ckAlta.setSelected(false);
    		ckBaixa.setSelected(false);
    		identificador_prioridade = 1;
    	}
    	else {
    		ckAlta.setDisable(false);
    		ckBaixa.setDisable(false);
    	}
    }
	
}
