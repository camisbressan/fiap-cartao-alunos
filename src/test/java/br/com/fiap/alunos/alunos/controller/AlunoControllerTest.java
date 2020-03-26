package br.com.fiap.alunos.alunos.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.alunos.controller.AlunoController;
import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.dto.CreateAlunoDTO;

class AlunoControllerTest {

	@InjectMocks
	private AlunoController alunoController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testar_se_consulta_todos_os_alunos()  {
		ResponseEntity<List<AlunoDTO>> aluno = alunoController.getAll();
		Assert.assertEquals(HttpStatus.OK, aluno.getStatusCodeValue());		
	}
	
	@Test
	public void testar_se_consulta_um_aluno()  {
		ResponseEntity<AlunoDTO> aluno = alunoController.findById(1);
		Assert.assertEquals(HttpStatus.OK, aluno.getStatusCodeValue());		
	}
	
	@Test
	public void testar_se_cria_aluno()  {
		CreateAlunoDTO alunoDTO = new CreateAlunoDTO();
		ResponseEntity<AlunoDTO> aluno = alunoController.create(alunoDTO);
		Assert.assertEquals(HttpStatus.CREATED, aluno.getStatusCodeValue());
	}
	
	@Test
	public void testar_se_altera_aluno()  {
		CreateAlunoDTO alunoDTO = new CreateAlunoDTO();
		ResponseEntity<AlunoDTO> aluno = alunoController.update(1,alunoDTO);
		Assert.assertEquals(HttpStatus.OK, aluno.getStatusCodeValue());
	}
	
	@Test
	public void testar_se_deleta_aluno()  {
		//TODO
	}
}