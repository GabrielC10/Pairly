package application.classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRAMITACAO")
public class Tramitacao {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_tramitacao;
	private Date tramitacao;
	@ManyToOne
	@JoinColumn(name="id_fase")
	private Fase fase;
	@ManyToOne
	@JoinColumn(name="id_atividade")
	private Atividade atividade;
	
	public Integer getId_tramitacao() {
		return id_tramitacao;
	}
	public void setId_tramitacao(Integer id_tramitacao) {
		this.id_tramitacao = id_tramitacao;
	}
	public Date getTramitacao() {
		return tramitacao;
	}
	public void setTramitacao(Date tramitacao) {
		this.tramitacao = tramitacao;
	}
	public Fase getFase() {
		return fase;
	}
	public void setFase(Fase fase) {
		this.fase = fase;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
}
