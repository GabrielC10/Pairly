package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import application.classes.Sprint;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Alterar_Sprint;
import application.screen.Inserir_Sprint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Sprints implements Initializable {

    @FXML
    private Pane root;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private TableView<Sprint> tbSprints;

    @FXML
    private TableColumn<Sprint, String> clnSprints;

    @FXML
    private TableColumn<Sprint, Integer> clnId;

    @FXML
    private JFXButton btn_Novo;

    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private JFXButton btn_VisualizarR;
    
    @FXML
    private JFXSpinner btn_Refresh;
    
    static Integer codigo_sprint;

    @FXML
    void visualizar(MouseEvent btn_VisualizarR) {
    	codigo_sprint = tbSprints.getSelectionModel().getSelectedItem().getId_sprint();
    	Alterar_Sprint t = new Alterar_Sprint();
    	try {
    		t.start(new Stage());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    
    @FXML
    void excluir(MouseEvent btn_Excluir) {
    	Alerta alerta1 = new Alerta();
    	Integer exclusao = tbSprints.getSelectionModel().getSelectedItem().getId_sprint();
    	try {
    		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_sprint", exclusao);
    		String where = " where t.id_sprint = :id_sprint";
    		List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
    		Sprint sprint = new Sprint();
    		sprint.setAtividade(listaSprint.get(0).getAtividade());
    		sprint.setDescricao_sprint(listaSprint.get(0).getDescricao_sprint());
    		sprint.setId_sprint(listaSprint.get(0).getId_sprint());
    		sprint.setNome_sprint(listaSprint.get(0).getNome_sprint());
    		sprint.setPrioridade(listaSprint.get(0).getPrioridade());
    		sprint.setProjeto(listaSprint.get(0).getProjeto());
    		sprint.setRelease(listaSprint.get(0).getRelease());
    		if(sprint.getRelease() == null) {
    			daoSprint.excluir(sprint);
    			popularTable();
    		}
    		else {
    			alerta1.alert("Impossível remover a sprint, a mesma já faz parte de uma Release.");
    		}
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Impossível remover essa sprint, verifique novamente.");
    		e.printStackTrace();
    	}
    }

    @FXML
    void novo(MouseEvent btn_Novo) {
    	Inserir_Sprint i = new Inserir_Sprint();
    	try {
    		i.start(new Stage());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void refreshProjetos(MouseEvent btn_Refresh) {
    	popularTable();
    }

    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    public void popularTable() {
    	ObservableList<Sprint> obsSprint = FXCollections.observableArrayList();
    	obsSprint.clear();
    	Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
    	List<Sprint> listaSprint = daoSprint.listar(new Sprint());
    	for(Sprint x : listaSprint) {
    		if(x.getProjeto().getId_projeto() == Controller_Dashboard.codigo_projeto) {
    			obsSprint.add(x);
    		}
    	}
    	clnSprints.setCellValueFactory(new PropertyValueFactory<>("nome_sprint"));
    	clnId.setCellValueFactory(new PropertyValueFactory<>("id_sprint"));
    	tbSprints.setItems(obsSprint);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popularTable();
	}

}
