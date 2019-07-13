package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.classes.Release;
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

public class ControllerInserirRelease implements Initializable {

    @FXML
    private JFXButton btnSair;

    @FXML
    private TableView<Sprint> tbResponsavel;

    @FXML
    private TableColumn<Sprint, String> clnResponsavel;

    @FXML
    private JFXButton btnSelecionar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private JFXTextField dtCriacao;

    List<Sprint> listaSprintRelease = new ArrayList<Sprint>();
    
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
    	Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
    	Dao<Release> daoRelease = new DaoGeneric<Release>();
    	Release release = new Release();
    	if(txtNome.getText().equals(null) || txtDesc.getText().equals(null)) {
    		alerta.alertInformation("Os campos principais estão nulos!");
    	}
    	else {
    		if(listaSprintRelease.isEmpty()) {
    			alerta.alertInformation("Selecione uma Sprint para compor seu Release!");
    		}
    		else {
    			try {
        			release.setNome_release(txtNome.getText());
            		release.setDisponibilidade(Calendar.getInstance().getTime());
            		release.setDescricao_release(txtDesc.getText());
            		for(Sprint x : listaSprintRelease) {
                		x.setRelease(release);
                	}
            		daoRelease.inserir(release);
            		for(Sprint q : listaSprintRelease) {
            			daoSprint.alterar(q);
            		}
            		alerta.alertInformation("Release inserida com sucesso!");
            		final Node source = (Node) btn_Salvar.getSource();
            		final Stage stage = (Stage) source.getScene().getWindow();
            		stage.close();
        		}
        		catch(Exception e) {
        			alerta.alert("Erro 904 - Impossível inserir a Release.");
        		}	
    		}
    	}
    }

    @FXML
    void selecionar(MouseEvent btnSelecionar) {
    	Alerta alerta = new Alerta();
    	try {
    		Integer atencao = 0;
    		Integer codigo = tbResponsavel.getSelectionModel().getSelectedItem().getId_sprint();
        	for(Sprint q : listaSprintRelease) {
        		if(q.getId_sprint() == codigo) {
        			atencao = 1;
        		}
        	}
        	if(atencao != 0) {
        		alerta.alert("Release já inclusa.");
        	}
        	else {
        		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
            	Map<String,Object> argumentos = new HashMap<String,Object>();
            	argumentos.put("id_sprint", codigo);
            	String where = " where t.id_sprint = :id_sprint";
            	List<Sprint> listaSprint = daoSprint.listarWhere(new Sprint(), where, argumentos);
            	listaSprintRelease.add(listaSprint.get(0));
            	alerta.alertInformation("Sprint selecionada com sucesso!");
        	}
    	}
    	catch(Exception e) {
    		alerta.alertInformation("Selecione uma sprint para compor a Release!");
    	}
    }

	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtNome.setStyle("-fx-text-inner-color:white");
		txtDesc.setStyle("-fx-text-inner-color:white");
		dtCriacao.setStyle("-fx-text-inner-color:white");
		ObservableList<Sprint> obsSprint = FXCollections.observableArrayList();
		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		List<Sprint> listaSprint = daoSprint.listar(new Sprint());
		try {
			for(Sprint t : listaSprint) {
				if(t.getProjeto().getId_projeto() == Controller_Dashboard.codigo_projeto) {
					if(t.getRelease() == null) {
						obsSprint.add(t);
					}
				}
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("nome_sprint"));
		tbResponsavel.setItems(obsSprint);
		dtCriacao.setDisable(true);
		dtCriacao.setText(Calendar.getInstance().getTime().toGMTString());
	}

}
