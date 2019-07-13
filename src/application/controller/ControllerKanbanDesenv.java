package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import application.classes.Anexos;
import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Projeto;
import application.classes.Sprint;
import application.classes.Tramitacao;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Alterar_Atividade;
import application.screen.Informacoes_Atividade;
import application.screen.Inserir_Atividade;
import application.screen.Mover;
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

public class ControllerKanbanDesenv implements Initializable {
	
	@FXML
	private JFXButton btnInserirAtividade;
	
	@FXML
	private JFXButton btnSair;
	
    @FXML
    private TableView<Atividade> tbKanban;

    @FXML
    private TableColumn<Atividade, String> clnBacklog;

    @FXML
    private TableView<Atividade> tbKanban2;

    @FXML
    private TableColumn<Atividade, String> clnAFazer;

    @FXML
    private TableView<Atividade> tbKanban3;

    @FXML
    private TableColumn<Atividade, String> clnFazendo;

    @FXML
    private TableView<Atividade> tbKanban4;

    @FXML
    private TableColumn<Atividade, String> clnFeito;

    @FXML
    private JFXButton btnAlterar;
    
    @FXML
    private JFXButton btnMover;
    
    @FXML
    private JFXButton btnInform;
    
    @FXML
    private JFXSpinner btn_Refresh;
    
    @FXML
    private JFXButton btnRemover;
    
    static Integer codigo_atividade;
    
    static Integer codigo_kanban1;
    
    static Integer codigo_kanban2;
    
    static Integer codigo_kanban3;
    
    static Integer codigo_kanban4;
    
    @FXML
    void remover(MouseEvent btnRemover) {
    	try {
    		codigo_kanban1 = null;
        	codigo_kanban2 = null;
        	codigo_kanban3 = null;
        	codigo_kanban4 = null;
        	try {
        		codigo_kanban1 = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();	
        	}
        	catch(Exception e) {
        		codigo_kanban1 = null;
        	}
        	try {
        		codigo_kanban2 = tbKanban2.getSelectionModel().getSelectedItem().getId_atividade();	
        	}
        	catch(Exception e) {
        		codigo_kanban2 = null;
        	}
        	try {
        		codigo_kanban3 = tbKanban3.getSelectionModel().getSelectedItem().getId_atividade();	
        	}
        	catch(Exception e) {
        		codigo_kanban3 = null;
        	}
        	try {
        		codigo_kanban4 = tbKanban4.getSelectionModel().getSelectedItem().getId_atividade();	
        	}
        	catch(Exception e) {
        		codigo_kanban4 = null;
        	}
        	
        	if(codigo_kanban1 != null) {
        		codigo_atividade = codigo_kanban1;
        	}
        	else if(codigo_kanban2 != null) {
        		codigo_atividade = codigo_kanban2;
        	}
        	else if(codigo_kanban3 != null) {
        		codigo_atividade = codigo_kanban3;
        	}
        	else if(codigo_kanban4 != null) {
        		codigo_atividade = codigo_kanban4;
        	}
        	Alerta alerta = new Alerta();
        	Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
        	Map<String,Object> argumentos = new HashMap<String,Object>();
        	argumentos.put("id_atividade", codigo_atividade);
        	String where = " where t.id_atividade = :id_atividade";
        	List<Atividade> listaAtividade = daoAtividade.listarWhere(new Atividade(), where, argumentos);
        	Atividade atividade = new Atividade();
        	Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
        	List<Sprint> listaSprint = daoSprint.listar(new Sprint());
        	Dao<Anexos> daoAnexos = new DaoGeneric<Anexos>();
        	List<Anexos> listaAnexos = daoAnexos.listar(new Anexos());
        	Dao<Tramitacao> daoTramitar = new DaoGeneric<Tramitacao>();
        	List<Tramitacao> listaTramitacao = daoTramitar.listar(new Tramitacao());
        	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        	Map<String,Object> argumentos2 = new HashMap<String,Object>();
        	argumentos2.put("id_projeto", Controller_Dashboard.codigo_projeto);
        	String where2 = " where t.id_projeto = :id_projeto";
        	List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where2, argumentos2);
        	List<Atividade> atividade2 = new ArrayList<Atividade>();
        	List<Atividade> atividade3 = new ArrayList<Atividade>();
        	Integer codigo1 = 0;
        	try {
        		System.out.println("testeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        		for(int x = 0; x < listaSprint.size(); x++) {
        			atividade3.addAll(listaSprint.get(x).getAtividade());
        		}
        		for(int c = 0; c < atividade3.size(); c++) {
        			if(atividade3.get(c).getId_atividade() == codigo_atividade) {
        				codigo1 = 1;
        			}
        		}
        		if(codigo1 == 1) {
        			alerta.alert("Essa atividade já pertence há uma sprint!");
        		}
        		else {
        			atividade2.addAll(listaProjeto.get(0).getAtividades());
                	for(int u = 0; u < atividade2.size(); u++) {
                		if(atividade2.get(u).getId_atividade() == codigo_atividade) {
                			atividade2.remove(u);
                		}
                	}
                	listaProjeto.get(0).setAtividades(atividade2);
                	daoProjeto.alterar(listaProjeto.get(0));
                	for(Tramitacao t : listaTramitacao) {
                		if(t.getAtividade().getId_atividade() == codigo_atividade) {
                			daoTramitar.excluir(t);
                		}
                	}
                	for(Anexos o : listaAnexos) {
                		if(o.getAtividade().getId_atividade() == codigo_atividade) {
                			daoAnexos.excluir(o);
                		}
                	}
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
                	atividade.setFuncionario(null);
                	atividade.setFase(null);
                	atividade.setProjeto(null);
                	daoAtividade.excluir(atividade);	
                	alerta.alertInformation("Atividade removida com sucesso!");
        		}
           	}
        	catch(Exception e) {
        		alerta.alert("A atividade já está vinculada há uma Sprint.");
        	}
    	}
    	catch(Exception e) {
    		Alerta al = new Alerta();
        	al.alertInformation("Selecione uma atividade para excluir.");	
    	}
    }
    
    @FXML
    void refresh(MouseEvent btn_Refresh) {
    	popularTable();
    }
    
    @FXML
    void informacoes(MouseEvent btnInform) {
    	codigo_kanban1 = null;
    	codigo_kanban2 = null;
    	codigo_kanban3 = null;
    	codigo_kanban4 = null;
    	Informacoes_Atividade inf = new Informacoes_Atividade();
    	try {
    		codigo_kanban1 = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban1 = null;
    	}
    	try {
    		codigo_kanban2 = tbKanban2.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban2 = null;
    	}
    	try {
    		codigo_kanban3 = tbKanban3.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban3 = null;
    	}
    	try {
    		codigo_kanban4 = tbKanban4.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban4 = null;
    	}
    	
    	if(codigo_kanban1 != null) {
    		codigo_atividade = codigo_kanban1;
    	}
    	else if(codigo_kanban2 != null) {
    		codigo_atividade = codigo_kanban2;
    	}
    	else if(codigo_kanban3 != null) {
    		codigo_atividade = codigo_kanban3;
    	}
    	else if(codigo_kanban4 != null) {
    		codigo_atividade = codigo_kanban4;
    	}
    	if(codigo_atividade != null) {
        	try {
				inf.start(new Stage());
				popularTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Alerta al = new Alerta();
        	al.alertInformation("Selecione uma atividade para visualizar.");
        }
    }
    
    @FXML
    void mover(MouseEvent btnMover) {
    	Mover b = new Mover();
    	codigo_kanban1 = null;
    	codigo_kanban2 = null;
    	codigo_kanban3 = null;
    	codigo_kanban4 = null;
    	try {
    		codigo_kanban1 = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban1 = null;
    	}
    	try {
    		codigo_kanban2 = tbKanban2.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban2 = null;
    	}
    	try {
    		codigo_kanban3 = tbKanban3.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban3 = null;
    	}
    	try {
    		codigo_kanban4 = tbKanban4.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban4 = null;
    	}
    	
    	if(codigo_kanban1 != null) {
    		codigo_atividade = codigo_kanban1;
    	}
    	else if(codigo_kanban2 != null) {
    		codigo_atividade = codigo_kanban2;
    	}
    	else if(codigo_kanban3 != null) {
    		codigo_atividade = codigo_kanban3;
    	}
    	else if(codigo_kanban4 != null) {
    		codigo_atividade = codigo_kanban4;
    	}
        if(codigo_atividade != null) {
        	try {
				b.start(new Stage());
				popularTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Alerta al = new Alerta();
        	al.alertInformation("Selecione uma atividade para mover.");
        }

    }
    
    @FXML
    void sair(MouseEvent btnSair) {
    	final Node source = (Node) btnSair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void inserirAtividade(MouseEvent btnInserirAtividade) {
    	try {
    		Inserir_Atividade a = new Inserir_Atividade();
    		a.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");
    	}
    }
    
    @FXML
    void alterarAtividade(MouseEvent btnAlterar) {
    	Alterar_Atividade a = new Alterar_Atividade();
    	codigo_kanban1 = null;
    	codigo_kanban2 = null;
    	codigo_kanban3 = null;
    	codigo_kanban4 = null;
    	try {
    		codigo_kanban1 = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban1 = null;
    	}
    	try {
    		codigo_kanban2 = tbKanban2.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban2 = null;
    	}
    	try {
    		codigo_kanban3 = tbKanban3.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban3 = null;
    	}
    	try {
    		codigo_kanban4 = tbKanban4.getSelectionModel().getSelectedItem().getId_atividade();	
    	}
    	catch(Exception e) {
    		codigo_kanban4 = null;
    	}
    	
    	if(codigo_kanban1 != null) {
    		codigo_atividade = codigo_kanban1;
    	}
    	else if(codigo_kanban2 != null) {
    		codigo_atividade = codigo_kanban2;
    	}
    	else if(codigo_kanban3 != null) {
    		codigo_atividade = codigo_kanban3;
    	}
    	else if(codigo_kanban4 != null) {
    		codigo_atividade = codigo_kanban4;
    	}
        if(codigo_atividade != null) {
        	try {
				a.start(new Stage());
				popularTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        else {
        	Alerta al = new Alerta();
        	al.alertInformation("Selecione uma atividade para alterar.");
       }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popularTable();
	}
	
	public void popularTable() {
		ObservableList<Atividade> obsAtvBacklog = FXCollections.observableArrayList();
		ObservableList<Atividade> obsAtvAFazer = FXCollections.observableArrayList();
		ObservableList<Atividade> obsAtvFazendo = FXCollections.observableArrayList();
		ObservableList<Atividade> obsAtvFeito = FXCollections.observableArrayList();
		obsAtvBacklog.clear();
		obsAtvAFazer.clear();
		obsAtvFazendo.clear();
		obsAtvFeito.clear();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Dao<Fase> daoFase = new DaoGeneric<Fase>();
		List<Fase> listaFase = daoFase.listar(new Fase());
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaProjeto.get(0).getAtividades());
		System.out.println(listaAtividade.size());
		for(Atividade x : listaAtividade) {
			if(x.getFase().getId_fase() == listaFase.get(1).getId_fase()) {
				obsAtvBacklog.add(x);
			}
			else if(x.getFase().getId_fase() == listaFase.get(2).getId_fase()) {
				obsAtvAFazer.add(x);
			}
			else if(x.getFase().getId_fase() == listaFase.get(3).getId_fase()) {
				obsAtvFazendo.add(x);
			}
			else if(x.getFase().getId_fase() == listaFase.get(4).getId_fase()) {
				obsAtvFeito.add(x);
			}
		}
		System.out.println(obsAtvBacklog.size());
		clnBacklog.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		clnAFazer.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		clnFazendo.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		clnFeito.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tbKanban.setItems(obsAtvBacklog);
		tbKanban2.setItems(obsAtvAFazer);
		tbKanban3.setItems(obsAtvFazendo);
		tbKanban4.setItems(obsAtvFeito);	
	}

}
