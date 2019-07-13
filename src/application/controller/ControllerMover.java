package application.controller;

import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Projeto;
import application.classes.Tramitacao;
import application.dao.Dao;
import application.dao.DaoGeneric;
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

public class ControllerMover implements Initializable {

    @FXML
    private Pane root;

    @FXML
    private TableView<Fase> tbFase;

    @FXML
    private TableColumn<Fase, String> clnFase;

    @FXML
    private JFXButton btnMover;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    void cancelar(MouseEvent btnCancelar) {
    	final Node source = (Node) btnCancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void mover(MouseEvent btnMover) {
    	Integer codigo_fase = tbFase.getSelectionModel().getSelectedItem().getId_fase();
    	Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	Dao<Tramitacao> daoTramitar = new DaoGeneric<Tramitacao>();
    	Tramitacao tramitar = new Tramitacao();
    	argumentos.put("id_atividade", ControllerKanbanDesenv.codigo_atividade);
    	String where = " where t.id_atividade = :id_atividade";
    	List<Atividade> listaAtividade = daoAtividade.listarWhere(new Atividade(), where, argumentos);
    	Atividade atividade = new Atividade();
    	atividade.setData_fim(listaAtividade.get(0).getData_fim());
    	atividade.setData_inicio(listaAtividade.get(0).getData_inicio());
    	atividade.setDescricao(listaAtividade.get(0).getDescricao());
    	atividade.setFase(listaAtividade.get(0).getFase());
    	atividade.setFuncionario(listaAtividade.get(0).getFuncionario());
    	atividade.setId_atividade(listaAtividade.get(0).getId_atividade());
    	atividade.setNome_projeto(listaAtividade.get(0).getNome_projeto());
    	atividade.setPrioridade(listaAtividade.get(0).getPrioridade());
    	atividade.setProjeto(listaAtividade.get(0).getProjeto());
    	atividade.setStatus(listaAtividade.get(0).getStatus());
    	atividade.setTotal_dias_fazendo(listaAtividade.get(0).getTotal_dias_fazendo());
    	Dao<Fase> daoFase = new DaoGeneric<Fase>();
    	Map<String,Object> argumentos2 = new HashMap<String,Object>();
    	argumentos2.put("id_fase", codigo_fase);
    	String where2 = " where t.id_fase = :id_fase";
    	List<Fase> listaFase = daoFase.listarWhere(new Fase(), where2, argumentos2);
    	Fase fase = new Fase();
    	fase.setDescricao(listaFase.get(0).getDescricao());
    	fase.setId_fase(listaFase.get(0).getId_fase());
    	fase.setNome_fase(listaFase.get(0).getNome_fase());
    	atividade.setFase(fase);
    	daoAtividade.alterar(atividade);
    	tramitar.setAtividade(atividade);
    	tramitar.setFase(fase);
    	tramitar.setTramitacao(Calendar.getInstance().getTime());
    	daoTramitar.inserir(tramitar);
    	final Node source = (Node) btnMover.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		ObservableList<Fase> obsFase = FXCollections.observableArrayList();
		obsFase.addAll(listaProjeto.get(0).getFase());
		clnFase.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
		tbFase.setItems(obsFase);
	}

}
