package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import application.classes.Release;
import application.classes.Sprint;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Alterar_Release;
import application.screen.Inserir_Release;
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

public class Controller_Release implements Initializable {

    @FXML
    private Pane root;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private TableView<Release> tbSprints;

    @FXML
    private TableColumn<Release, String> clnSprints;

    @FXML
    private TableColumn<Release, Integer> clnId;

    @FXML
    private JFXButton btn_Novo;

    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private JFXSpinner btn_Refresh;

    static Integer codigo_alteracao;
    
    static Release release;
    
    @FXML
    void visualizar(MouseEvent btn_VisualizarR) {
    	release = tbSprints.getSelectionModel().getSelectedItem();
    	codigo_alteracao = tbSprints.getSelectionModel().getSelectedItem().getId_release();
    	try {
    		Alterar_Release r = new Alterar_Release();
    		r.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Impossível remover essa sprint, verifique novamente.");
    		e.printStackTrace();	
    	}
    }
    
    @FXML
    void excluir(MouseEvent btn_Excluir) {
    	Alerta alerta = new Alerta();
    	try {
    		Integer codigo = null;
    		codigo = tbSprints.getSelectionModel().getSelectedItem().getId_release();
    		Dao<Release> daoRelease = new DaoGeneric<Release>();
    		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
    		List<Sprint> listaSprint = daoSprint.listar(new Sprint());
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_release", codigo);
    		String where = " where t.id_release = :id_release";
    		List<Release> listaRelease = daoRelease.listarWhere(new Release(), where, argumentos);
    		System.out.println("CÓDIGO: "+codigo);
    		Sprint sprint = new Sprint();
    		for(int x = 0; x < listaSprint.size(); x++) {
    			try {
    				if(listaSprint.get(x).getRelease().getId_release() == codigo) {
        				sprint.setAtividade(listaSprint.get(x).getAtividade());
        				sprint.setDescricao_sprint(listaSprint.get(x).getDescricao_sprint());
        				sprint.setId_sprint(listaSprint.get(x).getId_sprint());
        				sprint.setNome_sprint(listaSprint.get(x).getNome_sprint());
        				sprint.setPrioridade(listaSprint.get(x).getPrioridade());
        				sprint.setProjeto(listaSprint.get(x).getProjeto());
        				sprint.setRelease(null);
        				daoSprint.alterar(sprint);
        				popularTable();
        			}	
    			}
    			catch(Exception e) {
    				
    			}
    		}
    		daoRelease.excluir(listaRelease.get(0));
    		alerta.alertInformation("Release removida com sucesso!");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		alerta.alert("Impossível remover a release!");
    	}
    }

    @FXML
    void novo(MouseEvent btn_Novo) {
    	try {
    		Inserir_Release i = new Inserir_Release();
    		i.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");		
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popularTable();
	}
	
	public void popularTable() {
		ObservableList<Release> obsRelease = FXCollections.observableArrayList();
		obsRelease.clear();
		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		List<Sprint> listaSprint = daoSprint.listar(new Sprint());
		for(Sprint q : listaSprint) {
			if(q.getRelease() != null) {
				obsRelease.add(q.getRelease());
			}
		}
		for(int x = 0; x < obsRelease.size(); x++) {
			for(int y = 0; y < obsRelease.size(); y++) {
				if(x != y) {
					if(obsRelease.get(x).getId_release()
							== obsRelease.get(y).getId_release()) {
						System.out.println("boa");
						obsRelease.remove(y);
						y = 0;
					}	
				}
			}
		}
		System.out.println(obsRelease.size());
		clnId.setCellValueFactory(new PropertyValueFactory<>("id_release"));
		clnSprints.setCellValueFactory(new PropertyValueFactory<>("nome_release"));
		tbSprints.setItems(obsRelease);
	}

}
