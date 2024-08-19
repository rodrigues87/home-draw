package agilebit.homedraw.controllers;

import agilebit.homedraw.dtos.familia.FamiliaRequestDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponseDTO;
import agilebit.homedraw.dtos.familia.FamiliaResponsePageDTO;
import agilebit.homedraw.services.FamiliaService;
import agilebit.homedraw.util.FamiliaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class FamiliaControllerTest {
	
	@Mock
	private FamiliaService familiaService;
	
	@InjectMocks
	private FamiliaController familiaController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createReturnsFamiliaResponseDTO() {
		FamiliaRequestDTO requestDTO = FamiliaBuilder.createFamiliaRequestDTO();
		FamiliaResponseDTO responseDTO = FamiliaBuilder.createFamiliaResponseDTO();
		when(familiaService.save(requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<FamiliaResponseDTO> response = familiaController.create(requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void findByIdReturnsFamiliaResponseDTO() {
		Long id = 1L;
		FamiliaResponseDTO responseDTO = FamiliaBuilder.createFamiliaResponseDTO();
		when(familiaService.findById(id)).thenReturn(responseDTO);
		
		ResponseEntity<FamiliaResponseDTO> response = familiaController.findById(id);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void findAllReturnsFamiliaResponsePageDTO() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "pontuacao"));
		FamiliaResponsePageDTO responsePageDTO = FamiliaBuilder.createFamiliaResponsePageDTO();
		when(familiaService.findAll(pageable)).thenReturn(responsePageDTO);
		
		ResponseEntity<FamiliaResponsePageDTO> response = familiaController.findAll(pageable);
		
		assertEquals(ResponseEntity.ok(responsePageDTO), response);
	}
	

	
	@Test
	void updateReturnsUpdatedFamiliaResponseDTO() {
		Long id = 1L;
		FamiliaRequestDTO requestDTO = new FamiliaRequestDTO();
		FamiliaResponseDTO responseDTO = FamiliaBuilder.createFamiliaResponseDTO();
		when(familiaService.update(id, requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<FamiliaResponseDTO> response = familiaController.update(id, requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void deleteFamiliaReturnsNoContent() {
		Long id = 1L;
		
		ResponseEntity<Void> response = familiaController.deleteFamilia(id);
		
		assertEquals(ResponseEntity.noContent().build(), response);
	}
}