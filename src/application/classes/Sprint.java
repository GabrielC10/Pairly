package application.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="SPRINT")
public class Sprint {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_sprint;
	@Column(nullable=false, length=50)
	private String nome_sprint;
	@Column(length=200)
	private String descricao_sprint;
	@Column(nullable=false)
	private char prioridade;
	@ManyToMany
	private List<Atividade> atividade;
	@ManyToOne
	@JoinColumn(name="id_release")
	private Release release;
	@ManyToOne
	@JoinColumn(name="id_projeto")
	private Projeto projeto;
	
	public Integer getId_sprint() {
		return id_sprint;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public void setId_sprint(Integer id_sprint) {
		this.id_sprint = id_sprint;
	}
	
	public String getNome_sprint() {
		return nome_sprint;
	}
	
	public void setNome_sprint(String nome_sprint) {
		this.nome_sprint = nome_sprint;
	}
	
	public String getDescricao_sprint() {
		return descricao_sprint;
	}
	
	public void setDescricao_sprint(String descricao_sprint) {
		this.descricao_sprint = descricao_sprint;
	}
	
	public char getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(char prioridade) {
		this.prioridade = prioridade;
	}
	
	public List<Atividade> getAtividade() {
		return atividade;
	}
	
	public void setAtividade(List<Atividade> atividade) {
		this.atividade = atividade;
	}
	
	public Release getRelease() {
		return release;
	}
	
	public void setRelease(Release release) {
		this.release = release;
	}
	
}
