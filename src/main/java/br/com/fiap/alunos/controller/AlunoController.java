package br.com.fiap.alunos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.alunos.dto.AlunoDTO;
import br.com.fiap.alunos.dto.CreateAlunoDTO;
import br.com.fiap.alunos.service.AlunoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Alunos")
@RequestMapping("alunos")
public class AlunoController {

	private final AlunoService service;

	public AlunoController(AlunoService service) {
		this.service = service;
	}

	@ApiOperation(value = "Lista todos os alunos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alunos listados com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtém um aluno")
	@ApiResponses({ @ApiResponse(code = 200, message = "Aluno encontrado"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@GetMapping("{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

	@ApiOperation(value = "Cria um aluno")
	@ApiResponses({ @ApiResponse(code = 201, message = "Aluno criado com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AlunoDTO> create(@RequestBody @Valid CreateAlunoDTO createAlunoDTO) {
		return ResponseEntity.ok(service.create(createAlunoDTO));
	}

	@ApiOperation(value = "Atualiza um aluno")
	@ApiResponses({ @ApiResponse(code = 200, message = "Aluno atualizado com sucesso"),
			@ApiResponse(code = 201, message = "Aluno criado com sucesso"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@PutMapping("{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Integer id, @RequestBody CreateAlunoDTO createAlunoDTO){
        return ResponseEntity.ok(service.update(id, createAlunoDTO));
    }


	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui um aluno")
	@ApiResponses({ @ApiResponse(code = 204, message = "Exclusão com sucesso de um aluno"),
			@ApiResponse(code = 401, message = "Você não tem autorização"),
			@ApiResponse(code = 403, message = "É proibido acessar esse recurso"),
			@ApiResponse(code = 404, message = "O recurso não foi encontrado") })
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
