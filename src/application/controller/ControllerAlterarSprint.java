package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import application.classes.Atividade;
import application.classes.Sprint;
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

public class ControllerAlterarSprint implements Initializable {

    @FXML
    private JFXButton btnSair;

    @FXML
    private TableView<Atividade> tbResponsavel;

    @FXML
    private TableColumn<Atividade, String> clnResponsavel;

    @FXML
    private JFXButton btnSelecionar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXCheckBox ckAlta;

    @FXML
    private JFXCheckBox ckMedia;

    @FXML
    private JFXCheckBox ckBaixa;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private JFXSpinner btn_Refresh;
    
    Integer identificador_prioridade;
    
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
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
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

    @FXML
    void sair(MouseEvent btnSair) {
    	final Node source = (Node) btnSair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Alerta alerta = new Alerta();
    	Sprint sprint = new Sprint();
    	Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_sprint", Controller_Sprints.codigo_sprint);
		String where = " where t.id_sprint = :id_sprint";
		List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
		if(txtNome.getText().equals(null) || txtDesc.getText().equals(null)) {
			alerta.alertInformation("Verifique os campos novamente!");
		}
		else {
			sprint.setAtividade(listaSprint.get(0).getAtividade());
			sprint.setDescricao_sprint(txtDesc.getText());
			sprint.setId_sprint(listaSprint.get(0).getId_sprint());
			sprint.setNome_sprint(txtNome.getText());
			if(identificador_prioridade == null) {
				sprint.setPrioridade(listaSprint.get(0).getPrioridade());
			}
			else if(identificador_prioridade == 0) {
				sprint.setPrioridade('B');
			}
			else if(identificador_prioridade == 1) {
				sprint.setPrioridade('M');
			}
			else if(identificador_prioridade == 2) {
				sprint.setPrioridade('A');
			}
			else {
				sprint.setPrioridade(listaSprint.get(0).getPrioridade());
			}
			sprint.setProjeto(listaSprint.get(0).getProjeto());
			sprint.setRelease(listaSprint.get(0).getRelease());
			try {
				daoSprint.alterar(sprint);
				alerta.alertInformation("Sprint alterada com sucesso!");
				final Node source = (Node) btn_Salvar.getSource();
				final Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
			catch(Exception e) {
				alerta.alert("Impossível alterar a sprint!");
			}
		}
    }

    @FXML
    void selecionar(MouseEvent btn_Selecionar) {
    	Alerta alerta = new Alerta();
    	try {
    		
    	}
    	catch(Exception e) {
    		alerta.alert("Impossível remover a atividade.");
    	}
    	Integer codigo = tbResponsavel.getSelectionModel().getSelectedItem().getId_atividade();
    	Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_sprint", Controller_Sprints.codigo_sprint);
		String where = " where t.id_sprint = :id_sprint";
		List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaSprint.get(0).getAtividade());
		if(listaAtividade.size() <= 1) {
			alerta.alertInformation("A sprint deve conter ao menos uma atividade.");
		}
		else {
			for(int x = 0; x < listaAtividade.size(); x++) {
				if(listaAtividade.get(x).getId_atividade() == codigo) {
					listaAtividade.remove(x);
				}
			}
			listaSprint.get(0).setAtividade(listaAtividade);
			daoSprint.alterar(listaSprint.get(0));
			alerta.alertInformation("Atividade removida!");
			popularTable();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_Salvar.setDisable(true);
		btnSelecionar.setDisable(true);
		tbResponsavel.setDisable(true);
		txtNome.setDisable(true);
		txtDesc.setDisable(true);
		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_sprint", Controller_Sprints.codigo_sprint);
		String where = " where t.id_sprint = :id_sprint";
		List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
		txtNome.setStyle("-fx-text-inner-color:white");
		txtDesc.setStyle("-fx-text-inner-color:white");
		txtNome.setText(listaSprint.get(0).getNome_sprint());
		txtDesc.setText(listaSprint.get(0).getDescricao_sprint());
		if(listaSprint.get(0).getPrioridade() == 'A') {
			ckAlta.setSelected(true);
			ckMedia.setDisable(true);
			ckBaixa.setDisable(true);
		}
		else if(listaSprint.get(0).getPrioridade() == 'M') {
			ckMedia.setSelected(true);
			ckAlta.setDisable(true);
			ckBaixa.setDisable(true);
		}
		else if(listaSprint.get(0).getPrioridade() == 'B') {
			ckBaixa.setSelected(true);
			ckAlta.setDisable(true);
			ckMedia.setDisable(true);
		}
		ObservableList<Atividade> listaAtividades = FXCollections.observableArrayList();
		listaAtividades.addAll(listaSprint.get(0).getAtividade());
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tbResponsavel.setItems(listaAtividades);
	}
	
	@FXML
	void refreshProjetos(MouseEvent btn_Refresh) {
		popularTable();
	}
	
	public void popularTable() {
		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_sprint", Controller_Sprints.codigo_sprint);
		String where = " where t.id_sprint = :id_sprint";
		List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
		ObservableList<Atividade> listaAtividades = FXCollections.observableArrayList();
		listaAtividades.clear();
		listaAtividades.addAll(listaSprint.get(0).getAtividade());
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tbResponsavel.setItems(listaAtividades);		
	}

}
