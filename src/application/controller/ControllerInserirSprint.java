package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import application.classes.Atividade;
import application.classes.Projeto;
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

public class ControllerInserirSprint implements Initializable {

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

    static Integer identificador_prioridade;
    
    List<Atividade> listaAtividade1 = new ArrayList<Atividade>();
    
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
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
    	String where = " where t.id_projeto = :id_projeto";
    	List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
    	if(txtNome.getText().equals(null) || txtDesc.getText().equals(null)) {
    		alerta.alertInformation("Verifique os campos novamente, algo está nulo.");
    	}
    	else {
    		if(listaAtividade1.isEmpty()) {
    			alerta.alertInformation("Selecione alguma atividade para compor a Sprint!");
    		}
    		else {
    			sprint.setAtividade(listaAtividade1);
        		sprint.setDescricao_sprint(txtDesc.getText());
        		sprint.setNome_sprint(txtNome.getText());
        		if(identificador_prioridade == null) {
            		sprint.setPrioridade('B');
            	}
        		else if(identificador_prioridade == 1) {
        			sprint.setPrioridade('M');
        		}
        		else if(identificador_prioridade == 0) {
        			sprint.setPrioridade('B');
        		}
        		else if(identificador_prioridade == 2) {
        			sprint.setPrioridade('A');
        		}
        		sprint.setProjeto(listaProjeto.get(0));
        		try {
        			daoSprint.inserir(sprint);
        			alerta.alertInformation("Sprint inserida com sucesso!");
        			final Node source = (Node) btn_Salvar.getSource();
        			final Stage stage = (Stage) source.getScene().getWindow();
        			stage.close();
        		}
        		catch(Exception e) {
        			alerta.alert("Erro ao tentar inserir a sprint, verifique os campos novamente!");
        		}
    		}
    	}
    	
    }

    @FXML
    void selecionar(MouseEvent btnSelecionar) {
    	Alerta alerta = new Alerta();
    	Integer codigo = tbResponsavel.getSelectionModel().getSelectedItem().getId_atividade();
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaProjeto.get(0).getAtividades());
		Integer codigo_atividade = 0;
    	for(Atividade x : listaAtividade1) {
    		if(x.getId_atividade() == codigo) {
    			alerta.alert("Essa atividade já está inserida na sprint.");
    			codigo_atividade = 1;
    		}
    	}
    	if(codigo_atividade != 1) {
    		for(Atividade y : listaAtividade) {
    			if(y.getId_atividade() == codigo) {
    				listaAtividade1.add(y);
    				alerta.alertInformation("Atividade inserida com sucesso na sprint.");
    			}
    		}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popularTable();
		listaAtividade1.clear();
		txtNome.setStyle("-fx-text-inner-color:white");
		txtDesc.setStyle("-fx-text-inner-color:white");
	}
	
	public void popularTable() {
		ObservableList<Atividade> obsAtividade = FXCollections.observableArrayList();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaProjeto.get(0).getAtividades());
		for(Atividade x : listaAtividade) {
			if(x.getFase().getNome_fase().equals("Feito")) {
				obsAtividade.add(x);
			}
		}
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tbResponsavel.setItems(obsAtividade);
	}

}
