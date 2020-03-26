package br.com.fiap.alunos.alunos.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.entity.Aluno;
import br.com.fiap.alunos.repository.AlunoRepository;
import br.com.fiap.alunos.service.impl.AlunoServiceImpl;

class AlunoServiceImplTest {

	@Mock
	private AlunoRepository alunoRepository;

	@InjectMocks
	private AlunoServiceImpl alunoServiceImpl;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testar_se_retorna_lista_aluno() {
		//Given
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		Aluno aluno = new Aluno();
		aluno.setMatricula("matricula");
		aluno.setNome("nome");
		listaAlunos.add(aluno);
		//When
		when(alunoRepository.findAll()).thenReturn(listaAlunos);
		List<AlunoDTO> result = alunoServiceImpl.findAll();
		//Then
		assertThat(result.get(0)).isEqualTo(listaAlunos);
	}
	
}
