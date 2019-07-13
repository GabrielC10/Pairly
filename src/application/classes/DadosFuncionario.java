package application.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DADOSFUNC")
public class DadosFuncionario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_dado;
	@Column(nullable = false, length = 50)
	private String nome_funcionario;
	@Column(nullable = false, length = 50)
	private String sobrenome_funcionario;
	@Column(nullable = true, length = 50)
	private String telefone_principal;
	@Column(nullable = false, length = 50)
	private String email;
	@OneToOne
	@JoinColumn(name="id_linguagem")
	private Linguagem linguagem;
	@Column(length=30)
	private String caminho_avatar;
	
	public String getCaminho_avatar() {
		return caminho_avatar;
	}

	public void setCaminho_avatar(String caminho_avatar) {
		this.caminho_avatar = caminho_avatar;
	}

	public Integer getId_dado() {
		return id_dado;
	}
	
	public void setId_dado(Integer id_dado) {
		this.id_dado = id_dado;
	}
	
	public String getNome_funcionario() {
		return nome_funcionario;
	}
	
	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}
	
	public String getSobrenome_funcionario() {
		return sobrenome_funcionario;
	}
	
	public void setSobrenome_funcionario(String sobrenome_funcionario) {
		this.sobrenome_funcionario = sobrenome_funcionario;
	}
	
	public String getTelefone_principal() {
		return telefone_principal;
	}
	
	public void setTelefone_principal(String telefone_principal) {
		this.telefone_principal = telefone_principal;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Linguagem getLinguagem() {
		return linguagem;
	}
	
	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}
	
}
