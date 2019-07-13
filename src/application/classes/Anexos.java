package application.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ANEXOS")
public class Anexos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_anexos;
	@Column(nullable=false, length=50)
	private String nome_anexo;
	@Column(length=200)
	private String descricao_anexo;
	@Column(nullable=false, length=100)
	private String caminho;
	@ManyToOne
	@JoinColumn(name="id_atividade")
	private Atividade atividade;

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Integer getId_anexos() {
		return id_anexos;
	}
	
	public void setId_anexos(Integer id_anexos) {
		this.id_anexos = id_anexos;
	}
	
	public String getNome_anexo() {
		return nome_anexo;
	}
	
	public void setNome_anexo(String nome_anexo) {
		this.nome_anexo = nome_anexo;
	}
	
	public String getDescricao_anexo() {
		return descricao_anexo;
	}
	
	public void setDescricao_anexo(String descricao_anexo) {
		this.descricao_anexo = descricao_anexo;
	}
	
	public String getCaminho() {
		return caminho;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
