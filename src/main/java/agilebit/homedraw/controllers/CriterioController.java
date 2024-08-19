package agilebit.homedraw.controllers;

import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.services.CriterioService;
import agilebit.homedraw.services.FamiliaCriterioService;
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
@RequestMapping("/criterios")
public class CriterioController {
	
	private final CriterioService criterioService;
	private final FamiliaCriterioService familiaCriterioService;
	
	@PostMapping
	public ResponseEntity<CriterioResponseDTO> create(@RequestBody CriterioRequestDTO criterio) {
		CriterioResponseDTO criterioResponseDTO = criterioService.save(criterio);
		return ResponseEntity.ok(criterioResponseDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CriterioResponseDTO> findById(@PathVariable Long id) {
		CriterioResponseDTO criterioResponseDTO = criterioService.findById(id);
		return ResponseEntity.ok(criterioResponseDTO);
	}
	
	@GetMapping
	public ResponseEntity<CriterioResponsePageDTO> findAll(Pageable pageable) {
		CriterioResponsePageDTO criterios = criterioService.findAll(pageable);
		return ResponseEntity.ok(criterios);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CriterioResponseDTO> update(@PathVariable Long id,
	                                                  @RequestBody CriterioRequestDTO criterio) {
		
		CriterioResponseDTO updatedCriterio = criterioService.update(id, criterio);
		return ResponseEntity.ok(updatedCriterio);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		criterioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/sincronizar")
	public ResponseEntity<Void> sincronizarCriterios() {
		familiaCriterioService.sincronizarCriterios();
		return ResponseEntity.ok().build();
	}
}
