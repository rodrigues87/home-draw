package agilebit.homedraw.controllers;

import agilebit.homedraw.dtos.pessoa.PessoaRequestDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponseDTO;
import agilebit.homedraw.dtos.pessoa.PessoaResponsePageDTO;
import agilebit.homedraw.services.PessoaService;
import agilebit.homedraw.util.PessoaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PessoaControllerTest {
	
	@Mock
	private PessoaService pessoaService;
	
	@InjectMocks
	private PessoaController pessoaController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createReturnsPessoaResponseDTO() {
		PessoaRequestDTO requestDTO = PessoaBuilder.createPessoaRequestDTO();
		PessoaResponseDTO responseDTO = PessoaBuilder.createPessoaResponseDTO();
		when(pessoaService.save(requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<PessoaResponseDTO> response = pessoaController.create(requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void findByIdReturnsPessoaResponseDTO() {
		Long id = 1L;
		PessoaResponseDTO responseDTO = PessoaBuilder.createPessoaResponseDTO();
		when(pessoaService.findById(id)).thenReturn(responseDTO);
		
		ResponseEntity<PessoaResponseDTO> response = pessoaController.findById(id);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void findAllReturnsPessoaResponsePageDTO() {
		Pageable pageable = PageRequest.of(0, 10);
		PessoaResponsePageDTO responsePageDTO = PessoaBuilder.createPessoaResponsePageDTO();
		when(pessoaService.findAll(pageable)).thenReturn(responsePageDTO);
		
		ResponseEntity<PessoaResponsePageDTO> response = pessoaController.findAll(pageable);
		
		assertEquals(ResponseEntity.ok(responsePageDTO), response);
	}
	
	@Test
	void updateReturnsUpdatedPessoaResponseDTO() {
		Long id = 1L;
		PessoaRequestDTO requestDTO = PessoaBuilder.createPessoaRequestDTO();
		PessoaResponseDTO responseDTO = PessoaBuilder.createPessoaResponseDTO();
		when(pessoaService.update(id, requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<PessoaResponseDTO> response = pessoaController.update(id, requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void deletePessoaReturnsNoContent() {
		Long id = 1L;
		
		ResponseEntity<Void> response = pessoaController.deletePessoa(id);
		
		assertEquals(ResponseEntity.noContent().build(), response);
	}
}