package br.com.fiap.alunos.alunos.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.dto.CreateAlunoDTO;
import br.com.fiap.alunos.entity.Aluno;
import br.com.fiap.alunos.repository.AlunoRepository;
import br.com.fiap.alunos.service.AlunoService;
import br.com.fiap.alunos.service.impl.AlunoServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class AlunoServiceImplTest {

	@Test
	public void testFindAll() {
		AlunoRepository alunoRepository = mock(AlunoRepository.class);
		List<Aluno> alunoList = new ArrayList<Aluno>();
		alunoList.add(new Aluno());
		alunoList.add(new Aluno());
		alunoList.add(new Aluno());
		when(alunoRepository.findAll()).thenReturn(alunoList);

		AlunoService alunoService = new AlunoServiceImpl(alunoRepository);

		assertEquals(alunoService.findAll().size(), 3);
	}

	@Test
	public void testFindById() {
		AlunoRepository alunoRepository = mock(AlunoRepository.class);
		Aluno aluno = gerarAluno();
		Optional<Aluno> alunoOptional = Optional.of(aluno);

		when(alunoRepository.findById(1)).thenReturn(alunoOptional);
		AlunoService alunoService = new AlunoServiceImpl(alunoRepository);

		assertEquals(alunoService.findById(1).getId(), aluno.getId());
		assertEquals(alunoService.findById(1).getNome(), aluno.getNome());
		assertEquals(alunoService.findById(1).getMatricula(), aluno.getMatricula());
	}

	@Test
	public void testCreate() {
		AlunoRepository alunoRepository = mock(AlunoRepository.class);
		when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(gerarAluno());

		AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
		AlunoDTO alunoRetorno = alunoService.create(gerarCreateAlunoDTO());

		assertNotNull(alunoRetorno);
		assertEquals(alunoRetorno.getNome(), gerarAluno().getNome());
		assertEquals(alunoRetorno.getId(), gerarAluno().getId());
	}

	@Test
	public void testUpdate() {
		AlunoRepository alunoRepository = mock(AlunoRepository.class);
		Aluno aluno = gerarAluno();
		Optional<Aluno> alunoOptional = Optional.of(aluno);

		when(alunoRepository.findById(aluno.getId())).thenReturn(alunoOptional);
		when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);

		AlunoService alunoService = new AlunoServiceImpl(alunoRepository);
		AlunoDTO alunoRetorno = alunoService.update(aluno.getId(), gerarCreateAlunoDTO());

		assertNotNull(alunoRetorno);
		assertEquals(alunoRetorno.getNome(), aluno.getNome());
		assertEquals(alunoRetorno.getId(), aluno.getId());
	}

	@Test
	public void testDelete() {
		AlunoRepository alunoRepository = mock(AlunoRepository.class);
		alunoRepository.deleteById(any());
		verify(alunoRepository, times(1)).deleteById(any());
	}

	public Aluno gerarAluno() {
		Aluno aluno = new Aluno();
		aluno.setId(999999999);
		aluno.setNome("nome");
		aluno.setMatricula("matricula");
		aluno.setDataCriacao(new Date());
		aluno.setDataModificacao(new Date());
		return aluno;
	}

	public CreateAlunoDTO gerarCreateAlunoDTO() {
		CreateAlunoDTO aluno = new CreateAlunoDTO();
		aluno.setNome("nome");
		aluno.setMatricula("matricula");
		return aluno;
	}

}
