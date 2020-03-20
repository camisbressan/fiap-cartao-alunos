package br.com.fiap.alunos.dto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import br.com.fiap.alunos.entity.Aluno;

public class AlunoDTO {

    private Integer id;
    private String nome;
    private String matricula;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataAtualizacao;
    
    public AlunoDTO(){}
    
	public AlunoDTO(Integer id, String nome, String matricula) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
	}
	
	public AlunoDTO(CreateAlunoDTO alunoDTO, Integer id) {
		this.id = id;
		this.nome = alunoDTO.getNome();
		this.matricula = alunoDTO.getMatricula();
	}

	public AlunoDTO(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.matricula = aluno.getMatricula();
        this.dataCriacao = convertToZonedDateTime(aluno.getDataCriacao());
        this.dataAtualizacao = convertToZonedDateTime(aluno.getDataModificacao());
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

    private ZonedDateTime convertToZonedDateTime(Date data) {
        if(data != null){
            return ZonedDateTime.ofInstant(data.toInstant(), ZoneOffset.systemDefault());
        } else {
            return null;
        }
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(ZonedDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ZonedDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(ZonedDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
