package agilebit.homedraw.controllers;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import agilebit.homedraw.services.FamiliaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/familias")
public class FamiliaController {
	private final FamiliaService familiaService;
	
	@PostMapping
	public ResponseEntity<FamiliaResponseDTO> create(@RequestBody @Valid FamiliaRequestDTO familia) {
		return ResponseEntity.ok(familiaService.save(familia));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FamiliaResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(familiaService.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<FamiliaResponsePageDTO> findAll(@PageableDefault(
			sort = "pontuacao", direction = Sort.Direction.DESC) Pageable pageable) {
		return ResponseEntity.ok(familiaService.findAll(pageable));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FamiliaResponseDTO> update(@PathVariable Long id,
	                                                 @RequestBody @Valid FamiliaRequestDTO familia) {
		return ResponseEntity.ok(familiaService.update(id, familia));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFamilia(@PathVariable Long id) {
		familiaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
