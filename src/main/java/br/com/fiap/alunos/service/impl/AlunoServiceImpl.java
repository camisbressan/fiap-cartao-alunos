package br.com.fiap.alunos.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.dto.CreateAlunoDTO;
import br.com.fiap.alunos.entity.Aluno;
import br.com.fiap.alunos.repository.AlunoRepository;
import br.com.fiap.alunos.service.AlunoService;

@Service
public class AlunoServiceImpl implements AlunoService {

	private AlunoRepository alunoRepository;

	public AlunoServiceImpl(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@Override
	public List<AlunoDTO> findAll() {
		List<Aluno> alunosList = alunoRepository.findAll();
		return alunosList.stream().map(AlunoDTO::new).collect(Collectors.toList());
	}

    @Override
    public AlunoDTO findById(Integer id) {
        return saveAndGetAlunoDTO(getAluno(id));
    }

    private Aluno getAluno(Integer id) {
        return alunoRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public AlunoDTO create(CreateAlunoDTO createAlunoDTO) {
        Aluno aluno = new Aluno(createAlunoDTO);
        aluno.setDataCriacao(new Date());
        aluno.setDataModificacao(new Date());
        return saveAndGetAlunoDTO(aluno);
    }

    @Override
    public AlunoDTO update(Integer id, CreateAlunoDTO createAlunoDTO) {
        Aluno aluno =  getAluno(id);
        aluno.setNome(createAlunoDTO.getNome());
        aluno.setMatricula(createAlunoDTO.getMatricula());
        aluno.setDataModificacao(new Date());
        return saveAndGetAlunoDTO(aluno);
    }


    private AlunoDTO saveAndGetAlunoDTO(Aluno aluno) {
        Aluno savedAluno = alunoRepository.save(aluno);
        return new AlunoDTO(savedAluno);
    }

    @Override
    public void delete(Integer id) {
        Aluno aluno = getAluno(id);
        alunoRepository.delete(aluno);
    }

}
