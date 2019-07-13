package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import application.classes.Anexos;
import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Funcionario;
import application.classes.Projeto;
import application.classes.Tramitacao;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Inserir_Imagem;
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

public class ControllerInformAtivPlanilha implements Initializable {

    @FXML
    private JFXSpinner btn_Refresh;

    @FXML
    private JFXButton btnSair;
    
    @FXML
    private TableView<Tramitacao> tbTramitacao;
    
    @FXML
    private TableColumn<Tramitacao, String> clnTramitacao;

    @FXML
    private TableView<Fase> tbFase;
    
    @FXML
    private TableColumn<Fase, String> clnFase;
    
    @FXML
    private TableView<Funcionario> tbResponsavel;

    @FXML
    private TableColumn<Funcionario, String> clnResponsavel;

    @FXML
    private TableView<Anexos> tbAnexos;
    
    @FXML
    private TableColumn<Anexos, String> clnAnexos;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXDatePicker dtCreate;

    @FXML
    private JFXDatePicker dtPrazo;

    @FXML
    private JFXCheckBox ckAlta;

    @FXML
    private JFXCheckBox ckMedia;

    @FXML
    private JFXCheckBox ckBaixa;

    @FXML
    private JFXTextField txtDias;

    @FXML
    private JFXCheckBox ckPronto;

    @FXML
    private JFXCheckBox ckAtrasado;

    @FXML
    private JFXCheckBox ckEspera;

    @FXML
    private JFXButton btnInserir;
    
    @FXML
    private JFXButton btnRemover;
    
    @FXML
    private JFXButton btnVisualizar;
    
    @FXML
    void refreshProjetos(MouseEvent btn_Refresh) {
    
    }
    
    @FXML
    void alta(MouseEvent event) {

    }

    @FXML
    void atrasado(MouseEvent event) {

    }

    @FXML
    void baixa(MouseEvent event) {

    }

    @FXML
    void espera(MouseEvent event) {

    }

    @FXML
    void media(MouseEvent event) {

    }

    @FXML
    void pronto(MouseEvent event) {

    }

    @FXML
    void sair(MouseEvent btnSair) {
    	final Node source = (Node) btnSair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tbFase.setDisable(true);
		btnInserir.setDisable(true);
		btnRemover.setDisable(true);
		btnVisualizar.setDisable(true);
		ObservableList<Funcionario> obsFuncionario = FXCollections.observableArrayList();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
		Map<String,Object> argumentos2 = new HashMap<String,Object>();
		argumentos2.put("id_atividade", ControllerPlanilha.codigo_atividade);
		String where2 = " where t.id_atividade = :id_atividade";
		List<Atividade> listaAtividade = daoAtividade.listarWhere(new Atividade(), where2, argumentos2);
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		listaFuncionario.addAll(listaProjeto.get(0).getFuncionarios());
		obsFuncionario.addAll(listaAtividade.get(0).getFuncionario());
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("login_funcionario"));
		tbResponsavel.setItems(obsFuncionario);
		txtNome.setStyle("-fx-text-inner-color: white");
		txtDesc.setStyle("-fx-text-inner-color: white");
		dtCreate.setStyle("-fx-text-inner-color: white");
		dtPrazo.setStyle("-fx-text-inner-color: white");
		txtDias.setStyle("-fx-text-inner-color: white");
		txtDias.setDisable(true);
		txtDias.setText("1");
		txtNome.setDisable(true);
		txtDesc.setDisable(true);
		ckPronto.setDisable(true);
		ckAtrasado.setDisable(true);
		ckEspera.setDisable(true);
		dtCreate.setDisable(true);
		dtPrazo.setDisable(true);
		ckAlta.setDisable(true);
		ckMedia.setDisable(true);
		ckBaixa.setDisable(true);
		txtNome.setText(listaAtividade.get(0).getNome_projeto());
		txtDesc.setText(listaAtividade.get(0).getDescricao());
		if(listaAtividade.get(0).getStatus() == 'P') {
			ckPronto.setSelected(true);
			ckAtrasado.setDisable(true);
			ckEspera.setDisable(true);
		}
		else if(listaAtividade.get(0).getStatus() == 'A') {
			ckAtrasado.setSelected(true);
			ckPronto.setDisable(true);
			ckEspera.setDisable(true);
		}
		else if(listaAtividade.get(0).getStatus() == 'E') {
			ckEspera.setSelected(true);
			ckPronto.setDisable(true);
			ckAtrasado.setDisable(true);
		}
		Atividade ativ = new Atividade();
		ativ.setData_inicio(listaAtividade.get(0).getData_inicio());
		java.util.Date date = ativ.getData_inicio();
		LocalDate dataLocal = converterLocalDate(date);
		dtCreate.setValue(dataLocal);
		java.util.Date date2 = listaAtividade.get(0).getData_fim();
		LocalDate dataLocal2 = converterLocalDate(date2);
		dtPrazo.setValue(dataLocal2);
		if(listaAtividade.get(0).getPrioridade() == 'A') {
			ckAlta.setSelected(true);
			ckMedia.setDisable(true);
			ckBaixa.setDisable(true);
		}
		else if(listaAtividade.get(0).getPrioridade() == 'M') {
			ckMedia.setSelected(true);
			ckAlta.setDisable(true);
			ckBaixa.setDisable(true);
		}
		else if(listaAtividade.get(0).getPrioridade() == 'B') {
			ckBaixa.setSelected(true);
			ckAlta.setDisable(true);
			ckMedia.setDisable(true);
		}
		Calendar hoje = Calendar.getInstance();
		txtDias.setText(hoje.getTime().toGMTString());
		try {
			Dao<Tramitacao> daoTramitacao = new DaoGeneric<Tramitacao>();
			List<Tramitacao> listaTramitacao = daoTramitacao.listar(new Tramitacao());
			ObservableList<Tramitacao> obsTramitacao = FXCollections.observableArrayList();
			ObservableList<Fase> obsFase = FXCollections.observableArrayList();
			for(Tramitacao o : listaTramitacao) {
				if(o.getAtividade().getId_atividade() == ControllerPlanilha.codigo_atividade) {
					obsTramitacao.add(o);
					obsFase.add(o.getFase());
				}
			}
			clnTramitacao.setCellValueFactory(new PropertyValueFactory<>("tramitacao"));
			tbTramitacao.setItems(obsTramitacao);
			clnFase.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
			tbFase.setItems(obsFase);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LocalDate converterLocalDate(java.util.Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public void popularTableAnexos() {
		ObservableList<Anexos> obsAnexos = FXCollections.observableArrayList();
		obsAnexos.clear();
		Dao<Anexos> daoAnexos = new DaoGeneric<Anexos>();
		List<Anexos> listaAnexos = daoAnexos.listar(new Anexos());
		for(Anexos q : listaAnexos) {
			if(q.getAtividade().getId_atividade() == ControllerKanbanDesenv.codigo_atividade) {
				obsAnexos.add(q);
			}
		}
		clnAnexos.setCellValueFactory(new PropertyValueFactory<>("nome_anexo"));
		tbAnexos.setItems(obsAnexos);
	}
	
	@FXML
	void inserirAnexo(MouseEvent btnInserir) {
		try {
			Inserir_Imagem ins = new Inserir_Imagem();
			ins.start(new Stage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void remover(MouseEvent btnRemover) {
		Alerta alerta = new Alerta();
		try {
			Integer codigo = tbAnexos.getSelectionModel().getSelectedItem().getId_anexos();
			Dao<Anexos> daoAnexos = new DaoGeneric<Anexos>();
			Map<String,Object> argumentos = new HashMap<String,Object>();
			argumentos.put("id_anexos", codigo);
			String where = " where t.id_anexos = :id_anexos";
			List<Anexos> listaAnexos = daoAnexos.listarWhere(new Anexos(), where, argumentos);
			Anexos anexos = new Anexos();
			anexos.setAtividade(listaAnexos.get(0).getAtividade());
			anexos.setCaminho(listaAnexos.get(0).getCaminho());
			anexos.setDescricao_anexo(listaAnexos.get(0).getDescricao_anexo());
			anexos.setId_anexos(listaAnexos.get(0).getId_anexos());
			anexos.setNome_anexo(listaAnexos.get(0).getNome_anexo());
			daoAnexos.excluir(anexos);
			popularTableAnexos();
			alerta.alertInformation("Anexo removido com sucesso!");
		}
		catch(Exception e) {
			alerta.alert("Selecione um anexo para excluir!");
		}
	}

}
