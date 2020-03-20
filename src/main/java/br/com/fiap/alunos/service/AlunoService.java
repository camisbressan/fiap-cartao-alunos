package br.com.fiap.alunos.service;

import java.util.List;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.dto.CreateAlunoDTO;

public interface AlunoService {

	List<AlunoDTO> findAll();

	AlunoDTO findById(Integer id);

	AlunoDTO create(CreateAlunoDTO createAlunoDTO);

	AlunoDTO update(Integer id, CreateAlunoDTO createAlunoDTO);

	void delete(Integer id);
}
