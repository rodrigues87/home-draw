package agilebit.homedraw.controllers;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.services.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	private final PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<PessoaResponseDTO> create(@RequestBody @Valid PessoaRequestDTO pessoa) {
		PessoaResponseDTO pessoaResponseDTO = pessoaService.save(pessoa);
		return ResponseEntity.ok(pessoaResponseDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaResponseDTO> findById(@PathVariable Long id) {
		PessoaResponseDTO pessoaResponseDTO = pessoaService.findById(id);
		return ResponseEntity.ok(pessoaResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<PessoaResponsePageDTO> findAll(Pageable pageable) {
		PessoaResponsePageDTO pessoas = pessoaService.findAll(pageable);
		return ResponseEntity.ok(pessoas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaResponseDTO> update(@PathVariable Long id,
	                                                @RequestBody @Valid PessoaRequestDTO pessoa) {
		
		PessoaResponseDTO updatedPessoa = pessoaService.update(id, pessoa);
		return ResponseEntity.ok(updatedPessoa);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
		pessoaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
