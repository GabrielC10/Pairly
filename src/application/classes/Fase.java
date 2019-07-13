package application.classes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FASE")
public class Fase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_fase;
	@Column(nullable=false, length=50)
	private String nome_fase;
	@Column(length=200)
	private String descricao;

	public Integer getId_fase() {
		return id_fase;
	}
	
	public void setId_fase(Integer id_fase) {
		this.id_fase = id_fase;
	}
	
	public String getNome_fase() {
		return nome_fase;
	}
	
	public void setNome_fase(String nome_fase) {
		this.nome_fase = nome_fase;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
