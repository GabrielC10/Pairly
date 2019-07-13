package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import application.classes.Fase;
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

public class Controller_Alterar_Projeto implements Initializable {

    @FXML
    private Pane root;
    
    @FXML
    private Pane paneColor;
    
    @FXML
    private TableView<Fase> tbFases;

    @FXML
    private TableColumn<Fase, String> clFases;

    @FXML
    private JFXButton btnInserir;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btn_Salvar;
    
    @FXML 
    private JFXButton btnQuit;
    
    @FXML
    private JFXButton btn_Cancelar;
    
    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXTextField txtCadastrarFase;

    @FXML
    private JFXDatePicker dtInicio;

    @FXML
    private JFXTextField idProjeto;

    @FXML
    private JFXDatePicker dtFim;

    @FXML
    private JFXCheckBox ckAlta;

    @FXML
    private JFXCheckBox ckMedia;

    @FXML
    private JFXCheckBox ckBaixa;

    @FXML
    private JFXColorPicker colorProject;

    @FXML
    private JFXCheckBox ck_java;

    @FXML
    private JFXCheckBox ck_php;

    @FXML
    private JFXCheckBox ck_cs;

    @FXML
    private JFXCheckBox ck_c;

    @FXML
    private JFXButton btn_Verificar;
    
    @FXML
    private JFXCheckBox ckDesenvolvimento;

    @FXML
    private JFXCheckBox ckPessoa;
    
    @FXML
    private JFXTextField txt_Meses;

    @FXML
    private JFXTextField txt_Timebox;

    @FXML
    private JFXTextField txt_NDesenvolvedores;

    @FXML
    private JFXTextField txt_CustoPF;

    @FXML
    private JFXTextField txt_Custo;

    static Integer identificador;
    
    static Integer identificador_prioridade;
    
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
    void removerFase(MouseEvent btnRemover) {
    	Alerta a = new Alerta();
    	try {
    		Integer id_selecionado = tbFases.getSelectionModel().getSelectedItem().getId_fase();
        	Dao<Fase> daoFase = new DaoGeneric<Fase>();
        	Fase fase = new Fase();
        	List<Fase> lista_fase = daoFase.listar(fase);
        	for(Fase x : lista_fase) {
        		if(x.getId_fase() == id_selecionado) {
        			fase.setDescricao(x.getDescricao());
        			fase.setId_fase(x.getId_fase());
        			fase.setNome_fase(x.getNome_fase());
        		}
        	}
        	if(fase.getId_fase() == 2) {
        		a.alert("Impossível remover a fase.");
        	}
        	else {
        		Projeto projeto = new Projeto();
        		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
        		Map<String,Object> argumentos = new HashMap<String,Object>();
        		argumentos.put("id_projeto", Controller_Projetos.getCodigo_projeto());
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
            	List<Fase> listaFase = new ArrayList<Fase>();
            	listaFase.addAll(projeto.getFase());
            	for(int x = 0; x < listaFase.size(); x++) {
            		if(listaFase.get(x).getId_fase() == id_selecionado) {
            			listaFase.remove(x);
            		}
            	}
            	projeto.setFase(listaFase);
            	daoProjeto.alterar(projeto);
            	daoFase.excluir(fase);	
            	a.alertInformation("Fase removida!");
            	popularTable();	
        	}
    	}
    	catch(Exception e) {
    		a.alertInformation("Selecione uma fase para remover!");
    	}
    		
    }
    
    @FXML
    void inserirFase(MouseEvent btnInserir) {
    	try {
    		Alerta alerta = new Alerta();
    		Projeto projeto = new Projeto();
        	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    		Map<String,Object> argumentos = new HashMap<String,Object>();
    		argumentos.put("id_projeto", Controller_Projetos.getCodigo_projeto());
    		String where = " where t.id_projeto = :id_projeto";
    		List<Projeto> lista_projetos = daoProjeto.listarWhere(new Projeto(), where, argumentos);
    		Dao<Fase> daoFase = new DaoGeneric<Fase>();
    		Fase fase = new Fase();
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
    		fase.setNome_fase(txtCadastrarFase.getText());
    		fase.setDescricao(txtCadastrarFase.getText());
    		if(txtCadastrarFase.getText().equals(null) 
    				|| txtCadastrarFase.getText().length() <= 1) {
    			alerta.alertInformation("Verifique o campo 'Cadastrar Fase'");
    		}
    		else {
    			daoFase.inserir(fase);
        		projeto.getFase().add(fase);
        		daoProjeto.alterar(projeto);
        		txtCadastrarFase.setText("");
        		alerta.alertInformation("Fase inserida com sucesso!");
        		popularTable();
    		}
    	}
    	catch(Exception e) {
    		Alerta alerta = new Alerta();
    		alerta.alert("Erro 9009 - Erro não catalogado na base Pairly, favor contatar o suporte!");
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Projeto projeto = new Projeto();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Projetos.getCodigo_projeto());
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
		if(projeto.getTimebox() != 0) {
			txt_NDesenvolvedores.setEditable(true);
			txt_Meses.setEditable(true);
			txt_CustoPF.setEditable(true);
		}
		txtNome.setStyle("-fx-text-inner-color:white");
		txtDesc.setStyle("-fx-text-inner-color:white");
		dtInicio.setStyle("-fx-text-inner-color:white");
		dtFim.setStyle("-fx-text-inner-color:white");
		txt_Custo.setStyle("-fx-text-inner-color:white");
		txt_Timebox.setStyle("-fx-text-inner-color:white");
		txt_Meses.setStyle("-fx-text-inner-color:white");
		txt_NDesenvolvedores.setStyle("-fx-text-inner-color:white");
		txt_Timebox.setDisable(true);
		txt_CustoPF.setStyle("-fx-text-inner-color:white");
		txtNome.setText(projeto.getNome_projeto());
		idProjeto.setText(projeto.getId_projeto().toString());
		txtDesc.setText(projeto.getDescricao());
		txt_Timebox.setText(projeto.getMeses().toString());
		txt_Custo.setText(projeto.getCusto().toString());
		char idprioridade = projeto.getPrioridade();
		popularTable();
		if(idprioridade == 'A') {
			ckAlta.setSelected(true);
			ckMedia.setSelected(false);
			ckBaixa.setSelected(false);
			ckMedia.setDisable(true);
			ckBaixa.setDisable(true);
		}
		else if(idprioridade == 'B') {
			ckBaixa.setSelected(true);
			ckAlta.setSelected(false);
			ckAlta.setDisable(true);
			ckMedia.setSelected(false);
			ckMedia.setDisable(true);
		}
		else if(idprioridade == 'M') {
			ckMedia.setSelected(true);
			ckAlta.setSelected(false);
			ckAlta.setDisable(true);
			ckBaixa.setSelected(false);
			ckBaixa.setDisable(true);
		}
		Date date; 
		date = projeto.getData_inicio();
		LocalDate dataLocal = converterLocalDate(date);
		dtInicio.setValue(dataLocal);
		Date date2;
		date2 = projeto.getData_fim();
		LocalDate dataLocal2 = converterLocalDate(date2);
		dtFim.setValue(dataLocal2);
		paneColor.setStyle("-fx-background-color: #"+projeto.getCor().substring(2));
		if(projeto.getTimebox() == 0) {
			ckDesenvolvimento.setSelected(false);
			ckPessoa.setDisable(true);
			ckPessoa.setSelected(true);
			ckDesenvolvimento.setDisable(true);
			txt_Custo.setDisable(true);
			txt_CustoPF.setDisable(true);
			txt_Meses.setDisable(true);
			txt_NDesenvolvedores.setDisable(true);
			txt_Timebox.setDisable(true);
			btn_Verificar.setDisable(true);
			ck_c.setDisable(true);
			ck_java.setDisable(true);
			ck_php.setDisable(true);
			ck_cs.setDisable(true);
		}
		else if(projeto.getTimebox() != 0) {
			ckPessoa.setSelected(false);
			ckPessoa.setDisable(true);
			ckDesenvolvimento.setDisable(true);
			ckDesenvolvimento.setSelected(true);
			txtCadastrarFase.setDisable(true);
			btnInserir.setDisable(true);
			btnRemover.setDisable(true);
		}
	}
	
	public void popularTable() {
		Projeto projeto = new Projeto();
		ObservableList<Fase> obsFase = FXCollections.observableArrayList();
		Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Projetos.getCodigo_projeto());
		String where = " where t.id_projeto = :id_projeto";
		List<Projeto> lista_projetos = daoProjeto.listarWhere(projeto, where, argumentos);
		obsFase.clear();
		projeto.setFase(lista_projetos.get(0).getFase());
		obsFase.addAll(projeto.getFase());
		clFases.setCellValueFactory(new PropertyValueFactory<>("nome_fase"));
		tbFases.setItems(obsFase);
	}
	
	public LocalDate converterLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void salvar(MouseEvent btn_Salvar) {
    	Projeto projeto = new Projeto();
    	Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_projeto", Controller_Projetos.getCodigo_projeto());
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
		projeto.setAtivo(lista_projetos.get(0).getAtivo());
		projeto.setNome_projeto(lista_projetos.get(0).getNome_projeto());
		if(identificador_prioridade == null) {
			projeto.setPrioridade(lista_projetos.get(0).getPrioridade());
		}
		else if(identificador_prioridade == 0) {
			projeto.setPrioridade('B');
		}
		else if(identificador_prioridade == 1) {
			projeto.setPrioridade('M');
		}
		else if(identificador_prioridade == 2) {
			projeto.setPrioridade('A');
		}
		projeto.setProduct_owner(lista_projetos.get(0).getProduct_owner());
		projeto.setScrum_master(lista_projetos.get(0).getScrum_master());
		projeto.setTimebox(lista_projetos.get(0).getTimebox());
		projeto.setNome_projeto(txtNome.getText());
		projeto.setCor(colorProject.getValue().toString());
		Alerta alerta = new Alerta();
		if(projeto.getTimebox() == 0) {
			LocalDate data1 = dtInicio.getValue();
			LocalDate data2 = dtFim.getValue();
			Date data3 = java.sql.Date.valueOf(data1);
			Date data4 = java.sql.Date.valueOf(data2);
			projeto.setData_inicio(data3);
			projeto.setData_fim(data4);
			if(data1.compareTo(data2) > 0) {
				alerta.alertInformation("A data fim não pode ser menor que a data início!");
			}
			else {
				if(projeto.getNome_projeto().equals(null) 
						|| projeto.getNome_projeto().length() <= 1) {
					alerta.alertInformation("Verifique o campo 'Nome'!");
				}
				else {
					daoProjeto.alterar(projeto);
					alerta.alertInformation("Projeto alterado com sucesso!");
					final Node source = (Node) btn_Salvar.getSource();
					final Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
				}
			}
		}
		else if(projeto.getTimebox() != 0) {
			LocalDate data1 = dtInicio.getValue();
			LocalDate data2 = dtFim.getValue();
			Date data3 = java.sql.Date.valueOf(data1);
			Date data4 = java.sql.Date.valueOf(data2);
			projeto.setData_inicio(data3);
			projeto.setData_fim(data4);
			projeto.setCusto(Double.parseDouble(txt_Custo.getText()));
			projeto.setMeses(Integer.parseInt(txt_Timebox.getText()));
			if(data1.compareTo(data2) > 0) {
				alerta.alertInformation("A data fim não pode ser menor que a data início!");
			}
			else {
				if(projeto.getNome_projeto().equals(null) 
						|| projeto.getNome_projeto().length() <= 1) {
					alerta.alertInformation("Verifique o campo 'Nome'!");
				}
				else {
					daoProjeto.alterar(projeto);
					alerta.alertInformation("Projeto alterado com sucesso!");
					final Node source = (Node) btn_Salvar.getSource();
					final Stage stage = (Stage) source.getScene().getWindow();
					stage.close();	
				}
			}
		}
		
    }
    
    @SuppressWarnings("unused")
	@FXML
	void verificacao(MouseEvent btn_Verificar) {
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
    
    @FXML
    void quit(MouseEvent btnQuit) {
    	final Node source = (Node) btnQuit.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }
    
}