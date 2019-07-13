package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXToggleButton;

import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Funcionario;
import application.classes.Projeto;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Dados_Usuario;
import application.screen.Kanban_Desenv;
import application.screen.Login;
import application.screen.Planilha;
import application.screen.Planos;
import application.screen.Projetos;
import application.screen.Release;
import application.screen.Sprints;
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

public class Controller_Dashboard implements Initializable {
	
	private static Integer indentificador;
	
	Timer timer = new Timer();
	
	@FXML
    private Pane root;

    @FXML
    private TableView<Projeto> tb_projetos;

    @FXML
    private TableColumn<Projeto, String> cln_desc;
    
    @FXML
    private TableView<Funcionario> tb_equipe;

    @FXML
    private TableColumn<Funcionario, String> cln_colab;

    @FXML
    private TableView<Atividade> tb_quantidade;

    @FXML
    private TableColumn<Atividade, Integer> cln_qtd;

    @FXML
    private TableView<Fase> tb_fases;

    @FXML
    private TableColumn<Fase, String> cln_fases;

    @FXML
    private JFXButton btn_sair;
    
    @FXML
    private JFXButton btn_projetos;
    
    @FXML
    private JFXButton btn_perfis;
    
    @FXML
    private JFXToggleButton tg_Deslogar;
    
    @FXML
    private JFXSpinner btn_RefreshEquipe;
    
    @FXML
    private JFXButton btn_GerenciarProjeto;
    
    @FXML
    private JFXButton btnKanban;
    
    @FXML
    private JFXButton btnPlanilha;
    
    @FXML
    private JFXButton btnSprint;
    
    @FXML
    private JFXButton btnRelease;
    
    @FXML
    private JFXButton btnPlano;
    
    static Integer codigo_projeto;
    
    @FXML
    void plano(MouseEvent btnPlano) {
    	try {
    		Planos plano = new Planos();
    		plano.start(new Stage());
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnKanban.setDisable(true);
		btnPlanilha.setDisable(true);
		btnSprint.setDisable(true);
		btnRelease.setDisable(true);
		task();
	}
	
	public void popularTable() {
		Integer id = Controller_Dashboard.getIndentificador();
		ObservableList<Projeto> lista = FXCollections.observableArrayList();
		@SuppressWarnings("unused")
		Projeto p = new Projeto();
		Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
		List<Funcionario> lista_projeto = daoFunc.listar(new Funcionario());
		List<Projeto> lista_funcionario = new ArrayList<Projeto>();
		for(Funcionario q : lista_projeto) {
			if(q.getId_funcionario() == id) {
				lista_funcionario.addAll(q.getProjetos());		
			}
		}
		for(Projeto a : lista_funcionario) {
			if(a.getAtivo() == '1') {
				lista.add(a);
			}
		}
		tb_projetos.getItems().clear();
		cln_desc.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tb_projetos.setItems(lista);
	}
	
	public static void passagemParametro(Integer id) {
		Controller_Dashboard.setIndentificador(id);
		System.out.println(id);
	}

	public static Integer getIndentificador() {
		return indentificador;
	}

	public static void setIndentificador(Integer indentificador) {
		Controller_Dashboard.indentificador = indentificador;
	}
	
	public void popularEquipe() {
		Integer id = Controller_Dashboard.getIndentificador();
		Dao<Funcionario> daoFuncionario = new DaoGeneric<Funcionario>();
		List<Funcionario> lista_funcionario = 
				daoFuncionario.listar(new Funcionario());
		List<Projeto> lista = new ArrayList<Projeto>();
		List<Funcionario> lista_func = new ArrayList<Funcionario>();
		ObservableList<Funcionario> equipe_lista = FXCollections.observableArrayList();
		for(Funcionario x : lista_funcionario) {
			if(x.getId_funcionario() == id) {
				lista.addAll(x.getProjetos());
			}
		}
		for(Projeto q : lista) {
			lista_func.addAll(q.getFuncionarios());
		}
		for(int x = 0; x < lista_func.size(); x++) {
			for(int y = 0; y < lista_func.size(); y++) {
				if(x != y) {
					if(lista_func.get(x).getId_funcionario()
							== lista_func.get(y).getId_funcionario()) {
						System.out.println("boa");
						lista_func.remove(y);
						y = 0;
					}	
				}
			}
		}
		for(Funcionario r : lista_func) {
			System.out.println(r.getLogin_funcionario());
			equipe_lista.add(r);
		}
		tb_equipe.getItems().clear();
		cln_colab.setCellValueFactory(new PropertyValueFactory<>("login_funcionario"));
		tb_equipe.setItems(equipe_lista);
	}
	
	@FXML
	void gerenciarProjeto(MouseEvent btn_GerenciarProjeto) {
		try {
			codigo_projeto = tb_projetos.getSelectionModel().getSelectedItem().getId_projeto();
			if(codigo_projeto != null) {
				Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
				Map<String,Object> argumentos = new HashMap<String,Object>();
				argumentos.put("id_projeto", codigo_projeto);
				String where = " where t.id_projeto = :id_projeto";
				List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
				if(listaProjeto.get(0).getTimebox() != 0) {
					btnKanban.setDisable(false);
					btnSprint.setDisable(false);
					btnRelease.setDisable(false);
					btnPlanilha.setDisable(true);
				}
				else {
					btnPlanilha.setDisable(false);
					btnSprint.setDisable(true);
					btnRelease.setDisable(true);
					btnKanban.setDisable(true);
				}
			}
		}
		catch(Exception e) {
			Alerta alerta = new Alerta();
			alerta.alertInformation("Selecione um projeto antes de continuar.");
		}
		
	}
	
	public void popularFase() {
		Integer id = Controller_Dashboard.getIndentificador();
		List<Projeto> lista = new ArrayList<Projeto>();
		Dao<Funcionario> daoFuncionario = new DaoGeneric<Funcionario>();
		List<Funcionario> lista_funcionario = daoFuncionario.listar(new Funcionario());
		List<Fase> lista_fase = new ArrayList<Fase>();
		ObservableList<Fase> fases = FXCollections.observableArrayList();
		for(Funcionario x : lista_funcionario) {
			if(x.getId_funcionario() == id) {
				lista.addAll(x.getProjetos());
				System.out.println(lista.size());
			}
		}
		for(Projeto q : lista) {
			lista_fase.addAll(q.getFase());
			System.out.println(lista_fase.size());
		}
		for(Fase f : lista_fase) {
			System.out.println(f.getNome_fase());
			System.out.println(lista_fase.size());
			fases.add(f);
		}
		tb_fases.getItems().clear();
		cln_fases.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
		tb_fases.setItems(fases);
	}
	
	public void popularQuantidade() {
		Integer id = Controller_Dashboard.getIndentificador();
		List<Projeto> lista = new ArrayList<Projeto>();
		Dao<Funcionario> daoFuncionario = new DaoGeneric<Funcionario>();
		List<Funcionario> lista_funcionario = daoFuncionario.listar(new Funcionario());
		List<Atividade> lista_atividade = new ArrayList<Atividade>();
		ObservableList<Atividade> ativ = FXCollections.observableArrayList();
		for(Funcionario x : lista_funcionario) {
			if(x.getId_funcionario() == id) {
				lista.addAll(x.getProjetos());
				System.out.println(lista.size());
			}
		}
		for(Projeto a : lista) {
			lista_atividade.addAll(a.getAtividades());
		}
		System.out.println(lista_atividade.size());
		for(Atividade p : lista_atividade) {
			if(p.getFase().getId_fase() != 7) {
				ativ.add(p);
			}
		}
		tb_quantidade.getItems().clear();
		cln_qtd.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tb_quantidade.setItems(ativ);
	}
	

    @FXML
    void sair(MouseEvent btn_sair) {
    	final Node source = (Node) btn_sair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    	timer.cancel();
    }
    
    @FXML
    void screenProjetos(MouseEvent btn_projetos) {
    	Projetos p = new Projetos();
    	try {
			p.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void screenPerfis(MouseEvent btn_perfis) {
    	Dados_Usuario p = new Dados_Usuario();
    	try {
			p.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void deslogar(MouseEvent tg_Deslogar) {
    	if(this.tg_Deslogar.isArmed()) {
    		
    	}
    	else {
    		final Node source = (Node) tg_Deslogar.getSource();
        	final Stage stage = (Stage) source.getScene().getWindow();
        	stage.close();
        	timer.cancel();
        	Login l = new Login();
        	try {
    			l.start(new Stage());
    		} catch (Exception e) {
    			e.printStackTrace();
    		}	
    	}
    }
    
    @FXML
    void refresh(MouseEvent btn_RefreshEquipe) {
    	popularTable();
    	popularFase();
		popularEquipe();
		popularQuantidade();
		btnKanban.setDisable(true);
		btnPlanilha.setDisable(true);
		btnSprint.setDisable(true);
		btnRelease.setDisable(true);
    }
    
    public void task() {
    	TimerTask task = new TimerTask() {
			@Override
			public void run() {
				popularTable();
				popularFase();
				popularEquipe();
				popularQuantidade();
				System.out.println("Entrando no método Task");
			}
    	};
    	timer.scheduleAtFixedRate(task, 0, 120*1000);
    }
    
    @FXML
    void abrirKanban(MouseEvent btnKanban) {
    	Kanban_Desenv k = new Kanban_Desenv();
    	try {
    		k.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");
    	}
    }
    
    @FXML
    void abrirPlanilha(MouseEvent btnPlanilha) {
    	try {
    		Planilha p = new Planilha();
    		p.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");	
    	}
    }
    
    @FXML
    void abrirSprint(MouseEvent btnSprint) {
    	try {
    		Sprints s = new Sprints();
    		s.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");	
    	}
    }
    
    @FXML
    void abrirRelease(MouseEvent btnRelease) {
    	try {
    		Release r = new Release();
    		r.start(new Stage());
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");		
    	}
    }
    
}
