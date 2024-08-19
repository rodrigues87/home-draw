package agilebit.homedraw.controllers;

import agilebit.homedraw.constants.CriterioTypeEnum;
import agilebit.homedraw.dtos.criterio.CriterioRequestDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponseDTO;
import agilebit.homedraw.dtos.criterio.CriterioResponsePageDTO;
import agilebit.homedraw.services.CriterioService;
import agilebit.homedraw.services.FamiliaCriterioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CriterioControllerTest {
	
	@Mock
	private CriterioService criterioService;
	
	@Mock
	private FamiliaCriterioService familiaCriterioService;
	
	@InjectMocks
	private CriterioController criterioController;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createReturnsCriterioResponseDTO() {
		CriterioRequestDTO requestDTO = createCriteiroRequestDTO();
		
		CriterioResponseDTO responseDTO = createCriterioResponseDTO();
		
		when(criterioService.save(requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<CriterioResponseDTO> response = criterioController.create(requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	
	@Test
	void findByIdReturnsCriterioResponseDTO() {
		Long id = 1L;
		CriterioResponseDTO responseDTO = createCriterioResponseDTO();
		when(criterioService.findById(id)).thenReturn(responseDTO);
		
		ResponseEntity<CriterioResponseDTO> response = criterioController.findById(id);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void findAllReturnsCriterioResponsePageDTO() {
		Pageable pageable = PageRequest.of(0, 10);
		CriterioResponsePageDTO responsePageDTO = createCriterioResponsePageDTO();
		when(criterioService.findAll(pageable)).thenReturn(responsePageDTO);
		
		ResponseEntity<CriterioResponsePageDTO> response = criterioController.findAll(pageable);
		
		assertEquals(ResponseEntity.ok(responsePageDTO), response);
	}
	
	@Test
	void updateReturnsUpdatedCriterioResponseDTO() {
		Long id = 1L;
		CriterioRequestDTO requestDTO = createCriteiroRequestDTO();
		CriterioResponseDTO responseDTO = createCriterioResponseDTO();
		when(criterioService.update(id, requestDTO)).thenReturn(responseDTO);
		
		ResponseEntity<CriterioResponseDTO> response = criterioController.update(id, requestDTO);
		
		assertEquals(ResponseEntity.ok(responseDTO), response);
	}
	
	@Test
	void deleteReturnsNoContent() {
		Long id = 1L;
		
		ResponseEntity<Void> response = criterioController.delete(id);
		
		assertEquals(ResponseEntity.noContent().build(), response);
	}
	
	@Test
	void sincronizarCriteriosReturnsOk() {
		ResponseEntity<Void> response = criterioController.sincronizarCriterios();
		
		assertEquals(ResponseEntity.ok().build(), response);
	}
	
	private CriterioResponsePageDTO createCriterioResponsePageDTO() {
		return CriterioResponsePageDTO.builder().content(Collections.singletonList(createCriterioResponseDTO())).build();
	}
	
	private CriterioRequestDTO createCriteiroRequestDTO() {
		return CriterioRequestDTO.builder().descricao("descricao").pontuacao(1).limiteInferior(BigDecimal.ONE).limiteSuperior(BigDecimal.TEN).tipo(CriterioTypeEnum.DEPENDENTES).build();
	}
	
	private CriterioResponseDTO createCriterioResponseDTO() {
		return CriterioResponseDTO.builder().id(1L).descricao("descricao").limiteInferior(BigDecimal.ONE).limiteSuperior(BigDecimal.TEN).tipo(CriterioTypeEnum.DEPENDENTES).build();
	}
}