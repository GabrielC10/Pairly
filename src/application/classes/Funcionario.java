package application.classes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="FUNCIONARIO")
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_funcionario;
	@Column(unique=true, nullable=false)
	private String login_funcionario;
	@Column(nullable=false)
	private String senha_funcionario;
	@OneToOne
	@JoinColumn(name="id_plano")
	private Plano plano;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Projeto> projetos;
	@Column(length=30, nullable=false)
	private String pergunta;
	public DadosFuncionario getDados() {
		return dados;
	}

	public void setDados(DadosFuncionario dados) {
		this.dados = dados;
	}

	@Column(length=10, nullable=false)
	private String chave_recuperacao;
	@OneToOne
	@JoinColumn(name="id_dado")
	private DadosFuncionario dados;
	
	public String getChave_recuperacao() {
		return chave_recuperacao;
	}

	public void setChave_recuperacao(String chave_recuperacao) {
		this.chave_recuperacao = chave_recuperacao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Integer getId_funcionario() {
		return id_funcionario;
	}
	
	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	
	public String getLogin_funcionario() {
		return login_funcionario;
	}
	
	@Column(nullable = false, unique = true)
	public void setLogin_funcionario(String login_funcionario) {
		this.login_funcionario = login_funcionario;
	}
	
	public String getSenha_funcionario() {
		return senha_funcionario;
	}
	
	@Column(nullable = false, unique = true)
	public void setSenha_funcionario(String senha_funcionario) {
		this.senha_funcionario = senha_funcionario;
	}
	
	public Plano getPlano() {
		return plano;
	}
	
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
}
