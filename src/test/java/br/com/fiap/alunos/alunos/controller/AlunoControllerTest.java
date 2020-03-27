package br.com.fiap.alunos.alunos.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.entity.Aluno;
import br.com.fiap.alunos.service.AlunoService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AlunoControllerTest {

	@MockBean
	private AlunoService alunoService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAll() throws Exception {

		List<AlunoDTO> alunoDTOList = new ArrayList<AlunoDTO>();
		alunoDTOList.add(new AlunoDTO(gerarAluno()));

		given(alunoService.findAll()).willReturn(alunoDTOList);

		mockMvc.perform(get("/alunos")).andExpect(status().isOk())
				.andExpect(content().json("[{'id': 999999999,'nome': 'testenome', 'matricula': 'testematricula'}]"));

		verify(alunoService, times(1)).findAll();
	}

	@Test
	public void testFindById() throws Exception {

		AlunoDTO alunoDTOList = new AlunoDTO(gerarAluno());

		given(alunoService.findById(999999999)).willReturn(alunoDTOList);

		mockMvc.perform(get("/alunos/{id}", 999999999)).andExpect(status().isOk())
				.andExpect(content().json("{'id': 999999999,'nome': 'testenome', 'matricula': 'testematricula'}"));

		verify(alunoService, times(1)).findById(999999999);
	}

	@Test
	public void testCreate() throws Exception {

		mockMvc.perform(post("/alunos").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nome\": \"testenome\", \"matricula\": \"testematricula\" }"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testUpdate() throws Exception {
		AlunoDTO aluno = gerarAlunoDTO();

		mockMvc.perform(put("/alunos/{id}", aluno.getId()).contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nome\": \"testenome\", \"matricula\": \"testematricula\" }"))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testDelete() throws Exception {
		AlunoDTO aluno = gerarAlunoDTO();

		doNothing().when(alunoService).delete(aluno.getId());

		mockMvc.perform(delete("/alunos/{id}", aluno.getId())).andExpect(status().is2xxSuccessful());

		verify(alunoService, times(1)).delete(aluno.getId());
	}

	public Aluno gerarAluno() {
		Aluno aluno = new Aluno();
		aluno.setId(999999999);
		aluno.setNome("testenome");
		aluno.setMatricula("testematricula");
		aluno.setDataCriacao(new Date());
		aluno.setDataModificacao(new Date());
		return aluno;
	}

	public AlunoDTO gerarAlunoDTO() {
		AlunoDTO aluno = new AlunoDTO();
		aluno.setId(999999999);
		aluno.setNome("testenome");
		aluno.setMatricula("testematricula");
		return aluno;
	}

}