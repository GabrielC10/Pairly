package application.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.classes.Anexos;
import application.classes.Atividade;
import application.classes.Fase;
import application.dao.Dao;
import application.dao.DaoGeneric;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerImagem implements Initializable {

	protected String caminho;
	protected String destino;
	
	@FXML
    private Pane root;

    @FXML
    private JFXTextField txtCaminho;

    @FXML
    private JFXButton btnSelecionar;

    @FXML
    private ImageView imgImagem;

    @FXML
    private JFXButton btnSair;

    @FXML
    void sair(MouseEvent btnSair) {
    	final Node source = (Node) btnSair.getSource();
    	final Stage stage = (Stage) source.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void selecionar(MouseEvent btnSelecionar) {
    	Dao<Anexos> daoAnexo = new DaoGeneric<Anexos>();
    	Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
    	Map<String,Object> argumentos = new HashMap<String,Object>();
    	argumentos.put("id_atividade", ControllerKanbanDesenv.codigo_atividade);
    	String where = " where t.id_atividade = :id_atividade";
    	List<Atividade> listaAtividade = daoAtividade.listarWhere(new Atividade(), where, argumentos);
    	Atividade atividade = new Atividade();
    	Fase fase = new Fase();
    	atividade.setData_fim(listaAtividade.get(0).getData_fim());
    	atividade.setData_inicio(listaAtividade.get(0).getData_inicio());
    	atividade.setDescricao(listaAtividade.get(0).getDescricao());
    	fase.setDescricao(listaAtividade.get(0).getFase().getDescricao());
    	fase.setId_fase(listaAtividade.get(0).getFase().getId_fase());
    	fase.setNome_fase(listaAtividade.get(0).getFase().getNome_fase());
    	atividade.setFase(listaAtividade.get(0).getFase());
    	atividade.setFuncionario(listaAtividade.get(0).getFuncionario());
    	atividade.setId_atividade(listaAtividade.get(0).getId_atividade());
    	atividade.setNome_projeto(listaAtividade.get(0).getNome_projeto());
    	atividade.setPrioridade(listaAtividade.get(0).getPrioridade());
    	atividade.setProjeto(listaAtividade.get(0).getProjeto());
    	atividade.setStatus(listaAtividade.get(0).getStatus());
    	atividade.setTotal_dias_fazendo(listaAtividade.get(0).getTotal_dias_fazendo());
    	FileChooser f = new FileChooser();
    	f.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens, PDF", "*.jpeg", "*.png", "*.jpg", "*.pdf"));
    	File file = f.showSaveDialog(new Stage());
    	if(file != null) {
    		txtCaminho.setText(file.getAbsolutePath());
    		imgImagem.setImage(new Image("file:///"+file.getAbsolutePath()));
    		caminho = file.getAbsolutePath();
    		destino = "C:\\Users\\lenovo\\workspace-3sem\\Pairly\\src\\application\\arquivos\\"+file.getName();
    		Anexos anexos = new Anexos();
    		anexos.setCaminho(file.getAbsolutePath());
    		anexos.setDescricao_anexo(file.getPath());
    		anexos.setNome_anexo(file.getName());
    		anexos.setAtividade(listaAtividade.get(0));
    		daoAnexo.inserir(anexos);
    		try {
				Files.copy(Paths.get(caminho), Paths.get(destino));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtCaminho.setStyle("-fx-text-inner-color:white");
	}

}
