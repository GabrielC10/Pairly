package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import application.classes.Funcionario;
import application.classes.Projeto;
import application.dao.Dao;
import application.dao.DaoGeneric;
import application.exception.Alerta;
import application.screen.Alterar_Projeto;
import application.screen.Novo_Projeto;
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

public class Controller_Projetos implements Initializable {
	
    @FXML
    private Pane root;

    @FXML
    private JFXButton btn_Sair;

    @FXML
    private TableView<Projeto> tb_Projetos;

    @FXML
    private TableColumn<Projeto, String> cl_Projetos;

    @FXML
    private TableView<Projeto> tbProjetosInativos;

    @FXML
    private TableColumn<Projeto, String> clnProjetosInativos;
    
    @FXML
    private TableColumn<?, ?> cl_Status;

    @FXML
    private JFXButton btn_Novo;

    @FXML
    private JFXButton btn_Alterar;

    @FXML
    private JFXButton btn_Excluir;

    @FXML
    private TableView<Funcionario> tb_Equipe;

    @FXML
    private TableColumn<Funcionario, String> cl_Usuario;

    @FXML
    private TableView<Funcionario> tbScrum;

    @FXML
    private TableColumn<Funcionario, String> clnScrum;
    
    @FXML
    private JFXButton btn_Inserir;

    @FXML
    private JFXButton btn_Remover;

    @FXML
    private JFXButton btn_GerenciarEquipe;

    @FXML
    private JFXTextField txt_Login;

    @FXML
    private JFXSpinner btn_RefreshEquipe;

    @FXML
    private JFXButton btn_Voltar;

    @FXML
    private JFXSpinner btn_Refresh;

    @FXML
    private JFXButton btnSelecionar;
    
    @FXML
    private JFXButton btnAtivar;
    
    Integer id_selecionado;
    
    private static Integer codigo_projeto = 0;
    
    public static Integer getCodigo_projeto() {
		return codigo_projeto;
	}
    
    @FXML
    void selecionar(MouseEvent btnSelecionar) {
    	Alerta alerta = new Alerta();
    	try {
    		Integer codigo = tb_Equipe.getSelectionModel().getSelectedItem().getId_funcionario();
    		Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    		Map<String,Object> argumentos2 = new HashMap<String,Object>();
    		argumentos2.put("id_funcionario", codigo);
    		String where2 = " where t.id_funcionario = :id_funcionario";
    		List<Funcionario> listaFunc = daoFunc.listarWhere(new Funcionario(), where2, argumentos2);
    		Funcionario func = new Funcionario();
    		func.setChave_recuperacao(listaFunc.get(0).getChave_recuperacao());
    		func.setDados(listaFunc.get(0).getDados());
    		func.setId_funcionario(listaFunc.get(0).getId_funcionario());
    		func.setLogin_funcionario(listaFunc.get(0).getLogin_funcionario());
    		func.setPergunta(listaFunc.get(0).getPergunta());
    		func.setPlano(listaFunc.get(0).getPlano());
    		func.setProjetos(listaFunc.get(0).getProjetos());
    		func.setSenha_funcionario(listaFunc.get(0).getSenha_funcionario());
    		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        	Projeto projeto = new Projeto();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", id_selecionado);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
    		projeto.setAtividades(lista_projetos.get(0).getAtividades());
    		projeto.setCor(lista_projetos.get(0).getCor());
    		projeto.setCusto(lista_projetos.get(0).getCusto());
    		projeto.setData_fim(lista_projetos.get(0).getData_fim());
    		projeto.setData_inicio(lista_projetos.get(0).getData_inicio());
    		projeto.setDescricao(lista_projetos.get(0).getDescricao());
    		projeto.setFase(lista_projetos.get(0).getFase());
    		projeto.setFuncionarios(lista_projetos.get(0).getFuncionarios());
    		projeto.setId_projeto(lista_projetos.get(0).getId_projeto());
    		projeto.setLinguagem(lista_projetos.get(0).getLinguagem());
    		projeto.setMeses(lista_projetos.get(0).getMeses());
    		projeto.setNome_projeto(lista_projetos.get(0).getNome_projeto());
    		projeto.setPrioridade(lista_projetos.get(0).getPrioridade());
    		projeto.setProduct_owner(lista_projetos.get(0).getProduct_owner());
    		projeto.setScrum_master(lista_projetos.get(0).getScrum_master());
    		projeto.setTimebox(lista_projetos.get(0).getTimebox());
    		projeto.setAtivo(lista_projetos.get(0).getAtivo());
    		projeto.setScrum_master(func);
    		daoProjeto.alterar(projeto);
    		alerta.alertInformation("Scrum Master adicionado ao Projeto!");
    		popularTableScrum();
    	}
    	catch(Exception e) {
    		alerta.alert("Selecione um membro da equipe para qualificá-lo como Scrum Master.");
    	}
    }
    
    public void popularTableScrum() {
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	argumentos.put("id_projeto", id_selecionado);
    	String where = " where t.id_projeto = :id_projeto";
    	List<Projeto> listaProjeto = daoProjeto.listarWhere(new Projeto(), where, argumentos);
    	ObservableList<Funcionario> obsFuncionario = FXCollections.observableArrayList();
    	obsFuncionario.clear();
    	obsFuncionario.addAll(listaProjeto.get(0).getScrum_master());
    	clnScrum.setCellValueFactory(new PropertyValueFactory<>("login_funcionario"));
    	tbScrum.setItems(obsFuncionario);
    }

	@FXML
    void alterar(MouseEvent btn_Alterar) {
    	try {
    		codigo_projeto = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
    		System.out.println(codigo_projeto);
    		Alterar_Projeto a = new Alterar_Projeto();
    		try {
    			a.start(new Stage());
    		}
    		catch(Exception e) {
    			Alerta al = new Alerta();
    			al.alert("Erro 9009 - Não catalogado na base de dados, favor contatar o suporte.");
    		}
    		
    	}
    	catch(Exception e) {
    		Alerta a = new Alerta();
    		a.alert("Selecione um projeto.");
    	}
    }

    @FXML
    void excluir(MouseEvent btn_Excluir) {
    	try {
    		Projeto p = new Projeto();
    		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    		Integer codigo = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
    		System.out.println("CÓDIGO:::::::"+codigo);
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", codigo);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> projeto = daoProjeto.listarWhere(p, where, argumentos);	
    		projeto.get(0).setAtivo('0');
    		daoProjeto.alterar(projeto.get(0));
    		popularTableProjetos();
    		popularTableProjetosInativos();
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
			alerta.alert("Erro inesperado: "+e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void ativar(MouseEvent btnAtivar) {
    	try {
    		Projeto p = new Projeto();
    		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    		Integer codigo = tbProjetosInativos.getSelectionModel().getSelectedItem().getId_projeto();
    		System.out.println("CÓDIGO:::::::"+codigo);
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", codigo);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> projeto = daoProjeto.listarWhere(p, where, argumentos);	
    		projeto.get(0).setAtivo('1');
    		daoProjeto.alterar(projeto.get(0));
    		popularTableProjetos();
    		popularTableProjetosInativos();
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
			alerta.alert("Selecione um projeto para ativar!");
    		e.printStackTrace();
    	}
    }

    @FXML
    void gerenciar_equipe(MouseEvent btn_GerenciarEquipe) {
    	try {
    		btnSelecionar.setDisable(false);
    		tb_Equipe.setDisable(false);
        	btn_Inserir.setDisable(false);
        	btn_Remover.setDisable(false);
        	txt_Login.setDisable(false);
        	tbScrum.setDisable(false);
        	id_selecionado = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
        	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        	Projeto projeto = new Projeto();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", id_selecionado);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
    		projeto.setAtividades(lista_projetos.get(0).getAtividades());
    		projeto.setCor(lista_projetos.get(0).getCor());
    		projeto.setCusto(lista_projetos.get(0).getCusto());
    		projeto.setData_fim(lista_projetos.get(0).getData_fim());
    		projeto.setData_inicio(lista_projetos.get(0).getData_inicio());
    		projeto.setDescricao(lista_projetos.get(0).getDescricao());
    		projeto.setFase(lista_projetos.get(0).getFase());
    		projeto.setFuncionarios(lista_projetos.get(0).getFuncionarios());
    		projeto.setId_projeto(lista_projetos.get(0).getId_projeto());
    		projeto.setLinguagem(lista_projetos.get(0).getLinguagem());
    		projeto.setMeses(lista_projetos.get(0).getMeses());
    		projeto.setNome_projeto(lista_projetos.get(0).getNome_projeto());
    		projeto.setPrioridade(lista_projetos.get(0).getPrioridade());
    		projeto.setProduct_owner(lista_projetos.get(0).getProduct_owner());
    		projeto.setScrum_master(lista_projetos.get(0).getScrum_master());
    		projeto.setTimebox(lista_projetos.get(0).getTimebox());
    		projeto.setAtivo(lista_projetos.get(0).getAtivo());
    		popularTableEquipe();
    		popularTableScrum();
    	}
    	catch(Exception e) {
    		Alerta a = new Alerta();
    		a.alertInformation("Verificar o campo 'Insira o login desejado'");
    	}
    }

    @FXML
    void inserir(MouseEvent btn_Inserir) {
    	Alerta alerta = new Alerta();
    	try {
    		id_selecionado = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
        	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        	Projeto projeto = new Projeto();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", id_selecionado);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
    		projeto.setAtividades(lista_projetos.get(0).getAtividades());
    		projeto.setCor(lista_projetos.get(0).getCor());
    		projeto.setCusto(lista_projetos.get(0).getCusto());
    		projeto.setData_fim(lista_projetos.get(0).getData_fim());
    		projeto.setData_inicio(lista_projetos.get(0).getData_inicio());
    		projeto.setDescricao(lista_projetos.get(0).getDescricao());
    		projeto.setFase(lista_projetos.get(0).getFase());
    		projeto.setFuncionarios(lista_projetos.get(0).getFuncionarios());
    		projeto.setId_projeto(lista_projetos.get(0).getId_projeto());
    		projeto.setLinguagem(lista_projetos.get(0).getLinguagem());
    		projeto.setMeses(lista_projetos.get(0).getMeses());
    		projeto.setNome_projeto(lista_projetos.get(0).getNome_projeto());
    		projeto.setPrioridade(lista_projetos.get(0).getPrioridade());
    		projeto.setProduct_owner(lista_projetos.get(0).getProduct_owner());
    		projeto.setScrum_master(lista_projetos.get(0).getScrum_master());
    		projeto.setTimebox(lista_projetos.get(0).getTimebox());
    		projeto.setAtivo(lista_projetos.get(0).getAtivo());
    		Dao<Funcionario> daoFuncionario = new DaoGeneric<Funcionario>();
    		List<Funcionario> listaFuncionario = daoFuncionario.listar(new Funcionario());
    		List<Funcionario> listaFuncProjeto = new ArrayList<Funcionario>();
    		Integer condicao = 0;
    		listaFuncProjeto.addAll(projeto.getFuncionarios());
    		for(Funcionario q : listaFuncProjeto) {
    			if(txt_Login.getText().equals(q.getLogin_funcionario())) {
    				alerta.alert("Login já cadastrado!");
    				condicao = 1;
    			}
    		}
    		if(condicao == 0) {
    			for(Funcionario x : listaFuncionario) {
    				if(txt_Login.getText().equals(x.getLogin_funcionario())) {
    					projeto.getFuncionarios().add(x);
    					daoProjeto.alterar(projeto);
    					Funcionario func = new Funcionario();
    					func.setChave_recuperacao(x.getChave_recuperacao());
    					func.setDados(x.getDados());
    					func.setId_funcionario(x.getId_funcionario());
    					func.setLogin_funcionario(x.getLogin_funcionario());
    					func.setPergunta(x.getPergunta());
    					func.setPlano(x.getPlano());
    					func.setProjetos(x.getProjetos());
    					func.getProjetos().add(projeto);
    					func.setSenha_funcionario(x.getSenha_funcionario());
    					daoFuncionario.alterar(func);
    					alerta.alertInformation("O usuário foi inserido no projeto!");
    					popularTableEquipe();
    				}
    			}
    		}	
    	}
    	catch(Exception e) {
    		alerta.alert("Erro 9009 - Não catalogado na base de dados Pairly, contate um suporte!");
    	}
    	
    }

    @FXML
    void novo(MouseEvent btn_Novo) {
    	Novo_Projeto n = new Novo_Projeto();
    	try {
			n.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void remover(MouseEvent btn_Remover) {
    	Alerta alerta = new Alerta();
    	try {
    		Integer id_selecionado = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
        	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        	Projeto projeto = new Projeto();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", id_selecionado);
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
    		projeto.setAtividades(lista_projetos.get(0).getAtividades());
    		projeto.setCor(lista_projetos.get(0).getCor());
    		projeto.setCusto(lista_projetos.get(0).getCusto());
    		projeto.setData_fim(lista_projetos.get(0).getData_fim());
    		projeto.setData_inicio(lista_projetos.get(0).getData_inicio());
    		projeto.setDescricao(lista_projetos.get(0).getDescricao());
    		projeto.setFase(lista_projetos.get(0).getFase());
    		projeto.setFuncionarios(lista_projetos.get(0).getFuncionarios());
    		projeto.setId_projeto(lista_projetos.get(0).getId_projeto());
    		projeto.setLinguagem(lista_projetos.get(0).getLinguagem());
    		projeto.setMeses(lista_projetos.get(0).getMeses());
    		projeto.setNome_projeto(lista_projetos.get(0).getNome_projeto());
    		projeto.setPrioridade(lista_projetos.get(0).getPrioridade());
    		projeto.setProduct_owner(lista_projetos.get(0).getProduct_owner());
    		projeto.setScrum_master(lista_projetos.get(0).getScrum_master());
    		projeto.setTimebox(lista_projetos.get(0).getTimebox());
    		projeto.setAtivo(lista_projetos.get(0).getAtivo());
    		Integer idFunc = tb_Equipe.getSelectionModel().getSelectedItem().getId_funcionario();
    		Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
    		Map<String,Object> argumentos2 = new HashMap<String,Object>();
    		argumentos2.put("id_funcionario", idFunc);
    		String where2 = " where t.id_funcionario = :id_funcionario";
    		List<Funcionario> listarFunc = daoFunc.listarWhere(new Funcionario(), where2, argumentos2);
    		Funcionario funcionario = new Funcionario();
    		funcionario.setChave_recuperacao(listarFunc.get(0).getChave_recuperacao());
    		funcionario.setDados(listarFunc.get(0).getDados());
    		funcionario.setId_funcionario(listarFunc.get(0).getId_funcionario());
    		funcionario.setLogin_funcionario(listarFunc.get(0).getLogin_funcionario());
    		funcionario.setPergunta(listarFunc.get(0).getPergunta());
    		funcionario.setPlano(listarFunc.get(0).getPlano());
    		funcionario.setProjetos(listarFunc.get(0).getProjetos());
    		funcionario.setSenha_funcionario(listarFunc.get(0).getSenha_funcionario());
    		List<Projeto> listaProjetos = new ArrayList<Projeto>();
    		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
    		listaProjetos.addAll(funcionario.getProjetos());
    		listaFuncionario.addAll(projeto.getFuncionarios());
    		Integer minha_confirmacao = 0;
    		for(int x = 0; x < listaProjetos.size(); x++) {
    			if(listaProjetos.get(x).getId_projeto() == id_selecionado) {
    				if(listaProjetos.get(x).getProduct_owner().getId_funcionario() == idFunc) {
    					alerta.alert("Impossível remover o manager do projeto!");
    					minha_confirmacao = 1;
    				}
    				else {
    					listaProjetos.remove(x);
    					funcionario.setProjetos(listaProjetos);
    					daoFunc.alterar(funcionario);
    					alerta.alertInformation("Usuário removido!");
    					popularTableEquipe();
    				}
    			}
    		}
    		for(int y = 0; y < listaFuncionario.size(); y++) {
    			if(listaFuncionario.get(y).getId_funcionario() == idFunc) {
    				if(minha_confirmacao != 1) {
    					listaFuncionario.remove(y);
    					projeto.setFuncionarios(listaFuncionario);
    					daoProjeto.alterar(projeto);
    				}
    			}
    		}	
    	}
    	catch(Exception e) {
    		alerta.alert("Erro 9009 - Erro não catalogado na base da Pairly, favor contatar o suporte!");
    	}
    }

    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void voltar(MouseEvent btn_Voltar) {
    	final Node source = (Node) btn_Voltar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		popularTableProjetos();
		popularTableProjetosInativos();
		btnSelecionar.setDisable(true);
		tb_Equipe.setDisable(true);
		btn_Inserir.setDisable(true);
		btn_Remover.setDisable(true);
		txt_Login.setDisable(true);
		tbScrum.setDisable(true);
	}
	
	public void popularTableProjetos() {
		try {
			Funcionario f = new Funcionario();
			Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
			Integer identificador = Controller_Login.getId_user();
			Map<String,Object> argumentos = new HashMap<String,Object>();
			argumentos.put("id_funcionario", identificador);
			String where = " where t.id_funcionario = :id_funcionario";
			List<Funcionario> lista_funcionario = daoFunc.listarWhere(f, where, argumentos);
			List<Projeto> lista_projetos = new ArrayList<Projeto>();
			for(Funcionario x : lista_funcionario) {
				lista_projetos.addAll(x.getProjetos());
			}
			ObservableList<Projeto> projetos = FXCollections.observableArrayList();
			projetos.clear();
			for(Projeto a : lista_projetos) {
				if(a.getAtivo() == '1') {
					projetos.add(a);
				}
			}
			System.out.println(projetos.size());
			cl_Projetos.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
			cl_Status.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
			tb_Projetos.setItems(projetos);
		}
		catch(Exception e) {
			Alerta alerta = new Alerta();
			alerta.alert("Erro inesperado: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void popularTableProjetosInativos() {
		Funcionario f = new Funcionario();
		Dao<Funcionario> daoFunc = new DaoGeneric<Funcionario>();
		Integer identificador = Controller_Login.getId_user();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_funcionario", identificador);
		String where = " where t.id_funcionario = :id_funcionario";
		List<Funcionario> lista_funcionario = daoFunc.listarWhere(f, where, argumentos);
		List<Projeto> lista_projetos = new ArrayList<Projeto>();
		for(Funcionario x : lista_funcionario) {
			lista_projetos.addAll(x.getProjetos());
		}
		ObservableList<Projeto> projetos = FXCollections.observableArrayList();
		projetos.clear();
		for(Projeto a : lista_projetos) {
			if(a.getAtivo() == '0') {
				projetos.add(a);
			}
		}
		clnProjetosInativos.setCellValueFactory(new PropertyValueFactory<>("nome_projeto"));
		tbProjetosInativos.setItems(projetos);
	}
	
	@FXML
    void refreshEquipe(MouseEvent btn_RefreshEquipe) {
		popularTableEquipe();
		popularTableScrum();
	}

    @FXML
    void refreshProjetos(MouseEvent btn_Refresh) {
    	popularTableProjetos();
    	popularTableProjetosInativos();
    }
    
    public void popularTableEquipe() {
    	Integer id_selecionado = tb_Projetos.getSelectionModel().getSelectedItem().getId_projeto();
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	Projeto projeto = new Projeto();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", id_selecionado);
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
		projeto.setFuncionarios(lista_projetos.get(0).getFuncionarios());
		ObservableList<Funcionario> obsFuncionarios = FXCollections.observableArrayList();
		obsFuncionarios.clear();
		obsFuncionarios.addAll(projeto.getFuncionarios());
		cl_Usuario.setCellValueFactory(new PropertyValueFactory<>("login_funcionario"));
		tb_Equipe.setItems(obsFuncionarios);	
    }

}
