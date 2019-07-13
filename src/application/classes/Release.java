package application.classes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RELEASE")
public class Release {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_release;
	@Column(nullable=false, length=50)
	private String nome_release;
	@Column(length=200)
	private String descricao_release;
	@Column(nullable=false)
	private Date disponibilidade;

	public Integer getId_release() {
		return id_release;
	}
	
	public void setId_release(Integer id_release) {
		this.id_release = id_release;
	}
	
	public String getNome_release() {
		return nome_release;
	}
	
	public void setNome_release(String nome_release) {
		this.nome_release = nome_release;
	}
	
	public String getDescricao_release() {
		return descricao_release;
	}
	
	public void setDescricao_release(String descricao_release) {
		this.descricao_release = descricao_release;
	}
	
	public Date getDisponibilidade() {
		return disponibilidade;
	}
	
	public void setDisponibilidade(Date disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
}
