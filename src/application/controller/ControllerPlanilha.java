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
import application.screen.AlterarAtividadePlanilha;
import application.screen.InformacoesAtivPlanilha;
import application.screen.Inserir_Atividade;
import application.screen.MoverPlanilha;
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

public class ControllerPlanilha implements Initializable {

    @FXML
    private TableView<Atividade> tbKanban;

    @FXML
    private TableView<Fase> tbFase;
    
    @FXML
    private TableColumn<Fase, String> clnFase1;
    
    @FXML
    private TableColumn<Atividade, Integer> clnId;

    @FXML
    private TableColumn<Atividade, String> clnNomeAtividade;

    @FXML
    private TableColumn<Atividade, String> clnFase;

    @FXML
    private TableColumn<Atividade, String> clnDtCriacao;

    @FXML
    private TableColumn<Atividade, String> clnPrazo;

    @FXML
    private JFXButton btnSair;

    @FXML
    private JFXButton btnMover;

    @FXML
    private JFXButton btnInform;

    @FXML
    private JFXButton btnInserirAtividade;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXSpinner btn_Refresh;

    static Integer codigo_atividade;
    
    @FXML
    void remover(MouseEvent btnRemover) {
    	try {
    		codigo_atividade = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();
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
		popularTableFase();
    }
    
    @FXML
    void informacoes(MouseEvent btnInform) {
    	InformacoesAtivPlanilha inf = new InformacoesAtivPlanilha();
    	codigo_atividade = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();
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
    	MoverPlanilha b = new MoverPlanilha();
    	codigo_atividade = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();
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
    	AlterarAtividadePlanilha a = new AlterarAtividadePlanilha();
    	codigo_atividade = tbKanban.getSelectionModel().getSelectedItem().getId_atividade();
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
		popularTableFase();
		tbFase.setDisable(true);
	}
	
	public void popularTable() {
		ObservableList<Atividade> obsAtvBacklog = FXCollections.observableArrayList();
		obsAtvBacklog.clear();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaProjeto.get(0).getAtividades());
		System.out.println(listaAtividade.size());
		for(Atividade x : listaAtividade) {
			obsAtvBacklog.add(x);
		}
		System.out.println(obsAtvBacklog.size());
		clnId.setCellValueFactory(new PropertyValueFactory<>("id_atividade"));
		clnNomeAtividade.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		clnFase.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		clnDtCriacao.setCellValueFactory(new PropertyValueFactory<>("data_inicio"));
		clnPrazo.setCellValueFactory(new PropertyValueFactory<>("data_fim"));
		tbKanban.setItems(obsAtvBacklog);
	}
	
	public void popularTableFase() {
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Dashboard.codigo_projeto);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
		List<Atividade> listaAtividade = new ArrayList<Atividade>();
		listaAtividade.addAll(listaProjeto.get(0).getAtividades());
		List<Fase> listaFase = new ArrayList<Fase>();
		for(Atividade x : listaAtividade) {
			listaFase.add(x.getFase());
		}
		ObservableList<Fase> obsFase = FXCollections.observableArrayList();
		obsFase.addAll(listaFase);
		clnFase1.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
		tbFase.setItems(obsFase);
	}
}