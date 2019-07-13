package application.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import application.classes.Release;
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

public class ControllerAlterarRelease implements Initializable {

    @FXML
    private JFXButton btnSair;

    @FXML
    private TableView<Sprint> tbResponsavel;

    @FXML
    private TableColumn<Sprint, String> clnResponsavel;

    @FXML
    private JFXButton btnSelecionar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtDesc;

    @FXML
    private JFXButton btn_Salvar;

    @FXML
    private JFXButton btn_Cancelar;

    @FXML
    private JFXTextField dtCriacao;

    @FXML
    void cancelar(MouseEvent btn_Cancelar) {
    	final Node source = (Node) btn_Cancelar.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
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
    	Dao<Release> daoRelease = new DaoGeneric<Release>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_release", Controller_Release.codigo_alteracao);
		String where = " where t.id_release = :id_release";
		List<Release> listaRelease = daoRelease.listarWhere(new Release(), where, argumentos);
		if(txtNome.getText().equals(null) || txtDesc.getText().equals(null)) {
			alerta.alertInformation("Os campos principais estão nulos!");
		}
		else {
			try {
				Release release = new Release();
				release.setDescricao_release(txtDesc.getText());
				release.setDisponibilidade(listaRelease.get(0).getDisponibilidade());
				release.setId_release(listaRelease.get(0).getId_release());
				release.setNome_release(txtNome.getText());
				daoRelease.alterar(release);
				alerta.alertInformation("Release alterada com sucesso!");
				final Node source = (Node) btn_Salvar.getSource();
				final Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			}
			catch(Exception e) {
				alerta.alert("Erro 900 - Impossível alterar a Release!");
			}
		}
    }

    @FXML
    void selecionar(MouseEvent event) {

    }

	@SuppressWarnings("deprecation")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btn_Salvar.setDisable(true);
		txtNome.setDisable(true);
		txtDesc.setDisable(true);
		txtNome.setStyle("-fx-text-inner-color:white");
		txtDesc.setStyle("-fx-text-inner-color:white");
		dtCriacao.setText("-fx-text-inner-color:white");
		tbResponsavel.setDisable(true);
		btnSelecionar.setDisable(true);
		Dao<Release> daoRelease = new DaoGeneric<Release>();
		Map<String,Object> argumentos = new HashMap<String,Object>();
		argumentos.put("id_release", Controller_Release.codigo_alteracao);
		String where = " where t.id_release = :id_release";
		List<Release> listaRelease = daoRelease.listarWhere(new Release(), where, argumentos);
		txtNome.setText(listaRelease.get(0).getNome_release());
		txtDesc.setText(listaRelease.get(0).getDescricao_release());
		dtCriacao.setText(listaRelease.get(0).getDisponibilidade().toGMTString());
		Dao<Sprint> daoSprint = new DaoGeneric<Sprint>();
		List<Sprint> listaSprint = daoSprint.listar(new Sprint());
		ObservableList<Sprint> obsSprint = FXCollections.observableArrayList();
		try {
			for(Sprint x : listaSprint) {
				if(x.getRelease() != null) {
					if(x.getRelease().getId_release() == Controller_Release.codigo_alteracao) {
						obsSprint.add(x);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		clnResponsavel.setCellValueFactory(new PropertyValueFactory<>("nome_sprint"));
		tbResponsavel.setItems(obsSprint);
	}

}
