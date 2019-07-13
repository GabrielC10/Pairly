package application.classes;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PLANO")
public class Plano {
	
	private Integer id_plano;
	private String nome_plano;
	private BigDecimal preco;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId_plano() {
		return id_plano;
	}
	
	public void setId_plano(Integer id_plano) {
		this.id_plano = id_plano;
	}
	
	public String getNome_plano() {
		return nome_plano;
	}
	
	@Column(nullable = false)
	public void setNome_plano(String nome_plano) {
		this.nome_plano = nome_plano;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	@Column(nullable = false)
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
