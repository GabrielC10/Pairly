package application.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LINGUAGEM")
public class Linguagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_linguagem;
	@Column(nullable = false, length = 50)
	private String nome_linguagem;
	@Column(nullable = false)
	private Integer carga_horaria;
	
	public Integer getId_linguagem() {
		return id_linguagem;
	}
	
	public void setId_linguagem(Integer id_linguagem) {
		this.id_linguagem = id_linguagem;
	}
	
	public String getNome_linguagem() {
		return nome_linguagem;
	}
	
	public void setNome_linguagem(String nome_linguagem) {
		this.nome_linguagem = nome_linguagem;
	}
	
	public Integer getCarga_horaria() {
		return carga_horaria;
	}
	
	public void setCarga_horaria(Integer carga_horaria) {
		this.carga_horaria = carga_horaria;
	}
	
}
