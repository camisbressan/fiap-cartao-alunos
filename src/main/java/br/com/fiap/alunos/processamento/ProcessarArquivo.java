package br.com.fiap.alunos.processamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.fiap.alunos.controller.AlunoController;
import br.com.fiap.alunos.dto.CreateAlunoDTO;

@Component
public class ProcessarArquivo {

	private AlunoController alunoController;

	public ProcessarArquivo(AlunoController alunoController) {
		this.alunoController = alunoController;
	}

	@Bean
	public void lerArquivo() {

		InputStream arquivo = getClass().getClassLoader().getResourceAsStream("lista_alunos.txt");
		InputStreamReader inputStreamReader = new InputStreamReader(arquivo);
		BufferedReader buffer = new BufferedReader(inputStreamReader);
		String linha;
		try {
			linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains("---") || linha.isEmpty()) {
				} else {
					cadastrarAluno(rtrim(linha.substring(0, 40)), linha.substring(41, 48));
				}
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String rtrim(String testeTrim) {
		return testeTrim.replaceAll("\\s+$", "");
	}

	public void cadastrarAluno(String nome, String matricula) {
		CreateAlunoDTO aluno = new CreateAlunoDTO();
		aluno.setNome(nome);
		aluno.setMatricula(matricula);
		alunoController.create(aluno);
	}

}
