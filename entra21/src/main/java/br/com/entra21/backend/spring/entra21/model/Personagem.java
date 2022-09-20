package br.com.entra21.backend.spring.entra21.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="personagem")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Personagem extends MaturidadeNivel3Richardson{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	String nome_heroi;
	String nome_real;
	String habilidade;
	Boolean acessorio;
	Integer idade;
	
	public Personagem() {
		super();
	}

	public Personagem(Integer id, String nome_heroi, String nome_real, String habilidade, Boolean acessorio,
			Integer idade) {
		super();
		this.id = id;
		this.nome_heroi = nome_heroi;
		this.nome_real = nome_real;
		this.habilidade = habilidade;
		this.acessorio = acessorio;
		this.idade = idade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome_heroi() {
		return nome_heroi;
	}

	public void setNome_heroi(String nome_heroi) {
		this.nome_heroi = nome_heroi;
	}

	public String getNome_real() {
		return nome_real;
	}

	public void setNome_real(String nome_real) {
		this.nome_real = nome_real;
	}

	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public Boolean getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Boolean acessorio) {
		this.acessorio = acessorio;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
