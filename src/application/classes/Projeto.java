package application.classes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROJETO")
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_projeto;
	@Column(nullable = false, length = 50)
	private String nome_projeto;
	@Column(nullable = false)
	private Date data_inicio;
	@Column(nullable = false)
	private Date data_fim;
	@Column(nullable = false)
	private Double custo;
	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Column(length = 1)
	private char ativo;
	@Column(nullable = false)
	private Integer meses;
	@Column(nullable = false, length = 100)
	private String cor;
	@Column(length = 200)
	private String descricao;
	private char prioridade;
	private Integer timebox;
	@ManyToOne
	@JoinColumn(name = "id_linguagem")
	private Linguagem linguagem;
	@ManyToMany(mappedBy = "projetos")
	private List<Funcionario> funcionarios;
	@ManyToMany
	private List<Fase> fase;
	@OneToMany
	private List<Atividade> atividades;
	@ManyToOne
	private Funcionario scrum_master;
	@ManyToOne
	private Funcionario product_owner;
	
	public Funcionario getProduct_owner() {
		return product_owner;
	}

	public void setProduct_owner(Funcionario product_owner) {
		this.product_owner = product_owner;
	}

	public Funcionario getScrum_master() {
		return scrum_master;
	}

	public void setScrum_master(Funcionario scrum_master) {
		this.scrum_master = scrum_master;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Fase> getFase() {
		return fase;
	}

	public void setFase(List<Fase> fase) {
		this.fase = fase;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public Integer getId_projeto() {
		return id_projeto;
	}

	public void setId_projeto(Integer id_projeto) {
		this.id_projeto = id_projeto;
	}

	public String getNome_projeto() {
		return nome_projeto;
	}

	public void setNome_projeto(String nome_projeto) {
		this.nome_projeto = nome_projeto;
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

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Integer getMeses() {
		return meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(char prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getTimebox() {
		return timebox;
	}

	public void setTimebox(Integer timebox) {
		this.timebox = timebox;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
