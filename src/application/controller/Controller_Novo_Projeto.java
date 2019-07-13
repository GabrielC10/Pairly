package application.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.classes.Atividade;
import application.classes.Fase;
import application.classes.Funcionario;
import application.classes.Linguagem;
import application.classes.Projeto;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller_Novo_Projeto implements Initializable{

    @FXML
    private Pane root;

    @FXML
    private JFXButton btn_Sair;
    
    @FXML
    private JFXTextField txt_CustoPF;

    @FXML
    private TableView<Fase> tb_Fases;

    @FXML
    private TableColumn<Fase, String> cl_Fases;

    @FXML
    private JFXButton btn_Inserir;

    @FXML
    private JFXTextField txt_Nome;

    @FXML
    private JFXTextField txt_Descricao;

    @FXML
    private JFXTextField txt_CadastrarFase;

    @FXML
    private JFXDatePicker dt_Inicio;

    @FXML
    private JFXCheckBox ck_Agil;

    @FXML
    private JFXCheckBox ck_Pessoal;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private JFXTextField txt_IdProjeto;

    @FXML
    private JFXDatePicker dt_Fim;

    @FXML
    private JFXCheckBox ck_Alta;

    @FXML
    private JFXCheckBox ck_Medio;

    @FXML
    private JFXCheckBox ck_Baixa;

    @FXML
    private JFXColorPicker txt_Color;

    @FXML
    private JFXTextField txt_Custo;

    @FXML
    private JFXTextField txt_Meses;

    @FXML
    private JFXTextField txt_Timebox;

    @FXML
    private JFXTextField txt_NDesenvolvedores;

    @FXML
    private JFXButton btn_Verificar;

    @FXML
    private JFXCheckBox ck_java;

    @FXML
    private JFXCheckBox ck_php;

    @FXML
    private JFXCheckBox ck_cs;

    @FXML
    private JFXCheckBox ck_c;

    static Integer identificador = 0;
    
    static Integer identificador_prioridade = 0;
    
    static Integer identificador_template = 0;
    
    Integer id_projeto_novo;
    
    Integer verificacao_fase = 0;
    
    @FXML
    void alta(MouseEvent ck_Alta) {
    	if(this.ck_Alta.isSelected()) {
    		ck_Medio.setDisable(true);
    		ck_Baixa.setDisable(true);
    		ck_Medio.setSelected(false);
    		ck_Baixa.setSelected(false);
    		identificador_prioridade = 2;
    	}
    	else {
    		ck_Medio.setDisable(false);
    		ck_Baixa.setDisable(false);
    	}
    }

    @FXML
    void ativarC(MouseEvent ck_c) {
    	if(this.ck_c.isSelected()) {
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_java.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_php.setSelected(false);
    		identificador = 4;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_cs.setDisable(false);
    	}
    }

    @FXML
    void ativarCS(MouseEvent ck_cs) {
    	if(this.ck_cs.isSelected()) {
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_c.setDisable(true);
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		identificador = 3;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_c.setDisable(false);
    	}
    }

    @FXML
    void ativarJava(MouseEvent ck_java) {
    	if(this.ck_java.isSelected()) {
    		ck_php.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		identificador = 1;
    		ck_php.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_c.setSelected(false);
    	}
    	else {
    		ck_php.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    	}
    }

    @FXML
    void ativarPHP(MouseEvent ck_php) {
    	if(this.ck_php.isSelected()) {
    		ck_java.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_c.setDisable(true);
    		ck_java.setSelected(false);
    		ck_cs.setSelected(false);
    		ck_c.setSelected(false);
    		identificador = 2;
    	}
    	else {
    		ck_java.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_c.setDisable(false);
    	}
    }

    @FXML
    void baixa(MouseEvent ck_Baixa) {
    	if(this.ck_Baixa.isSelected()) {
    		ck_Alta.setDisable(true);
    		ck_Medio.setDisable(true);
    		ck_Alta.setSelected(false);
    		ck_Medio.setSelected(false);
    		identificador_prioridade = 0;
    	}
    	else {
    		ck_Alta.setDisable(false);
    		ck_Medio.setDisable(false);
    	}
    }

    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void desenvAgil(MouseEvent ck_Agil) {
    	if(this.ck_Agil.isSelected()) {
    		identificador_template = 1;
    		ck_Pessoal.setDisable(true);
    		ck_Pessoal.setSelected(false);
    		txt_Custo.setEditable(false);
    		txt_Custo.setDisable(false);
    		txt_CustoPF.setDisable(false);
    		txt_CustoPF.setEditable(true);
    		txt_Timebox.setEditable(false);
    		txt_Timebox.setDisable(true);
    		txt_Meses.setDisable(false);
    		txt_Meses.setEditable(true);
    		txt_NDesenvolvedores.setEditable(true);
    		txt_NDesenvolvedores.setDisable(false);
    		ck_java.setDisable(false);
    		ck_php.setDisable(false);
    		ck_c.setDisable(false);
    		ck_cs.setDisable(false);
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		ck_cs.setSelected(false);
    		btn_Verificar.setDisable(false);
    	}
    	else {
    		ck_Pessoal.setDisable(false);
    		txt_Custo.setEditable(false);
    		txt_Custo.setDisable(true);
    		txt_Timebox.setEditable(false);
    		txt_Timebox.setDisable(true);
    		txt_Meses.setDisable(true);
    		txt_Meses.setEditable(false);
    		txt_NDesenvolvedores.setEditable(false);
    		txt_NDesenvolvedores.setDisable(true);
    		txt_CustoPF.setEditable(false);
    		txt_CustoPF.setDisable(true);
    		ck_java.setDisable(true);
    		ck_php.setDisable(true);
    		ck_c.setDisable(true);
    		ck_cs.setDisable(true);
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		ck_cs.setSelected(false);
    		btn_Verificar.setDisable(true);
    		txt_Custo.setText("");
    		txt_Timebox.setText("");
    		txt_CustoPF.setText("");
    		txt_NDesenvolvedores.setText("");
    		txt_Meses.setText("");
    		txt_CustoPF.setText("");
    	}
    }

    @FXML
    void media(MouseEvent ck_Medio) {
    	if(this.ck_Medio.isSelected()) {
    		ck_Alta.setDisable(true);
    		ck_Baixa.setDisable(true);
    		ck_Alta.setSelected(false);
    		ck_Baixa.setSelected(false);
    		identificador_prioridade = 1;
    	}
    	else {
    		ck_Alta.setDisable(false);
    		ck_Baixa.setDisable(false);
    	}
    }

    @FXML
    void pessoal(MouseEvent ck_Pessoal) {
    	if(this.ck_Pessoal.isSelected()) {
    		identificador_template = 2;
    		ck_Agil.setDisable(true);
    		ck_Agil.setSelected(false);
    		txt_Custo.setEditable(false);
    		txt_Custo.setDisable(true);
    		txt_Timebox.setEditable(false);
    		txt_Timebox.setDisable(true);
    		txt_Meses.setDisable(true);
    		txt_Meses.setEditable(false);
    		txt_NDesenvolvedores.setEditable(false);
    		txt_NDesenvolvedores.setDisable(true);
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		ck_cs.setSelected(false);
    	}
    	else {
    		ck_Agil.setDisable(false);
    		ck_java.setSelected(false);
    		ck_php.setSelected(false);
    		ck_c.setSelected(false);
    		ck_cs.setSelected(false);
    	}
    }
    
    @FXML
    void sair(MouseEvent btn_Sair) {
    	final Node source = (Node) btn_Sair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
    public Integer sortearId() {
    	Projeto p = new Projeto();
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	List<Projeto> lista_Projeto = daoProjeto.listar(p);
    	Integer id = lista_Projeto.size()+1;
    	return id;
    }
    
    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Funcionario f = new Funcionario();
    	Projeto p = new Projeto();
    	Linguagem lingua = new Linguagem();
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	Dao<Funcionario> daoFuncionario = new DaoGeneric<Funcionario>();
    	Dao<Linguagem> daoLing = new DaoGeneric<Linguagem>();
    	List<Linguagem> linguagens = daoLing.listar(lingua);
    	List<Fase> minhas_fases = lista_fase();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	argumentos.put("id_funcionario", Controller_Dashboard.getIndentificador());
    	String where = " where t.id_funcionario = :id_funcionario";
    	List<Funcionario> lista_funcionario = daoFuncionario.listarWhere(f, where, argumentos);
    	for(Funcionario x : lista_funcionario) {
    		if(x.getId_funcionario() == Controller_Dashboard.getIndentificador()) {
    			f.setChave_recuperacao(x.getChave_recuperacao());
    			f.setDados(x.getDados());
    			f.setId_funcionario(x.getId_funcionario());
    			f.setLogin_funcionario(x.getLogin_funcionario());
    			f.setPergunta(x.getPergunta());
    			f.setPlano(x.getPlano());
    			f.setProjetos(x.getProjetos());
    			f.setSenha_funcionario(x.getSenha_funcionario());
    		}
    	}
    	try {
    		p.setAtividades(new ArrayList<Atividade>());
    		p.setCor(txt_Color.getValue().toString());
    		Date l = Date.valueOf(dt_Fim.getValue());
    		System.out.println("DATA: "+l);
    		p.setData_fim(l);
    		Date l1 = Date.valueOf(dt_Inicio.getValue());
    		System.out.println("DATA: "+l1);
    		p.setData_inicio(l1);
    		p.setDescricao(txt_Descricao.getText());
    		Fase fases = new Fase();
    		System.out.println("TAMANHO LISTA: "+minhas_fases.size());
    		fases.setDescricao(minhas_fases.get(0).getDescricao());
    		fases.setId_fase(minhas_fases.get(0).getId_fase());
    		fases.setNome_fase(minhas_fases.get(0).getNome_fase());
    		p.setFase(new ArrayList<Fase>());
    		p.setFuncionarios(new ArrayList<Funcionario>());
    		p.setAtivo('1');
    		if(identificador == 1) {
    			lingua.setCarga_horaria(linguagens.get(1).getCarga_horaria());
    			lingua.setId_linguagem(linguagens.get(1).getId_linguagem());
    			lingua.setNome_linguagem(linguagens.get(1).getNome_linguagem());
    		}
    		else if(identificador == 2) {
    			lingua.setCarga_horaria(linguagens.get(2).getCarga_horaria());
    			lingua.setId_linguagem(linguagens.get(2).getId_linguagem());
    			lingua.setNome_linguagem(linguagens.get(2).getNome_linguagem());
    		}
    		else if(identificador == 3) {
    			lingua.setCarga_horaria(linguagens.get(3).getCarga_horaria());
    			lingua.setId_linguagem(linguagens.get(3).getId_linguagem());
    			lingua.setNome_linguagem(linguagens.get(3).getNome_linguagem());
    		}
    		else if(identificador == 4) {
    			lingua.setCarga_horaria(linguagens.get(4).getCarga_horaria());
    			lingua.setId_linguagem(linguagens.get(4).getId_linguagem());
    			lingua.setNome_linguagem(linguagens.get(4).getNome_linguagem());
    		}
    		else {
    			lingua.setCarga_horaria(linguagens.get(0).getCarga_horaria());
    			lingua.setId_linguagem(linguagens.get(0).getId_linguagem());
    			lingua.setNome_linguagem(linguagens.get(0).getNome_linguagem());	
    		}
    		p.setLinguagem(lingua);
    		p.setNome_projeto(txt_Nome.getText());
    		if(identificador_prioridade == 0) {
    			p.setPrioridade('B');
    		}
    		else if(identificador_prioridade == 1) {
    			p.setPrioridade('M');
    		}
    		else if(identificador_prioridade == 2) {
    			p.setPrioridade('A');
    		}
    		else {
    			p.setPrioridade('B');
    		}
    		LocalDate data1;
    		LocalDate data2;
    		data1 = dt_Inicio.getValue();
    		data2 = dt_Fim.getValue();
    		if(data2.isBefore(data1)) {
    			Alerta alerta = new Alerta();
    			alerta.alertInformation("A data fim não pode ser anterior com a data de início!");
    		}
    		else {
    			p.setProduct_owner(null);
        		p.setScrum_master(null);
        		if(identificador_template == 2) {
        			p.setMeses(0);
        			p.setCusto(0.00);
        			p.setTimebox(0);
        			verificacao_fase = 0;
        			btn_Inserir.setDisable(false);
        		}
        		else if(identificador_template == 1) {
        			Alerta alerta = new Alerta();
        			if(txt_Custo.getText().equals(null)) {
        				alerta.alertInformation("Por favor informe use o botão verificar para finalizar a operação!");
        			}
        			else {
        				p.setMeses(Integer.parseInt(txt_Timebox.getText()));
        				p.setCusto(Double.parseDouble(txt_Custo.getText()));
        				p.setTimebox(10);
        				verificacao_fase = 1;
        			}
        		}
        		if(verificacao_fase == 1) {
        			Dao<Fase> daoFase = new DaoGeneric<Fase>();
        			List<Fase> lista_fase = daoFase.listar(new Fase());
        			p.getFase().add(lista_fase.get(1));
        			p.getFase().add(lista_fase.get(2));
        			p.getFase().add(lista_fase.get(3));
        			p.getFase().add(lista_fase.get(4));
        		}
        		else if(verificacao_fase == 0) {
        			Dao<Fase> daoFase = new DaoGeneric<Fase>();
        			List<Fase> lista_fase = daoFase.listar(new Fase());
        			p.getFase().add(lista_fase.get(1));	
        		}
        		daoProjeto.inserir(p);
        		f.getProjetos().add(p);
        		daoFuncionario.alterar(f);
        		p.setProduct_owner(f);
        		daoProjeto.alterar(p);
        		Alerta a = new Alerta();
        		a.alertInformation("Projeto inserido com sucesso!");
        		final Node source1 = (Node) btn_Salvar.getSource();
    			final Stage stage1 = (Stage) source1.getScene().getWindow();
    			stage1.close();
        		desabilitarTxt();
        		if(verificacao_fase == 1) {
        			final Node source = (Node) btn_Salvar.getSource();
        			final Stage stage = (Stage) source.getScene().getWindow();
        			stage.close();
        		}
        	}
    	}
    	catch(Exception e) {
    		Alerta a = new Alerta();
    		a.alertInformation("Use a opção 'Verificar' para o calculo às opções e preencha os campos corretamentes!");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_Custo.setEditable(false);
		txt_Custo.setDisable(true);
		txt_Timebox.setEditable(false);
		txt_Timebox.setDisable(true);
		txt_Meses.setDisable(true);
		txt_Meses.setEditable(false);
		txt_NDesenvolvedores.setEditable(false);
		txt_NDesenvolvedores.setDisable(true);
		txt_CustoPF.setEditable(false);
		txt_CustoPF.setDisable(true);
		ck_java.setDisable(true);
		ck_php.setDisable(true);
		ck_c.setDisable(true);
		ck_cs.setDisable(true);
		btn_Verificar.setDisable(true);
		Integer id = sortearId();
		id_projeto_novo = id;
		txt_IdProjeto.setText(id.toString()); 
		txt_Nome.setStyle("-fx-text-inner-color:white");
		txt_Descricao.setStyle("-fx-text-inner-color:white");
		dt_Inicio.setStyle("-fx-text-inner-color:white");
		dt_Fim.setStyle("-fx-text-inner-color:white");
		txt_Custo.setStyle("-fx-text-inner-color:white");
		txt_Timebox.setStyle("-fx-text-inner-color:white");
		txt_Meses.setStyle("-fx-text-inner-color:white");
		txt_NDesenvolvedores.setStyle("-fx-text-inner-color:white");
		txt_CustoPF.setStyle("-fx-text-inner-color:white");
		btn_Inserir.setDisable(true);
		txt_CadastrarFase.setEditable(false);
		txt_CadastrarFase.setDisable(true);
		tb_Fases.setDisable(true);
		btn_Inserir.setDisable(true);
	}
	
	public List<Fase> lista_fase() {
		Dao<Fase> daoFases = new DaoGeneric<Fase>();
		Fase f = new Fase();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_fase", 1);
		String where = " where t.id_fase = :id_fase";
    	List<Fase> unica_fase = daoFases.listarWhere(f, where, argumentos);
		return unica_fase;		
	}
	
	@SuppressWarnings("unused")
	@FXML
	void verificacao() {
		Alerta a = new Alerta();
		try {
			Integer tamanho = Integer.parseInt(txt_Meses.getText());
			Integer tamanho_desenv = Integer.parseInt(txt_NDesenvolvedores.getText());
			Double tamanho_real = Double.parseDouble(txt_CustoPF.getText());
			if(txt_Meses.getText().equals(null) || tamanho <= 0) {
				a.alertInformation("Número de backlogs inválido!");
			}
			else if(txt_NDesenvolvedores.getText().equals(null) || tamanho_desenv <= 0) {
				a.alertInformation("Número de desenvolvedores inválido!");
			}
			else if(txt_CustoPF.getText().equals(null) || tamanho_real <= 0) {
				a.alertInformation("Valor informado não corresponde ao permitido!");
			}
			else {
				Integer total_horas = 132;
				if(identificador == 1) {
					Integer conversao_pts = (tamanho*35);
					System.out.println(conversao_pts);
					Integer horas = (conversao_pts*10);
					System.out.println(horas);
					Integer horas_desenv = (tamanho_desenv*132);
					System.out.println(horas_desenv);
					Double custo_total = (conversao_pts*tamanho_real);
					int meses = (int) Math.round(((double)horas/horas_desenv)+0.5d);
					Integer mmeses= meses;
					txt_Timebox.setText(mmeses.toString());
					txt_Custo.setText(custo_total.toString());
					System.out.println(conversao_pts+" "+horas_desenv);
					System.out.println(mmeses);
				}
				else if(identificador == 2) {
					Integer conversao_pts = (tamanho*35);
					System.out.println(conversao_pts);
					Integer horas = (conversao_pts*06);
					System.out.println(horas);
					Integer horas_desenv = (tamanho_desenv*132);
					System.out.println(horas_desenv);
					Double custo_total = (conversao_pts*tamanho_real);
					int meses = (int) Math.round(((double)horas/horas_desenv)+0.5d);
					Integer mmeses= meses;
					txt_Timebox.setText(mmeses.toString());
					txt_Custo.setText(custo_total.toString());
					System.out.println(conversao_pts+" "+horas_desenv);
					System.out.println(mmeses);	
				}
				else if(identificador == 3) {
					Integer conversao_pts = (tamanho*35);
					System.out.println(conversao_pts);
					Integer horas = (conversao_pts*10);
					System.out.println(horas);
					Integer horas_desenv = (tamanho_desenv*132);
					System.out.println(horas_desenv);
					Double custo_total = (conversao_pts*tamanho_real);
					int meses = (int) Math.round(((double)horas/horas_desenv)+0.5d);
					Integer mmeses= meses;
					txt_Timebox.setText(mmeses.toString());
					txt_Custo.setText(custo_total.toString());
					System.out.println(conversao_pts+" "+horas_desenv);
					System.out.println(mmeses);
				}
				else if(identificador == 4) {
					Integer conversao_pts = (tamanho*35);
					System.out.println(conversao_pts);
					Integer horas = (conversao_pts*10);
					System.out.println(horas);
					Integer horas_desenv = (tamanho_desenv*132);
					System.out.println(horas_desenv);
					Double custo_total = (conversao_pts*tamanho_real);
					int meses = (int) Math.round(((double)horas/horas_desenv)+0.5d);
					Integer mmeses= meses;
					txt_Timebox.setText(mmeses.toString());
					txt_Custo.setText(custo_total.toString());
					System.out.println(conversao_pts+" "+horas_desenv);
					System.out.println(mmeses);
				}
				else {
					a.alertInformation("Selecione uma linguagem para seu projeto!");
				}
			}		
		}
		catch(NumberFormatException e) {
			a.alert("Impossível calcular com os valores informados!");
			txt_Custo.setText("");
    		txt_Timebox.setText("");
    		txt_CustoPF.setText("");
    		txt_NDesenvolvedores.setText("");
    		txt_Meses.setText("");
    		txt_CustoPF.setText("");
		}
	
	}
	
	public void desabilitarTxt() {
		txt_Nome.setDisable(true);
		txt_Descricao.setDisable(true);
		ck_Alta.setDisable(true);
		ck_Medio.setDisable(true);
		ck_Baixa.setDisable(true);
		txt_Color.setDisable(true);
		dt_Inicio.setDisable(true);
		dt_Fim.setDisable(true);
		txt_Custo.setDisable(true);
		txt_Timebox.setDisable(true);
		txt_Meses.setDisable(true);
		txt_CustoPF.setDisable(true);
		txt_NDesenvolvedores.setDisable(true);
		ck_java.setDisable(true);
		ck_php.setDisable(true);
		ck_c.setDisable(true);
		ck_cs.setDisable(true);
		btn_Verificar.setDisable(true);
		ck_Agil.setDisable(true);
		ck_Pessoal.setDisable(true);
		btn_Salvar.setDisable(true);
	}
	
	@FXML
	void inserirFases() {
		try {
			ObservableList<Fase> obs_Fase = FXCollections.observableArrayList();
			obs_Fase.clear();
			Alerta a = new Alerta();
			Fase fase = new Fase();
			Dao<Fase> daoFase = new DaoGeneric<Fase>();
			Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
			Map<String,Object> argumentos = new HashMap<String,Object>();
			argumentos.put("id_projeto", id_projeto_novo);
			String where = " where t.id_projeto = :id_projeto";
			List<Projeto> lista_projetos = daoProjeto.listarWhere(new Projeto(), where, argumentos);
			Projeto p = new Projeto();
			if(lista_projetos.size() == 1) {
				p.setAtividades(lista_projetos.get(0).getAtividades());
				p.setCor(lista_projetos.get(0).getCor());
				p.setCusto(lista_projetos.get(0).getCusto());
				p.setData_fim(lista_projetos.get(0).getData_fim());
				p.setData_inicio(lista_projetos.get(0).getData_inicio());
				p.setDescricao(lista_projetos.get(0).getDescricao());
				p.setFase(lista_projetos.get(0).getFase());
				p.setFuncionarios(lista_projetos.get(0).getFuncionarios());
				p.setId_projeto(lista_projetos.get(0).getId_projeto());
				p.setLinguagem(lista_projetos.get(0).getLinguagem());
				p.setMeses(lista_projetos.get(0).getMeses());
				p.setNome_projeto(lista_projetos.get(0).getNome_projeto());
				p.setPrioridade(lista_projetos.get(0).getPrioridade());
				p.setProduct_owner(lista_projetos.get(0).getProduct_owner());
				p.setScrum_master(lista_projetos.get(0).getScrum_master());
				p.setTimebox(lista_projetos.get(0).getTimebox());	
			}
			else {
				a.alert("Erro 9009 - Favor contatar o suporte!");
			}
			if(txt_CadastrarFase.equals(null)) {
				a.alertInformation("O campo está nulo!");
			}
			else {
				obs_Fase.addAll(p.getFase());
				fase.setNome_fase(txt_CadastrarFase.getText());
				fase.setDescricao(txt_CadastrarFase.getText());
				System.out.println(obs_Fase.size());
				daoFase.inserir(fase);
				p.getFase().add(fase);
				daoProjeto.alterar(p);
				cl_Fases.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
				tb_Fases.setItems(obs_Fase);
			}		
		}
		catch(Exception e) {
			Alerta alerta = new Alerta();
			alerta.alert("Erro 9009 - Favor contatar o suporte!");
		}
	}
	
}
