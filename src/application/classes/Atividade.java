package application.classes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ATIVIDADE")
public class Atividade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_atividade;
	@Column(nullable=false, length=50)
	private String nome_projeto;
	@Column(length=100)
	private String descricao;
	@Column(nullable=false)
	private char status;
	@Column(nullable=false)
	private Date data_inicio;
	@Column(nullable=false)
	private Date data_fim;
	private Integer total_dias_fazendo;
	private char prioridade;
	@ManyToOne
	@JoinColumn(name="id_projeto")
	private Projeto projeto;
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario funcionario;
	@ManyToOne
	@JoinColumn(name="id_fase")
	private Fase fase;

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Integer getId_atividade() {
		return id_atividade;
	}
	
	public void setId_atividade(Integer id_atividade) {
		this.id_atividade = id_atividade;
	}
	
	public String getNome_projeto() {
		return nome_projeto;
	}
	
	public void setNome_projeto(String nome_projeto) {
		this.nome_projeto = nome_projeto;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public char getStatus() {
		return status;
	}
	
	public void setStatus(char status) {
		this.status = status;
	}
	
	public Date getData_inicio() {
		return data_inicio;
	}
	
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}
	
	public Date getData_fim() {
		return data_fim;
	}
	
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	
	public Integer getTotal_dias_fazendo() {
		return total_dias_fazendo;
	}
	
	public void setTotal_dias_fazendo(Integer total_dias_fazendo) {
		this.total_dias_fazendo = total_dias_fazendo;
	}
	
	public char getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(char prioridade) {
		this.prioridade = prioridade;
	}
	
}
