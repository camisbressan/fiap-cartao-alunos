package br.com.fiap.alunos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.fiap.alunos.dto.CreateAlunoDTO;

@Entity
@Table(name = "TB_ALUNOS")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "email", length = 200)
	private String email;

	@Column(name = "data_criacao")
	@CreatedDate
	private Date dataCriacao;

	@Column(name = "data_atualizacao")
	@LastModifiedDate
	private Date dataModificacao;

	public Aluno() {
	}

	public Aluno(CreateAlunoDTO createAlunoDTO) {
		this.nome = createAlunoDTO.getNome();
		this.matricula = createAlunoDTO.getMatricula();
		this.email = createAlunoDTO.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

}
